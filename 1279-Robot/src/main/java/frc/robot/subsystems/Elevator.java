/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem
{
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  private WPI_TalonSRX elevatorTalon = new WPI_TalonSRX(RobotMap.ELEVATOR_TALON); //identifies what the talons are
  private WPI_TalonSRX otherElevatorTalon = new WPI_TalonSRX(RobotMap.OTHER_ELEVATOR_TALON); //^^
  private SpeedControllerGroup elevatorGroup = new SpeedControllerGroup(elevatorTalon, otherElevatorTalon); //creates a group that will work in unison

  DigitalInput elevatorSwitchTop = new DigitalInput(RobotMap.ELEVATOR_SWITCH_ID_TOP); // PLACEHOLDER NUMBER -- DK what Marco meant - this is for the limit switch
  DigitalInput elevatorSwitchBottom = new DigitalInput(RobotMap.ELEVATOR_SWITCH_ID_BOTTOM); // also for the limit switch
  Counter counterTop = new Counter(elevatorSwitchTop); //idk - counts??????????????????????????????
  Counter counterBottom = new Counter(elevatorSwitchBottom);//i really don't have any idea what this does - counts????????????

  @Override
  public void initDefaultCommand()
  {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void initializeCounter()
  {
    counterTop.reset();
    counterBottom.reset();
  }

  public boolean isSwitchSet()
  {
    return counterTop.get() > 0; //checks if the top switch is set
  }

  public void raiseElevator()
  {
    elevatorGroup.set(.25); //This value is there just for testing. Can raise or lower the value later on
  }

  public void lowerElevator()
  {
    elevatorGroup.set(-.25); //This value is there just for testing. Can raise or lower the value later on
  }

  public void climb()
  {
    elevatorGroup.set(-.75); //This is for climbing. idea is to go down as fast as possible
  }

  public void hold()
  {
    elevatorGroup.set(-.15); //idk
  }
  
  public void stop()
  {
    elevatorGroup.stopMotor(); //stops motor
    elevatorTalon.setNeutralMode(NeutralMode.Brake); //makes the talon stop
    otherElevatorTalon.setNeutralMode(NeutralMode.Brake); //makes the talon stop
  }

  /**
   * Gets the top return value
   */
  public boolean getTop()
  {
    return counterTop.get() > 0; // returns the top value
  }

  public boolean getBottom()
  {
    return counterBottom.get() > 0;
  }

  public boolean getRawTop()
  {
    return elevatorSwitchTop.get();
  }

  public boolean getRawBottom()
  {
    return elevatorSwitchBottom.get();
  }

}