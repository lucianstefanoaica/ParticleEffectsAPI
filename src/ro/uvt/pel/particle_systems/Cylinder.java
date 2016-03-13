
package ro.uvt.pel.particle_systems;

import javax.media.opengl.GL2;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class Cylinder extends ParticleSystem {

  public Cylinder(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    aBackupPosition = new Vertex(source.getPositionX(), source.getPositionY(), source.getPositionZ());
  }

  protected void generateParticleDirectionVector() {
    Vertex pointInFirstSphere = generatePointInSphere(source, aBackupPosition, systemRadius);
    Vertex pointInSecondSphere = Calculator.add(destination, Calculator.subtract(pointInFirstSphere, source));

    Vertex differenceVector = Calculator.subtract(pointInSecondSphere, pointInFirstSphere);

    startPosition = pointInFirstSphere;
    speed = Calculator.scaleDown(differenceVector, scalar);
  }
}
