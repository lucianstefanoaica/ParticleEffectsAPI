package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class Fire extends ParticleSystem {

  private Vertex center;
  private float radius;

  Fire(Vertex center, float radius, Texture texture, Material material, float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.center = center;
    this.radius = radius;
  }

  List<Vertex> generateSpeedVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      list.add(new Vertex());
    }
    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      list.add(Calculator.generateVertexInCircle(center, radius));
    }
    return list;
  }
}
