/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class LinkageIn extends Command {
  public LinkageIn() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.fourBarLinkage);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.fourBarLinkage.initializeCounter();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!Robot.fourBarLinkage.getRawRear()){ // returns true when open
      System.out.println("linkage blocked by rear switch");
      end();
    } 
    else{
    Robot.fourBarLinkage.hatchBack();
    }
    //System.out.println("linkage in; getRawRear: " + Robot.fourBarLinkage.getRawRear());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.fourBarLinkage.getRear();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.fourBarLinkage.stopLinkage();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
