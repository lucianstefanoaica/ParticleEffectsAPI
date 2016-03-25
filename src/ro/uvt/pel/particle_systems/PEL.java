package ro.uvt.pel.particle_systems;

import java.util.List;

import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

public interface PEL {

  public void pelCone(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fade, float scalar,
      float angle);

  public void pelCylinder(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle);

  public void pelReversedCone(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle);

  public void pelFountain(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle, Vertex gravity);

  public void pelLine(Vertex left, Vertex right, Texture texture, Material material,
      int particlesPerSpawn, float particleRadius, float fadeUnit, int scalar, float angle,
      Vertex gravity);

  public void pelFire(Vertex center, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);

  public void pelRing(Vertex center, Texture texture, Material material, int particlesPerSpawn,
      float particleRadius, float fadeUnit, float scalar, float angle, Vertex gravity);

  public void pelFireworks(List<Vertex> sources, Texture texture, Material material,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity);

  public void pelAtom(Vertex center, Texture texture, Material material, int particlesPerSpawn,
      float particleRadius, float fadeUnit, float scalar, float angle);

  public void pelDisk(Vertex center, float diskRadius, float particleRadius, Texture texture,
      Material material, int particlesPerSpawn, float fadeUnit, float scalar, float cameraAngle);

  public void registerGLAutoDrawable(GLAutoDrawable drawable);
}
