
package ro.space.display.particles;

import javax.media.opengl.GL2;

import ro.space.read.textures.TextureReader;
import ro.space.util.algebra.Calculator;
import ro.space.util.constants.Numbers;

import com.jogamp.opengl.util.texture.Texture;

public class SprayedFireSystem extends ParticleSystem {

  private TextureReader reader;

  // private Random generator = new Random();

  private Trio source;
  private Trio destination;
  private float radius;

  public SprayedFireSystem(GL2 gl, Trio eye, double cameraAngle, Trio source, Trio destination, float radius) {
    super(eye, cameraAngle);

    this.gl = gl;
    reader = new TextureReader(this.gl, "res/");

    this.source = source;
    this.destination = destination;
    this.radius = radius;
  }

  protected void spawnParticles() {

    Texture texture = reader.readTexture("particle.png", ".png");

    for (int i = 0; i < Numbers.NUMBER_OF_PARTICLES.getValue(); ++i) {

      Trio loc = source.clone();
      Trio speed = new Trio(0.0f, 0.0f, 0.0f);

      Trio acceleration = generateParticleDirectionVector();
      // new Trio(1.0f / generator.nextInt(1000), 1.0f / generator.nextInt(1000), 1.0f / generator.nextInt(1000));

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

    return smallerDirectionVector ;
  }
}