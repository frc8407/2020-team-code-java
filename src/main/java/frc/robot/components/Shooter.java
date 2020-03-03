package frc.robot.components;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotConfig.ShooterConfig;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class Shooter extends LoggableRobotComponent {
  private CANSparkMax leftShooter;
  private CANSparkMax rightShooter;

  private CANEncoder leftEncoder;
  private CANEncoder rightEncoder;

  ShooterConfig config;

  public Shooter(ShooterConfig config) {
    this.config = config;

    leftShooter = new CANSparkMax(config.leftShooterControllerID, MotorType.kBrushless);
    rightShooter = new CANSparkMax(config.rightShooterControllerID, MotorType.kBrushless);

    leftShooter.setInverted(true);
    rightShooter.setInverted(false);

    leftShooter.enableVoltageCompensation(12.0);
    rightShooter.enableVoltageCompensation(12.0);

    leftEncoder = leftShooter.getEncoder();
    rightEncoder = rightShooter.getEncoder();
  }
  
  public void drive(double speed) {
    leftShooter.set(speed);
    rightShooter.set(speed);
  }

  public double getVelocity() {
    return (leftEncoder.getVelocity() + rightEncoder.getVelocity()) / 2.0;
  }

  public boolean isReadyToShoot() {
    return getVelocity() > 5300.0;
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("shooter.leftMotorFrame", leftShooter);
    LoggerUtil.logSparkMax("shooter.rightMotorFrame", rightShooter);
  }
}