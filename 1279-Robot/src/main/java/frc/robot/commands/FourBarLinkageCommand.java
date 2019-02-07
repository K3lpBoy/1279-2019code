/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class FourBarLinkageCommand extends Command 
{
  boolean linkageDeployed = false;

  public FourBarLinkageCommand() 
  {
    linkageDeployed = false;
    requires(Robot.fourBarLinkage);
    setTimeout(0.267);
    System.out.println("Four Bar Linkage has been enacted");
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
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
    if(linkageDeployed == false)
    {
      Robot.fourBarLinkage.turnLinkageToPutHatchOn();
    }
    else
    {
      Robot.fourBarLinkage.turnLinkageToPutHatchOn();
    }

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
    Robot.fourBarLinkage.stopLinkage();
    linkageDeployed = !linkageDeployed;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {

  }
}
