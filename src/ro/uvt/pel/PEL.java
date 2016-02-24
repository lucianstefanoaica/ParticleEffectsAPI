
package ro.uvt.pel;

import javax.media.opengl.GL2;
import ro.uvt.api.systems.CylindricalSystem;
import ro.uvt.api.systems.FountainSystem;
import ro.uvt.api.systems.ReversedSystem;
import ro.uvt.api.systems.SprayedSystem;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;
import com.jogamp.opengl.util.texture.Texture;

public class PEL {

  private SprayedSystem sprayed;
  private CylindricalSystem cylindrical;
  private ReversedSystem reversed;
  private FountainSystem fountain;

  public void pelDrawSprayedSystem(GL2 gl,
                                   Vertex[] positions,
                                   Texture texture,
                                   Material material,
                                   float systemRadius,
                                   int pps,
                                   float pr,
                                   float fu,
                                   float scalar,
                                   float angle) {
    if (sprayed == null) {
      sprayed = new SprayedSystem(gl, positions, texture, material, systemRadius);
      sprayed.setParticlesPerSpawn(pps);
      sprayed.setParticleRadius(pr);
      sprayed.setFadeUnit(fu);
      sprayed.setScalar(scalar);
    }

    sprayed.draw(angle);
  }

  public void pelDrawCylindricalSystem(GL2 gl,
                                       Vertex[] positions,
                                       Texture texture,
                                       Material material,
                                       float systemRadius,
                                       int pps,
                                       float pr,
                                       float fu,
                                       float scalar,
                                       float angle) {
    if (cylindrical == null) {
      cylindrical = new CylindricalSystem(gl, positions, texture, material, systemRadius);
      cylindrical.setParticlesPerSpawn(pps);
      cylindrical.setParticleRadius(pr);
      cylindrical.setFadeUnit(fu);
      cylindrical.setScalar(scalar);
    }

    cylindrical.draw(angle);
  }

  public void pelDrawReversedSystem(GL2 gl,
                                    Vertex[] positions,
                                    Texture texture,
                                    Material material,
                                    float systemRadius,
                                    int pps,
                                    float pr,
                                    float fu,
                                    float scalar,
                                    float angle) {

    if (reversed == null) {
      reversed = new ReversedSystem(gl, positions, texture, material, systemRadius);
      reversed.setParticlesPerSpawn(pps);
      reversed.setParticleRadius(pr);
      reversed.setFadeUnit(fu);
      reversed.setScalar(scalar);
    }

    reversed.draw(angle);
  }

  public void pelDrawFountainSystem(GL2 gl,
                                    Vertex[] positions,
                                    Texture texture,
                                    Material material,
                                    float systemRadius,
                                    int pps,
                                    float pr,
                                    float fu,
                                    float scalar,
                                    float angle,
                                    Vertex gravity) {

    if (fountain == null) {
      fountain = new FountainSystem(gl, positions, texture, material, systemRadius);
      fountain.setParticlesPerSpawn(pps);
      fountain.setParticleRadius(pr);
      fountain.setFadeUnit(fu);
      fountain.setScalar(scalar);
      fountain.setGravityVector(gravity);
    }

    fountain.draw(angle);
  }
}
