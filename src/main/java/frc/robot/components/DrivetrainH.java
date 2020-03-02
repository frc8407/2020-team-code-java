package frc.robot.components;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.RobotConfig.DrivetrainConfig;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class DrivetrainH extends LoggableRobotComponent {
  CANSparkMax controllerLeft1;
  CANSparkMax controllerLeft2;
  CANSparkMax controllerRight1;
  CANSparkMax controllerRight2;

  DrivetrainConfig config;

  private double _multiplier = 0.5;

  public DrivetrainH(DrivetrainConfig config) {
    this.config = config;
    controllerLeft1 = new CANSparkMax(config.leftController1ID, MotorType.kBrushless);
    controllerLeft2 = new CANSparkMax(config.leftController2ID, MotorType.kBrushless);
    controllerRight1 = new CANSparkMax(config.rightController1ID, MotorType.kBrushless);
    controllerRight2 = new CANSparkMax(config.rightController2ID, MotorType.kBrushless);
  
    controllerLeft2.setInverted(true);
    controllerRight2.setInverted(true);
  }

  private double deadband(double v) {
    if(Math.abs(v) < 0.05) return 0;
    return v;
  }

  public void drive(Vector2d leftStick, Vector2d rightStick, double yaw) {
    double ly = deadband(leftStick.y);
    double ry = deadband(rightStick.y);
    
    controllerLeft1.set(ly * _multiplier);
    controllerLeft2.set(ly * _multiplier);

    controllerRight1.set(-ry * _multiplier);
    controllerRight2.set(-ry * _multiplier);
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("drivetrain.leftMotor1Frame", controllerLeft1);
    LoggerUtil.logSparkMax("drivetrain.leftMotor2Frame", controllerLeft2);

    LoggerUtil.logSparkMax("drivetrain.rightMotor1Frame", controllerRight1);
    LoggerUtil.logSparkMax("drivetrain.rightMotor2Frame", controllerRight2);
    
    // LoggerUtil.logSparkMax("drivetrainLeft", controllerLeft);
    // LoggerUtil.logSparkMax("drivetrainRight", controllerRight);
  }
}