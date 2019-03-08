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
 * Add your docs here.
 */

 /**
  * This subsystem is for taking in the cargo AND pushing it out
  */
public class CargoIntake extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX leftTalon = new WPI_TalonSRX(RobotMap.CARGO_INTAKE_LEFT);
  private WPI_TalonSRX rightTalon = new WPI_TalonSRX(RobotMap.CARGO_INTAKE_RIGHT);

  @Override
  public void initDefaultCommand()
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  /**
   * Spins the Cargo out at 70%
   * Spins the left clockwise
   * Spins the right counterclock wise
   */
  public void outtake()
  {
    leftTalon.set(ControlMode.PercentOutput, 0.7);
    rightTalon.set(ControlMode.PercentOutput, -0.7);
  }

  public void outtakeFast(){
    leftTalon.set(ControlMode.PercentOutput, 1);
    rightTalon.set(ControlMode.PercentOutput, -1);
  }

  public void intake()
  {
    leftTalon.set(ControlMode.PercentOutput, -0.4);
    rightTalon.set(ControlMode.PercentOutput, 0.4);
  }

  public void stop()
  {
    leftTalon.stopMotor();
    rightTalon.stopMotor();
  }
}
