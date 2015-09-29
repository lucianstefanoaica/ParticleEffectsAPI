
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalFireSystem extends ParticleSystem {

  private Trio startPosition = null;
  private Trio startSpeed = null;
  private Trio acceleration = null;

  private Trio firstBackup = null;
  private Trio secondBackup = null;

  public CylindricalFireSystem(GL2 gl, Trio[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    firstBackup = new Trio(source.getX(), source.getY(), source.getZ());
    secondBackup = new Trio(destination.getX(), destination.getY(), destination.getZ());
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
    Trio pointInFirstSphere = generatePointInSphere(source, firstBackup);
    Trio pointInSecondSphere = generatePointInSphere(destination, secondBackup);

    Trio directionVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    acceleration = Calculator.makeItSmaller(directionVector, directionVectorScalar);
  }
}
