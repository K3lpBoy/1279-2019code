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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class DriveTrain extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DriveTrain drive;

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public DriveTrain(WPI_TalonSRX frontLeft, WPI_TalonSRX rearLeft, WPI_TalonSRX frontRight, WPI_TalonSRX rearRight){
    // drive = new DriveTrain(frontLeft, rearLeft, frontRight, rearRight));

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
}
