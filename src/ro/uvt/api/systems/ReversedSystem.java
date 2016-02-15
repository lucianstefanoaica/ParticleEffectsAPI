
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.particles.Particle;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.MaterialProperties;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class ReversedSystem extends ParticleSystem {

  private Vertex startPosition = null;
  private Vertex startSpeed = null;
  private Vertex acceleration = null;

  private Vertex backupPosition = null;

  public ReversedSystem(GL2 gl, Vertex[] positions, Texture texture, MaterialProperties material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    backupPosition = new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {

      startSpeed = new Vertex(0.0f, 0.0f, 0.0f);

      generateParticleDirectionVector();

      Particle particle = new Particle(gl, startPosition, startSpeed, acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit, material.clone());

      particles.add(particle);
    }
  }

  private void generateParticleDirectionVector() {
    startPosition = generatePointInSphere(source, backupPosition);

    Vertex differenceVector = Calculator.subtract(destination, startPosition);

    acceleration = Calculator.scaleDown(differenceVector, scalar);
  }
}
