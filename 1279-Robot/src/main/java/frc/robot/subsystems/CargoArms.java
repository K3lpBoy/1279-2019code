/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class CargoArms extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  WPI_TalonSRX cargoArmTalon = RobotMap.cargoArmTalon;
  DigitalInput hatchArmSwitch = new DigitalInput(0); // PLACEHOLDER NUMBER
  Counter counter = new Counter(hatchArmSwitch);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initializeCounter(){
    counter.reset();
  }

  public boolean isSwitchSet(){
    return counter.get() > 0;
  }

  public void raiseArms(){
    cargoArmTalon.set(ControlMode.PercentOutput, 0.5); // placeholder speed, gotta do some testing for it
  }

  public void lowerArms(){
    cargoArmTalon.set(ControlMode.PercentOutput, -0.5); // also a placeholder speed
  }

  public void stop(){
    cargoArmTalon.stopMotor();
  }

}
