package ro.uvt.pel.particle_systems;

import static javax.media.opengl.GL.GL_TRIANGLE_STRIP;

import java.util.HashMap;
import java.util.Map;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

class Particle {

  private Vertex position;
  private Vertex speed;
  private Vertex weight;
  private float lifespan;
  private float size;
  private float fadeQuotient;
  private Texture texture;
  private Material material;

  Particle(Vertex position, Vertex speed, Vertex weight, float size, float fadeQuotient,
      Texture texture, Material material) {
    this.position = position;
    this.speed = speed;
    this.size = size;
    this.material = material;
    this.texture = texture;
    this.fadeQuotient = fadeQuotient;
    this.weight = weight;
    lifespan = 1.0f;
  }

  void move() {
    speed = Calculator.add(speed, weight);
    position = Calculator.add(position, speed);
    lifespan -= fadeQuotient;
    material.decreaseAlpha(fadeQuotient);
  }

  void appearOnGLAutoDrawable(GLAutoDrawable drawable, float cameraAngle) {
    GL2 gl = drawable.getGL().getGL2();
    Map<String, Vertex> map = computeCornerCoordinates(position, size, cameraAngle);
    material.bind(gl);
    drawCorners(gl, map);
  }

  boolean isDead() {
    if (lifespan <= 0.0f) {
      return true;
    } else {
      return false;
    }
  }

  private Map<String, Vertex> computeCornerCoordinates(Vertex center, float particleSize,
      float cameraAngle) {
    Vertex leftBottom =
        new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());
    Vertex rightBottom =
        new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());
    Vertex rightTop =
        new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());
    Vertex leftTop =
        new Vertex(center.getPositionX(), center.getPositionY(), center.getPositionZ());

    float rightSideZ = -particleSize * (float) Math.sin(cameraAngle);
    float rightSideX = particleSize * (float) Math.cos(cameraAngle);
    rightBottom = Calculator.add(rightBottom, new Vertex(rightSideX, -particleSize, rightSideZ));
    rightTop = Calculator.add(rightTop, new Vertex(rightSideX, particleSize, rightSideZ));

    float leftSideZ = -particleSize * (float) Math.sin(cameraAngle + Math.PI);
    float leftSideX = particleSize * (float) Math.cos(cameraAngle + Math.PI);
    leftTop = Calculator.add(leftTop, new Vertex(leftSideX, particleSize, leftSideZ));
    leftBottom = Calculator.add(leftBottom, new Vertex(leftSideX, -particleSize, leftSideZ));

    Map<String, Vertex> map = new HashMap<>();
    map.put("leftBottom", leftBottom);
    map.put("rightBottom", rightBottom);
    map.put("leftTop", leftTop);
    map.put("rightTop", rightTop);
    return map;
  }

  private void drawCorners(GL2 gl, Map<String, Vertex> map) {
    Vertex rightBottom = map.get("rightBottom");
    Vertex leftBottom = map.get("leftBottom");
    Vertex rightTop = map.get("rightTop");
    Vertex leftTop = map.get("leftTop");
    TextureCoords textureCoords = texture.getImageTexCoords();

    float leftTex = textureCoords.left();
    float rightTex = textureCoords.right();
    float topTex = textureCoords.top();
    float bottomTex = textureCoords.bottom();

    gl.glPushMatrix();
    gl.glBegin(GL_TRIANGLE_STRIP);
    gl.glTexCoord2d(rightTex, bottomTex);
    gl.glVertex3f(rightBottom.getPositionX(), rightBottom.getPositionY(),
        rightBottom.getPositionZ());

    gl.glTexCoord2d(rightTex, topTex);
    gl.glVertex3f(rightTop.getPositionX(), rightTop.getPositionY(), rightTop.getPositionZ());

    gl.glTexCoord2d(leftTex, bottomTex);
    gl.glVertex3f(leftBottom.getPositionX(), leftBottom.getPositionY(), leftBottom.getPositionZ());

    gl.glTexCoord2d(leftTex, topTex);
    gl.glVertex3f(leftTop.getPositionX(), leftTop.getPositionY(), leftTop.getPositionZ());
    gl.glEnd();
    gl.glPopMatrix();
  }
}
