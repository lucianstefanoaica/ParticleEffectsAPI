
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class ReversedConeFireSystem extends ParticleSystem {

  private Trio startPosition = null;
  private Trio startSpeed = null;
  private Trio acceleration = null;

  private Trio backupPosition = null;

  public ReversedConeFireSystem(GL2 gl, Trio[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    backupPosition = new Trio(source.getX(), source.getY(), source.getZ());
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {

      startSpeed = new Trio(0.0f, 0.0f, 0.0f);

      generateParticleDirectionVector();

      Particle particle = new Particle(gl, startPosition, startSpeed, acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit);

      particles.add(particle);
    }
  }

  private void generateParticleDirectionVector() {
    startPosition = generatePointInSphere(source, backupPosition);

    Trio directionVector = Calculator.subtract(destination, startPosition);

    acceleration = Calculator.makeItSmaller(directionVector, directionVectorScalar);
  }
}
