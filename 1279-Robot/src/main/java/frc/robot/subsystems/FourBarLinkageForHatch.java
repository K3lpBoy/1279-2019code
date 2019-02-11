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
public class FourBarLinkageForHatch extends Subsystem 
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  WPI_TalonSRX fourBarLinkageTalon = RobotMap.fourBarLinkageTalon;
  DigitalInput hatchArmSwitchFront = new DigitalInput(RobotMap.HATCH_ARM_SWITCH_ID_FRONT); 
  DigitalInput hatchArmSwitchBack = new DigitalInput(RobotMap.HATCH_ARM_SWITCH_ID_BACK); 
  Counter counterFront = new Counter(hatchArmSwitchFront);
  Counter counterRear = new Counter(hatchArmSwitchBack);

  @Override
  public void initDefaultCommand() 
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void hatchForward()
  {
    fourBarLinkageTalon.set(ControlMode.PercentOutput, 0.8);
    //0.267 turn for it;
  }

  public void hatchBack()
  {
    fourBarLinkageTalon.set(ControlMode.PercentOutput, -0.8);
  }

  public void stopLinkage()
  {
    fourBarLinkageTalon.set(ControlMode.PercentOutput, 0);
  }

  public boolean isFrontSwitchSet(){
    return counterFront.get() > 0; // increments when the limit switch is activated
  }

  public boolean isRearSwitchSet(){
    return counterRear.get() > 0;
  }

  public void initializeCounter(){
    counterFront.reset();
    counterRear.reset();
  }
}
