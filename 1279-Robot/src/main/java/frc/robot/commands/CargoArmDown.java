/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;

public class CargoArmDown extends Command 
{
  public CargoArmDown() 
  {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoArms);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    Robot.cargoArms.initializeCounter();
    //if(!Robot.cargoArms.isSwitchSet())
    //Robot.cargoArms.lowerArms(); // hopefully this should prevent it from driving while already up
    /* if(Robot.cargoArms.getRear())
    {
      end(); // prevents it from running while down
    } */
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    if(!Robot.cargoArms.getRawFront()){
      end();
      System.out.println("cargoArms going down blocked by front limit switch");
    }
    else{
      Robot.cargoArms.lowerArms();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return Robot.cargoArms.getFront(); // limit switch or x button is released
  }

  // Called once after isFinished returns true
  @Override
  protected void end() 
  {
    Robot.cargoArms.stop();
    System.out.println("cargo arm bottom limit switch hit");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() 
  {
    end();
  }
}
