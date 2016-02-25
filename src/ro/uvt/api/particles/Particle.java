
package ro.uvt.api.particles;

import javax.media.opengl.GL2;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_TRIANGLE_STRIP;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;

public class Particle {

  private GL2 gl;
  private Vertex leftBottom;
  private Vertex rightBottom;
  private Vertex rightTop;
  private Vertex leftTop;
  private Texture texture;
  private float fadeUnit;
  private Material material;
  private Vertex gravityVector = new Vertex(0.0f, 0.0f, 0.0f);

  private float particleRadius;
  private Vertex particlePosition;
  private Vertex speed;
  private float lifespan;

  public Particle(GL2 gl, Vertex position, Vertex speed, float cameraAngle, Texture texture, float radius, float fade, Material material) {
    this.particlePosition = position;
    this.particleRadius = radius;

    this.speed = speed;

    this.material = material;

    fadeUnit = fade;

    lifespan = 1.0f;

    this.gl = gl;

    this.texture = texture;
  }

  public void move() {
    speed.add(gravityVector);
    particlePosition.add(speed);
    lifespan -= fadeUnit;
    // I don't want particles to grow right now
    // particleRadius += 0.001f;
  }

  public void draw(float cameraAngle) {
    computeCornerCoordinates(particlePosition, particleRadius, cameraAngle);

    material.decreaseAlpha(fadeUnit);

    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, material.getDiffuse(), 0);
    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, material.getSpecular(), 0);
    gl.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, material.getAmbient(), 0);

    drawCorners();
  }

  public boolean died() {
    if (lifespan <= 0.0f) {
      return true;
    } else {
      return false;
    }
  }

  private void computeCornerCoordinates(Vertex center, float particleSize, float cameraAngle) {
    leftBottom = new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());
    rightBottom = new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());
    rightTop = new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());
    leftTop = new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());

    float rightSideZ = -particleSize * (float) Math.sin(cameraAngle);
    float rightSideX = particleSize * (float) Math.cos(cameraAngle);
    rightBottom.add(new Vertex(rightSideX, -particleSize, rightSideZ));
    rightTop.add(new Vertex(rightSideX, particleSize, rightSideZ));

    float leftSideZ = -particleSize * (float) Math.sin(cameraAngle + Math.PI);
    float leftSideX = particleSize * (float) Math.cos(cameraAngle + Math.PI);
    leftTop.add(new Vertex(leftSideX, particleSize, leftSideZ));
    leftBottom.add(new Vertex(leftSideX, -particleSize, leftSideZ));
  }

  private void drawCorners() {
    TextureCoords textureCoords = texture.getImageTexCoords();

    float leftTex = textureCoords.left();
    float rightTex = textureCoords.right();
    float topTex = textureCoords.top();
    float bottomTex = textureCoords.bottom();

    gl.glPushMatrix();
    gl.glBegin(GL_TRIANGLE_STRIP);
    gl.glTexCoord2d(rightTex, bottomTex);
    gl.glVertex3f(rightBottom.getPositionX(), rightBottom.getPositionY(), rightBottom.getPositionZ());

    gl.glTexCoord2d(rightTex, topTex);
    gl.glVertex3f(rightTop.getPositionX(), rightTop.getPositionY(), rightTop.getPositionZ());

    gl.glTexCoord2d(leftTex, bottomTex);
    gl.glVertex3f(leftBottom.getPositionX(), leftBottom.getPositionY(), leftBottom.getPositionZ());

    gl.glTexCoord2d(leftTex, topTex);
    gl.glVertex3f(leftTop.getPositionX(), leftTop.getPositionY(), leftTop.getPositionZ());
    gl.glEnd();
    gl.glPopMatrix();
  }

  // getters & setters
  public Vertex getGravityVector() {
    return gravityVector;
  }

  public void setGravityVector(Vertex gravityVector) {
    this.gravityVector = gravityVector;
  }
}
