
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class ReversedConeFireSystem extends ParticleSystem {

  private Trio startPosition = null;
  private Trio startSpeed = null;
  private Trio acceleration = null;

  private Trio backupStartPosition = null;

  public ReversedConeFireSystem(GL2 gl, Trio source, Trio destination, float systemRadius, Trio cameraPosition, Texture texture, Material material) {
    super(gl, source, destination, cameraPosition, texture, material);
    backupStartPosition = new Trio(source.getX(), source.getY(), source.getZ());
    this.systemRadius = systemRadius;
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
    Trio pointInFirstSphere = null;

    for (int i = 1; i <= 5; ++i) {
      double xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      double yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      double zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

      pointInFirstSphere = Calculator.add(source, new Trio(xVal, yVal, zVal));

      if (Calculator.computeDistance(source, pointInFirstSphere) <= systemRadius) {
        backupStartPosition = new Trio(pointInFirstSphere.getX(), pointInFirstSphere.getY(), pointInFirstSphere.getZ());
        break;
      } else {
        if (i == 5) {
          pointInFirstSphere = new Trio(backupStartPosition.getX(), backupStartPosition.getY(), backupStartPosition.getZ());
        }
      }
    }

    startPosition = pointInFirstSphere;

    Trio directionVector = Calculator.subtract(destination, pointInFirstSphere);

    acceleration = Calculator.makeItSmaller(directionVector, 450f);
  }
}
