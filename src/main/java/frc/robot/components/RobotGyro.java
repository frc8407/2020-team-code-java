package frc.robot.components;

import java.util.HashMap;
import java.util.Map;

import com.analog.adis16448.frc.ADIS16448_IMU;

import frc.robot.logging.LoggableRobotComponent;

public class RobotGyro extends LoggableRobotComponent {
  private ADIS16448_IMU imu;

  public RobotGyro() {
    imu = new ADIS16448_IMU();
  }

  public void calibrate() {
    imu.calibrate();
  }

  public double getYaw() {
    return imu.getAngle();
  }

  @Override
  public String getComponentName() {
    return "Gyro";
  }

  @Override
  public Map<String, Double> getLogs() {
    Map<String, Double> info = new HashMap<>();
    
    info.put("yaw", getYaw());

    return info;
  }
}