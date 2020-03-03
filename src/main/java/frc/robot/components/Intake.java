package frc.robot.components;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotConfig.IntakeConfig;
import frc.robot.components.DriverJoystick.TriState;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class Intake extends LoggableRobotComponent {
  private CANSparkMax intake;
  private CANSparkMax indexer;

  IntakeConfig config;

  public Intake(IntakeConfig config) {
    this.config = config;

    intake = new CANSparkMax(config.intakeControllerID, MotorType.kBrushless);
    indexer = new CANSparkMax(config.indexerControllerID, MotorType.kBrushless);
  
    indexer.setInverted(true);
  }

  private void driveIntake(double power) {
    intake.set(power * 0.75);
  }

  private void driveIndexer(boolean drive) {
    indexer.set(drive? 0.1 : 0.0);
  }
  
  public void drive(TriState intakeState, boolean driveIndexer, boolean isShooterReady) {
    driveIntake(intakeState.value);
    driveIndexer(driveIndexer && isShooterReady);
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("intake.indexerMotorFrame", indexer);
    LoggerUtil.logSparkMax("intake.intakeMotorFrame", intake);
  }
}