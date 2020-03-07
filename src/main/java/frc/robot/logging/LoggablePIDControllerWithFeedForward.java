package frc.robot.logging;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.controller.PIDController;

public class LoggablePIDControllerWithFeedForward extends PIDController {
  public double ff;

  public LoggablePIDControllerWithFeedForward(String name, double p, double i, double d, double ff) {
    super(p, i, d);
    this.ff = ff;

    pEntry = LoggerUtil.getEntry("pid." + name + ".p");
    iEntry = LoggerUtil.getEntry("pid." + name + ".i");
    dEntry = LoggerUtil.getEntry("pid." + name + ".d");
    ffEntry = LoggerUtil.getEntry("pid." + name + ".ff");

    if(pEntry.isPersistent()) {
      updateFromTelemetry();
    }
    else {
      saveToTelemetry();
    }
  }

  private NetworkTableEntry pEntry;
  private NetworkTableEntry iEntry;
  private NetworkTableEntry dEntry;
  private NetworkTableEntry ffEntry;

  public void updateFromTelemetry() {
    setP(pEntry.getDouble(0.0));
    setI(iEntry.getDouble(0.0));
    setD(dEntry.getDouble(0.0));
    ff = ffEntry.getDouble(0.0);
  }

  public void saveToTelemetry() {
    pEntry.setDouble(getP());
    iEntry.setDouble(getI());
    dEntry.setDouble(getD());
    ffEntry.setDouble(ff);
  }
}