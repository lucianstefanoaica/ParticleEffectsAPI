
package ro.uvt.api.particles;

import static javax.media.opengl.GL.GL_FRONT_AND_BACK;
import static javax.media.opengl.GL.GL_TRIANGLE_STRIP;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public class Particle implements Comparable<Particle> {
  // test
  private GL2 gl;
  private Vertex leftBottom;
  private Vertex rightBottom;
  private Vertex rightTop;
  private Vertex leftTop;
  private Vertex cameraPosition;
  private double cameraAngle = 0.0f;
  private double cameraDistance;
  private Texture texture;
  private float fadeUnit;

  protected float particleRadius;
  protected Vertex particlePosition;
  protected Vertex speed;
  protected Vertex acceleration;
  protected float lifespan;

  private Material material;

  private Vertex gravityVector = new Vertex(0.0f, 0.0f, 0.0f);

  public Particle(GL2 gl, Vertex position, Vertex speed, Vertex acceleration, Vertex cameraPosition, double cameraAngle, Texture texture, float radius,
                  float fade, Material material) {
    this.particlePosition = position;
    this.particleRadius = radius;
    computeCornerCoordinates(this.particlePosition, this.particleRadius);

    this.acceleration = acceleration;
    this.speed = speed;

    this.cameraPosition = cameraPosition;
    this.cameraAngle = cameraAngle;

    this.material = material;

    fadeUnit = fade;

    lifespan = 1.0f;

    this.gl = gl;

    this.texture = texture;

    computeCameraDistance();
  }

  public void move() {
    speed.add(acceleration);
    particlePosition.add(acceleration);
    lifespan -= fadeUnit;
    // I don't want particles to grow right now
    // particleRadius += 0.001f;

    acceleration.add(gravityVector);
  }

  public void draw() {
    computeCornerCoordinates(particlePosition, particleRadius);
    computeCameraDistance();

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

  @Override
  public int compareTo(Particle that) {
    int result = 0;

    if (this.cameraDistance < that.cameraDistance) {
      result = -1;
    } else if (this.cameraDistance > that.cameraDistance) {
      result = 1;
    }
    return result;
  }

  private void computeCornerCoordinates(Vertex center, float particleSize) {
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

  private void computeCameraDistance() {
    //    Don`t know if I will ever need this code, but i will keep it commented here just in case
    //    Vertex planeCameraVector = Calculator.subtract(cameraPosition, particlePosition);
    //
    //    Vertex pb = Calculator.subtract(rightTop, particlePosition);
    //    Vertex pa = Calculator.subtract(leftTop, particlePosition);
    //    Vertex planeNormal = Calculator.cross(pb, pa);
    //    cameraDistance = Calculator.computePointPlaneDistance(planeCameraVector, planeNormal);

    cameraDistance = Calculator.computeDistance(cameraPosition, particlePosition);
  }

  // getters & setters
  public Vertex getCameraPosition() {
    return cameraPosition;
  }

  public void setCameraPosition(Vertex cameraPosition) {
    this.cameraPosition = cameraPosition;
  }

  public double getCameraAngle() {
    return cameraAngle;
  }

  public void setCameraAngle(double cameraAngle) {
    this.cameraAngle = cameraAngle;
  }

  public Vertex getGravityVector() {
    return gravityVector;
  }

  public void setGravityVector(Vertex gravityVector) {
    this.gravityVector = gravityVector;
  }
}
