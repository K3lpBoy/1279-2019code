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
import frc.robot.commands.Autonomous;
import frc.robot.commands.DrivetrainCommand;
//import frc.robot.commands.DrivingTheRobot;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.AutonomousSubsystem;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.FourBarLinkageForHatch;
import frc.robot.subsystems.HatchSubsystem;

import java.sql.Driver;

//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
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
  /*xbox buttons
  private final int DRIVER_JOYSTICK = 0;
  private final int A_BUTTON = 1;
  private final int LEFT_X_AXIS = 1;
  private final int RIGHT_Y_AXIS = 4;
  private final int AUTONOMOUS_BOTTON = 5;

  private final int HATCH_SPINNER_BUTTON = 2;

  private static final int AUTONOMOUS_BUTTON = 5;

  // TODO: eventually make these into an interface

  //talon IDs (NOT FINAL)
  //note: TALONS ARE INCREDIBLY DUMB AND ARE ONE INDEXED
  private final int FRONT_LEFT_ID = 1;
  private final int REAR_LEFT_ID = 2;
  private final int FRONT_RIGHT_ID = 3;
  private final int REAR_RIGHT_ID = 4;
  private final int BALL_ARM_LIFTER_ID = 5; */
  
  // private final int HATCH_TALON_ID = 7; // This is just a test ID for hatch talon
  
  boolean autoDriver = false;

  // WPI_TalonSRX hatchTalon = new WPI_TalonSRX(HATCH_TALON_ID);

  Relay stopDriveTrain = new Relay(0);

  public static DriveTrain robotDriveTrain = new DriveTrain(); // constructing our drivetrain SUBSYSTEM
  public static HatchSubsystem hatch = new HatchSubsystem();
  public static FourBarLinkageForHatch fourBarLinkage = new FourBarLinkageForHatch();
  
  // WPI_TalonSRX hatchTalon = new WPI_TalonSRX(5); // this is only for testing
  // purposes right now
  //HatchMechanismCommand hatchMech = ;

  Joystick driverStick = new Joystick(RobotMap.DRIVER_JOYSTICK);

  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static OI m_oi;

  public static AutonomousSubsystem m_autoSubSystem = new AutonomousSubsystem();

  Command m_autonomousCommand;

  Command Autonomous;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  //HatchMechanismCommand hatchSpin = new HatchMechanismCommand(hatchTalon, 2, driverStick); // fix magic number

  //drivetrain stuff
  WPI_TalonSRX frontLeft = new WPI_TalonSRX(RobotMap.FRONT_LEFT_ID);
  WPI_TalonSRX rearLeft = new WPI_TalonSRX(RobotMap.REAR_LEFT_ID);
  SpeedControllerGroup m_left = new SpeedControllerGroup(frontLeft, rearLeft);
  WPI_TalonSRX frontRight = new WPI_TalonSRX(RobotMap.FRONT_RIGHT_ID);
  WPI_TalonSRX rearRight = new WPI_TalonSRX(RobotMap.REAR_RIGHT_ID);
  SpeedControllerGroup m_right = new SpeedControllerGroup(frontRight, rearRight);

  DifferentialDrive drive = new DifferentialDrive(m_left, m_right);
  //DrivingTheRobot driveTrain = new DrivingTheRobot(drive, driverStick);
  //end of drivetrain definitions

  LimitSwitchNormal limitSwitch = LimitSwitchNormal.NormallyClosed;

  // this is test stuff to test watchdog stuff
  //WPI_TalonSRX itsAProgrammingProblem = new WPI_TalonSRX(7);


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    //m_oi = new OI();
    m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);

    /* eraServer.getInstance().startAutomaticCapture(0); // gets the camera feed
    CameraServer.getInstance().startAutomaticCapture(1); */

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
    autoDriver = true;
  }

  @Override
  public void disabledPeriodic() 
  {
    //RJW: Scheduler.getInstance().run();
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
    //MAJOR NEWS: SANDSTORM IS IN AUTONOMOUS AND NOT IN TELEOP. TO DRIVE NORMALLY, MUST USE SAME TECHNIQUES AS IN TELEOP


    System.out.println("autonomous init is being run");

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

    RobotMap.frontLeft.configFactoryDefault();
    RobotMap.frontRight.configFactoryDefault();
    RobotMap.rearLeft.configFactoryDefault();
    RobotMap.rearRight.configFactoryDefault();

    // adjust these so that when the stick is forward both of these are green
    //RobotMap.frontLeft.setInverted(false);  UNCOMMENT
    //RobotMap.rearLeft.setInverted(false);   UNCOMMENT
    //RobotMap.frontRight.setInverted(true); UNCOMMENT
    //RobotMap.rearRight.setInverted(true);  UNCOMMENT
    // DO NOT TOUCH THIS OR YOU WILL GRENADE THE TRANSMISSION

    RobotMap.diffDrive.setRightSideInverted(false); // don't change this

    RobotMap.diffDrive.setSafetyEnabled(false);
        // end of drivetrain stuff

    //RobotMap.diffDrive.setExpiration(2);
    RobotMap.diffDrive.setSafetyEnabled(false);
    
    RobotMap.frontLeft.setSafetyEnabled(false);
    RobotMap.rearLeft.setSafetyEnabled(false);
    RobotMap.frontRight.setSafetyEnabled(false);
    RobotMap.rearRight.setSafetyEnabled(false);
    
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {
    //RJW: Scheduler.getInstance().run();
    System.out.println("autonomous periodic is being run");
    RobotMap.diffDrive.arcadeDrive(0.7, 0);

    //double xSpeed = driverStick.getRawAxis(RobotMap.DRIVER_LEFT_X_AXIS) * -1; // makes forward stick positive
    //double zRotation =  driverStick.getRawAxis(RobotMap.DRIVER_RIGHT_Y_AXIS); // WPI Drivetrain uses positive=> right; right stick for left and right

    ////drive.arcadeDrive(xSpeed, zRotation);

    //autodriver
    /*if(driverStick.getRawButton(RobotMap.AUTONOMOUS_BOTTON))
    {
      autoDriver = false;

      while(autoDriver == false)
      {
      
        double autoSpeed = 0.4;
        double autoRotation = 0;

        ////drive.arcadeDrive(autoSpeed, autoRotation);
        //Troublsome part of coding auto
        if(driverStick.getRawButton(8))
        {
          autoDriver = true;
        }
      }
    }*/
  }

  @Override
  public void teleopInit() 
  {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    System.out.println("teleop init is being run");

    if (m_autonomousCommand != null) 
    {
      m_autonomousCommand.cancel();
    } 

    RobotMap.frontLeft.configFactoryDefault();
    RobotMap.frontRight.configFactoryDefault();
    RobotMap.rearLeft.configFactoryDefault();
    RobotMap.rearRight.configFactoryDefault();

    // adjust these so that when the stick is forward both of these are green
    RobotMap.frontLeft.setInverted(false);
    RobotMap.rearLeft.setInverted(false);
    RobotMap.frontRight.setInverted(true); 
    RobotMap.rearRight.setInverted(true);
    // DO NOT TOUCH THIS OR YOU WILL GRENADE THE TRANSMISSION

    RobotMap.diffDrive.setRightSideInverted(false); // don't change this

    RobotMap.diffDrive.setSafetyEnabled(false);
        // end of drivetrain stuff

    RobotMap.diffDrive.setExpiration(2);
    RobotMap.diffDrive.setSafetyEnabled(false);
    
    RobotMap.frontLeft.setSafetyEnabled(false);
    RobotMap.rearLeft.setSafetyEnabled(false);
    RobotMap.frontRight.setSafetyEnabled(false);
    RobotMap.rearRight.setSafetyEnabled(false);

    //itsAProgrammingProblem.setSafetyEnabled(false);
    //itsAProgrammingProblem.setExpiration(2);
// RJW TEST:
   //Scheduler.getInstance().removeAll();
// END RJW
    /* System.out.println("teleopInit() has been run");
    itsAProgrammingProblem.configFactoryDefault();
    itsAProgrammingProblem.setSafetyEnabled(true); */
  }

  /** 
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() 
  {
    Scheduler.getInstance().run(); // afaik this is responsible for the OI stuff

    robotDriveTrain.robotDrive();

    System.out.println("DiffDrive Safety - " + RobotMap.diffDrive.isSafetyEnabled());
    System.out.println("Front Left Safety - " + RobotMap.frontLeft.isSafetyEnabled());
    System.out.println("Rear Left Safety - " + RobotMap.rearLeft.isSafetyEnabled());
    System.out.println("Front Right Safety - " + RobotMap.frontRight.isSafetyEnabled());
    System.out.println("Rear Right Safety - " + RobotMap.rearRight.isSafetyEnabled());

    
    /* if(driverStick.getRawButton(1))
    {
      itsAProgrammingProblem.set(ControlMode.PercentOutput, 0.8);
      //itsAProgrammingProblem.feed();
    }
    else
    {
      itsAProgrammingProblem.stopMotor();
    }
    System.out.print(itsAProgrammingProblem.isSafetyEnabled() + " ");
    System.out.println(itsAProgrammingProblem.getExpiration()); */

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    /*driveTrain.drive();

    if(driverStick.getRawButton(HATCH_SPINNER_BUTTON))
    {
      hatchSpin.toggle();
    }*/
    //4itsAProgrammingProblem.setExpiration(2);

    //WPI_TalonSRX itsAProgrammingProblem = new WPI_TalonSRX(4);
    /*if(driverStick.getRawButton(1)){
      itsAProgrammingProblem.set(ControlMode.PercentOutput, 0.8);
      itsAProgrammingProblem.feed();
    } 
    System.out.println(itsAProgrammingProblem.isSafetyEnabled()); */
    /* else{
      itsAProgrammingProblem.set(ControlMode.PercentOutput, 0);
    } */

  }
}