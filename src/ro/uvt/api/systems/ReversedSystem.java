
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class ReversedSystem extends ParticleSystem {

  public ReversedSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition = new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    startPosition = generatePointInSphere(source, aBackupPosition);

    Vertex differenceVector = Calculator.subtract(destination, startPosition);

    acceleration = Calculator.scaleDown(differenceVector, scalar);
  }
}
