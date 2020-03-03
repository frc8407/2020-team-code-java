package frc.robot.components;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class DrivetrainAutonomous extends LoggableRobotComponent {
  private final CANSparkMax controllerLeft1;
  private final CANSparkMax controllerRight1;

  private final CANEncoder encoderLeft1;
  private final CANEncoder encoderLeft2;
  private final CANEncoder encoderRight1;
  private final CANEncoder encoderRight2;

  final private double circumference = Math.PI * (6 * 2.54);
  final private double speedCap = 0.3;

  public DrivetrainAutonomous(final CANSparkMax controllerLeft1, final CANSparkMax controllerLeft2, final CANSparkMax controllerRight1, final CANSparkMax controllerRight2) {
    this.controllerLeft1 = controllerLeft1;
    this.controllerRight1 = controllerRight1;
  
    this.encoderLeft1 = controllerLeft1.getEncoder();
    this.encoderLeft2 = controllerLeft2.getEncoder();
    this.encoderRight1 = controllerRight1.getEncoder();
    this.encoderRight2 = controllerRight2.getEncoder();
  }

  public double getLeftPosition() {
    return (encoderLeft1.getPosition() + encoderLeft2.getPosition()) / 2.0;
  }

  public double getLeftVelocity() {
    return (encoderLeft1.getVelocity() + encoderLeft2.getVelocity()) / 2.0;
  }

  public double getRightPosition() {
    return (encoderRight1.getPosition() + encoderRight2.getPosition()) / 2.0;
  }

  public double getRightVelocity() {
    return (encoderRight1.getVelocity() + encoderRight2.getVelocity()) / 2.0;
  }

  public void reset() {
    encoderLeft1.setPosition(0.0);
    encoderLeft2.setPosition(0.0);
    encoderRight1.setPosition(0.0);
    encoderRight2.setPosition(0.0);
  }

  public void driveForward(final double distance) {
    final double rotations = distance / circumference;
    reset();

    while(true) {
      double errLeft = getLeftPosition() - rotations;
      double errRight = getRightPosition() - rotations;

      if(Math.abs(errLeft) < 0.5 && Math.abs(errRight) < 0.5) {
        break;
      }
      
      double velLeft = errLeft > 0? speedCap : -speedCap;
      double velRight = errRight > 0? speedCap : -speedCap;

      controllerLeft1.set(velLeft);
      controllerRight1.set(velRight);
    }

    controllerLeft1.set(0.0);
    controllerRight1.set(0.0);

    return;
  }

  @Override
  public void log() {
    LoggerUtil.logNumber("drivetrainAuto.left.velocity", getLeftVelocity());
    LoggerUtil.logNumber("drivetrainAuto.left.position", getLeftPosition());

    LoggerUtil.logNumber("drivetrainAuto.right.velocity", getRightVelocity());
    LoggerUtil.logNumber("drivetrainAuto.right.position", getRightPosition());
  }
}