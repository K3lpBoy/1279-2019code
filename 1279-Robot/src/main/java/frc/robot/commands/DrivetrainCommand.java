/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;
//import frc.robot.subsystems.DriveTrain;

/**
 * This is the command that we use to drive the robot, using the DriveTrain SUBSYSTEM to get the method to drive
 */
public class DrivetrainCommand extends Command 
{
  public DrivetrainCommand() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    //NAME THE SUBSYSTEMS IN THE ROBOT CLASS
    //MAKE THE SUBSYSTEMS STATIC
    requires(Robot.robotDriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

  }

  // Called repeatedly when this Command is scheduled to run
  /**
   * Drives the robot
   */
  @Override
  protected void execute() 
  {
    Robot.robotDriveTrain.robotDrive();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {

  }
}
