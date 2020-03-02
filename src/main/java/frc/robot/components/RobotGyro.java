package frc.robot.components;

import com.analog.adis16448.frc.ADIS16448_IMU;

import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

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
  public void log() {
    LoggerUtil.getEntry("gyro.yaw").forceSetDouble(getYaw());
  }
}