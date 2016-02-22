
package ro.uvt.api.systems;

import javax.media.opengl.GL2;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;
import com.jogamp.opengl.util.texture.Texture;

public class FountainSystem extends ParticleSystem {

  private Vertex backupPosition = null;

  public FountainSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    backupPosition = new Vertex(destination.getPositionX(), destination.getPositionY(), destination.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInSphere = generatePointInSphere(destination, backupPosition);

    Vertex differenceVector = Calculator.subtract(pointInSphere, source);

    acceleration = Calculator.scaleDown(differenceVector, scalar);

    startPosition = source.clone();

    gravityVector = new Vertex(0.0f, -0.006f, 0.0f);
  }
}
