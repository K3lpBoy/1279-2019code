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
import frc.robot.HatchMechanism;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.HatchSubsystem;

import java.lang.module.ModuleDescriptor.Requires;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;

/**
 * Add your docs here.
 */
public class HatchMechanismCommand extends Trigger 
{
  
  final int hatchTalonID = RobotMap.getHatchTalonID();
  WPI_TalonSRX hatchSpin = new WPI_TalonSRX(hatchTalonID);

  final int hatchButton = RobotMap.getHatchButton();
  
  final Joystick driverJoyStick = RobotMap.getJoystick();
  
  HatchMechanism hatchMech = new HatchMechanism(hatchSpin);
  
  /*public HatchSubsystem turnTheHatch()
  {
    //hatchMech.turnHatch();
  }
  */
  @Override
  public boolean get() 
  {
    return false;
  }
}
