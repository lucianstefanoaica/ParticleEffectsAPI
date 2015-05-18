
package ro.space.display.particles;

import static javax.media.opengl.GL.GL_FRONT;
import static javax.media.opengl.GL.GL_TRIANGLE_STRIP;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;

import javax.media.opengl.GL2;

import ro.space.util.algebra.Calculator;

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

  private double cameraAngle;

  private Trio speed;
  private Trio acceleration;

  private float lifespan;

  private float particleRadius = 0.08f;

  private double cameraDistance;

  private Texture texture;

  private float fadeUnit;

  // private Random rand = new Random();

  public Particle(GL2 gl, Trio particlePosition, Trio speed, Trio acceleration, Trio cameraPosition, double cameraAngle, Texture texture) {
    this.particlePosition = particlePosition;

    computeCornerCoordinates(this.particlePosition, particleRadius);

    this.speed = speed;
    this.acceleration = acceleration;

    this.cameraPosition = cameraPosition;
    this.cameraAngle = cameraAngle;

    fadeUnit = 0.05f; 
    // rand.nextInt(100) / 1000.0f + 0.003f;

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

    enableMaterial();
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

  private void enableMaterial() {
    float[] ambient = {0.0f, 0.2f, 0.3f};
    float[] diffuse = {0.0f, 0.2f, 0.3f};
    float[] specular = {0.0f, 0.2f, 0.3f};
    float[] shine = {120.078431f};

    gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, diffuse, 0);
    gl.glMaterialfv(GL_FRONT, GL_SPECULAR, specular, 0);
    gl.glMaterialfv(GL_FRONT, GL_AMBIENT, ambient, 0);
    gl.glMaterialfv(GL_FRONT, GL_SHININESS, shine, 0);
  }

  private void computeCameraDistance() {
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

  public double getCameraDistance() {
    return cameraDistance;
  }
}