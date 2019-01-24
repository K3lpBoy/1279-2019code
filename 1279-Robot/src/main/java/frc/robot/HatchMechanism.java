package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.Timer;

public class HatchMechanism {
    WPI_TalonSRX hatchTalon;
    boolean raised;
    static final double waitTime = 0.8;
    Timer hatchTimer = new Timer();

    public HatchMechanism(WPI_TalonSRX hatchTalonInput){
        hatchTalon = hatchTalonInput; // takes the input talon and allows it to work in here
        if(true){
            raised = true;
        }
    }

    /* public void Open(){
        if(hatchSpinning){
            hatchTalon.set(ControlMode.PercentOutput, 0.8);
            Timer.delay(waitTime); // seconds
            hatchTalon.set(ControlMode.PercentOutput, 0);
        }
        else{
            // find a way to do rumble, if I can't do that just do nothing then
        }
    }

    public void Close(){
        if(!hatchSpinning){
            hatchTalon.set(ControlMode.PercentOutput, -0.8);
            Timer.delay(waitTime);
            hatchTalon.set(ControlMode.PercentOutput, 0);
        }
    } */

    // so this currently doesn't work, just try and set a timer and then a while loop checking if the timer is equal or greater and then stop the motor
    /*public void toggle(){
        if(hatchSpinning){
            hatchTalon.set(ControlMode.PercentOutput, 0.8); // raises the claw
            Timer.delay(waitTime); // seconds               
            hatchTalon.set(ControlMode.PercentOutput, 0);
            hatchSpinning = !hatchSpinning;
            System.out.println("hatch mech going up");
        }
        else if(!hatchSpinning){
            hatchTalon.set(ControlMode.PercentOutput, -0.8);
            Timer.delay(waitTime);
            hatchTalon.set(ControlMode.PercentOutput, 0);
            hatchSpinning = !hatchSpinning;
            System.out.println("hatch mech going down");
        }
    }*/

    /*
    public void toggle(){
        if(raised){
            // none of this works aaa
            hatchTalon.set(ControlMode.PercentOutput, 0.8);
            
        }
    }
    */
}