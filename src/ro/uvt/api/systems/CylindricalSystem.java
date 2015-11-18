
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.particles.Particle;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.MaterialProperties;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalSystem extends ParticleSystem {

  private Vertex startPosition = null;
  private Vertex startSpeed = null;
  private Vertex acceleration = null;

  private Vertex firstBackup = null;
  private Vertex secondBackup = null;

  public CylindricalSystem(GL2 gl, Vertex[] positions, Texture texture, MaterialProperties material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    firstBackup = new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
    secondBackup = new Vertex(destination.getPositionX(), destination.getPositionY(), destination.getPositionZ());
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {

      startSpeed = new Vertex(0.0f, 0.0f, 0.0f);

      generateParticleDirectionVector();

      Particle particle = new Particle(gl, startPosition, startSpeed, acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit);

      particles.add(particle);
    }
  }

  private void generateParticleDirectionVector() {
    Vertex pointInFirstSphere = generatePointInSphere(source, firstBackup);
    Vertex pointInSecondSphere = generatePointInSphere(destination, secondBackup);

    Vertex directionVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    acceleration = Calculator.makeItSmaller(directionVector, directionVectorScalar);
  }
}
