
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class CylindricalSystem extends ParticleSystem {

  public CylindricalSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition = new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInFirstSphere = generatePointInSphere(source, aBackupPosition);
    Vertex pointInSecondSphere = Calculator.add(destination, Calculator.subtract(pointInFirstSphere, source));

    Vertex differenceVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    speed = Calculator.scaleDown(differenceVector, scalar);
  }
}
