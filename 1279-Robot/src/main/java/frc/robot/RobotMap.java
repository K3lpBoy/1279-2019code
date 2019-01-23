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
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
  private static final int HATCH_SPINNER_BUTTON = 2;
  private static final int HATCH_TALON_ID = 7; // This is just a test ID for hatch talon

  WPI_TalonSRX hatchSpin = new WPI_TalonSRX(HATCH_TALON_ID);

  private static final int DRIVER_JOYSTICK = 0;
  private static final int A_BUTTON = 1;
  private static final int LEFT_X_AXIS = 1;
  private static final int RIGHT_Y_AXIS = 4;
  private static final int AUTONOMOUS_BOTTON = 5;

  //talon IDs (NOT FINAL)
  //note: TALONS ARE INCREDIBLY DUMB AND ARE ONE INDEXED
  private final int FRONT_LEFT_ID = 1;
  private final int REAR_LEFT_ID = 2;
  private final int FRONT_RIGHT_ID = 3;
  private final int REAR_RIGHT_ID = 4;
  private final int BALL_ARM_LIFTER_ID = 5;

  WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(FRONT_LEFT_ID);
  WPI_TalonSRX rearLeftTalon = new WPI_TalonSRX(REAR_LEFT_ID);
  SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeftTalon, rearLeftTalon);
  WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(FRONT_RIGHT_ID);
  WPI_TalonSRX rearRightTalon = new WPI_TalonSRX(REAR_RIGHT_ID);
  SpeedControllerGroup m_right = new SpeedControllerGroup(frontRightTalon, rearRightTalon);

  static Joystick driverStick = new Joystick(DRIVER_JOYSTICK);

  DifferentialDrive drive = new DifferentialDrive(m_left, m_right);

  public static int getHatchTalonID()
  {
    return HATCH_TALON_ID;
  }

  public static int getHatchButton()
  {
    return HATCH_SPINNER_BUTTON;
  }
  
  public static Joystick getJoystick()
  {
    return driverStick;
  }
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
