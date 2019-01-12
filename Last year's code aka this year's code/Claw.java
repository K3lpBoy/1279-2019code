package org.usfirst.frc.team1279.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Claw
{
   private WPI_TalonSRX m_leftTalon;
   private WPI_TalonSRX m_rightTalon;
   private WPI_TalonSRX m_armTalon;
   private boolean      m_isRaised;

   public Claw(int leftID, int rightID, int armID)
   {
      m_leftTalon = new WPI_TalonSRX(leftID);
      m_rightTalon = new WPI_TalonSRX(rightID);
      m_armTalon = new WPI_TalonSRX(armID);
      m_isRaised = false;

      // set mode as percent output
      m_leftTalon.set(ControlMode.PercentOutput, 0);
      m_leftTalon.setInverted(false);
      m_leftTalon.setSafetyEnabled(false);
      m_rightTalon.set(ControlMode.PercentOutput, 0);
      m_rightTalon.setInverted(false);
      m_rightTalon.setSafetyEnabled(false);
      m_armTalon.set(ControlMode.PercentOutput, 0);
      m_armTalon.setInverted(false);
      m_armTalon.setSafetyEnabled(false);

      /* configure both nominal, peak outputs and deadbands */
      m_leftTalon.configNominalOutputForward(0, 0);
      m_leftTalon.configNominalOutputReverse(0, 0);
      m_leftTalon.configPeakOutputForward(1, 0);
      m_leftTalon.configPeakOutputReverse(-1, 0);
      m_leftTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
      m_rightTalon.configNominalOutputForward(0, 0);
      m_rightTalon.configNominalOutputReverse(0, 0);
      m_rightTalon.configPeakOutputForward(1, 0);
      m_rightTalon.configPeakOutputReverse(-1, 0);
      m_rightTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
      m_armTalon.configNominalOutputForward(0, 0);
      m_armTalon.configNominalOutputReverse(0, 0);
      m_armTalon.configPeakOutputForward(1, 0);
      m_armTalon.configPeakOutputReverse(-1, 0);
      m_armTalon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
   }

   public void intake()
   {
      m_leftTalon.set(-0.9);
      m_rightTalon.set(0.9);
   }

   public void outtake()
   {
      m_leftTalon.set(0.9);
      m_rightTalon.set(-0.9);
   }

   public void stop()
   {
      m_leftTalon.set(0);
      m_rightTalon.set(0);
   }

   public void raise()
   {
      m_armTalon.set(-0.25);
   }

   public void lower()
   {
      m_armTalon.set(0.25);
   }

   public void stopArm()
   {
      m_armTalon.set(0);
   }

}
