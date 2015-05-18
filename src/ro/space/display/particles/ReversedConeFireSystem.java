
package ro.space.display.particles;

import javax.media.opengl.GL2;

import ro.space.read.textures.TextureReader;
import ro.space.util.algebra.Calculator;
import ro.space.util.constants.Numbers;

import com.jogamp.opengl.util.texture.Texture;

public class ReversedConeFireSystem extends ParticleSystem {

  private TextureReader reader;

  private Trio source;
  private Trio destination;
  private float radius;

  public ReversedConeFireSystem(GL2 gl, Trio eye, double cameraAngle, Trio source, Trio destination, float radius) {
    super(eye, cameraAngle);

    this.gl = gl;
    reader = new TextureReader(this.gl, "res/");

    this.source = source;
    this.destination = destination;
    this.radius = radius;
  }

  private Trio particleStartPosition = null;
  private Trio particleStartSpeed = null;
  private Trio particleAcceleration = null;

  protected void spawnParticles() {
    Texture texture = reader.readTexture("particle.png", ".png");

    for (int i = 0; i < Numbers.NUMBER_OF_PARTICLES.getValue(); ++i) {

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
    particleStartPosition = pointInFirstSphere;
    
    Trio directionVector = Calculator.subtract(destination, pointInFirstSphere);
    
    particleAcceleration = Calculator.makeItSmaller(directionVector, 450f);
  }
}