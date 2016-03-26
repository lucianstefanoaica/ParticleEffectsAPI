package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class Cone extends ParticleSystem {

  private Vertex source;
  private Vertex destination;
  private float radius;

  Cone(Vertex source, Vertex destination, float radius, Texture texture, Material material,
      float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.source = source;
    this.destination = destination;
    this.radius = radius;
  }

  List<Vertex> generateSpeedVectors(float speedScalar, int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      Vertex pointInSphere = Calculator.generateVertexInSphere(destination, radius);
      Vertex movementVector = Calculator.subtract(pointInSphere, source);
      list.add(Calculator.scaleUp(movementVector, speedScalar));
    }
    return list;
  }

  List<Vertex> generatePositionVectors(int count) {
    List<Vertex> list = new ArrayList<>();
    for (int i = 1; i <= count; ++i) {
      list.add(source);
    }
    return list;
  }
}
