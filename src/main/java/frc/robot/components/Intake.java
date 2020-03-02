package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
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
    intake.set(power * 1.0);
  }

  private void driveIndexer(double power) {
    indexer.set(power * 0.25);
  }
  
  public void drive(TriState intakeState, TriState indexerState) {
    driveIntake(intakeState.value);
    driveIndexer(indexerState.value);
  }

  @Override
  public void log() {
    LoggerUtil.logSparkMax("intake.indexerMotorFrame", indexer);
    LoggerUtil.logSparkMax("intake.intakeMotorFrame", intake);
  }
}