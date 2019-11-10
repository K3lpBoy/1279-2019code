/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
/**
 * THIS IS NOTES FROM DAN
 * The Robot Map allows us to assign what talons go to which mechanism
 * MAKE THE DIFFERENT VARIABLES STATIC - that way, they are accessable across all of the different classes
 */
public class RobotMap 
{
  public static final int HATCH_SPINNER_BUTTON = 2;

  public static Joystick driverStick = new Joystick(0);
  public static final int DRIVER_JOYSTICK = 0;
  public static final int A_BUTTON = 1;
  public static final int DRIVER_LEFT_X_AXIS = 1;
  public static final int DRIVER_RIGHT_Y_AXIS = 4;
  public static final int AUTONOMOUS_BOTTON = 5;

  //talon IDs (NOT FINAL)
  //note: TALONS ARE INCREDIBLY DUMB AND ARE ONE INDEXED -- Thanks Marco
  //These are the talons for the drive train
  public final static int FRONT_LEFT_ID = 7; // 1 was hooked up to hatch turning
  public final static int REAR_LEFT_ID = 13; 
  public final static int FRONT_RIGHT_ID = 8;
  public final static int REAR_RIGHT_ID = 5; // changed for testing purposes, was 4
  
  public static final int HATCH_TALON_ID = 2; //Hatch Talon ID

  public static final int FOUR_BAR_LINKAGE_TALON_ID = 1;

  public static final int ELEVATOR_TALON = 6; // was originally the first cargo arm talon THIS IS THE ELEVATOR TALON
  public static final int OTHER_ELEVATOR_TALON = 4; // was originally the other cargo arm talon THIS ONE AS WELL

  public static final int CARGO_INTAKE_LEFT = 10;
  public static final int CARGO_INTAKE_RIGHT = 3;
  // drivetrain stuff
  //TODO: IMPORTANT: THIS IS TO BE COPIED AND PASTED INTO THE NEW CODE FOR NEXT YEAR. WHILE THE IDs MAY BE DIFFERENT, THIS SHOULD REMAIN THE SAME
  public static WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_ID);
  public static WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.REAR_LEFT_ID);
  public static SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);
  public static WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_ID);
  public static WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.REAR_RIGHT_ID);
  public static SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, rearRight);
  //TODO: THIS BOX RIGHT HERE
  //public static DifferentialDrive diffDrive = new DifferentialDrive(m_left, m_right);

  public static WPI_TalonSRX hatchTalon = new WPI_TalonSRX(HATCH_TALON_ID);
  //public static WPI_TalonSRX fourBarLinkageTalon = new WPI_TalonSRX(FOUR_BAR_LINKAGE_TALON);
  
  //limit switches
  public static int HATCH_ARM_SWITCH_ID_BACK = 0;
  public static int HATCH_ARM_SWITCH_ID_FRONT = 1;
  public static int ELEVATOR_SWITCH_ID_TOP = 2; // placeholder
  public static int ELEVATOR_SWITCH_ID_BOTTOM = 3; // placeholder
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}