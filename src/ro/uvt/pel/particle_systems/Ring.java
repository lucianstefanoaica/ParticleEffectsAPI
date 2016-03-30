package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class Ring extends ParticleSystem {

  private Vertex center;

  Ring(Vertex center, Texture texture, Material material, float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.center = center;
  }

  List<Vertex> generateSpeedVectors(float speedScalar, int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      list.add(Calculator.generateVertexOnCircle(0, speedScalar));
    }
    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      list.add(center);
    }
    return list;
  }

  Vertex getCenter() {
    return center;
  }

  void setCenter(Vertex center) {
    this.center = center;
  }
}
