package frc.robot;

public class RobotConfig {
  public class DrivetrainConfig {
    public final int frontLeftControllerID = 6;
    public final int frontRightControllerID = 2;

    public final int backLeftControllerID = 3;
    public final int backRightControllerID = 4;

    public final int leftControllerID = 5;
    public final int rightControllerID = 1;
  }

  public class IntakeConfig {
    public final int outerIntakeControllerID = 10;
    public final int innerIntakeControllerID = 11;
    public final int indexerControllerID = 12;
  }

  public class ShooterConfig {
    public final int leftShooterControllerID = -1;
    public final int rightShooterControllerID = -1;
  }

  public class HangConfig {
    public final int leftHangDownControllerID = 7;
    public final int rightHangDownControllerID = 8;
    public final int hangUpControllerID = 9;
  }

  public final DrivetrainConfig drivetrainConfig = new DrivetrainConfig();
  public final IntakeConfig intakeConfig = new IntakeConfig();
  public final ShooterConfig shooterConfig = new ShooterConfig();
  public final HangConfig hangConfig = new HangConfig();
}