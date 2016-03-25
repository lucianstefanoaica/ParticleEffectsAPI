package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class Fireworks extends ParticleSystem {

  private List<Vertex> sources;

  Fireworks(List<Vertex> sources, Texture texture, Material material, float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.sources = sources;
  }

  List<Vertex> generateSpeedVectors(float speedScalar, List<Vertex> vertices) {
    List<Vertex> list = new ArrayList<>();
    for (Vertex vertex : vertices) {
      Vertex vertexInSphere = Calculator.generateVertexInSphere(vertex, speedScalar);
      Vertex difference = Calculator.subtract(vertexInSphere, vertex);
      list.add(Calculator.scaleDown(difference, speedScalar));
    }
    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (Vertex source : sources) {
      for (int i = 0; i < count; ++i) {
        list.add(source);
      }
    }
    return list;
  }
}
