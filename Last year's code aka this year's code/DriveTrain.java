package org.usfirst.frc.team1279.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DriveTrain
{
   private final double      COUNTS_PER_INCH = 53;

   private WPI_TalonSRX      m_frontLeftTalon;
   private WPI_TalonSRX      m_frontRightTalon;
   private WPI_TalonSRX      m_rearLeftTalon;
   private WPI_TalonSRX      m_rearRightTalon;

   private DifferentialDrive m_drive;
   private boolean           m_isReversed;

   public DriveTrain(int frontLeftID, int frontRightID, int rearLeftID, int rearRightID)
   {
      m_frontLeftTalon = new WPI_TalonSRX(frontLeftID);
      m_frontRightTalon = new WPI_TalonSRX(frontRightID);
      m_rearLeftTalon = new WPI_TalonSRX(rearLeftID);
      m_rearRightTalon = new WPI_TalonSRX(rearRightID);

      m_isReversed = false;
      m_frontLeftTalon.setInverted(false);
      m_frontRightTalon.setInverted(false);
      m_rearLeftTalon.setInverted(false);
      m_rearRightTalon.setInverted(false);

      // set front motors as masters
      m_frontLeftTalon.set(ControlMode.PercentOutput, 0);
      m_frontRightTalon.set(ControlMode.PercentOutput, 0);

      // set rear motors as slaves
      m_rearLeftTalon.follow(m_frontLeftTalon);
      m_rearRightTalon.follow(m_frontRightTalon);

      /* configure all nominal, peak outputs and deadbands */
      m_frontLeftTalon.configNominalOutputForward(0, 0);
      m_frontLeftTalon.configNominalOutputReverse(0, 0);
      m_frontLeftTalon.configPeakOutputForward(1, 0);
      m_frontLeftTalon.configPeakOutputReverse(-1, 0);
      m_frontLeftTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
      m_frontRightTalon.configNominalOutputForward(0, 0);
      m_frontRightTalon.configNominalOutputReverse(0, 0);
      m_frontRightTalon.configPeakOutputForward(1, 0);
      m_frontRightTalon.configPeakOutputReverse(-1, 0);
      m_frontRightTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
      m_rearLeftTalon.configNominalOutputForward(0, 0);
      m_rearLeftTalon.configNominalOutputReverse(0, 0);
      m_rearLeftTalon.configPeakOutputForward(1, 0);
      m_rearLeftTalon.configPeakOutputReverse(-1, 0);
      m_rearLeftTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
      m_rearRightTalon.configNominalOutputForward(0, 0);
      m_rearRightTalon.configNominalOutputReverse(0, 0);
      m_rearRightTalon.configPeakOutputForward(1, 0);
      m_rearRightTalon.configPeakOutputReverse(-1, 0);
      m_rearRightTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)

      // configure front left and right encoders
      m_frontLeftTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
      m_frontRightTalon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
      resetEncoders();

      SpeedControllerGroup m_leftGroup = new SpeedControllerGroup(m_frontLeftTalon,
            m_rearLeftTalon);
      SpeedControllerGroup m_rightGroup = new SpeedControllerGroup(m_frontRightTalon,
            m_rearRightTalon);

      m_drive = new DifferentialDrive(m_leftGroup, m_rightGroup);
      m_drive.setExpiration(0.1); // 100 ms (default)
   }

   public void drive(double throttle, double turn)
   {
      // System.out.println("*** throttle:" + throttle + " turn:" + turn);
      if (m_isReversed)
      {
         throttle *= -1.0;
      }
      m_drive.arcadeDrive(throttle, turn);

      System.out.println("@@@ COUNT = " + getEncoderCount());
   }

   public void encoderDistance(double throttle, int inches, double timeout)
   {
      double stopCount = getEncoderCount() + (inches * COUNTS_PER_INCH);
      double stopTime = Timer.getFPGATimestamp() + timeout;

      // NOTE: resetting encoders not functional
      // resetEncoders();

      while ((Timer.getFPGATimestamp() < stopTime) && (getEncoderCount() < stopCount))
      {
         // drive straight w/o turning
         drive(throttle, 0);
         Timer.delay(0.05);
      }
      // stop on exit
      drive(0, 0);

      System.out.println("@@@ COUNT: " + getEncoderCount());
   }

   public int getEncoderCount()
   {
      // return negative of right encoder (left encoder is not functional)
      return (-m_frontRightTalon.getSelectedSensorPosition(0));
   }

   public void resetEncoders()
   {
      m_frontLeftTalon.setSelectedSensorPosition(0, 0, 10);
      m_frontRightTalon.setSelectedSensorPosition(0, 0, 10);
   }

   public void turn90Left()
   {
      // pulse a left turn for 25 msec.
      System.out.println("*** LEFT 90");
      drive(0, -0.5);
      Timer.delay(1.0);
      drive(0, 0);
   }

   public void turn90Right()
   {
      // pulse a right turn for 25 msec.
      System.out.println("*** RIGHT 90");
      drive(0, 0.5);
      Timer.delay(1.0);
      drive(0, 0);
   }

   public void reverseDirection()
   {
      System.out.println("@@@ REVERSED");
      m_isReversed = !m_isReversed;
   }

   public boolean isReversed()
   {
      return m_isReversed;
   }

   public void setSafetyMode(boolean enabled)
   {
      m_drive.setSafetyEnabled(enabled);
   }
}
