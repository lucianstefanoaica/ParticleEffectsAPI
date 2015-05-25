
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;

public class FountainParticle extends Particle {

  private Trio gravityForce = new Trio(0.0f, -0.01f, 0.0f);

  public FountainParticle(GL2 gl, Trio position, Trio speed, Trio acceleration, Trio cameraPosition, double cameraAngle, Texture texture, float radius,
                          float fade) {
    super(gl, position, speed, acceleration, cameraPosition, cameraAngle, texture, radius, fade);
  }

  @Override
  public void move() {
    acceleration.add(gravityForce);
    super.move();
  }
}