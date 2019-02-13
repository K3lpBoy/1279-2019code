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
public class CargoIntake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX leftTalon = new WPI_TalonSRX(RobotMap.CARGO_INTAKE_LEFT);
  private WPI_TalonSRX rightTalon = new WPI_TalonSRX(RobotMap.CARGO_INTAKE_RIGHT);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void outtake(){
    leftTalon.set(ControlMode.PercentOutput, 0.5);
    rightTalon.set(ControlMode.PercentOutput, -0.5);
  }

  public void intake(){
    leftTalon.set(ControlMode.PercentOutput, -0.5);
    rightTalon.set(ControlMode.PercentOutput, 0.5);
  }

  public void stop(){
    leftTalon.stopMotor();
    rightTalon.stopMotor();
  }
}
