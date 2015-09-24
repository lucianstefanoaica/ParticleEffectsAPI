
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class SprayedFireSystem extends ParticleSystem {

  public SprayedFireSystem(GL2 gl, Trio source, Trio destination, float systemRadius, Trio cameraPosition, Texture texture, Material material) {
    super(gl, source, destination, cameraPosition, texture, material);
    this.systemRadius = systemRadius;
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
    Trio pointInSphere = generatePointInSphere(destination);

    Trio directionVector = Calculator.subtract(pointInSphere, source);

    Trio smallerDirectionVector = Calculator.makeItSmaller(directionVector, 400f);

    return smallerDirectionVector;
  }
}