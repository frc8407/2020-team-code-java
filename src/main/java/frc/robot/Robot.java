/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.components.DriverJoystick;
import frc.robot.components.DrivetrainH;
import frc.robot.components.Hang;
import frc.robot.components.Intake;
import frc.robot.components.RobotGyro;
import frc.robot.components.Shooter;
import frc.robot.logging.LoggerUtil;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final DriverJoystick joystickMain = new DriverJoystick(0, DriverJoystick.defaultLogitechConfig);

  private final RobotConfig config = new RobotConfig();
  private final DrivetrainH drivetrain = new DrivetrainH(config.drivetrainConfig);
  private final Intake intake = new Intake(config.intakeConfig);
  private final Shooter shooter = new Shooter(config.shooterConfig);
  private final RobotGyro gyro = new RobotGyro();
  private final Hang hang = new Hang(config.hangConfig);

  @Override
  public void robotInit() {
    LoggerUtil.init();
  }

  @Override
  public void robotPeriodic() {
    log();
  }

  @Override
  public void autonomousInit() {
  }

  @Override
  public void autonomousPeriodic() {
  }

  NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
  NetworkTableEntry tx = limelightTable.getEntry("tx");
  NetworkTableEntry ty = limelightTable.getEntry("ty");
  NetworkTableEntry ta = limelightTable.getEntry("ta");

  int sign(double a) {
    if(a > 0) return 1;
    else return -1;
  }
  void aimLimelight() {
    double x = tx.getDouble(0.0);
    double area = ta.getDouble(0.0);

    double vel = x * 0.0105;
    vel += sign(vel) * 0.025;

    if(vel > 0 && vel > 0.1) vel = 0.1;
    else if(vel < 0 && vel < -0.1) vel = -0.1;


    drivetrain.drive(new Vector2d(0, vel), new Vector2d(0, -vel), 0.0);
  }

  @Override
  public void teleopPeriodic() {
    Vector2d leftStick = joystickMain.getLeftStick();
    Vector2d rightStick = joystickMain.getRightStick();
    double yaw = gyro.getYaw();

    if (joystickMain.getRB2()) {
      aimLimelight();
    } else {
      drivetrain.drive(leftStick, rightStick, yaw);
    }

    if(joystickMain.getY()) {
      hang.driveDown(1);
    }
    else if(joystickMain.getA()) {
      hang.driveDown(-1);
    }
    else {
      hang.driveDown(0);
    }
    shooter.drive(joystickMain.getRB2Double());
    intake.drive(joystickMain.getLeftTriState(), joystickMain.getRB1(), shooter.isReadyToShoot());
  }

  @Override
  public void testPeriodic() {
  }

  public void logBatteryVoltage() {
    LoggerUtil.logNumber("battery.voltage", RobotController.getBatteryVoltage());
  }

  public void log() {
    logBatteryVoltage();
    drivetrain.log();
    intake.log();
    shooter.log();
    gyro.log();
  }
}
