
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class SprayedFireSystem extends ParticleSystem {

  private Trio source;
  private Trio destination;
  private float radius;

  private int pps;

  private Texture texture;

  public SprayedFireSystem(GL2 gl, Trio eye, double cameraAngle, Trio source, Trio destination, float radius, int pps, Texture texture) {
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

      Trio loc = source.clone();
      Trio speed = new Trio(0.0f, 0.0f, 0.0f);

      Trio acceleration = generateParticleDirectionVector();
      // new Trio(1.0f / generator.nextInt(1000), 1.0f / generator.nextInt(1000), 1.0f / generator.nextInt(1000));

      texture.bind(gl);

      Particle particle = new Particle(gl, loc, speed, acceleration, cameraPosition, cameraAngle, texture);

      particles.add(particle);
    }
  }

  private Trio generateParticleDirectionVector() {
    double xVal = Calculator.getRandomNumberInRange(-radius, radius);
    double yVal = Calculator.getRandomNumberInRange(-radius, radius);
    double zVal = Calculator.getRandomNumberInRange(-radius, radius);

    Trio pointInSphere = Calculator.add(destination, new Trio(xVal, yVal, zVal));

    Trio directionVector = Calculator.subtract(pointInSphere, source);

    Trio smallerDirectionVector = Calculator.makeItSmaller(directionVector, 400f);
    // Calculator.normalize(directionVector);

    return smallerDirectionVector;
  }
}