
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalSystem extends ParticleSystem {

  private Vertex anotherBackupPosition = null;

  public CylindricalSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition = new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
    anotherBackupPosition = new Vertex(destination.getPositionX(), destination.getPositionY(), destination.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInFirstSphere = generatePointInSphere(source, aBackupPosition);
    Vertex pointInSecondSphere = generatePointInSphere(destination, anotherBackupPosition);

    Vertex differenceVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    acceleration = Calculator.scaleDown(differenceVector, scalar);
  }
}
