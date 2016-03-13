package ro.uvt.pel.particle_systems;

import javax.media.opengl.GL2;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class Cone extends ParticleSystem {

  public Cone(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition =
        new Vertex(destination.getPositionX(), destination.getPositionY(),
            destination.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInSphere = generatePointInSphere(destination, aBackupPosition, systemRadius);
    Vertex movementVector = Calculator.subtract(pointInSphere, source);

    speed = Calculator.scaleDown(movementVector, scalar);
    startPosition = source.clone();
  }
}
