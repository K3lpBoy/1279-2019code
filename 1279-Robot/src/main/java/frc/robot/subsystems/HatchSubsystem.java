/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.HatchMechanism;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class HatchSubsystem extends Subsystem 
{
  
  final int hatchTalonID = RobotMap.getHatchTalonID();
  WPI_TalonSRX hatchSpin = new WPI_TalonSRX(hatchTalonID);

  final int hatchButton = RobotMap.getHatchButton();
  
  final Joystick driverJoyStick = RobotMap.getJoystick();

  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  
  boolean hatchSpinning = false;

  @Override
  public void initDefaultCommand()
  {
    
    hatchSpinning = false;
    
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

  }

}