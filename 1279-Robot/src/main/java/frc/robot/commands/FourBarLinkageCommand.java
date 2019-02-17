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
  int limitSwitch;

  public FourBarLinkageCommand() 
  {
    linkageDeployed = false;
    requires(Robot.fourBarLinkage);
    //setTimeout(0.267);
    System.out.println("Four Bar Linkage has been enacted");
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    System.out.println("linkage initialized");
    /* Robot.fourBarLinkage.initializeCounter(); // important to reset the counter
    if(linkageDeployed)
    {
      Robot.fourBarLinkage.hatchBack();
    }
    else
    {
      Robot.fourBarLinkage.hatchForward();
    }
    linkageDeployed = !linkageDeployed; */
    Robot.fourBarLinkage.initializeCounter();
    //Robot.fourBarLinkage.hatchForward();
    if(Robot.fourBarLinkage.getFront()){
      linkageDeployed = true;
      limitSwitch = 1; // looks for the rear limit switch
      System.out.println("linkage deployed, set to go back");
    }
    if(Robot.fourBarLinkage.getRear()){
      linkageDeployed = false;
      limitSwitch = 0; // looks for the front limit switch
      System.out.println("linkage not deployed, set to go forward");
    }
    System.out.println("Initialized At " + java.lang.System.currentTimeMillis());
    Robot.fourBarLinkage.initializeCounter();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    System.out.println("TIMESTAMP: " + java.lang.System.currentTimeMillis());
    if(linkageDeployed)
    {
      Robot.fourBarLinkage.hatchBack();
    }
    else
    {
      Robot.fourBarLinkage.hatchForward();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    System.out.println(Robot.fourBarLinkage.isFrontSwitchSet() || Robot.fourBarLinkage.isRearSwitchSet());
    return Robot.fourBarLinkage.getSwitch(limitSwitch); // finishes when the limit switch is activated, shouldn't be an issue due to how slowly it goes
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.fourBarLinkage.stopLinkage();
    System.out.println("linkage finished at: " + java.lang.System.currentTimeMillis());
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {

  }
}
