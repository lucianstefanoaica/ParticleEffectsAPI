
package ro.uvt.api.particles;

import static javax.media.opengl.GL.GL_TRIANGLE_STRIP;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public class Particle implements Comparable<Particle> {

  private GL2 gl;

  private Trio particlePosition;

  private Trio leftBottom;
  private Trio rightBottom;
  private Trio rightTop;
  private Trio leftTop;

  private Trio cameraPosition;

  private double cameraAngle = 0.0f;

  private Trio speed;
  protected Trio acceleration;

  private float lifespan;

  private float particleRadius;

  private double cameraDistance;

  private Texture texture;

  private float fadeUnit;

  public Particle(GL2 gl, Trio position, Trio speed, Trio acceleration, Trio cameraPosition, double cameraAngle, Texture texture, float radius, float fade) {
    this.particlePosition = position;
    this.particleRadius = radius;
    computeCornerCoordinates(this.particlePosition, this.particleRadius);

    this.speed = speed;
    this.acceleration = acceleration;

    this.cameraPosition = cameraPosition;
    this.cameraAngle = cameraAngle;

    fadeUnit = fade;

    lifespan = 1.0f;

    this.gl = gl;

    this.texture = texture;

    computeCameraDistance();
  }

  public void move() {
    speed.add(acceleration);
    particlePosition.add(speed);
    lifespan -= fadeUnit;
  }

  public void draw() {
    computeCornerCoordinates(particlePosition, particleRadius);
    computeCameraDistance();

    gl.glColor4f(0.0f, 0.0f, 0.0f, lifespan);
    drawCorners();
  }

  public boolean died() {
    if (lifespan < 0) {
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

  private void computeCornerCoordinates(Trio position, float particleSize) {
    leftBottom = new Trio(position.getX(), position.getY(), position.getZ());
    rightBottom = new Trio(position.getX(), position.getY(), position.getZ());
    rightTop = new Trio(position.getX(), position.getY(), position.getZ());
    leftTop = new Trio(position.getX(), position.getY(), position.getZ());

    float sinSize = particleSize * (float) Math.sin(cameraAngle);
    float cosSize = particleSize * (float) Math.cos(cameraAngle);

    Trio positiveSin = new Trio(0.0f, 0.0f, sinSize);
    rightBottom.add(positiveSin);
    rightTop.add(positiveSin);

    Trio negativeSin = new Trio(0.0f, 0.0f, -1 * sinSize);
    leftBottom.add(negativeSin);
    leftTop.add(negativeSin);

    Trio positiveCos = new Trio(cosSize, 0.0f, 0.0f);
    rightBottom.add(positiveCos);
    rightTop.add(positiveCos);

    Trio negativeCos = new Trio(-1 * cosSize, 0.0f, 0.0f);
    leftBottom.add(negativeCos);
    leftTop.add(negativeCos);

    Trio negativeSize = new Trio(0.0f, -1 * particleSize, 0.0f);
    leftBottom.add(negativeSize);
    rightBottom.add(negativeSize);

    Trio positiveSize = new Trio(0.0f, particleSize, 0.0f);
    leftTop.add(positiveSize);
    rightTop.add(positiveSize);
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
    gl.glVertex3f(rightBottom.getX(), rightBottom.getY(), rightBottom.getZ());

    gl.glTexCoord2d(rightTex, topTex);
    gl.glVertex3f(rightTop.getX(), rightTop.getY(), rightTop.getZ());

    gl.glTexCoord2d(leftTex, bottomTex);
    gl.glVertex3f(leftBottom.getX(), leftBottom.getY(), leftBottom.getZ());

    gl.glTexCoord2d(leftTex, topTex);
    gl.glVertex3f(leftTop.getX(), leftTop.getY(), leftTop.getZ());

    gl.glEnd();

    gl.glPopMatrix();
  }

  private void computeCameraDistance() {
    //    Don`t know if I will ever need this code, but i will keep it commented here just in case
    //    Trio planeCameraVector = Calculator.subtract(cameraPosition, particlePosition);
    //
    //    Trio pb = Calculator.subtract(rightTop, particlePosition);
    //    Trio pa = Calculator.subtract(leftTop, particlePosition);
    //    Trio planeNormal = Calculator.cross(pb, pa);
    //    cameraDistance = Calculator.computePointPlaneDistance(planeCameraVector, planeNormal);

    cameraDistance = Calculator.computeDistance(cameraPosition, particlePosition);
  }

  public void setCameraPosition(Trio cameraPosition) {
    this.cameraPosition = cameraPosition;
  }

  public void setCameraAngle(double cameraAngle) {
    this.cameraAngle = cameraAngle;
  }
}