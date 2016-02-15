
package ro.uvt.api.util;

public class MaterialProperties {

  private float ambient[];
  private float diffuse[];
  private float specular[];
  private float shine[];

  public MaterialProperties(float[] ambient, float[] diffuse, float[] specular, float[] shine) {
    this.ambient = ambient;
    this.diffuse = diffuse;
    this.specular = specular;
    this.shine = shine;
  }

  public void decreaseAlphaComponent(float fadeUnit) {
    ambient[3] -= fadeUnit;
    diffuse[3] -= fadeUnit;
    specular[3] -= fadeUnit;
  }

  public MaterialProperties clone() {
    return new MaterialProperties(ambient.clone(), diffuse.clone(), specular.clone(), shine.clone());
  }

  public float[] getAmbient() {
    return ambient;
  }

  public void setAmbient(float[] ambient) {
    this.ambient = ambient;
  }

  public float[] getDiffuse() {
    return diffuse;
  }

  public void setDiffuse(float[] diffuse) {
    this.diffuse = diffuse;
  }

  public float[] getSpecular() {
    return specular;
  }

  public void setSpecular(float[] specular) {
    this.specular = specular;
  }

  public float[] getShine() {
    return shine;
  }

  public void setShine(float[] shine) {
    this.shine = shine;
  }
}