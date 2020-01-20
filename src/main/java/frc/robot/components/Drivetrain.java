package frc.robot.components;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Drivetrain {
    VictorSPX victorLeft1;
    VictorSPX victorLeft2;
    VictorSPX victorRight1;
    VictorSPX victorRight2;

    private Drivetrain() {
        victorLeft1 = new VictorSPX(0);    
        victorLeft2 = new VictorSPX(1);
        victorRight1 = new VictorSPX(2);
        victorRight2 = new VictorSPX(3);
    }
}