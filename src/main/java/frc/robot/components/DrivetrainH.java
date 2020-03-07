package frc.robot.components;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotConfig.DrivetrainConfig;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class DrivetrainH extends LoggableRobotComponent {
  private CANSparkMax controllerLeft1;
  private CANSparkMax controllerLeft2;
  private CANSparkMax controllerRight1;
  private CANSparkMax controllerRight2;

  public DrivetrainAutonomous autonomous;

  DrivetrainConfig config;

  private double _multiplier = 0.5;
  private double _turnMultiplier = 0.25; 

  public DrivetrainH(DrivetrainConfig config) {
    this.config = config;
    controllerLeft1 = new CANSparkMax(config.leftController1ID, MotorType.kBrushless);
    controllerLeft2 = new CANSparkMax(config.leftController2ID, MotorType.kBrushless);
    controllerRight1 = new CANSparkMax(config.rightController1ID, MotorType.kBrushless);
    controllerRight2 = new CANSparkMax(config.rightController2ID, MotorType.kBrushless);
  
    controllerRight1.setInverted(true);

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

    this.autonomous = new DrivetrainAutonomous(controllerLeft1, controllerLeft2, controllerRight1, controllerRight2);
  }

  private double deadband(double v) {
    if(Math.abs(v) < 0.05) return 0;
    return v;
  }

  public void drive(double leftY, double rightY) {
    double ly = deadband(leftY);
    double ry = deadband(rightY);

    double _mult = _multiplier;
    if(ly * ry < 0) {
      _mult = _turnMultiplier;
    }
    
    controllerLeft1.set(ly * _mult);
    controllerRight1.set(ry * _mult);
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("drivetrain.leftMotor1Frame", controllerLeft1);
    LoggerUtil.logSparkMax("drivetrain.leftMotor2Frame", controllerLeft2);

    LoggerUtil.logSparkMax("drivetrain.rightMotor1Frame", controllerRight1);
    LoggerUtil.logSparkMax("drivetrain.rightMotor2Frame", controllerRight2);
    
    autonomous.log();
  }
}