/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.*;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DifferentialDrive drive;
  Joystick driverStick;

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public DriveTrain(int FL, int RL, int FR, int RR, Joystick stick){
    // drive = new DriveTrain(frontLeft, rearLeft, frontRight, rearRight));

    driverStick = stick;
    WPI_TalonSRX frontLeft = new WPI_TalonSRX(FL);
    WPI_TalonSRX rearLeft = new WPI_TalonSRX(RL);
    WPI_TalonSRX frontRight = new WPI_TalonSRX(FR);
    WPI_TalonSRX rearRight = new WPI_TalonSRX(RR);
    SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);
    SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, rearRight);

    DifferentialDrive drive = new DifferentialDrive(m_left, m_right);

    frontLeft.configFactoryDefault();
    frontRight.configFactoryDefault();
    rearLeft.configFactoryDefault();
    rearRight.configFactoryDefault();

    // adjust these so that when the stick is forward both of these are green
    frontLeft.setInverted(false);
    rearLeft.setInverted(false);
    frontRight.setInverted(true); 
    rearRight.setInverted(true);
    // DO NOT TOUCH THIS OR YOU WILL GRENADE THE TRANSMISSION

    drive.setRightSideInverted(false); // don't change this

    drive.setSafetyEnabled(true);
  }

  public void robotDrive(){
    double xSpeed = driverStick.getRawAxis(RobotMap.DRIVER_LEFT_X_AXIS) * -1; // makes forward stick positive
    double zRotation =  driverStick.getRawAxis(RobotMap.DRIVER_RIGHT_Y_AXIS); // WPI Drivetrain uses positive=> right; right stick for left and right

    drive.arcadeDrive(xSpeed, zRotation);
  }
}
