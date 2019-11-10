/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

 /**
  * This subsystem is for taking in the cargo AND pushing it out
  */
public class CargoIntake extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these subsystems from inside the Commands
  private WPI_TalonSRX leftTalon = new WPI_TalonSRX(RobotMap.CARGO_INTAKE_LEFT);
  private WPI_TalonSRX rightTalon = new WPI_TalonSRX(RobotMap.CARGO_INTAKE_RIGHT);

  @Override
  public void initDefaultCommand()
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Spins the Cargo out at 50%
   * Spins the left clockwise
   * Spins the right counterclock wise
   */
  public void outtake()
  {
    //cargoTalon.set(ControlMode.PercentOutput, -0.5);
    leftTalon.set(ControlMode.PercentOutput, 0.3);
    rightTalon.set(ControlMode.PercentOutput, -0.3);
  }

  /**
   * Spins the Cargo out at 35%
   * Spins the left clockwise
   * Spins the right counterclock wise
   */
  public void outtakeFast()
  {
    // this goes slow now
    //cargoTalon.set(ControlMode.PercentOutput, -0.35);
    leftTalon.set(ControlMode.PercentOutput, 0.55);
    rightTalon.set(ControlMode.PercentOutput, -0.55);
  }

  /**
   * This is the intake method
   * Spins the Cargo in at 50%
   * Spins the right clockwise
   * Spins the left counterclock wise
   */
  public void intake()
  {
    //cargoTalon.set(ControlMode.PercentOutput, 0.5);
    leftTalon.set(ControlMode.PercentOutput, -0.5);
    rightTalon.set(ControlMode.PercentOutput, 0.5);
    //left and right Talon values are alternated to make sure that the wheels turn clockwise and counter-clockwise
  }

  /**
   * Stops the Cargo.
   * Use stopMotor method to stop. Not just set it to zero
   */
  public void stop()
  {
    //cargoTalon.stopMotor();
    leftTalon.stopMotor();
    rightTalon.stopMotor();
  }
}