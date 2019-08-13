/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorUp extends Command
{
  public ElevatorUp()
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  /**
   * This will initialize the limit switch
   * Checks to see if the bottom switch is set
   * if so, then it stops it
   * otherwise, it goes up
   */
  @Override
  protected void initialize()
  {
    Robot.elevator.initializeCounter();
    if(!Robot.elevator.isSwitchSet())
    {
      Robot.elevator.raiseElevator();
    }
    if(Robot.elevator.getBottom())
    {
      end();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  /**
   * 
   */
  @Override
  protected void execute()
  {
    if(!Robot.elevator.getRawBottom())
    {
      end();
      System.out.println("Elevator is hitting the bottom limit switch");
    }
    else
    {
      Robot.elevator.raiseElevator();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()
  {
    return Robot.elevator.getBottom();
  }

  // Called once after isFinished returns true
  @Override
  protected void end()
  {
    System.out.println("Elevator has hit the top limit switch");
    Robot.elevator.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted()
  {
    end();
  }
}