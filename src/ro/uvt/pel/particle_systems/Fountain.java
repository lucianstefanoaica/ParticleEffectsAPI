
package ro.uvt.pel.particle_systems;

import javax.media.opengl.GL2;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class Fountain extends ParticleSystem {

  public Fountain(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition = new Vertex(destination.getPositionX(), destination.getPositionY(), destination.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInSphere = generatePointInSphere(destination, aBackupPosition, systemRadius);

    Vertex differenceVector = Calculator.subtract(pointInSphere, source);

    speed = Calculator.scaleDown(differenceVector, scalar);

    startPosition = source.clone();
  }
}
