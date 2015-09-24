
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalFireSystem extends ParticleSystem {

  private Trio startPosition = null;
  private Trio startSpeed = null;
  private Trio acceleration = null;

  public CylindricalFireSystem(GL2 gl, Trio source, Trio destination, float systemRadius, Trio cameraPosition, Texture texture, Material material) {
    super(gl, source, destination, cameraPosition, texture, material);
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
    double xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
    double yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
    double zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

    Trio pointInFirstSphere = Calculator.add(source, new Trio(xVal, yVal, zVal));
    Trio pointInSecondSphere = Calculator.add(destination, new Trio(xVal, yVal, zVal));

    Trio directionVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    acceleration = Calculator.makeItSmaller(directionVector, 450f);
  }
}
