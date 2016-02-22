
package ro.uvt.api.systems;

import static javax.media.opengl.GL.GL_BLEND;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.media.opengl.GL2;

import ro.uvt.api.particles.Particle;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Observer;
import ro.uvt.api.util.Subject;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public abstract class ParticleSystem implements Observer {

  private float systemRadius;
  protected Material material;
  protected GL2 gl;
  protected List<Particle> particles = new ArrayList<>();
  protected Texture texture;
  protected Vertex cameraPosition;
  protected double cameraAngle = 0.0f;
  protected int particlesPerSpawn = 350;
  protected Vertex source;
  protected Vertex destination;
  protected float particleRadius = 0.08f;
  protected float fadeUnit = 0.07f;
  protected float scalar = 400f;

  protected Vertex gravityVector = new Vertex(0.0f, 0.0f, 0.0f);

  protected Vertex acceleration;

  private Vertex startSpeed = new Vertex(0.0f, 0.0f, 0.0f);

  protected Vertex startPosition;

  protected ParticleSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    this.gl = gl;
    this.source = positions[0];
    this.destination = positions[1];
    this.cameraPosition = positions[2];
    this.texture = texture;
    this.material = material;
    this.systemRadius = systemRadius;
  }

  protected Vertex generatePointInSphere(Vertex sphereCenter, Vertex backup) {
    Vertex point = null;

    for (int i = 1; i <= 5; ++i) {
      float xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      float yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      float zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

      point = Calculator.add(sphereCenter, new Vertex(xVal, yVal, zVal));

      if (Calculator.computeDistance(sphereCenter, point) <= systemRadius) {
        backup.setPositionX(point.getPositionX());
        backup.setPositionY(point.getPositionY());
        backup.setPositionZ(point.getPositionZ());
        break;
      } else {
        if (i == 5) {
          point = new Vertex(backup.getPositionX(), backup.getPositionY(), backup.getPositionZ());
        }
      }
    }

    return point;
  }

  public void draw() {
    material.bind(gl);

    texture.bind(gl);

    spawnParticles();

    gl.glEnable(GL_BLEND);
    gl.glDepthMask(false);

    for (int index = particles.size() - 1; index >= 0; --index) {
      Particle aParticle = particles.get(index);

      aParticle.draw();
      aParticle.move();

      if (aParticle.died()) {
        particles.remove(index);
      }
    }
    gl.glDepthMask(true);
  }

  @Override
  public void update(Subject toObserve) {
    HashMap<String, Object> subjectState = toObserve.getState();
    cameraPosition = (Vertex) subjectState.get("camera_position");
    cameraAngle = (Double) subjectState.get("camera_angle");

    for (Particle particle : particles) {
      particle.setCameraAngle(cameraAngle);
      particle.setCameraPosition(cameraPosition);
    }
  }

  protected void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {
      generateParticleDirectionVector();

      Particle particle =
        new Particle(gl, startPosition, startSpeed.clone(), acceleration, cameraPosition, cameraAngle, texture, particleRadius, fadeUnit, material.clone());

      particle.setGravityVector(gravityVector);

      particles.add(particle);
    }
  }

  protected abstract void generateParticleDirectionVector();

  // getters & setters
  public float getSystemRadius() {
    return systemRadius;
  }

  public void setSystemRadius(float systemRadius) {
    this.systemRadius = systemRadius;
  }

  public Texture getTexture() {
    return texture;
  }

  public void setTexture(Texture texture) {
    this.texture = texture;
  }

  public int getParticlesPerSpawn() {
    return particlesPerSpawn;
  }

  public void setParticlesPerSpawn(int particlesPerSpawn) {
    this.particlesPerSpawn = particlesPerSpawn;
  }

  public float getParticleRadius() {
    return particleRadius;
  }

  public void setParticleRadius(float particleRadius) {
    this.particleRadius = particleRadius;
  }

  public float getFadeUnit() {
    return fadeUnit;
  }

  public void setFadeUnit(float fadeUnit) {
    this.fadeUnit = fadeUnit;
  }

  public float getScalar() {
    return scalar;
  }

  public void setScalar(float scalar) {
    this.scalar = scalar;
  }

  public Vertex getGravityVector() {
    return gravityVector;
  }

  public void setGravityVector(Vertex gravityVector) {
    this.gravityVector = gravityVector;
  }
}
