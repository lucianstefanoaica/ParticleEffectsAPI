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
import javax.media.opengl.GLAutoDrawable;

import ro.uvt.pel.particle_systems.Atom;
import ro.uvt.pel.particle_systems.Fire;
import ro.uvt.pel.particle_systems.Cone;
import ro.uvt.pel.particle_systems.Cylinder;
import ro.uvt.pel.particle_systems.Disk;
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

public class DefaultPEL implements PEL {

  private static DefaultPEL uniqueInstance;
  private GL2 gl;
  private ParticleSystem system;
  private IntBuffer previousEquation;
  private IntBuffer previousSourceFactor;
  private IntBuffer previousDestinationFactor;
  private boolean blendWasEnabled;
  private List<ParticleSystem> systems = new ArrayList<>();

  private DefaultPEL(GLAutoDrawable drawable) {
    gl = drawable.getGL().getGL2();
  }

  public static PEL getInstance(GLAutoDrawable drawable) {
    if (uniqueInstance == null) {
      uniqueInstance = new DefaultPEL(drawable);
    }
    return uniqueInstance;
  }

  public void pelCone(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle) {
    setBlending();
    if (system instanceof Cone == false) {
      system = new Cone(gl, positions, texture, material, systemRadius);
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, new Vertex(),
          false);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelCylinder(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle) {
    setBlending();
    if (system instanceof Cylinder == false) {
      system = new Cylinder(gl, positions, texture, material, systemRadius);
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, new Vertex(),
          false);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelReversedCone(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle) {
    setBlending();
    if (system instanceof ReversedCone == false) {
      system = new ReversedCone(gl, positions, texture, material, systemRadius);
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, new Vertex(),
          false);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelFountain(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle, Vertex gravity) {
    setBlending();
    if (system instanceof Fountain == false) {
      system = new Fountain(gl, positions, texture, material, systemRadius);
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity, false);
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
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity, false);
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
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity, false);
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
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity, true);
    }
    system.draw(angle);
    unsetBlending();
  }

  public void pelFireworks(Vertex[] positions, Texture texture, Material material,
      float systemRadius, int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar,
      float angle, Vertex gravity) {
    setBlending();
    if (system instanceof Fireworks == false) {
      systems.clear();

      for (int i = 0; i < 5; ++i) {
        Vertex[] newPos = new Vertex[2];
        Random rand = new Random();
        newPos[0] = new Vertex(positions[0].getPositionX() + rand.nextInt(5),
            positions[0].getPositionY() + rand.nextInt(5),
            positions[0].getPositionZ() + rand.nextInt(5));

        newPos[1] = new Vertex(positions[1].getPositionX() + rand.nextInt(5),
            positions[1].getPositionY() + rand.nextInt(5),
            positions[1].getPositionZ() + rand.nextInt(5));

        ParticleSystem aSystem =
            new Fireworks(gl, newPos, texture, material.randomize(), systemRadius);
        injectAttributes(aSystem, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity,
            true);
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
        injectAttributes(aSystem, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity,
            true);
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
  
  public void pelDisk(Vertex[] positions, Texture texture, Material material, float systemRadius,
      int particlesPerSpawn, float particleRadius, float fadeUnit, float scalar, float angle,
      Vertex gravity) {
    setBlending();
    if (system instanceof Disk == false) {
      system = new Disk(gl, positions, texture, material, systemRadius);
      injectAttributes(system, particlesPerSpawn, particleRadius, fadeUnit, scalar, gravity, false);
    }
    system.draw(angle);
    unsetBlending();
  }

  private void injectAttributes(ParticleSystem system, int particlesPerSpawn, float particleRadius,
      float fadeUnit, float scalar, Vertex gravity, boolean pulse) {
    system.setParticlesPerSpawn(particlesPerSpawn);
    system.setParticleRadius(particleRadius);
    system.setFadeUnit(fadeUnit);
    system.setScalar(scalar);
    system.setGravityVector(gravity);
    system.setPulsate(pulse);
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
