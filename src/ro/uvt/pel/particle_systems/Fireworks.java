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

  List<Vertex> generateSpeedVectors(List<Vertex> vertices, float speedScalar) {
    List<Vertex> list = new ArrayList<>();
    for (Vertex vertex : vertices) {
      Vertex vertexInSphere = Calculator.generateVertexInSphere(vertex, 1.0f);
      Vertex difference = Calculator.subtract(vertexInSphere, vertex);
      list.add(Calculator.scaleUp(difference, speedScalar));
    }
    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (Vertex source : sources) {
      for (int i = 1; i <= count; ++i) {
        list.add(source);
      }
    }
    return list;
  }

  List<Vertex> getSources() {
    return sources;
  }

  void setSources(List<Vertex> sources) {
    this.sources = sources;
  }
}
