
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class FountainSystem extends ParticleSystem {

  private Trio backupPosition = null;

  public FountainSystem(GL2 gl, Trio[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    backupPosition = new Trio(destination.getX(), destination.getY(), destination.getZ());
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {

      Trio loc = source.clone();
      Trio speed = new Trio(0.0f, 0.0f, 0.0f);

      Trio acceleration = generateParticleDirectionVector();

      Particle particle = new FountainParticle(gl, loc, speed, acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit);

      particles.add(particle);
    }
  }

  private Trio generateParticleDirectionVector() {
    Trio pointInSphere = generatePointInSphere(destination, backupPosition);

    Trio directionVector = Calculator.subtract(pointInSphere, source);

    Trio smallerDirectionVector = Calculator.makeItSmaller(directionVector, directionVectorScalar);

    return smallerDirectionVector;
  }
}
