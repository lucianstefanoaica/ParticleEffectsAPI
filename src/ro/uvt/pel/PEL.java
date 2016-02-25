
package ro.uvt.pel;

import javax.media.opengl.GL2;

import ro.uvt.api.systems.CylindricalSystem;
import ro.uvt.api.systems.FountainSystem;
import ro.uvt.api.systems.ParticleSystem;
import ro.uvt.api.systems.ReversedSystem;
import ro.uvt.api.systems.SprayedSystem;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class PEL {

  private ParticleSystem system;
  private GL2 gl;

  public PEL(GL2 gl) {
    this.gl = gl;
  }

  public void pelDrawSprayedSystem(Vertex[] positions,
                                   Texture texture,
                                   Material material,
                                   float systemRadius,
                                   int particlesPerSpawn,
                                   float particleRadius,
                                   float fadeUnit,
                                   float scalar,
                                   float angle) {
    if (system instanceof SprayedSystem == false) {
      system = new SprayedSystem(gl, positions, texture, material, systemRadius);
      system.setParticlesPerSpawn(particlesPerSpawn);
      system.setParticleRadius(particleRadius);
      system.setFadeUnit(fadeUnit);
      system.setScalar(scalar);
    }

    system.draw(angle);
  }

  public void pelDrawCylindricalSystem(Vertex[] positions,
                                       Texture texture,
                                       Material material,
                                       float systemRadius,
                                       int particlesPerSpawn,
                                       float particleRadius,
                                       float fadeUnit,
                                       float scalar,
                                       float angle) {
    if (system instanceof CylindricalSystem == false) {
      system = new CylindricalSystem(gl, positions, texture, material, systemRadius);
      system.setParticlesPerSpawn(particlesPerSpawn);
      system.setParticleRadius(particleRadius);
      system.setFadeUnit(fadeUnit);
      system.setScalar(scalar);
    }

    system.draw(angle);
  }

  public void pelDrawReversedSystem(Vertex[] positions,
                                    Texture texture,
                                    Material material,
                                    float systemRadius,
                                    int particlesPerSpawn,
                                    float particleRadius,
                                    float fadeUnit,
                                    float scalar,
                                    float angle) {

    if (system instanceof ReversedSystem == false) {
      system = new ReversedSystem(gl, positions, texture, material, systemRadius);
      system.setParticlesPerSpawn(particlesPerSpawn);
      system.setParticleRadius(particleRadius);
      system.setFadeUnit(fadeUnit);
      system.setScalar(scalar);
    }

    system.draw(angle);
  }

  public void pelDrawFountainSystem(Vertex[] positions,
                                    Texture texture,
                                    Material material,
                                    float systemRadius,
                                    int particlesPerSpawn,
                                    float particleRadius,
                                    float fadeUnit,
                                    float scalar,
                                    float angle,
                                    Vertex gravity) {

    if (system instanceof FountainSystem == false) {
      system = new FountainSystem(gl, positions, texture, material, systemRadius);
      system.setParticlesPerSpawn(particlesPerSpawn);
      system.setParticleRadius(particleRadius);
      system.setFadeUnit(fadeUnit);
      system.setScalar(scalar);
      system.setGravityVector(gravity);
    }

    system.draw(angle);
  }
}
