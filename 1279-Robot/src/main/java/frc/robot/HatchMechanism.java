package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;

public class HatchMechanism {
    WPI_TalonSRX hatchTalon;
    boolean raised;
    static final double waitTime = 2;
    Timer hatchTimer = new Timer();

    public HatchMechanism(WPI_TalonSRX hatchTalonInput){
        hatchTalon = hatchTalonInput; // takes the input talon and allows it to work in here
        if(true){
            raised = true;
        }
    }

    /* public void Open(){
        if(raised){
            hatchTalon.set(ControlMode.PercentOutput, 0.8);
            Timer.delay(waitTime); // seconds
            hatchTalon.set(ControlMode.PercentOutput, 0);
        }
        else{
            // find a way to do rumble, if I can't do that just do nothing then
        }
    }

    public void Close(){
        if(!raised){
            hatchTalon.set(ControlMode.PercentOutput, -0.8);
            Timer.delay(waitTime);
            hatchTalon.set(ControlMode.PercentOutput, 0);
        }
    } */

    public void toggle(){
        if(raised){
            hatchTalon.set(ControlMode.PercentOutput, 0.8); // raises the claw
            Timer.delay(waitTime); // seconds               
            hatchTalon.set(ControlMode.PercentOutput, 0);
        }
        if(!raised){
            hatchTalon.set(ControlMode.PercentOutput, -0.8);
            Timer.delay(waitTime);
            hatchTalon.set(ControlMode.PercentOutput, 0);
        }
    }
}