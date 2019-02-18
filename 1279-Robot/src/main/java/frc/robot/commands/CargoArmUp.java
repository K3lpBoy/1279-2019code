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

public class CargoArmUp extends Command {
  public CargoArmUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.cargoArms);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.cargoArms.initializeCounter();
    //if(!Robot.cargoArms.isSwitchSet())
    //Robot.cargoArms.raiseArms(); // hopefully this should work so that it doesn't damage itself if it runs itself while it's already down because it resets the counter first
    if(Robot.cargoArms.getRear()){
      end(); // this is a failsafe
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.cargoArms.raiseArms();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.cargoArms.getRear(); // limit switch or the square button is released
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("cargo arm top limit switch hit");
    Robot.cargoArms.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end(); // this should work in tandem with the whileHeld() in OI to get this run off the button
  }
}
