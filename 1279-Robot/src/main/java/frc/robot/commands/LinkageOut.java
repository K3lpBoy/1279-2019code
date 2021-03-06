/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinkageOut extends Command
{
  public LinkageOut()
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.fourBarLinkage);
  }

  // Called just before this Command runs the first time
  /**
   * Initializes the limit switch counter
   */
  @Override
  protected void initialize()
  {
    Robot.fourBarLinkage.initializeCounter();
  }

  // Called repeatedly when this Command is scheduled to run
  /**
   * Moves the four-bar linkage forward
   * If blocked, then returns the sentence that it is blocked, and runs the end method
   */
  @Override
  protected void execute()
  {
    if(!Robot.fourBarLinkage.getRawFront())
    { // returns true when open
      System.out.println("linkage blocked by front switch");
      end();
    }
    else
    {
      Robot.fourBarLinkage.hatchForward();
    }
    //System.out.println("linkage out; getRawFront: " + Robot.fourBarLinkage.getRawFront());
  }

  // Make this return true when this Command no longer needs to run execute()
  /**
   * Returns when the four bar linkage is touching the front limit switch
   */
  @Override
  protected boolean isFinished()
  {
    return Robot.fourBarLinkage.getFront();
  }

  // Called once after isFinished returns true
  /**
   * Stops the four-bar linkage
   */
  @Override
  protected void end()
  {
    Robot.fourBarLinkage.stopLinkage();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted()
  {
    end();
  }
}