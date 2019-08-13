/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ElevatorClimb extends Command
{
  public ElevatorClimb()
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.elevator);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize()
  {
    Robot.elevator.initializeCounter();
    if(!Robot.elevator.isSwitchSet())
    {
      Robot.elevator.lowerElevator();
    }
    if(Robot.elevator.getBottom())
    {
      end();
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute()
  {
    if(!Robot.elevator.getRawTop())
    {
      end();
      System.out.println("Elevator going down is prevented by the top limit switch");
    }
    else
    {
      Robot.elevator.climb();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished()
  {
    return Robot.elevator.getTop();
  }

  // Called once after isFinished returns true
  @Override
  protected void end()
  {
    Robot.elevator.stop();
    System.out.println("Elevator bottom limit switch has been hit");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted()
  {
    end();
  }
}