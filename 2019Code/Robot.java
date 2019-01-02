/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1279.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.DriverStation.MatchType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot
{
   //private DriveTrain              m_drive; 
   //private Claw                    m_claw; 
   //private AutoLoader              m_autoLoader;
   //private Elevator                m_elevator;
   //private Climber                 m_climber;
   private WPI_TalonSRX            m_testTalon;
   private Joystick                m_drvrStick;
   private Joystick                m_ctrlStick;
   private UsbCamera               m_camera;
   private Alliance                m_alliance;
   private MatchType               m_matchType;

   // private NetworkTable m_robotTable;
   private boolean                 m_curReversed    = false;
   private boolean                 m_isAutoDone     = false;

   /* private static final String     kCenterAuto      = "Center";
   private static final String     kLeftAuto        = "Left";
   private static final String     kRightAuto       = "Right";			this is all old auto stuff
   private static final String     kShortLeftAuto   = "ShortLeft";
   private static final String     kShortRightAuto  = "ShortRight";
   private static final String     kStraightAuto    = "Straight"; */

   // CAN IDs
   private final int               FL_DRIVE_CAN_ID  = #;
   private final int               FR_DRIVE_CAN_ID  = #;
   private final int               RL_DRIVE_CAN_ID  = #;
   private final int               RR_DRIVE_CAN_ID  = #;

   /* private final int               L_CLAW_CAN_ID    = #;
   private final int               R_CLAW_CAN_ID    = #;
   private final int               CLAW_ARM_CAN_ID  = #; */

   /* private final int               AUTO_LDR_CAN_ID  = #;

   private final int               ELEVATOR_CAN_ID  = #;          // 9;

   private final int               CLIMBER_CAN_ID   = #;           // 10; */

   // DriveTrain Xbox buttons - port 0
   public final int                REVRS_A_BTN_ID   = 1;
   public final int                L_BMPER_BTN_ID   = 5;
   public final int                R_BMPER_BTN_ID   = 6;

   // Control Panel button IDs - port 1
   public final int                CTL_WHITE_BTN_ID = 4;
   public final int                CTL_BLUE_BTN_ID  = 3;
   public final int                CTL_RED_BTN_ID   = 7;
   public final int                CTL_GREEN_BTN_ID = 5;
   public final int                CTL_BLACK_BTN_ID = 6;
   public final int                CTL_BTM_RT_SW_ID = 2;
   public final int                CTL_TOP_LT_UP_ID = 10;
   public final int                CTL_TOP_LT_DN_ID = 11;
   public final int                CTL_BTM_LT_UP_ID = 8;
   public final int                CTL_BTM_LT_DN_ID = 9;

   private String                  m_autoSelected;
   private String                  m_gameData;
   private SendableChooser<String> m_chooser;

   private enum SwitchSide
   {
      LEFT, RIGHT
   }

   /**
    * This function is run when the robot is first started up and should be used
    * for any initialization code.
    */
   @Override
   public void robotInit()
   {
      // stream video from USB camera to dashboard w/o processing frames
      m_camera = CameraServer.getInstance().startAutomaticCapture();
      m_camera.setResolution(640, 480);
      m_camera.setFPS(10);

      m_chooser = new SendableChooser<>();
      m_chooser.addDefault("Center Auto", kCenterAuto);
      m_chooser.addObject("Left Auto", kLeftAuto);
      m_chooser.addObject("Right Auto", kRightAuto);
      m_chooser.addObject("Short Left Auto", kShortLeftAuto);
      m_chooser.addObject("Short Right Auto", kShortRightAuto);
      m_chooser.addObject("Straight Auto", kStraightAuto);

      SmartDashboard.putData("Auto Choices", m_chooser);

      // robotTable = NetworkTable.getTable("Robot");

      m_drive = new DriveTrain(FL_DRIVE_CAN_ID, FR_DRIVE_CAN_ID, RL_DRIVE_CAN_ID, RR_DRIVE_CAN_ID);
      m_drive.setSafetyMode(false);

      m_claw = new Claw(L_CLAW_CAN_ID, R_CLAW_CAN_ID, CLAW_ARM_CAN_ID);
      m_autoLoader = new AutoLoader(AUTO_LDR_CAN_ID);
      m_elevator = new Elevator(ELEVATOR_CAN_ID);
      m_climber = new Climber(CLIMBER_CAN_ID);
      m_drvrStick = new Joystick(0);
      m_ctrlStick = new Joystick(1);
   }

   /**
    * This autonomous (along with the chooser code above) shows how to select
    * between different autonomous modes using the dashboard. The sendable
    * chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
    * Dashboard, remove all of the chooser code and uncomment the getString line
    * to get the auto name from the text box below the Gyro
    *
    * <p>
    * You can add additional auto modes by adding additional comparisons to the
    * switch structure below with additional strings. If using the
    * SendableChooser make sure to add them to the chooser code above as well.
    */
   @Override
   public void autonomousInit()
   {
      // re-initialize
      m_isAutoDone = false;

      // start autonomous mode in reverse (autoloader is front of robot)
      System.out.println("@@@ AUTONOMOUS INIT");
      if (!m_drive.isReversed())
      {
         m_drive.reverseDirection();
      }

      // retrieve match data
      m_autoSelected = m_chooser.getSelected();
      m_gameData = m_ds.getGameSpecificMessage();
      m_alliance = m_ds.getAlliance();
      m_matchType = m_ds.getMatchType();
      String allianceStr = "";
      String matchTypeStr = "";

      if (m_alliance == Alliance.Red)
      {
         allianceStr = "RED";
      } else if (m_alliance == Alliance.Blue)
      {
         allianceStr = "BLUE";
      }

      if (m_matchType == MatchType.Qualification)
      {
         matchTypeStr = "QUALIFICATION";
      } else if (m_matchType == MatchType.Elimination)
      {
         matchTypeStr = "ELIMINATION";
      }

      System.out.println("@@@ " + matchTypeStr + " MATCH:" + m_ds.getMatchNumber() + "  "
            + allianceStr + "  SELECTED:" + m_autoSelected + "  ASSIGNED:" + m_gameData);
   }

   /**
    * This function is called periodically during autonomous.
    */
   @Override
   public void autonomousPeriodic()
   {
      boolean isMatched = false;
      boolean isStopShort = false;

      // execute first loop iteration only
      if (!m_isAutoDone)
      {
         System.out.println("@@@ AUTO START COUNT: " + m_drive.getEncoderCount());

         // alliance assigned left or right side of switch
         SwitchSide side = (m_gameData.charAt(0) == 'L') ? SwitchSide.LEFT : SwitchSide.RIGHT;

         // if positioned on left or right side
         if (m_autoSelected.equals(kLeftAuto) || m_autoSelected.equals(kRightAuto))
         {
            // if positioned on alliance side of local switch
            isMatched = (m_gameData.charAt(0) == m_autoSelected.charAt(0));

            autoDriveSwitchSide(side, isMatched, false);
         }
         // if positioned on left or right side
         else if (m_autoSelected.equals(kShortLeftAuto) || m_autoSelected.equals(kShortRightAuto))
         {
            // if positioned on alliance side of local switch
            isMatched = (m_gameData.charAt(0) == m_autoSelected.charAt(5));

            autoDriveSwitchSide(side, isMatched, true);
         }
         // else if drive from center to left or right side of switch
         else if (m_autoSelected.equals(kCenterAuto))
         {
            autoDriveSwitchFront(side);
         }
         // else drive straight to autoline
         else
         {
            autoDriveStraigt();
         }

         System.out.println("@@@ AUTO DONE");
         m_isAutoDone = true;
      }
   }

   public void autoDriveSwitchSide(SwitchSide side, boolean isMatched, boolean isStopShort)
   {
      // if matched drive to side of local switch
      if (isMatched)
      {
         // drive 70% throttle for 10.5 ft. with 8 sec. timeout
         m_drive.encoderDistance(0.7, 135, 8.0);
         Timer.delay(0.5);

         // turn towards switch
         if (side == SwitchSide.LEFT)
         {
            m_drive.turn90Right();
         } else
         {
            m_drive.turn90Left();
         }
         Timer.delay(0.5);

         // drive straight for 70% throttle for 2 ft. with 2 sec. timeout
         m_drive.encoderDistance(0.7, 22, 2.0);

         // unload the cube
         m_autoLoader.outtake();
         Timer.delay(0.5);
         m_autoLoader.stop();
      }
      // else if stop short
      else if (isStopShort)
      {
         // drive straight and stop along switch
         // drive 70% throttle for 10 ft. with 5 sec. timeout
         m_drive.encoderDistance(0.7, 120, 5.0);
      } else // else drive around back of local switch
      {
         // drive 70% throttle for 17 ft. 4 in. with 8 sec. timeout
         m_drive.encoderDistance(0.7, 228, 8.0);
         Timer.delay(0.5);

         // turn towards switch
         if (side == SwitchSide.LEFT)
         {
            m_drive.turn90Left();
         } else
         {
            m_drive.turn90Right();
         }
         Timer.delay(0.5);

         // drive 70% throttle for 12'4" with 8 sec. timeout
         m_drive.encoderDistance(0.7, 50, 4.0);
         m_drive.encoderDistance(0.5, 50, 4.0);
         // run the intake the last leg to hold onto the cube
         m_autoLoader.intake();
         m_drive.encoderDistance(0.3, 48, 4.0);
         m_autoLoader.stop();
      }
   }

   public void autoDriveSwitchFront(SwitchSide side)
   {
      // drive straight for 70% throttle for 2 ft. 9 in. with 4 sec. timeout
      m_drive.encoderDistance(0.7, 33, 4.0);
      Timer.delay(0.5);

      // drive towards alliance switch side
      if (side == SwitchSide.LEFT)
      {
         m_drive.turn90Left();
         Timer.delay(0.5);

         // drive 70% throttle for 4 ft. 6 in. with 4 sec. timeout
         m_drive.encoderDistance(0.7, 54, 4.0);
         Timer.delay(0.5);

         m_drive.turn90Right();
      } else
      {
         m_drive.turn90Right();
         Timer.delay(0.5);

         // drive 70% throttle for 2 ft. with 4 sec. timeout
         m_drive.encoderDistance(0.7, 24, 4.0);
         Timer.delay(0.5);

         m_drive.turn90Left();
      }

      // drive 70% throttle for 4 ft. 6 in. with 4 sec. timeout
      Timer.delay(0.5);
      m_drive.encoderDistance(0.7, 54, 4.0);

      // unload the cube
      m_autoLoader.outtake();
      Timer.delay(0.5);
      m_autoLoader.stop();
   }

   public void autoDriveStraigt()
   {
      // drive 70% throttle for 10 ft. with 5 sec. timeout
      m_drive.encoderDistance(0.7, 120, 5.0);
   }

   @Override
   public void teleopInit()
   {
      // enable safety mode on the drive train
      m_drive.setSafetyMode(true);

      // start teleop in forward direction (camera and claw are front of robot)
      System.out.println("@@@ TELEOP INIT");
      if (m_drive.isReversed())
      {
         m_drive.reverseDirection();
      }
      m_curReversed = m_drive.isReversed();
   }

   /**
    * This function is called periodically during operator control.
    */
   @Override
   public void teleopPeriodic()
   {
      // System.out.println("@@@ TELEOP ITERATION");

      // drive train joystick:
      //////////////////////////
      boolean reverse = m_drvrStick.getRawButton(REVRS_A_BTN_ID);
      // process button press but not the release
      if (reverse && !m_curReversed)
      {
         // switch directions on button press but not its release
         m_drive.reverseDirection();
      }
      m_curReversed = reverse;

      if (m_drvrStick.getRawButton(L_BMPER_BTN_ID))
      {
         // System.out.println("@@@ LEFT BUMPER");
         m_drive.drive(0, -0.5);
      } else if (m_drvrStick.getRawButton(R_BMPER_BTN_ID))
      {
         // System.out.println("@@@ RIGHT BUMPER");
         m_drive.drive(0, 0.5);
      } // left trigger maps to axis 2
      else if (m_drvrStick.getRawAxis(2) > 0.1)
      {
         // System.out.println("@@@ LEFT TRIGGER");
         m_drive.drive(0, -1 * m_drvrStick.getRawAxis(2));
      } // right trigger maps to axis 3
      else if (m_drvrStick.getRawAxis(3) > 0.1)
      {
         // System.out.println("@@@ RIGHT TRIGGER");
         m_drive.drive(0, m_drvrStick.getRawAxis(3));
      } else // else update the drivetrain every loop iteration
             // (4% deadband defined in the joysticks)
      {
         m_drive.drive(m_drvrStick.getRawAxis(5), 0.75 * m_drvrStick.getRawAxis(0));
      }

      // control panel:
      //////////////////////////

      // claw raise/lower:
      if (m_ctrlStick.getRawButton(CTL_BTM_LT_UP_ID))
      {
         // System.out.println("@@@ CLAW RAISE");
         m_claw.raise();
      } else if (m_ctrlStick.getRawButton(CTL_BTM_LT_DN_ID))
      {
         // System.out.println("@@@ CLAW LOWER");
         m_claw.lower();
      } else
      {
         m_claw.stopArm();
      }

      // claw intake/outtake:
      if (m_ctrlStick.getRawButton(CTL_BLUE_BTN_ID))
      {
         // System.out.println("@@@ CLAW INTAKE");
         m_claw.intake();
      } else if (m_ctrlStick.getRawButton(CTL_GREEN_BTN_ID))
      {
         // System.out.println("@@@ CLAW OUTTAKE");
         m_claw.outtake();
      } else
      {
         m_claw.stop();
      }

      // auto loader intake/outtake:
      if (m_ctrlStick.getRawButton(CTL_BLACK_BTN_ID))
      {
         // System.out.println("@@@ LOADER INTAKE");
         m_autoLoader.intake();
      } else if (m_ctrlStick.getRawButton(CTL_RED_BTN_ID))
      {
         // System.out.println("@@@ LOADER OUTTAKE");
         m_autoLoader.outtake();
      } else
      {
         m_autoLoader.stop();
      }

      // elevator raise/lower:
      if (m_ctrlStick.getRawButton(CTL_TOP_LT_UP_ID))
      {
         // System.out.println("@@@ ELEVATOR RAISE");
         m_elevator.raise();
      } else if (m_ctrlStick.getRawButton(CTL_TOP_LT_DN_ID))
      {
         // System.out.println("@@@ ELEVATOR LOWER");
         m_elevator.lower();
      } else
      {
         m_elevator.stop();
      }

      // climber:
      if (m_ctrlStick.getRawButton(CTL_WHITE_BTN_ID))
      {
         // System.out.println("@@@ CLIMBER RAISE");
         m_climber.raise();
      } else
      {
         m_climber.stop();
      }
   }

   @Override
   public void testInit()
   {
      final int TEST_CAN_ID = 9;
      m_testTalon = new WPI_TalonSRX(TEST_CAN_ID);

      // set mode as percent output
      m_testTalon.set(ControlMode.PercentOutput, 0);
      m_testTalon.setInverted(false);
      m_testTalon.setSafetyEnabled(true);

      /* configure both nominal, peak outputs and deadbands */
      m_testTalon.configNominalOutputForward(0, 0);
      m_testTalon.configNominalOutputReverse(0, 0);
      m_testTalon.configPeakOutputForward(1, 0);
      m_testTalon.configPeakOutputReverse(-1, 0);
      m_testTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
      m_drive.reverseDirection();
   }

   /**
    * This function is called periodically during test mode.
    */
   @Override
   public void testPeriodic()
   {
      System.out.println("@@@ TEST ITERATION");
      m_testTalon.set(-1 * m_drvrStick.getRawAxis(5));
   }

   @Override
   public void disabledInit()
   {
      System.out.println("@@@ DISABLED");
      m_drive.resetEncoders();
   }
}