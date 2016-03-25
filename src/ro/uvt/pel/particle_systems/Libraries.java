package ro.uvt.pel.particle_systems;

public class Libraries {

  private Libraries() {}

  private static PEL pel;

  public static PEL getPEL() {
    if (pel == null) {
      pel = new DefaultPEL();
    }
    return pel;
  }
}
