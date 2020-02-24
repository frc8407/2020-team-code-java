package frc.robot.components;

import java.util.HashMap;
import java.util.Map;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.RobotConfig.DrivetrainConfig;
import frc.robot.logging.LoggableRobotComponent;

public class DrivetrainX extends LoggableRobotComponent {
  CANSparkMax controllerFrontLeft;
  CANSparkMax controllerFrontRight;
  CANSparkMax controllerBackLeft;
  CANSparkMax controllerBackRight;
  CANSparkMax controllerLeft;
  CANSparkMax controllerRight;

  DrivetrainConfig config;

  public DrivetrainX(DrivetrainConfig config) {
    this.config = config;
    controllerFrontLeft = new CANSparkMax(config.frontLeftControllerID, MotorType.kBrushless);
    controllerFrontRight = new CANSparkMax(config.frontRightControllerID, MotorType.kBrushless);
    controllerBackLeft = new CANSparkMax(config.backLeftControllerID, MotorType.kBrushless);
    controllerBackRight = new CANSparkMax(config.backRightControllerID, MotorType.kBrushless);
    
    controllerLeft = new CANSparkMax(config.leftControllerID, MotorType.kBrushless);
    controllerRight = new CANSparkMax(config.rightControllerID, MotorType.kBrushless);
  }

  private double deadband(double v) {
    if(Math.abs(v) < 0.05) return 0;
    return v;
  }
  private double returnIfClose(double v1, double v2) {
    if(Math.abs(v1 - v2) < 0.4 && v1 != 0 && v2 != 0) {
      return v1;
    }
    return v2;
  }
  public void drive(Vector2d leftStick, Vector2d rightStick, double yaw) {
    double lx = -deadband(leftStick.x);
    double ly = deadband(leftStick.y);
    double rx = -returnIfClose(lx, deadband(rightStick.x));
    double ry = returnIfClose(ly, deadband(rightStick.y));
    
    controllerFrontRight.set(rx + ry);
    controllerBackRight.set(-(rx - ry));
    controllerFrontLeft.set(lx - ly);
    controllerBackLeft.set(-(lx + ly));

    controllerLeft.set(ly);
    controllerRight.set(-ry);
  }

  @Override
  public String getComponentName() {
    return "Drivetrain";
  }

  @Override
  public Map<String, Double> getLogs() {
    Map<String, Double> info = new HashMap<>();

    info.put("controllerFrontLeft.velocity", controllerFrontLeft.getEncoder().getVelocity());
    info.put("controllerFrontLeft.position", controllerFrontLeft.getEncoder().getPosition());
    
    info.put("controllerFrontRight.velocity", controllerFrontRight.getEncoder().getVelocity());
    info.put("controllerFrontRight.position", controllerFrontRight.getEncoder().getPosition());
    
    info.put("controllerBackLeft.velocity", controllerBackLeft.getEncoder().getVelocity());
    info.put("controllerBackLeft.position", controllerBackLeft.getEncoder().getPosition());
    
    info.put("controllerBackRight.velocity", controllerBackRight.getEncoder().getVelocity());
    info.put("controllerBackRight.position", controllerBackRight.getEncoder().getPosition());

    info.put("controllerLeft.velocity", controllerLeft.getEncoder().getVelocity());
    info.put("controllerLeft.position", controllerLeft.getEncoder().getPosition());
    
    info.put("controllerRight.velocity", controllerRight.getEncoder().getVelocity());
    info.put("controllerRight.position", controllerRight.getEncoder().getPosition());

    return info;
  }
}