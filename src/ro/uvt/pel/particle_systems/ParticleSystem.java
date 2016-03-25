package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

abstract class ParticleSystem {

  private List<Particle> particles;
  private Texture texture;
  private Material material;
  private float fadeQuotient;

  ParticleSystem(Texture texture, Material material, float fadeQuotient) {
    this.texture = texture;
    this.material = material;
    this.fadeQuotient = fadeQuotient;
    particles = new ArrayList<>();
  }

  void spawnParticles(int spawnCount, float size, Vertex weight, List<Vertex> speeds,
      List<Vertex> positions) {
    for (int index = 0; index < spawnCount; ++index) {
      Particle particle = new Particle(positions.get(index), speeds.get(index), weight, size,
          fadeQuotient, texture, material.clone());
      particles.add(particle);
    }
  }

  void appearOnGLAutoDrawable(GLAutoDrawable drawable, float angle) {
    GL2 gl = drawable.getGL().getGL2();
    texture.bind(gl);
    gl.glDepthMask(false);
    for (int index = particles.size() - 1; index >= 0; --index) {
      Particle aParticle = particles.get(index);
      aParticle.appearOnGLAutoDrawable(drawable, angle);
      aParticle.move();
      if (aParticle.isDead()) {
        particles.remove(index);
      }
    }
    gl.glDepthMask(true);
  }

  boolean isEmpty() {
    if (particles.isEmpty()) {
      return true;
    } else {
      return false;
    }
  }
}
