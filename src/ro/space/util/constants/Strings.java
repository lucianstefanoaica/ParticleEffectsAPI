
package ro.space.util.constants;

public enum Strings {
  TITLE("F16 In Space");

  private final String value;

  private Strings(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}