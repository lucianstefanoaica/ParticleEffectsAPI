package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class ReversedCone extends ParticleSystem {

  private Vertex source;
  private Vertex destination;
  private float radius;

  ReversedCone(Vertex source, Vertex destination, float radius, Texture texture, Material material,
      float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.source = source;
    this.destination = destination;
    this.radius = radius;
  }

  List<Vertex> generateSpeedVectors(List<Vertex> sources, float speedScalar) {
    List<Vertex> list = new ArrayList<>();
    for (Vertex vertex : sources) {
      Vertex difference = Calculator.subtract(destination, vertex);
      list.add(Calculator.scaleDown(difference, speedScalar));
    }
    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      list.add(Calculator.generateVertexInSphere(source, radius));
    }
    return list;
  }
}
