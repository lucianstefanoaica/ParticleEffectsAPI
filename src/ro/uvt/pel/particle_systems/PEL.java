package ro.uvt.pel.particle_systems;

import java.util.List;

import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

public interface PEL {

  public void pelCone(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float coneRadius, float particleRadius, float fadeQuotient, float speed,
      float angle);

  public void pelCylinder(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float cylinderRadius, float particleRadius, float fadeQuotient,
      float speed, float angle);

  public void pelReversedCone(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float coneRadius, float particleRadius, float fadeQuotient, float speed,
      float angle);

  public void pelFountain(Vertex source, Vertex destination, Vertex weight, Texture texture,
      Material material, int particleCount, float fountainRadius, float particleRadius,
      float fadeQuotient, float speed, float angle);

  public void pelLine(Vertex left, Vertex right, Vertex weight, Texture texture, Material material,
      int particleCount, float particleRadius, float fadeQuotient, float angle);

  public void pelFire(Vertex center, Vertex weight, Texture texture, Material material,
      int particleCount, float fireRadius, float particleRadius, float fadeQuotient, float angle);

  public void pelRing(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle);

  public void pelFireworks(List<Vertex> sources, Vertex weight, Texture texture, Material material,
      int particleCount, float particleRadius, float fadeQuotient, float speed, float angle);

  public void pelAtom(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle);

  public void pelDisk(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle);

  public void registerGLAutoDrawable(GLAutoDrawable drawable);
}
