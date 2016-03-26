package ro.uvt.pel.particle_systems;

import java.util.ArrayList;
import java.util.List;

import com.jogamp.opengl.util.texture.Texture;

import ro.uvt.pel.util.Calculator;
import ro.uvt.pel.util.Material;
import ro.uvt.pel.util.Vertex;

class Line extends ParticleSystem {

  private Vertex left;
  private Vertex right;

  Line(Vertex left, Vertex right, Texture texture, Material material, float fadeQuotient) {
    super(texture, material, fadeQuotient);
    this.left = left;
    this.right = right;
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
    for (int i = 0; i < count; ++i) {
      list.add(Calculator.generateVertexOnLine(left, right));
    }
    return list;
  }
}
