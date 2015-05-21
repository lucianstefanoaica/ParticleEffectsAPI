
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalFireSystem extends ParticleSystem {

  private Trio source;
  private Trio destination;
  private float radius;

  private int pps;

  private Trio particleStartPosition = null;
  private Trio particleStartSpeed = null;
  private Trio particleAcceleration = null;

  private Texture texture;

  public CylindricalFireSystem(GL2 gl, Trio eye, double cameraAngle, Trio source, Trio destination, float radius, int pps, Texture texture) {
    super(eye, cameraAngle);

    this.gl = gl;

    this.source = source;
    this.destination = destination;
    this.radius = radius;

    this.pps = pps;

    this.texture = texture;
  }

  protected void spawnParticles() {
    for (int i = 0; i < pps; ++i) {

      particleStartSpeed = new Trio(0.0f, 0.0f, 0.0f);

      generateParticleDirectionVector();

      // new Trio(1.0f / generator.nextInt(1000), 1.0f / generator.nextInt(1000), 1.0f / generator.nextInt(1000));

      Particle particle = new Particle(gl, particleStartPosition, particleStartSpeed, particleAcceleration, cameraPosition, cameraAngle, texture);

      particles.add(particle);
    }
  }

  private void generateParticleDirectionVector() {
    double xVal = Calculator.getRandomNumberInRange(-radius, radius);
    double yVal = Calculator.getRandomNumberInRange(-radius, radius);
    double zVal = Calculator.getRandomNumberInRange(-radius, radius);

    Trio pointInFirstSphere = Calculator.add(source, new Trio(xVal, yVal, zVal));
    Trio pointInSecondSphere = Calculator.add(destination, new Trio(xVal, yVal, zVal));

    Trio directionVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    particleStartPosition = pointInFirstSphere;
    particleAcceleration = Calculator.makeItSmaller(directionVector, 450f);
  }
}