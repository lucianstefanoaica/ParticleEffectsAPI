package ro.uvt.pel.particle_systems;

import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_BLEND_DST;
import static javax.media.opengl.GL.GL_BLEND_EQUATION;
import static javax.media.opengl.GL.GL_BLEND_SRC;
import static javax.media.opengl.GL.GL_FUNC_ADD;
import static javax.media.opengl.GL.GL_ONE;
import static javax.media.opengl.GL.GL_SRC_ALPHA;

import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class DefaultPEL implements PEL {

  private GLAutoDrawable drawable;
  private Map<String, ParticleSystem> systems = new HashMap<>();

  DefaultPEL() {}

  public void pelCone(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int spawnCount, float particleRadius, float fade, float scalar,
      float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("cone") == null) {
      systems.put("cone", new Cone(source, destination, systemRadius, texture, material, fade));
    }
    Cone cone = (Cone) systems.get("cone");
    List<Vertex> speeds = cone.generateSpeedVectors(scalar, spawnCount);
    List<Vertex> positions = cone.generatePositionVectors(spawnCount);
    cone.spawnParticles(spawnCount, particleRadius, new Vertex(), speeds, positions);
    cone.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelCylinder(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("cylinder") == null) {
      systems.put("cylinder",
          new Cylinder(source, destination, systemRadius, texture, material, fadeUnit));
    }
    Cylinder cylinder = (Cylinder) systems.get("cylinder");
    List<Vertex> positions = cylinder.generatePositionVectors(particlesPerSpawn);
    List<Vertex> speeds = cylinder.generateSpeedVectors(positions, scalar);
    cylinder.spawnParticles(particlesPerSpawn, particleRadius, new Vertex(), speeds, positions);
    cylinder.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelReversedCone(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("reversed_cone") == null) {
      systems.put("reversed_cone",
          new ReversedCone(source, destination, systemRadius, texture, material, fadeUnit));
    }
    ReversedCone reversedCone = (ReversedCone) systems.get("reversed_cone");
    List<Vertex> positions = reversedCone.generatePositionVectors(particlesPerSpawn);
    List<Vertex> speeds = reversedCone.generateSpeedVectors(positions, scalar);
    reversedCone.spawnParticles(particlesPerSpawn, particleRadius, new Vertex(), speeds, positions);
    reversedCone.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelFountain(Vertex source, Vertex destination, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle, Vertex gravity) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("fountain") == null) {
      systems.put("fountain",
          new Cone(source, destination, systemRadius, texture, material, fadeUnit));
    }
    Cone fountain = (Cone) systems.get("fountain");
    List<Vertex> speeds = fountain.generateSpeedVectors(scalar, particlesPerSpawn);
    List<Vertex> positions = fountain.generatePositionVectors(particlesPerSpawn);
    fountain.spawnParticles(particlesPerSpawn, particleRadius, gravity, speeds, positions);
    fountain.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelLine(Vertex left, Vertex right, Texture texture, Material material,
      int particlesPerSpawn, float particleRadius, float fadeUnit, int scalar, float angle,
      Vertex gravity) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("line") == null) {
      systems.put("line", new Line(left, right, texture, material, fadeUnit));
    }
    Line line = (Line) systems.get("line");
    List<Vertex> speeds = line.generateSpeedVectors(particlesPerSpawn);
    List<Vertex> positions = line.generatePositionVectors(particlesPerSpawn, scalar);
    line.spawnParticles(particlesPerSpawn, particleRadius, gravity, speeds, positions);
    line.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelFire(Vertex center, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("fire") == null) {
      systems.put("fire", new Fire(center, systemRadius, texture, material, fadeUnit));
    }
    Fire fire = (Fire) systems.get("fire");
    List<Vertex> speeds = fire.generateSpeedVectors(particlesPerSpawn);
    List<Vertex> positions = fire.generatePositionVectors(particlesPerSpawn);
    fire.spawnParticles(particlesPerSpawn, particleRadius, gravity, speeds, positions);
    fire.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelRing(Vertex center, Texture texture, Material material, int particlesPerSpawn,
      float particleRadius, float fadeUnit, float scalar, float angle, Vertex gravity) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("ring") == null) {
      systems.put("ring", new Ring(center, texture, material, fadeUnit));
    }
    Ring ring = (Ring) systems.get("ring");
    List<Vertex> speeds = ring.generateSpeedVectors(scalar, particlesPerSpawn);
    List<Vertex> positions = ring.generatePositionVectors(particlesPerSpawn);
    if (ring.isEmpty()) {
      ring.spawnParticles(particlesPerSpawn, particleRadius, new Vertex(), speeds, positions);
    }
    ring.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelFireworks(List<Vertex> sources, Texture texture, Material material,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("fireworks") == null) {
      systems.put("fireworks", new Fireworks(sources, texture, material, fadeUnit));
    }
    Fireworks fireworks = (Fireworks) systems.get("fireworks");
    List<Vertex> positions = fireworks.generatePositionVectors(particlesPerSpawn);
    List<Vertex> speeds = fireworks.generateSpeedVectors(scalar, positions);
    if (fireworks.isEmpty()) {
      fireworks.spawnParticles(positions.size(), particleRadius, gravity, speeds, positions);
    }
    fireworks.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelAtom(Vertex center, Texture texture, Material material, int particlesPerSpawn,
      float particleRadius, float fadeUnit, float scalar, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("atom") == null) {
      systems.put("atom", new Atom(center, texture, material, fadeUnit));
    }
    Atom atom = (Atom) systems.get("atom");
    List<Vertex> positions = atom.generatePositionVectors(particlesPerSpawn);
    List<Vertex> speeds = atom.generateSpeedVectors(scalar, particlesPerSpawn);
    if (atom.isEmpty()) {
      atom.spawnParticles(positions.size(), particleRadius, new Vertex(), speeds, positions);
    }
    atom.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelDisk(Vertex center, float diskRadius, float particleRadius, Texture texture,
      Material material, int particlesPerSpawn, float fadeUnit, float scalar, float cameraAngle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("disk") == null) {
      systems.put("disk", new Ring(center, texture, material, fadeUnit));
    }
    Ring disk = (Ring) systems.get("disk");
    List<Vertex> speeds = disk.generateSpeedVectors(scalar, particlesPerSpawn);
    List<Vertex> positions = disk.generatePositionVectors(particlesPerSpawn);
    disk.spawnParticles(particlesPerSpawn, particleRadius, new Vertex(), speeds, positions);
    disk.appearOnGLAutoDrawable(drawable, cameraAngle);
    resetBlending(previousBlendSettings);
  }

  @Override
  public void registerGLAutoDrawable(GLAutoDrawable drawable) {
    this.drawable = drawable;
  }

  private Map<String, Object> setBlending() {
    Map<String, Object> result = new HashMap<>();
    GL2 gl = drawable.getGL().getGL2();

    IntBuffer equation = Buffers.newDirectIntBuffer(1);
    gl.glGetIntegerv(GL_BLEND_EQUATION, equation);
    result.put("equation", equation);

    IntBuffer sourceFactor = Buffers.newDirectIntBuffer(1);
    gl.glGetIntegerv(GL_BLEND_SRC, sourceFactor);
    result.put("sourceFactor", sourceFactor);

    IntBuffer destinationFactor = Buffers.newDirectIntBuffer(1);
    gl.glGetIntegerv(GL_BLEND_DST, destinationFactor);
    result.put("destinationFactor", destinationFactor);

    boolean enabled = gl.glIsEnabled(GL_BLEND);
    result.put("enabled", enabled);

    gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
    gl.glBlendEquation(GL_FUNC_ADD);
    gl.glEnable(GL_BLEND);

    return result;
  }

  private void resetBlending(Map<String, Object> params) {
    GL2 gl = drawable.getGL().getGL2();
    IntBuffer sourceFactor = (IntBuffer) params.get("sourceFactor");
    IntBuffer destinationFactor = (IntBuffer) params.get("destinationFactor");
    gl.glBlendFunc(sourceFactor.get(0), destinationFactor.get(0));

    IntBuffer equation = (IntBuffer) params.get("equation");
    gl.glBlendEquation(equation.get(0));

    boolean enabled = (boolean) params.get("enabled");
    if (enabled == false) {
      gl.glDisable(GL_BLEND);
    }
  }
}
