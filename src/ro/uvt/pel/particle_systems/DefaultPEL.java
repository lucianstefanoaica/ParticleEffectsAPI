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
      int particleCount, float coneRadius, float particleRadius, float fadeQuotient, float speed,
      float particleAngle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("cone") == null) {
      systems.put("cone",
          new Cone(source, destination, coneRadius, texture, material, fadeQuotient));
    }
    Cone cone = (Cone) systems.get("cone");

    if (cone.getFadeQuotient() != fadeQuotient) {
      cone.setFadeQuotient(fadeQuotient);
    }
    if (cone.getRadius() != coneRadius) {
      cone.setRadius(coneRadius);
    }
    if (cone.getDestination().equals(destination) == false) {
      cone.setDestination(destination);
    }
    if (cone.getSource().equals(source) == false) {
      cone.setSource(source);
    }
    if (cone.getTexture().equals(texture) == false) {
      cone.setTexture(texture);
    }
    if (cone.getMaterial().equals(material) == false) {
      cone.setMaterial(material);
    }

    List<Vertex> speeds = cone.generateSpeedVectors(speed, particleCount);
    List<Vertex> positions = cone.generatePositionVectors(particleCount);
    cone.spawnParticles(particleCount, particleRadius, new Vertex(), speeds, positions);
    cone.appearOnGLAutoDrawable(drawable, particleAngle);
    resetBlending(previousBlendSettings);
  }

  public void pelCylinder(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float cylinderRadius, float particleRadius, float fadeQuotient,
      float speed, float particleAngle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("cylinder") == null) {
      systems.put("cylinder",
          new Cylinder(source, destination, cylinderRadius, texture, material, fadeQuotient));
    }
    Cylinder cylinder = (Cylinder) systems.get("cylinder");

    if (cylinder.getDestination().equals(destination) == false) {
      cylinder.setDestination(destination);
    }
    if (cylinder.getFadeQuotient() != fadeQuotient) {
      cylinder.setFadeQuotient(fadeQuotient);
    }
    if (cylinder.getMaterial().equals(material) == false) {
      cylinder.setMaterial(material);
    }
    if (cylinder.getRadius() != cylinderRadius) {
      cylinder.setRadius(cylinderRadius);
    }
    if (cylinder.getSource().equals(source) == false) {
      cylinder.setSource(source);
    }
    if (cylinder.getTexture().equals(texture) == false) {
      cylinder.setTexture(texture);
    }

    List<Vertex> positions = cylinder.generatePositionVectors(particleCount);
    List<Vertex> speeds = cylinder.generateSpeedVectors(positions, speed);
    cylinder.spawnParticles(particleCount, particleRadius, new Vertex(), speeds, positions);
    cylinder.appearOnGLAutoDrawable(drawable, particleAngle);
    resetBlending(previousBlendSettings);
  }

  public void pelReversedCone(Vertex source, Vertex destination, Texture texture, Material material,
      int particleCount, float coneRadius, float particleRadius, float fadeQuotient, float speed,
      float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("reversed_cone") == null) {
      systems.put("reversed_cone",
          new ReversedCone(source, destination, coneRadius, texture, material, fadeQuotient));
    }
    ReversedCone reversedCone = (ReversedCone) systems.get("reversed_cone");

    if (reversedCone.getDestination().equals(destination) == false) {
      reversedCone.setDestination(destination);
    }
    if (reversedCone.getFadeQuotient() != fadeQuotient) {
      reversedCone.setFadeQuotient(fadeQuotient);
    }
    if (reversedCone.getMaterial().equals(material) == false) {
      reversedCone.setMaterial(material);
    }
    if (reversedCone.getRadius() != coneRadius) {
      reversedCone.setRadius(coneRadius);
    }
    if (reversedCone.getSource().equals(source) == false) {
      reversedCone.setSource(source);
    }
    if (reversedCone.getTexture().equals(texture) == false) {
      reversedCone.setTexture(texture);
    }

    List<Vertex> positions = reversedCone.generatePositionVectors(particleCount);
    List<Vertex> speeds = reversedCone.generateSpeedVectors(positions, speed);
    reversedCone.spawnParticles(particleCount, particleRadius, new Vertex(), speeds, positions);
    reversedCone.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelFountain(Vertex source, Vertex destination, Vertex weight, Texture texture,
      Material material, int particleCount, float fountainRadius, float particleRadius,
      float fadeQuotient, float speed, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("fountain") == null) {
      systems.put("fountain",
          new Cone(source, destination, fountainRadius, texture, material, fadeQuotient));
    }
    Cone fountain = (Cone) systems.get("fountain");

    if (fountain.getDestination().equals(destination) == false) {
      fountain.setDestination(destination);
    }
    if (fountain.getFadeQuotient() != fadeQuotient) {
      fountain.setFadeQuotient(fadeQuotient);
    }
    if (fountain.getMaterial().equals(material) == false) {
      fountain.setMaterial(material);
    }
    if (fountain.getRadius() != fountainRadius) {
      fountain.setRadius(fountainRadius);
    }
    if (fountain.getSource().equals(source) == false) {
      fountain.setSource(source);
    }
    if (fountain.getTexture().equals(texture) == false) {
      fountain.setTexture(texture);
    }

    List<Vertex> speeds = fountain.generateSpeedVectors(speed, particleCount);
    List<Vertex> positions = fountain.generatePositionVectors(particleCount);
    fountain.spawnParticles(particleCount, particleRadius, weight, speeds, positions);
    fountain.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelLine(Vertex left, Vertex right, Vertex weight, Texture texture, Material material,
      int particleCount, float particleRadius, float fadeQuotient, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("line") == null) {
      systems.put("line", new Line(left, right, texture, material, fadeQuotient));
    }
    Line line = (Line) systems.get("line");

    if (line.getFadeQuotient() != fadeQuotient) {
      line.setFadeQuotient(fadeQuotient);
    }
    if (line.getLeft().equals(left) == false) {
      line.setLeft(left);
    }
    if (line.getRight().equals(right) == false) {
      line.setRight(right);
    }
    if (line.getMaterial().equals(material) == false) {
      line.setMaterial(material);
    }
    if (line.getTexture().equals(texture) == false) {
      line.setTexture(texture);
    }

    List<Vertex> speeds = line.generateSpeedVectors(particleCount);
    List<Vertex> positions = line.generatePositionVectors(particleCount);
    line.spawnParticles(particleCount, particleRadius, weight, speeds, positions);
    line.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelFire(Vertex center, Vertex weight, Texture texture, Material material,
      int particleCount, float fireRadius, float particleRadius, float fadeQuotient, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("fire") == null) {
      systems.put("fire", new Fire(center, fireRadius, texture, material, fadeQuotient));
    }
    Fire fire = (Fire) systems.get("fire");

    if (fire.getCenter().equals(center) == false) {
      fire.setCenter(center);
    }
    if (fire.getFadeQuotient() != fadeQuotient) {
      fire.setFadeQuotient(fadeQuotient);
    }
    if (fire.getMaterial().equals(material) == false) {
      fire.setMaterial(material);
    }
    if (fire.getRadius() != fireRadius) {
      fire.setRadius(fireRadius);
    }
    if (fire.getTexture().equals(texture) == false) {
      fire.setTexture(texture);
    }

    List<Vertex> speeds = fire.generateSpeedVectors(particleCount);
    List<Vertex> positions = fire.generatePositionVectors(particleCount);
    fire.spawnParticles(particleCount, particleRadius, weight, speeds, positions);
    fire.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelRing(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("ring") == null) {
      systems.put("ring", new Ring(center, texture, material, fadeQuotient));
    }
    Ring ring = (Ring) systems.get("ring");

    if (ring.getCenter().equals(center) == false) {
      ring.setCenter(center);
    }
    if (ring.getFadeQuotient() != fadeQuotient) {
      ring.setFadeQuotient(fadeQuotient);
    }
    if (ring.getMaterial().equals(material) == false) {
      ring.setMaterial(material);
    }
    if (ring.getTexture().equals(texture) == false) {
      ring.setTexture(texture);
    }

    List<Vertex> speeds = ring.generateSpeedVectors(speed, particleCount);
    List<Vertex> positions = ring.generatePositionVectors(particleCount);
    if (ring.isEmpty()) {
      ring.spawnParticles(particleCount, particleRadius, new Vertex(), speeds, positions);
    }
    ring.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelFireworks(List<Vertex> sources, Vertex weight, Texture texture, Material material,
      int particleCount, float particleRadius, float fadeQuotient, float speed, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("fireworks") == null) {
      systems.put("fireworks", new Fireworks(sources, texture, material, fadeQuotient));
    }
    Fireworks fireworks = (Fireworks) systems.get("fireworks");
    try {
      if (sources.size() != fireworks.getSources().size()) {
        fireworks.setSources(sources);
      } else {
        for (int i = 0; i < sources.size(); ++i) {
          if (sources.get(i).equals(fireworks.getSources().get(i)) == false) {
            fireworks.getSources().set(i, sources.get(i));
          }
        }
      }
      if (fireworks.getFadeQuotient() != fadeQuotient) {
        fireworks.setFadeQuotient(fadeQuotient);
      }
      if (fireworks.getMaterial().equals(material) == false) {
        fireworks.setMaterial(material);
      }
      if (fireworks.getTexture().equals(texture) == false) {
        fireworks.setTexture(texture);
      }
    } catch (Exception e) {
      System.err.println("DefaultPEL.java: 295 " + e);
    }
    List<Vertex> positions = fireworks.generatePositionVectors(particleCount);
    List<Vertex> speeds = fireworks.generateSpeedVectors(positions, speed);
    if (fireworks.isEmpty()) {
      fireworks.spawnParticles(positions.size(), particleRadius, weight, speeds, positions);
    }
    fireworks.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelAtom(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("atom") == null) {
      systems.put("atom", new Atom(center, texture, material, fadeQuotient));
    }
    Atom atom = (Atom) systems.get("atom");

    if (atom.getCenter().equals(center) == false) {
      atom.setCenter(center);
    }
    if (atom.getFadeQuotient() != fadeQuotient) {
      atom.setFadeQuotient(fadeQuotient);
    }
    if (atom.getMaterial().equals(material) == false) {
      atom.setMaterial(material);
    }
    if (atom.getTexture().equals(texture) == false) {
      atom.setTexture(texture);
    }

    List<Vertex> positions = atom.generatePositionVectors(particleCount);
    List<Vertex> speeds = atom.generateSpeedVectors(speed, particleCount);
    if (atom.isEmpty()) {
      atom.spawnParticles(positions.size(), particleRadius, new Vertex(), speeds, positions);
    }
    atom.appearOnGLAutoDrawable(drawable, angle);
    resetBlending(previousBlendSettings);
  }

  public void pelDisk(Vertex center, Texture texture, Material material, int particleCount,
      float particleRadius, float fadeQuotient, float speed, float angle) {
    Map<String, Object> previousBlendSettings = setBlending();
    if (systems.get("disk") == null) {
      systems.put("disk", new Ring(center, texture, material, fadeQuotient));
    }
    Ring disk = (Ring) systems.get("disk");

    if (disk.getCenter().equals(center) == false) {
      disk.setCenter(center);
    }
    if (disk.getFadeQuotient() != fadeQuotient) {
      disk.setFadeQuotient(fadeQuotient);
    }
    if (disk.getMaterial().equals(material) == false) {
      disk.setMaterial(material);
    }
    if (disk.getTexture().equals(texture) == false) {
      disk.setTexture(texture);
    }

    List<Vertex> speeds = disk.generateSpeedVectors(speed, particleCount);
    List<Vertex> positions = disk.generatePositionVectors(particleCount);
    disk.spawnParticles(particleCount, particleRadius, new Vertex(), speeds, positions);
    disk.appearOnGLAutoDrawable(drawable, angle);
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
