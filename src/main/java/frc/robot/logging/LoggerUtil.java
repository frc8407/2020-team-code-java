package frc.robot.logging;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LoggerUtil {
  private static NetworkTable table;
  public static void init() {
    table = NetworkTableInstance.getDefault().getTable("dashboard");
  }

  public static void logSparkMax(String name, CANSparkMax controller) {
    table.getEntry(name + ".temperature").forceSetDouble(controller.getMotorTemperature());
    table.getEntry(name + ".velocity").forceSetDouble(controller.getEncoder().getVelocity());
    table.getEntry(name + ".position").forceSetDouble(controller.getEncoder().getPosition());
    table.getEntry(name + ".voltage").forceSetDouble(controller.getBusVoltage());
    table.getEntry(name + ".current").forceSetDouble(controller.getOutputCurrent());
    table.getEntry(name + ".output").forceSetDouble(controller.getAppliedOutput());
    table.getEntry(name + ".openLoopRampRate").forceSetDouble(controller.getOpenLoopRampRate());
    table.getEntry(name + ".closedLoopRampRate").forceSetDouble(controller.getClosedLoopRampRate());
    table.getEntry(name + ".isInverted").forceSetBoolean(controller.getInverted());
  }
  
  public static void logVictorSPX(String name, VictorSPX controller) {
    table.getEntry(name + ".voltage").forceSetDouble(controller.getBusVoltage());
    table.getEntry(name + ".outputVoltage").forceSetDouble(controller.getMotorOutputVoltage());
    table.getEntry(name + ".outputPercentage").forceSetDouble(controller.getMotorOutputPercent());
    table.getEntry(name + ".temperature").forceSetDouble(controller.getTemperature());
    table.getEntry(name + ".isInverted").forceSetBoolean(controller.getInverted());
  }

  public static NetworkTableEntry getEntry(String name) {
    return table.getEntry(name);
  }
}