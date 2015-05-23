
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
import java.util.Map;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.api.util.Observer;
import ro.uvt.api.util.Subject;

public abstract class ParticleSystem implements Observer {

  protected GL2 gl;
  protected List<Particle> particles = new ArrayList<>();
  protected Texture texture;
  protected Trio cameraPosition;
  protected double cameraAngle = 0.0f;
  protected int particlesPerSpawn = 350;
  protected Trio source;
  protected Trio destination;
  protected float systemRadius = 3.0f;
  protected float particleRadius = 0.08f;
  private Map<String, float[]> materialProperties = new HashMap<String, float[]>();

  protected ParticleSystem(GL2 gl, Trio source, Trio destination, Trio cameraPosition, Texture texture) {
    this.gl = gl;
    this.source = source;
    this.destination = destination;
    this.cameraPosition = cameraPosition;
    this.texture = texture;

    float[] ambient = {0.0f, 0.2f, 0.3f};
    float[] diffuse = {0.0f, 0.2f, 0.3f};
    float[] specular = {0.0f, 0.2f, 0.3f};
    float[] shine = {120.078431f};

    materialProperties.put("ambient", ambient);
    materialProperties.put("diffuse", diffuse);
    materialProperties.put("specular", specular);
    materialProperties.put("shine", shine);
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

  private void enableMaterial() {
    gl.glMaterialfv(GL_FRONT, GL_AMBIENT, materialProperties.get("ambient"), 0);
    gl.glMaterialfv(GL_FRONT, GL_DIFFUSE, materialProperties.get("diffuse"), 0);
    gl.glMaterialfv(GL_FRONT, GL_SPECULAR, materialProperties.get("specular"), 0);
    gl.glMaterialfv(GL_FRONT, GL_SHININESS, materialProperties.get("shine"), 0);
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

  public Map<String, float[]> getMaterialProperties() {
    return materialProperties;
  }

  public void setMaterialProperties(Map<String, float[]> materialProperties) {
    this.materialProperties = materialProperties;
  }
}