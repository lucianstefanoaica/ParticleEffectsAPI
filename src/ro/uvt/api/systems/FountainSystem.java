
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.particles.FountainParticle;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.MaterialProperties;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class FountainSystem extends ParticleSystem {

  private Vertex backupPosition = null;

  private Vertex gravityVector = new Vertex(0.0f, -0.006f, 0.0f);

  public FountainSystem(GL2 gl, Vertex[] positions, Texture texture, MaterialProperties material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    backupPosition = new Vertex(destination.getPositionX(), destination.getPositionY(), destination.getPositionZ());
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {

      Vertex loc = source.clone();
      Vertex speed = new Vertex(0.0f, 0.0f, 0.0f);

      Vertex acceleration = generateParticleDirectionVector();

      FountainParticle particle = new FountainParticle(gl, loc, speed, acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit);

      particle.setGravityVector(gravityVector);

      particles.add(particle);
    }
  }

  private Vertex generateParticleDirectionVector() {
    Vertex pointInSphere = generatePointInSphere(destination, backupPosition);

    Vertex directionVector = Calculator.subtract(pointInSphere, source);

    Vertex smallerDirectionVector = Calculator.makeItSmaller(directionVector, directionVectorScalar);

    return smallerDirectionVector;
  }

  public Vertex getGravityVector() {
    return gravityVector;
  }

  public void setGravityVector(Vertex gravityVector) {
    this.gravityVector = gravityVector;
  }
}
