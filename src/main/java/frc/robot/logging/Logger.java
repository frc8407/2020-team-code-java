package frc.robot.logging;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logger {
  Map<String, Double> logs;

  public Logger() {
    open();
  }

  public void open() {
    logs = new HashMap<>();
  }

  public void put(LoggableRobotComponent component) {
    String componentName = component.getComponentName();
    Map<String, Double> componentLogs = component.getLogs();

    logs.putAll(componentLogs.entrySet().stream()
        .collect(Collectors.toMap(entry -> componentName + " | " + entry.getKey(), Map.Entry::getValue)));
  }

  public void send() {
    logs.forEach((k, v) -> {
      SmartDashboard.putNumber(k, v);
    });

    clear();
  }

  public void clear() {
    logs.clear();
  }
}