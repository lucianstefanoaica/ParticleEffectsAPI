package ro.uvt.pel.particle_systems;

import javax.media.opengl.GL2;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class ReversedCone extends ParticleSystem {

  public ReversedCone(GL2 gl, Vertex[] positions, Texture texture, Material material,
      float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition =
        new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    startPosition = generatePointInSphere(source, aBackupPosition, systemRadius);

    Vertex differenceVector = Calculator.subtract(destination, startPosition);

    speed = Calculator.scaleDown(differenceVector, scalar);
  }
}
