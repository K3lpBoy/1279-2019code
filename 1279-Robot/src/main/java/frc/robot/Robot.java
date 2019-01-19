/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.drive.*;  // these are necessary for the drivetrain builder
import edu.wpi.first.wpilibj.*; // see https://phoenix-documentation.readthedocs.io/en/latest/ch15_WPIDrive.html

import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.Talon; this isn't the right one

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot; // these allow the camera to work

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  //xbox buttons
  private final int DRIVER_JOYSTICK = 0;
  private final int A_BUTTON = 1;
  private final int LEFT_X_AXIS = 1;
  private final int RIGHT_Y_AXIS = 4;

  // TODO: eventually make these into an interface

  //talon IDs (NOT FINAL)
  //note: TALONS ARE INCREDIBLY DUMB AND ARE ONE INDEXED
  private final int FRONT_LEFT_ID = 1;
  private final int REAR_LEFT_ID = 2;
  private final int FRONT_RIGHT_ID = 3;
  private final int REAR_RIGHT_ID = 4;
  private final int BALL_ARM_LIFTER_ID = 5;

  //note: TALONS ARE INCREDIBLY DUMB AND ARE ONE INDEXED
  WPI_TalonSRX frontLeftTalon = new WPI_TalonSRX(FRONT_LEFT_ID);
  WPI_TalonSRX rearLeftTalon = new WPI_TalonSRX(REAR_LEFT_ID);
  SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeftTalon, rearLeftTalon);
  WPI_TalonSRX frontRightTalon = new WPI_TalonSRX(FRONT_RIGHT_ID);
  WPI_TalonSRX rearRightTalon = new WPI_TalonSRX(REAR_RIGHT_ID);
  SpeedControllerGroup m_right = new SpeedControllerGroup(frontRightTalon, rearRightTalon);
  
  WPI_TalonSRX hatchTalon = new WPI_TalonSRX(5); // this is only for testing purposes right now
  HatchMechanism hatchMech = new HatchMechanism(hatchTalon);

  Joystick driverStick = new Joystick(DRIVER_JOYSTICK);

  DifferentialDrive drive = new DifferentialDrive(m_left, m_right);

  WPI_TalonSRX ballArmLifterTalon = new WPI_TalonSRX(BALL_ARM_LIFTER_ID);

  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    CameraServer.getInstance().startAutomaticCapture(0); // gets the camera feed
    CameraServer.getInstance().startAutomaticCapture(1);

    //below this is my code, most of this was test stuff
    //leftTalon.set(ControlMode.PercentOutput, 0);
    //testJoystick = new Joystick(0);
    //testTalon = new Talon(1);
    //testTalon.set(ControlMode.PercentOutput, 0);
    //testTalon.setInverted(false);
    //testTalon.setSafetyEnabled(false);
    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() 
  {

  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() 
  {

  }

  @Override
  public void disabledPeriodic() 
  {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() 
  {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() 
  {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.cancel();
    }

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
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run();

    double xSpeed = driverStick.getRawAxis(LEFT_X_AXIS) * -1; // makes forward stick positive
    double zRotation =  driverStick.getRawAxis(RIGHT_Y_AXIS); // WPI Drivetrain uses positive=> right; right stick for left and right

    drive.arcadeDrive(xSpeed, zRotation);

    /*if (driverStick.getRawButton(1)){
      System.out.println("xSpeed:" + xSpeed + "    zRotation:" + zRotation);
    }*/

    /* below this is an example of how I would run a motor
    if (driverStick.getRawButton(A_BUTTON)){
      ballArmLifterTalon.set(ControlMode.PercentOutput, 0.5); //theoretically this should run this motor at half speed
    }
    else {
      ballArmLifterTalon.set(ControlMode.PercentOutput, 0); // making sure the motor shuts off when the button isn't pressed
    } */

    /* if(driverStick.getRawButton(A_BUTTON)){                      // this is test code for the bosch seat motor
      hatchMech.toggle(); so this stops everything when its running damn
    } */
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {

    // Potential solution (ERROR: max couldn't be resolved.) double max = testTalon.maxIntegralAccumulator;
    /*if(testJoystick.getRawButton(1))
    {
      testTalon.setSpeed(0.5);
    }
    else
    {
      testTalon.setRaw(30); 
    }*/

    if(driverStick.getRawButton(A_BUTTON)){                      // this is test code for the bosch seat motor
      hatchMech.toggle();
    }
    /*else if(driverStick.getRawButton(2)){ // b button
      ballArmLifterTalon.set(ControlMode.PercentOutput, -0.75); // note: this is like the perfect speed
    }  */                                                         // make a routine out of this so I don't have to use limit switches

  }
}
