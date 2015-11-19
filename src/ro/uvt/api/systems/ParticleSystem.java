
package ro.uvt.api.systems;

import static javax.media.opengl.GL.GL_BLEND;
import static javax.media.opengl.GL.GL_FRONT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_AMBIENT;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_DIFFUSE;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SHININESS;
import static javax.media.opengl.fixedfunc.GLLightingFunc.GL_SPECULAR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.media.opengl.GL2;

import ro.uvt.api.particles.Particle;
import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.MaterialProperties;
import ro.uvt.api.util.Observer;
import ro.uvt.api.util.Subject;
import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public abstract class ParticleSystem implements Observer {

  private float systemRadius;
  private MaterialProperties material;

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
  protected float directionVectorScalar = 400f;

  protected ParticleSystem(GL2 gl, Vertex[] positions, Texture texture, MaterialProperties material, float systemRadius) {
    this.gl = gl;
    this.source = positions[0];
    this.destination = positions[1];
    this.cameraPosition = positions[2];
    this.texture = texture;
    this.material = material;
    this.systemRadius = systemRadius;
  }

  protected abstract void spawnParticles();

  protected Vertex generatePointInSphere(Vertex sphereCenter, Vertex backup) {
    Vertex pointInSphere = null;

    for (int i = 1; i <= 5; ++i) {
      float xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      float yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      float zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

      pointInSphere = Calculator.add(sphereCenter, new Vertex(xVal, yVal, zVal));

      if (Calculator.computeDistance(sphereCenter, pointInSphere) <= systemRadius) {
        backup.setPositionX(pointInSphere.getPositionX());
        backup.setPositionY(pointInSphere.getPositionY());
        backup.setPositionZ(pointInSphere.getPositionZ());
        break;
      } else {
        if (i == 5) {
          pointInSphere = new Vertex(backup.getPositionX(), backup.getPositionY(), backup.getPositionZ());
        }
      }
    }

    return pointInSphere;
  }

  protected void enableMaterial() {
    gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, material.getDiffuse(), 0);
    gl.glMaterialfv(GL_FRONT, GL_SPECULAR, material.getSpecular(), 0);
    gl.glMaterialfv(GL_FRONT, GL_AMBIENT, material.getAmbient(), 0);
    gl.glMaterialfv(GL_FRONT, GL_SHININESS, material.getShine(), 0);
  }

  public void draw() {
    enableMaterial();
    texture.bind(gl);

    spawnParticles();
    Collections.sort(particles);

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

  public float getDirectionVectorScalar() {
    return directionVectorScalar;
  }

  public void setDirectionVectorScalar(float directionVectorScalar) {
    this.directionVectorScalar = directionVectorScalar;
  }
}
