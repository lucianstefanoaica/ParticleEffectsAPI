package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class Atom extends ParticleSystem {

  private Vertex center;

  Atom(Vertex center, Texture texture, Material material, float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.center = center;
  }

  List<Vertex> generateSpeedVectors(float speedScalar, int count) {
    List<Vertex> list = new ArrayList<>();
    int segment = count * 3;
    for (int type = 0; type <= 2; ++type) {
      for (int i = 0; i < segment / 3; ++i) {
        list.add(Calculator.generateVertexOnCircle(type, speedScalar));
      }
    }

    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 0; i < count * 3; ++i) {
      list.add(center);
    }
    return list;
  }
}
