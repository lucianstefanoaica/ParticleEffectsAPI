
package ro.uvt.api.util;

import java.util.Random;

public class Calculator {

  public static Vertex add(Vertex first, Vertex second) {
    float a = first.getPositionX() + second.getPositionX();
    float b = first.getPositionY() + second.getPositionY();
    float c = first.getPositionZ() + second.getPositionZ();

    return new Vertex(a, b, c);
  }

  public static Vertex subtract(Vertex first, Vertex second) {
    float a = first.getPositionX() - second.getPositionX();
    float b = first.getPositionY() - second.getPositionY();
    float c = first.getPositionZ() - second.getPositionZ();

    return new Vertex(a, b, c);
  }

  public static Vertex normalize(Vertex vector) {

    float x = vector.getPositionX();
    float y = vector.getPositionY();
    float z = vector.getPositionZ();

    double xSquared = Math.pow(x, 2);
    double ySquared = Math.pow(y, 2);
    double zSquared = Math.pow(z, 2);

    double magnitude = Math.sqrt(xSquared + ySquared + zSquared);

    if (magnitude == 0.0f) {
      magnitude = 1.0f;
    }

    float a = x / (float) magnitude;
    float b = y / (float) magnitude;
    float c = z / (float) magnitude;

    return new Vertex(a, b, c);
  }

  public static Vertex cross(Vertex first, Vertex second) {
    float a1 = first.getPositionX();
    float a2 = first.getPositionY();
    float a3 = first.getPositionZ();

    float b1 = second.getPositionX();
    float b2 = second.getPositionY();
    float b3 = second.getPositionZ();

    float a = a2 * b3 - a3 * b2;

    float b = a3 * b1 - a1 * b3;

    float c = a1 * b2 - a2 * b1;

    return new Vertex(a, b, c);
  }

  public static Vertex scale(Vertex vector, int scale) {
    float a = vector.getPositionX() * scale;
    float b = vector.getPositionY() * scale;
    float c = vector.getPositionZ() * scale;

    return new Vertex(a, b, c);
  }

  public static double computeDistance(Vertex first, Vertex second) {

    double x1 = first.getPositionX();
    double y1 = first.getPositionY();
    double z1 = first.getPositionZ();

    double x2 = second.getPositionX();
    double y2 = second.getPositionY();
    double z2 = second.getPositionZ();

    double firstTerm = Math.pow(x2 - x1, 2);
    double secondTerm = Math.pow(y2 - y1, 2);
    double thirdTerm = Math.pow(z2 - z1, 2);

    return Math.sqrt(firstTerm + secondTerm + thirdTerm);
  }

  /**
   * @param planePointVector a vector from a point P which is on the plane to a point Q which is not
   * on the plane I AM NOT SHURE I NEED THIS FUNCTION ANYMORE...
   * @param planeNormal a vector which represents the normal of the plane
   * @return the distance from the plane to the point Q
   */
  public static double computePointPlaneDistance(Vertex planePointVector, Vertex planeNormal) {
    double xPQ = planePointVector.getPositionX();
    double yPQ = planePointVector.getPositionY();
    double zPQ = planePointVector.getPositionZ();

    double xN = planeNormal.getPositionX();
    double yN = planeNormal.getPositionY();
    double zN = planeNormal.getPositionZ();

    double xSquared = Math.pow(xN, 2);
    double ySquared = Math.pow(yN, 2);
    double zSquared = Math.pow(zN, 2);

    double denominator = (xPQ * xN) + (yPQ * yN) + (zPQ * zN);
    double divisor = Math.sqrt(xSquared + ySquared + zSquared);

    return denominator / divisor;
  }

  public static float getRandomNumberInRange(float min, float max) {
    float range = max - min;

    Random gen = new Random();

    float scaledNumber = (float) gen.nextDouble() * range;

    return scaledNumber + min;
  }

  public static Vertex makeItSmaller(Vertex what, float denominator) {
    float xVal = what.getPositionX() / denominator;
    float yVal = what.getPositionY() / denominator;
    float zVal = what.getPositionZ() / denominator;

    return new Vertex(xVal, yVal, zVal);
  }
}