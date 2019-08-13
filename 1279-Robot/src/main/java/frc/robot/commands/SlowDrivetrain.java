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
 * This is the command that is used to slow down the drive train
 */
public class SlowDrivetrain extends Command
{
  public SlowDrivetrain()
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.robotDriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize()
  {

  }

  // Called repeatedly when this Command is scheduled to run
  /**
   * This method slows down the drive train to 60% power
   */
  @Override
  protected void execute()
  {
    Robot.robotDriveTrain.slowSpeed();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()
  {
    return false;
  }

  // Called once after isFinished returns true
  /**
   * When the command ends, the drive train returns to normal speed
   */
  @Override
  protected void end()
  {
    Robot.robotDriveTrain.normalSpeed();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  /**
   * When the command gets interuppted, it will return the normal speed
   */
  @Override
  protected void interrupted()
  {
    end();
  }
}