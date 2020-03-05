package frc.robot;

public class RobotConfig {
  public class DrivetrainConfig {
    public final int leftController1ID = 1;
    public final int leftController2ID = 2;

    public final int rightController1ID = 3;
    public final int rightController2ID = 4;
  }

  public class IntakeConfig {
    public final int intakeControllerID = 5;
    public final int indexerControllerID = 6;
  }

  public class ShooterConfig {
    public final int leftShooterControllerID = 7;
    public final int rightShooterControllerID = 8;
  }

  public class HangConfig {
    public final int leftHangDownControllerID = 9;
    public final int rightHangDownControllerID = 10;
    public final int hangUpControllerID = 11;
  }

  public final DrivetrainConfig drivetrainConfig = new DrivetrainConfig();
  public final IntakeConfig intakeConfig = new IntakeConfig();
  public final ShooterConfig shooterConfig = new ShooterConfig();
  public final HangConfig hangConfig = new HangConfig();
}