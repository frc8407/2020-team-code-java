package frc.robot.components;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotConfig.ShooterConfig;
import frc.robot.components.DriverJoystick.TriState;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class Shooter extends LoggableRobotComponent {
  private CANSparkMax leftShooter;
  private CANSparkMax rightShooter;

  ShooterConfig config;

  public Shooter(ShooterConfig config) {
    this.config = config;

    leftShooter = new CANSparkMax(config.leftShooterControllerID, MotorType.kBrushless);
    rightShooter = new CANSparkMax(config.rightShooterControllerID, MotorType.kBrushless);

    leftShooter.setInverted(true);
    rightShooter.setInverted(false);
  }
  
  public void drive(TriState triState) {
    leftShooter.set(triState.value);
    rightShooter.set(triState.value);
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("shooter.leftMotorFrame", leftShooter);
    LoggerUtil.logSparkMax("shooter.rightMotorFrame", rightShooter);
  }
}