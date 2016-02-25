
package ro.uvt.api.systems;

import static javax.media.opengl.GL.GL_BLEND;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import ro.uvt.api.particles.Particle;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Material;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public abstract class ParticleSystem {

  private GL2 gl;
  private float systemRadius;
  private Material material;
  private List<Particle> particles = new ArrayList<>();
  private Texture texture;
  private float cameraAngle;
  private int particlesPerSpawn = 350;
  private float particleRadius = 0.08f;
  private float fadeUnit = 0.07f;
  private Vertex gravityVector = new Vertex(0.0f, 0.0f, 0.0f);

  protected Vertex aBackupPosition;
  protected Vertex startPosition;
  protected float scalar = 400f;
  protected Vertex source;
  protected Vertex destination;
  protected Vertex speed;

  protected ParticleSystem(GL2 gl, Vertex[] positions, Texture texture, Material material, float systemRadius) {
    this.gl = gl;
    this.source = positions[0];
    this.destination = positions[1];
    this.texture = texture;
    this.material = material;
    this.systemRadius = systemRadius;
  }

  protected abstract void generateParticleDirectionVector();

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

  public void draw(float angle) {
    texture.bind(gl);
    spawnParticles();
    gl.glEnable(GL_BLEND);
    gl.glDepthMask(false);
    for (int index = particles.size() - 1; index >= 0; --index) {
      Particle aParticle = particles.get(index);
      aParticle.draw(angle);
      aParticle.move();
      if (aParticle.died()) {
        particles.remove(index);
      }
    }
    gl.glDepthMask(true);
  }

  private void spawnParticles() {
    for (int i = 0; i < particlesPerSpawn; ++i) {
      generateParticleDirectionVector();

      Particle particle = new Particle(gl, startPosition, speed, cameraAngle, texture, particleRadius, fadeUnit, material.clone());

      particle.setGravityVector(gravityVector);

      particles.add(particle);
    }
  }

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
