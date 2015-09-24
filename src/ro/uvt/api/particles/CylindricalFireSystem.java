
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalFireSystem extends ParticleSystem {

  private Trio startPosition = null;
  private Trio startSpeed = null;
  private Trio acceleration = null;

  private Trio secondBackup = null;

  public CylindricalFireSystem(GL2 gl, Trio source, Trio destination, float systemRadius, Trio cameraPosition, Texture texture, Material material) {
    super(gl, source, destination, cameraPosition, texture, material);
    this.systemRadius = systemRadius;
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
    Trio pointInFirstSphere = generatePointInSphere(source);
    Trio pointInSecondSphere = generatePointInSphereB(destination);

    Trio directionVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    acceleration = Calculator.makeItSmaller(directionVector, 450f);
  }

  // this is just a quick fix... this method will have to go away... I'll think of something...
  private Trio generatePointInSphereB(Trio sphereCenter) {
    Trio pointInSphere = null;

    for (int i = 1; i <= 5; ++i) {
      double xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      double yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      double zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

      pointInSphere = Calculator.add(sphereCenter, new Trio(xVal, yVal, zVal));

      if (Calculator.computeDistance(sphereCenter, pointInSphere) <= systemRadius) {
        secondBackup = new Trio(pointInSphere.getX(), pointInSphere.getY(), pointInSphere.getZ());
        break;
      } else {
        if (i == 5) {
          pointInSphere = new Trio(secondBackup.getX(), secondBackup.getY(), secondBackup.getZ());
        }
      }
    }

    return pointInSphere;
  }
}
