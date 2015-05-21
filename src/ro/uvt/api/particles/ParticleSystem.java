
package ro.uvt.api.particles;

import static javax.media.opengl.GL.GL_BLEND;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Observer;
import ro.uvt.api.util.Subject;

public abstract class ParticleSystem implements Observer {

  protected List<Particle> particles = new ArrayList<>();

  protected GL2 gl;

  protected double cameraAngle;

  protected Trio cameraPosition;

  public ParticleSystem(Trio cameraPosition, double cameraAngle) {
    this.cameraPosition = cameraPosition;
    this.cameraAngle = cameraAngle;
  }

  public void draw() {
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

  protected abstract void spawnParticles();
}