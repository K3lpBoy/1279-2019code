/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.HatchSubsystem;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;

/**
 * Add your docs here.
 */
public class HatchMechanismCommand extends Trigger 
{
  
  final int hatchTalonID = RobotMap.getHatchTalonID();
  WPI_TalonSRX hatchTalon = new WPI_TalonSRX(hatchTalonID);

  int hatchButton = RobotMap.getHatchButton();
  
  Joystick driverJoyStick = RobotMap.getJoystick();
  
  boolean hatchSpinning = true;

  static final double waitTime = 0.8;
  Timer hatchTimer = new Timer();
  
  public HatchMechanismCommand(WPI_TalonSRX talonForHatch, int ButtonForHatch, Joystick dJoystick)
  {
    hatchTalon = talonForHatch;
    hatchButton = ButtonForHatch;
    driverJoyStick = dJoystick;
  }

  public void Open()
  {
    if(hatchSpinning){
        hatchTalon.set(ControlMode.PercentOutput, 0.8);
        Timer.delay(waitTime); // seconds
        hatchTalon.set(ControlMode.PercentOutput, 0);
    }
    else
    {
        // find a way to do rumble, if I can't do that just do nothing then
    }
  }
  
  public void Close()
  {
    if(!hatchSpinning)
    {
        hatchTalon.set(ControlMode.PercentOutput, -0.8);
        Timer.delay(waitTime);
        hatchTalon.set(ControlMode.PercentOutput, 0);
    }
}

  public void toggle()
  {
    if(hatchSpinning)
    {
        hatchTalon.set(ControlMode.PercentOutput, 0.8); // raises the claw
        Timer.delay(waitTime); // seconds               
        hatchTalon.set(ControlMode.PercentOutput, 0);
        hatchSpinning = !hatchSpinning;
        System.out.println("hatch mech going up");
    }
    else if(!hatchSpinning)
    {
        hatchTalon.set(ControlMode.PercentOutput, -0.8);
        Timer.delay(waitTime);
        hatchTalon.set(ControlMode.PercentOutput, 0);
        hatchSpinning = !hatchSpinning;
        System.out.println("hatch mech going down");
    }

  }

  //drivingToPickUpHatch
  //if(getButton) {.driveWithHatch}

  /*public void turnHatch()
  {
    
    if(driverJoyStick.getRawButton(hatchButton))
    {
      while(hatchSpinning == false)
      {
        hatchTalon.set(ControlMode.PercentOutput, 0.5);

        if(driverJoyStick.getRawButton(3))
        {
          hatchTalon.set(ControlMode.PercentOutput, 0);
        }
      }

    }
  }*/
  
  @Override
  public boolean get() 
  {
    return false;
  }
}
