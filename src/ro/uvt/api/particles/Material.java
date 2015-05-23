
package ro.uvt.api.particles;

public class Material {

  private float ka[];
  private float kd[];
  private float ks[];
  private float ns[];

  public Material(float[] ka, float[] kd, float[] ks, float[] ns) {
    this.ka = ka;
    this.kd = kd;
    this.ks = ks;
    this.ns = ns;
  }

  public float[] getKa() {
    return ka;
  }

  public float[] getKd() {
    return kd;
  }

  public float[] getKs() {
    return ks;
  }

  public float[] getNs() {
    return ns;
  }
}