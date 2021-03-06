/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.drive.*;
import frc.robot.Robot;
import frc.robot.RobotMap;

/**
 * TODO: IMPORTANT: THIS IS TO BE COPIED AND PASTED FOR NEXT YEAR
 * THIS SHOULD BE COPIED UNLESS OTHERWISE MENTIONED
 * This is the drive train class subsystem
 * This is an extremely important class
 * Without this, we would not be able to drive
 */
public class DriveTrain extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private static int inverse = 1;
  private double modifier = 1;

  //static DifferentialDrive drive = RobotMap.diffDrive;
  static Joystick driverStick = RobotMap.driverStick;

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());

    
  }

  /*public DriveTrain(DifferentialDrive diffDrive, Joystick stick)
  {
    // drive = new DriveTrain(frontLeft, rearLeft, frontRight, rearRight));
    drive = diffDrive;
    driverStick = stick;
  }*/

  /**
   * This command drives the robot
   */
  public void robotDrive()
  {
    double xSpeed = driverStick.getRawAxis(RobotMap.DRIVER_LEFT_X_AXIS) * -1 * inverse * modifier; // makes forward stick positive
    double zRotation =  driverStick.getRawAxis(RobotMap.DRIVER_RIGHT_Y_AXIS) * modifier; // WPI Drivetrain uses positive=> right; right stick for left and right

    Robot.drive.arcadeDrive(xSpeed, zRotation);

    Robot.drive.feed();
  }

  /**
   * This flips the direction of the drive train
   */
  public void flipDirection()
  {
    inverse = inverse * -1; // just flips the value between 1 and negative 1
  }

  /**
   * Sets the direction forward
   * Forward is the formalerly hatch side
   */
  public void setDirectionForward()
  { // hatch side
    inverse = 1;
  }

  /**
   * Sets the direction backwards
   * Backwards is the formaleryly cargo side
   */
  public void setDirectionBack()
  { // cargo side
    inverse = -1;
  }

  /**
   * This slows down the drive train
   * Slows it to 60%
   * Multiplies the drive train by 0.6
   */
  public void slowSpeed()
  {
    modifier = 0.6; // 60%
  }

  /**
   * This is the normal speed
   * This will allow for the values to be times 1
   */
  public void normalSpeed()
  {
    modifier = 1; // 100%
  }

  /**
   * Returns the direction of the drivetrain
   * @return false when not inverted (cargo side), true when inverted (hatch side)
   */
  public static boolean getDirection()
  {
    if(inverse == 1)
    {
      return false; // cargo side
    }

    if(inverse == -1)
    {
      return true; // hatch side
    }

    else return false;
  }
}