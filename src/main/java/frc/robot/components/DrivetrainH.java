package frc.robot.components;

import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
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

  private double _multiplier = 0.75;

  public DrivetrainH(DrivetrainConfig config) {
    this.config = config;
    controllerLeft1 = new CANSparkMax(config.leftController1ID, MotorType.kBrushless);
    controllerLeft2 = new CANSparkMax(config.leftController2ID, MotorType.kBrushless);
    controllerRight1 = new CANSparkMax(config.rightController1ID, MotorType.kBrushless);
    controllerRight2 = new CANSparkMax(config.rightController2ID, MotorType.kBrushless);
  
    controllerLeft2.follow(controllerLeft1, true);
    controllerRight2.follow(controllerRight1, true);

    controllerLeft1.setIdleMode(IdleMode.kBrake);
    controllerLeft2.setIdleMode(IdleMode.kBrake);
    controllerRight1.setIdleMode(IdleMode.kBrake);
    controllerRight2.setIdleMode(IdleMode.kBrake);

    controllerLeft1.setOpenLoopRampRate(0.15);
    controllerLeft2.setOpenLoopRampRate(0.15);
    controllerRight1.setOpenLoopRampRate(0.15);
    controllerRight2.setOpenLoopRampRate(0.15);

    controllerLeft1.enableVoltageCompensation(12.0);
    controllerLeft2.enableVoltageCompensation(12.0);
    controllerRight1.enableVoltageCompensation(12.0);
    controllerRight2.enableVoltageCompensation(12.0);
  }

  private double deadband(double v) {
    if(Math.abs(v) < 0.05) return 0;
    return v;
  }

  public void drive(Vector2d leftStick, Vector2d rightStick, double yaw) {
    double ly = deadband(leftStick.y);
    double ry = deadband(rightStick.y);
    
    controllerLeft1.set(ly * _multiplier);
    controllerRight1.set(-ry * _multiplier);
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("drivetrain.leftMotor1Frame", controllerLeft1);
    LoggerUtil.logSparkMax("drivetrain.leftMotor2Frame", controllerLeft2);

    LoggerUtil.logSparkMax("drivetrain.rightMotor1Frame", controllerRight1);
    LoggerUtil.logSparkMax("drivetrain.rightMotor2Frame", controllerRight2);
  }
}