
package ro.space.display.particles;

public class Trio {

  private float x;
  private float y;
  private float z;

  public Trio(float x, float y, float z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public Trio(double x, double y, double z) {
    this.x = (float) x;
    this.y = (float) y;
    this.z = (float) z;
  }

  public Trio add(Trio vector) {
    x += vector.getX();
    y += vector.getY();
    z += vector.getZ();

    return this;
  }

  public float getX() {
    return x;
  }

  public void setX(float x) {
    this.x = x;
  }

  public float getY() {
    return y;
  }

  public void setY(float y) {
    this.y = y;
  }

  public float getZ() {
    return z;
  }

  public void setZ(float z) {
    this.z = z;
  }

  @Override
  public String toString() {
    return "Trio [x=" + x + ", y=" + y + ", z=" + z + "]";
  }

  public Trio clone() {
    return new Trio(x, y, z);
  }
}