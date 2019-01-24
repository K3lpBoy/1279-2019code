/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import frc.robot.*;

import edu.wpi.first.wpilibj.drive.*;  // these are necessary for the drivetrain builder
import edu.wpi.first.wpilibj.*; // see https://phoenix-documentation.readthedocs.io/en/latest/ch15_WPIDrive.html

/**
 * Add your docs here.
 */
public class AutonomousSubsystem extends Subsystem 
{
  private static final int AUTONOMOUS_BUTTON = 0;
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private final int DRIVER_JOYSTICK = 0;

  private final int FRONT_LEFT_ID = 1;
  private final int REAR_LEFT_ID = 2;
  private final int FRONT_RIGHT_ID = 3;
  private final int REAR_RIGHT_ID = 4;

  WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(FRONT_LEFT_ID);
  WPI_TalonSRX rearLeftTalon = new WPI_TalonSRX(REAR_LEFT_ID);
  SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeftTalon, rearLeftTalon);
  WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(FRONT_RIGHT_ID);
  WPI_TalonSRX rearRightTalon = new WPI_TalonSRX(REAR_RIGHT_ID);
  SpeedControllerGroup m_right = new SpeedControllerGroup(frontRightTalon, rearRightTalon);

  DifferentialDrive drive = new DifferentialDrive(m_left, m_right);
  
  Joystick driverStick = new Joystick(DRIVER_JOYSTICK);

  boolean autonomousDriving = false;

  public void driveForwardInAuto()
  {

    drive.setSafetyEnabled(true); //enables safety on the drivetrain

    frontLeftTalon.configFactoryDefault();
    frontRightTalon.configFactoryDefault();
    rearLeftTalon.configFactoryDefault();
    rearRightTalon.configFactoryDefault();

    // adjust these so that when the stick is forward both of these are green
    frontLeftTalon.setInverted(false);
    rearLeftTalon.setInverted(false);
    frontRightTalon.setInverted(true); 
    rearRightTalon.setInverted(true);
    // DO NOT TOUCH THIS OR YOU WILL GRENADE THE TRANSMISSION

    // dont change this
    drive.setRightSideInverted(false);

    double xSpeed = 0.3;
    double zRotation = 0;

    while(autonomousDriving == false)
    {
      drive.arcadeDrive(xSpeed, zRotation);
    }
  }
  
  public void stopAuto()
  {
    autonomousDriving = false;
    drive.arcadeDrive(0, 0);
  }

  @Override
  public void initDefaultCommand() 
  {

    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
