
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;

public class FountainParticle extends Particle {

  private Trio gravityVector = new Trio(0.0f, -0.006f, 0.0f);

  public FountainParticle(GL2 gl, Trio position, Trio speed, Trio acceleration, Trio cameraPosition, double cameraAngle, Texture texture, float radius,
                          float fade) {
    super(gl, position, speed, acceleration, cameraPosition, cameraAngle, texture, radius, fade);
  }

  @Override
  public void move() {
    acceleration.add(gravityVector);
    super.move();
  }

  public Trio getGravityVector() {
    return gravityVector;
  }

  public void setGravityVector(Trio gravityVector) {
    this.gravityVector = gravityVector;
  }
}