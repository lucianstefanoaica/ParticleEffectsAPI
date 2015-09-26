
package ro.uvt.api.particles;

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
import java.util.ListIterator;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Calculator;
import ro.uvt.api.util.Observer;
import ro.uvt.api.util.Subject;

import com.jogamp.opengl.util.texture.Texture;

public abstract class ParticleSystem implements Observer {

  protected GL2 gl;
  protected List<Particle> particles = new ArrayList<>();
  protected Texture texture;
  protected Trio cameraPosition;
  protected double cameraAngle = 0.0f;
  protected int particlesPerSpawn = 350;
  protected Trio source;
  protected Trio destination;
  protected float systemRadius;
  protected float particleRadius = 0.08f;
  private Material material;
  protected float fadeUnit = 0.07f;

  protected ParticleSystem(GL2 gl, Trio[] positions, Texture texture, Material material, float systemRadius) {
    this.gl = gl;
    this.source = positions[0];
    this.destination = positions[1];
    this.cameraPosition = positions[2];
    this.texture = texture;
    this.material = material;
    this.systemRadius = systemRadius;
  }

  protected void enableMaterial() {
    gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, material.getKd(), 0);
    gl.glMaterialfv(GL_FRONT, GL_SPECULAR, material.getKs(), 0);
    gl.glMaterialfv(GL_FRONT, GL_AMBIENT, material.getKa(), 0);
    gl.glMaterialfv(GL_FRONT, GL_SHININESS, material.getNs(), 0);
  }

  protected abstract void spawnParticles();

  public void draw() {
    enableMaterial();
    texture.bind(gl);

    spawnParticles();
    Collections.sort(particles);

    gl.glEnable(GL_BLEND);

    ListIterator<Particle> index = particles.listIterator(particles.size());

    while (index.hasPrevious()) {

      Particle particle = (Particle) index.previous();

      particle.draw();

      particle.move();

      if (particle.died() == true) {
        index.remove();
      }
    }
  }

  @Override
  public void update(Subject toObserve) {
    HashMap<String, Object> subjectState = toObserve.getState();
    cameraPosition = (Trio) subjectState.get("camera_position");
    cameraAngle = (Double) subjectState.get("camera_angle");

    for (Particle particle : particles) {
      particle.setCameraAngle(cameraAngle);
      particle.setCameraPosition(cameraPosition);
    }
  }

  protected Trio generatePointInSphere(Trio sphereCenter, Trio backupPosition) {
    Trio pointInSphere = null;

    for (int i = 1; i <= 5; ++i) {
      double xVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      double yVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);
      double zVal = Calculator.getRandomNumberInRange(-systemRadius, systemRadius);

      pointInSphere = Calculator.add(sphereCenter, new Trio(xVal, yVal, zVal));

      if (Calculator.computeDistance(sphereCenter, pointInSphere) <= systemRadius) {
        backupPosition = new Trio(pointInSphere.getX(), pointInSphere.getY(), pointInSphere.getZ());
        break;
      } else {
        if (i == 5) {
          pointInSphere = new Trio(backupPosition.getX(), backupPosition.getY(), backupPosition.getZ());
        }
      }
    }

    return pointInSphere;
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

  public float getSystemRadius() {
    return systemRadius;
  }

  public void setSystemRadius(float systemRadius) {
    this.systemRadius = systemRadius;
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
}
