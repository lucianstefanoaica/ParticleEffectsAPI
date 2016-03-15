package ro.uvt.pel.particle_systems;

import java.util.Random;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

public class Disk extends ParticleSystem {
  
  private Random rand;
  
  public Disk(GL2 gl, Vertex[] positions, Texture texture, Material material,
      float systemRadius) {
    super(gl, positions, texture, material, systemRadius);
    rand = new Random();
  }

  @Override
  protected void generateParticleDirectionVector() {
    startPosition = source.clone();
    speed = generatePointOnCircle();
  }

  private Vertex generatePointOnCircle() {
    float angle = rand.nextFloat() * 2 * (float) Math.PI;

    Vertex pointOnCircle = new Vertex((float) Math.cos(angle), 0.0f, (float) Math.sin(angle));

    return Calculator.scaleDown(pointOnCircle, systemRadius);
  }

}
