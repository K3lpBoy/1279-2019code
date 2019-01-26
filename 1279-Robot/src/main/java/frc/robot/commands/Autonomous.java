/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Robot;
import frc.robot.RobotMap;

import java.sql.Driver;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * Add your docs here.
 */
public class Autonomous extends Trigger 
{

  Joystick driverJoyStick;

  DifferentialDrive driveTrain;

  public Autonomous(Joystick dJoystick, DifferentialDrive drive)
  {
    driverJoyStick = dJoystick;
    driveTrain = drive;
  }

  public void driveAuto()
  {
    
  }
  
  @Override
  public boolean get() 
  {
    return false;
  }

  protected boolean isFinished()
  {
    return isFinished();
  }
}
