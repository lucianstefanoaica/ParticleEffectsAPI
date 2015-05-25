
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class SprayedFireSystem extends ParticleSystem {

  public SprayedFireSystem(GL2 gl, Trio source, Trio destination, Trio cameraPosition, Texture texture, Material material) {
    super(gl, source, destination, cameraPosition, texture, material);
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {

      Trio loc = source.clone();
      Trio speed = new Trio(0.0f, 0.0f, 0.0f);

      Trio acceleration = generateParticleDirectionVector();

      Particle particle = new Particle(gl, loc, speed, acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit);

      particles.add(particle);
    }
  }

  private Trio generateParticleDirectionVector() {
    double xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
    double yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
    double zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

    Trio pointInSphere = Calculator.add(destination, new Trio(xVal, yVal, zVal));

    Trio directionVector = Calculator.subtract(pointInSphere, source);

    Trio smallerDirectionVector = Calculator.makeItSmaller(directionVector, 400f);

    return smallerDirectionVector;
  }
}