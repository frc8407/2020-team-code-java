package frc.robot.math;

public class MathUtils {
  public static int sign(double number) {
    if(number > 0) return 1;
    else if(number < 0) return -1;
    return 0;
  }
}