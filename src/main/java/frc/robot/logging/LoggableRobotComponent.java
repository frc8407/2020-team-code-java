package frc.robot.logging;

import java.util.Map;

public abstract class LoggableRobotComponent {
    public abstract String getComponentName();
    public abstract Map<String, Double> getLogs();
}