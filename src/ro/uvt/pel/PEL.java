package ro.uvt.pel;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

public interface PEL {

  public void pelCone(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle);

  public void pelCylinder(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle);

  public void pelReversedCone(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle);

  public void pelFountain(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle, Vertex gravity);

  public void pelLine(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);

  public void pelFire(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);

  public void pelRing(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);

  public void pelFireworks(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle, Vertex gravity);

  public void pelAtom(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);

  public void pelDisk(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);
}
