package frc.robot.components;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.logging.LoggablePIDControllerWithFeedForward;

public class LimelightAimController {
  private LoggablePIDControllerWithFeedForward turnPID;
  private DrivetrainH drivetrain;

  private NetworkTable limelightTable;
  private NetworkTableEntry tx;
  // private NetworkTableEntry ty;
  // private NetworkTableEntry ta;

  public LimelightAimController(DrivetrainH drivetrain) {
    turnPID = new LoggablePIDControllerWithFeedForward("limelightTurn", 0.05, 0.0, 0.2, 0.0);

    limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    tx = limelightTable.getEntry("tx");

    this.drivetrain = drivetrain;
  }

  public double calculateTurnRate() {
    double dx = tx.getDouble(0.0);

    return turnPID.calculate(dx) + turnPID.ff;
  }

  public void driveTurn() {
    double turnRate = calculateTurnRate();
    drivetrain.drive(turnRate, -turnRate);
  }

  public void tryUpdate() {
    turnPID.updateFromTelemetry();
  }
}