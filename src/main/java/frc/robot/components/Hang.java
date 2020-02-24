package frc.robot.components;

import java.util.HashMap;
import java.util.Map;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotConfig.HangConfig;
import frc.robot.logging.LoggableRobotComponent;

public class Hang extends LoggableRobotComponent {
  // private VictorSPX hangDown1;
  // private VictorSPX hangDown2;
  private CANSparkMax hangDown1;
  private CANSparkMax hangDown2;
  private VictorSPX hangUp;

  HangConfig config;

  public Hang(HangConfig config) {
    this.config = config;

    hangDown1 = new CANSparkMax(config.leftHangDownControllerID, MotorType.kBrushless);
    hangDown2 = new CANSparkMax(config.rightHangDownControllerID, MotorType.kBrushless);
    hangUp = new VictorSPX(config.hangUpControllerID);

    hangDown2.setInverted(true);
  }

  public void driveUp(int direction) {
    if(direction == 1) {
      hangUp.set(ControlMode.PercentOutput, -0.75);
    }
    else if(direction == -1) {
      hangUp.set(ControlMode.PercentOutput, 0.75);
    }
    else {
      hangUp.set(ControlMode.PercentOutput, 0.0);
    }
  }

  public void driveDown(int direction) {
    if(direction == 1) {
      hangDown1.set(0.25);
      hangDown2.set(-0.25);
    }
    else if(direction == -1) {
      hangDown1.set(-1.0);
      hangDown2.set(1.0);
    }
    else {
      hangDown1.set(0.0);
      hangDown2.set(0.0);
    }
  }

  @Override
  public String getComponentName() {
    return "Hang";
  }

  @Override
  public Map<String, Double> getLogs() {
    Map<String, Double> info = new HashMap<>();

    return info;
  }
}