package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.robot.RobotConfig.HangConfig;
import frc.robot.components.DriverJoystick.TriState;
import frc.robot.logging.LoggableRobotComponent;
import frc.robot.logging.LoggerUtil;

public class Hang extends LoggableRobotComponent {
  private VictorSPX hangDown1;
  private VictorSPX hangDown2;
  private VictorSPX hangUp;

  HangConfig config;

  public Hang(HangConfig config) {
    this.config = config;

    hangDown1 = new VictorSPX(config.leftHangDownControllerID);
    hangDown2 = new VictorSPX(config.rightHangDownControllerID);
    hangUp = new VictorSPX(config.hangUpControllerID);

    hangDown1.follow(hangDown2);

    hangDown2.setInverted(true);
  }

  public void driveUp(TriState state) {
    hangUp.set(ControlMode.PercentOutput, state.value);
  }

  public void driveDown(TriState state) {
    hangDown1.set(ControlMode.PercentOutput, state.value);
  }

  @Override
  public void log() {
    LoggerUtil.logVictorSPX("hang.down1", hangDown1);
    LoggerUtil.logVictorSPX("hang.down2", hangDown2);
  }
}