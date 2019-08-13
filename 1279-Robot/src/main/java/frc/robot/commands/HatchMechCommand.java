/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class HatchMechCommand extends Command 
{
  boolean isRaised = true; // i'm not sure if this will stay after the command is finished

  public HatchMechCommand() 
  {
    //this.isRaised = isRaised;
    isRaised = true;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.hatch);
    setTimeout(1); // waits 1 second
    System.out.println("this code is being run");
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    //Robot.hatch.stopSpin();
    //RobotMap.hatchTalon.setSafetyEnabled(false);
    /* if(isRaised)
    {
      System.out.println("1 - HatchMechCommand: execute - before spin");
      Robot.hatch.spinToReleaseHatch();
      System.out.println("2 - HathMechCommand: execute - after spin");
      //isRaised = !isRaised;
      //System.out.println("Changed boolean - false");
    }
    else //if(!isRaised)
    {
      System.out.println("3 - HatchMechCommand: execute - before releasing");
      Robot.hatch.spinToGetHatch();
      System.out.println("4 - HatchMechCommand: execute - after releasing");
      //isRaised = !isRaised;
      //System.out.println("Changed boolean - true");
    } */
  }

  // Called repeatedly when this Command is scheduled to run
  /**
   * I is confused boi
   */
  @Override
  protected void execute() 
  {
    // I moved this all to the initialize command so they don't run a bunch of times - Marco 2/10 
    System.out.println("TIME: " + java.lang.System.currentTimeMillis());
    RobotMap.hatchTalon.feed();
    if(isRaised)
    {
      System.out.println("1 - HatchMechCommand: execute - before spin");
      Robot.hatch.spinToReleaseHatch();
      System.out.println("2 - HathMechCommand: execute - after spin");
      //isRaised = !isRaised;
      //System.out.println("Changed boolean - false");
    }
    else //if(!isRaised)
    {
      System.out.println("3 - HatchMechCommand: execute - before releasing");
      Robot.hatch.spinToGetHatch();
      System.out.println("4 - HatchMechCommand: execute - after releasing");
      //isRaised = !isRaised;
      //System.out.println("Changed boolean - true");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    System.out.println(isTimedOut());
    //isRaised = !isRaised;
    return isTimedOut();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    System.out.println("HatchMechCommand: end");
    Robot.hatch.stopSpin();
    isRaised = !isRaised;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
    System.out.println("hatch interrupted!");
  }
}