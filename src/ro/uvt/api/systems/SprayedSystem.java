
package ro.uvt.api.systems;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class SprayedSystem extends ParticleSystem {

  public SprayedSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition = new Vertex(destination.getPositionX(), destination.getPositionY(), destination.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInSphere = generatePointInSphere(destination, aBackupPosition, systemRadius);
    Vertex movementVector = Calculator.subtract(pointInSphere, source);

    speed = Calculator.scaleDown(movementVector, scalar);
    startPosition = source.clone();
  }
}
