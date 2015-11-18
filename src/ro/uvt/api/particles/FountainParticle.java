
package ro.uvt.api.particles;

import javax.media.opengl.GL2;

import ro.uvt.api.util.Vertex;

import com.jogamp.opengl.util.texture.Texture;

public class FountainParticle extends Particle {

  private Vertex gravityVector = new Vertex(0.0f, -0.006f, 0.0f);

  public FountainParticle(GL2 gl, Vertex position, Vertex speed, Vertex acceleration, Vertex cameraPosition, double cameraAngle, Texture texture, float radius,
                          float fade) {
    super(gl, position, speed, acceleration, cameraPosition, cameraAngle, texture, radius, fade);
  }

  @Override
  public void move() {
    acceleration.add(gravityVector);
    super.move();
  }

  public Vertex getGravityVector() {
    return gravityVector;
  }

  public void setGravityVector(Vertex gravityVector) {
    this.gravityVector = gravityVector;
  }
}