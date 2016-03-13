package ro.uvt.pel;

import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_BLEND_DST;
import static javax.media.opengl.GL.GL_BLEND_EQUATION;
import static javax.media.opengl.GL.GL_BLEND_SRC;
import static javax.media.opengl.GL.GL_FUNC_ADD;
import static javax.media.opengl.GL.GL_ONE;
import static javax.media.opengl.GL.GL_SRC_ALPHA;

import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.media.opengl.GL2;

import ro.uvt.pel.particle_systems.Atom;
import ro.uvt.pel.particle_systems.Fire;
import ro.uvt.pel.particle_systems.Cone;
import ro.uvt.pel.particle_systems.Cylinder;
import ro.uvt.pel.particle_systems.Fireworks;
import ro.uvt.pel.particle_systems.Fountain;
import ro.uvt.pel.particle_systems.Line;
import ro.uvt.pel.particle_systems.ParticleSystem;
import ro.uvt.pel.particle_systems.Ring;
import ro.uvt.pel.particle_systems.ReversedCone;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

import com.jogamp.common.nio.Buffers;
import com.jogamp.opengl.util.texture.Texture;

public class PEL {

  private ParticleSystem system;
  private GL2 gl;

  private IntBuffer previousEquation;
  private IntBuffer previousSourceFactor;
  private IntBuffer previousDestinationFactor;
  private boolean blendWasEnabled;

  private List<ParticleSystem> systems = new ArrayList<>();

  public PEL(GL2 gl) {
    this.gl = gl;
  }

  public void pelCone(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle) {
    setBlending();
    if (system instanceof Cone == false) {
      system = new Cone(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelCylinder(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit,
      float scalar, float angle) {
    setBlending();
    if (system instanceof Cylinder == false) {
      system = new Cylinder(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelReversedCone(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit,
      float scalar, float angle) {
    setBlending();
    if (system instanceof ReversedCone == false) {
      system = new ReversedCone(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelFountain(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit,
      float scalar, float angle, Vertex gravity) {
    setBlending();
    if (system instanceof Fountain == false) {
      system = new Fountain(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
      system.setGravityVector(gravity);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelLine(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    setBlending();
    if (system instanceof Line == false) {
      system = new Line(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
      system.setGravityVector(gravity);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelFire(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    setBlending();
    if (system instanceof Fire == false) {
      system = new Fire(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
      system.setGravityVector(gravity);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelRing(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    setBlending();
    if (system instanceof Ring == false) {
      system = new Ring(gl, positions, texture, material, systemRadius);
      setAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar);
      system.setGravityVector(gravity);
      system.setPulsate(true);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelFireworks(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit,
      float scalar, float angle, Vertex gravity) {
    setBlending();
    if (system instanceof Fireworks == false) {
      systems.clear();

      for (int i = 0; i < 5; ++i) {
        Vertex[] newPos = new Vertex[2];
        Random rand = new Random();
        newPos[0] =
            new Vertex(positions[0].getPositionX() + rand.nextInt(5), positions[0].getPositionY()
                + rand.nextInt(5), positions[0].getPositionZ() + rand.nextInt(5));

        newPos[1] =
            new Vertex(positions[1].getPositionX() + rand.nextInt(5), positions[1].getPositionY()
                + rand.nextInt(5), positions[1].getPositionZ() + rand.nextInt(5));

        ParticleSystem aSystem =
            new Fireworks(gl, newPos, texture, material.randomize(), systemRadius);
        setAttributes(aSystem, particlesPerSpawn, particleRadius, fadeUnit, scalar);
        aSystem.setGravityVector(gravity);
        aSystem.setPulsate(true);
        systems.add(aSystem);
      }
    }

    for (int i = 0; i < systems.size(); ++i) {
      system = systems.get(i);
      system.draw(angle);
    }
    unsetBlending();
  }

  public void pelAtom(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    setBlending();
    if (system instanceof Atom == false) {
      systems.clear();

      for (int i = 0; i < 3; ++i) {
        Atom aSystem = new Atom(gl, positions, texture, material, systemRadius);
        setAttributes(aSystem, particlesPerSpawn, particleRadius, fadeUnit, scalar);
        aSystem.setGravityVector(gravity);
        aSystem.setPulsate(true);
        aSystem.setType(i);

        systems.add(aSystem);
      }
    }
    for (int i = 0; i < systems.size(); ++i) {
      system = systems.get(i);
      system.draw(angle);
    }
    unsetBlending();
  }

  private void setAttributes(ParticleSystem system, int particlesPerSpawn, float particleRadius,
      float fadeUnit, float scalar) {
    system.setParticlesPerSpawn(particlesPerSpawn);
    system.setParticleRadius(particleRadius);
    system.setFadeUnit(fadeUnit);
    system.setScalar(scalar);
  }

  private void setBlending() {
    previousEquation = Buffers.newDirectIntBuffer(1);
    gl.glGetIntegerv(GL_BLEND_EQUATION, previousEquation);

    previousSourceFactor = Buffers.newDirectIntBuffer(1);
    gl.glGetIntegerv(GL_BLEND_SRC, previousSourceFactor);

    previousDestinationFactor = Buffers.newDirectIntBuffer(1);
    gl.glGetIntegerv(GL_BLEND_DST, previousDestinationFactor);

    blendWasEnabled = gl.glIsEnabled(GL_BLEND);

    gl.glBlendFunc(GL_SRC_ALPHA, GL_ONE);
    gl.glBlendEquation(GL_FUNC_ADD);
    gl.glEnable(GL_BLEND);
  }

  private void unsetBlending() {
    gl.glBlendFunc(previousSourceFactor.get(0), previousDestinationFactor.get(0));
    gl.glBlendEquation(previousEquation.get(0));
    if (blendWasEnabled == false) {
      gl.glDisable(GL_BLEND);
    }
  }
}
