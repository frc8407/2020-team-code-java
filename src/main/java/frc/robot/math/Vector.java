package frc.robot.math;

import edu.wpi.first.wpilibj.drive.Vector2d;

public class Vector {
    public static String vector2dToString(Vector2d vector) {
        return "(" + Double.toString(vector.x) + ", " + Double.toString(vector.y) + ")";
    }
}