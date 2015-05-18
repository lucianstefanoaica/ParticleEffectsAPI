
package ro.space.util.algebra;

import java.util.Random;

import ro.space.display.particles.Trio;

public class Calculator {

  public static Trio add(Trio first, Trio second) {
    float a = first.getX() + second.getX();
    float b = first.getY() + second.getY();
    float c = first.getZ() + second.getZ();

    return new Trio(a, b, c);
  }

  public static Trio subtract(Trio first, Trio second) {
    float a = first.getX() - second.getX();
    float b = first.getY() - second.getY();
    float c = first.getZ() - second.getZ();

    return new Trio(a, b, c);
  }

  public static Trio normalize(Trio vector) {

    float x = vector.getX();
    float y = vector.getY();
    float z = vector.getZ();

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

    return new Trio(a, b, c);
  }

  public static Trio cross(Trio first, Trio second) {
    float a1 = first.getX();
    float a2 = first.getY();
    float a3 = first.getZ();

    float b1 = second.getX();
    float b2 = second.getY();
    float b3 = second.getZ();

    float a = a2 * b3 - a3 * b2;

    float b = a3 * b1 - a1 * b3;

    float c = a1 * b2 - a2 * b1;

    return new Trio(a, b, c);
  }

  public static Trio scale(Trio vector, int scale) {
    float a = vector.getX() * scale;
    float b = vector.getY() * scale;
    float c = vector.getZ() * scale;

    return new Trio(a, b, c);
  }

  public static double computeDistance(Trio first, Trio second) {

    double x1 = first.getX();
    double y1 = first.getY();
    double z1 = first.getZ();

    double x2 = second.getX();
    double y2 = second.getY();
    double z2 = second.getZ();

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
  public static double computePointPlaneDistance(Trio planePointVector, Trio planeNormal) {
    double xPQ = planePointVector.getX();
    double yPQ = planePointVector.getY();
    double zPQ = planePointVector.getZ();

    double xN = planeNormal.getX();
    double yN = planeNormal.getY();
    double zN = planeNormal.getZ();

    double xSquared = Math.pow(xN, 2);
    double ySquared = Math.pow(yN, 2);
    double zSquared = Math.pow(zN, 2);

    double denominator = (xPQ * xN) + (yPQ * yN) + (zPQ * zN);
    double divisor = Math.sqrt(xSquared + ySquared + zSquared);

    return denominator / divisor;
  }

  public static double getRandomNumberInRange(double min, double max) {
    double range = max - min;

    Random gen = new Random();

    double scaledNumber = gen.nextDouble() * range;

    return scaledNumber + min;
  }
  
  public static Trio makeItSmaller(Trio what, float denominator) {
    float xVal = what.getX() / denominator;
    float yVal = what.getY() / denominator;
    float zVal = what.getZ() / denominator;
    
    return new Trio(xVal, yVal, zVal);
  }
}