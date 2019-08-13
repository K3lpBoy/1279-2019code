/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * THIS IS IMPORTANT
 * LOOK AT ME
 * THIS IS NOT TO BE USED
 * THIS IS THE TIMED ONE
 * THIS IS NOT GOOD FOR A FEW REASONS
 * A. DOESN'T ALLOW FOR ANY TYPE OF CONTROL
 * B. CAN GO SPINNY ROUND ROUND
 * C. MAKES A BIG BOY MESS THAT I DAN WILL HAVE TO CLEAN UP
 * D. can possibly stop the robot in its tracks
 * DON'T USE THIS
 */
public class HatchTabDown extends Command 
{
  public HatchTabDown() 
  {
    requires(Robot.hatch);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    setTimeout(1);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    Robot.hatch.spinToReleaseHatch();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.hatch.stopSpin();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    Robot.hatch.stopSpin();
  }
}