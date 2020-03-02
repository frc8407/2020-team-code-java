package frc.robot.components;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.drive.Vector2d;
import frc.robot.logging.LoggableRobotComponent;

class JoystickConfig {
  public int lx;
  public int ly;

  public int rx;
  public int ry;

  public int y;
  public int x;
  public int a;
  public int b;

  public int lb1;
  public int rb1;

  public int lb2;
  public int rb2;

  public JoystickConfig(int lx, int ly, int rx, int ry, int y, int x, int a, int b, int lb1, int rb1, int lb2,
      int rb2) {
    this.lx = lx;
    this.ly = ly;
    this.rx = rx;
    this.ry = ry;
    this.y = y;
    this.x = x;
    this.a = a;
    this.b = b;
    this.lb1 = lb1;
    this.rb1 = rb1;
    this.lb2 = lb2;
    this.rb2 = rb2;
  }
}

public class DriverJoystick extends LoggableRobotComponent {
  private int joystickID;
  private Joystick joystick;
  private JoystickConfig config;

  public static JoystickConfig defaultLogitechConfig = new JoystickConfig(1, 0, 5, 4, 4, 3, 1, 2, 5, 6, 2, 3);

  public DriverJoystick(int joystickID, JoystickConfig config) {
    this.joystickID = joystickID;
    this.joystick = new Joystick(joystickID);
    this.config = config;
  }

  public Vector2d getLeftStick() {
    return getStick(config.lx, config.ly);
  }

  public Vector2d getRightStick() {
    return getStick(config.rx, config.ry);
  }

  public boolean getA() {
    return joystick.getRawButton(config.a);
  }

  public boolean getB() {
    return joystick.getRawButton(config.b);
  }

  public boolean getX() {
    return joystick.getRawButton(config.x);
  }

  public boolean getY() {
    return joystick.getRawButton(config.y);
  }

  public boolean getLB1() {
    return joystick.getRawButton(config.lb1);
  }

  public boolean getLB2() {
    return joystick.getRawAxis(config.lb2) > 0.05;
  }

  public boolean getRB1() {
    return joystick.getRawButton(config.rb1);
  }

  public boolean getRB2() {
    return joystick.getRawAxis(config.rb2) > 0.05;
  }

  private Vector2d getStick(int axisVertical, int axisHorizontal) {
    double x = joystick.getRawAxis(axisHorizontal);
    double y = -joystick.getRawAxis(axisVertical);

    return new Vector2d(x, y);
  }

  public TriState getLeftTriState() {
    return TriState.fromTwoBooleanValues(getLB1(), getLB2());
  }

  public TriState getRightTriState() {
    return TriState.fromTwoBooleanValues(getRB1(), getRB2());
  }

  public TriState getYATriState() {
    return TriState.fromTwoBooleanValues(getY(), getA());
  }

  public TriState getXBTriState() {
    return TriState.fromTwoBooleanValues(getX(), getB());
  }

  @Override
  public void log() {
    
  }

  public enum TriState {
    FORWARD(1.0), REVERSE(-1.0), STOP(0.0);

    public final double value;

    private TriState(double value) {
      this.value = value;
    }

    public static TriState fromTwoBooleanValues(boolean b1, boolean b2) {
      if (b1 && b2)
        return TriState.STOP;

      if (b1)
        return TriState.FORWARD;
        
      if (b2)
        return TriState.REVERSE;

      return TriState.STOP;
    }
  }
}