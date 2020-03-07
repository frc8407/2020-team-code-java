package frc.robot.logging;

public class PIDTelemetryUtil {
  public static class PIDData {
    double p;
    double i;
    double d;
    double ff;

    public PIDData(double p, double i, double d, double ff) {
      this.p = p;
      this.i = i;
      this.d = d;
      this.ff = ff;
    }
  }

  public static PIDData getPIDDataFromTelemetry(String name) {
    double p = LoggerUtil.getEntry(name + ".p").getValue().getDouble();
    double i = LoggerUtil.getEntry(name + ".i").getValue().getDouble();
    double d = LoggerUtil.getEntry(name + ".d").getValue().getDouble();
    double ff = LoggerUtil.getEntry(name + ".ff").getValue().getDouble();

    return new PIDData(p, i, d, ff);
  }
}
