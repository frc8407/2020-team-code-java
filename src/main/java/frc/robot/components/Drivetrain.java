package frc.robot.components;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Drivetrain {
    VictorSPX victorLeft1;
    VictorSPX victorLeft2;
    VictorSPX victorRight1;
    VictorSPX victorRight2;

    public Drivetrain() {
        victorLeft1 = new VictorSPX(1);    
        victorLeft2 = new VictorSPX(3);
        victorRight1 = new VictorSPX(2);
        victorRight2 = new VictorSPX(4);
    }

    public void drive(double left, double right) {
        victorLeft1.set(ControlMode.PercentOutput, left);
        victorLeft2.set(ControlMode.PercentOutput, left);
        victorRight1.set(ControlMode.PercentOutput, right);
        victorRight2.set(ControlMode.PercentOutput, right);
    }
}