package org.usfirst.frc.team1279.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Climber
{
   private WPI_TalonSRX m_talon;

   public Climber(int canID)
   {
      m_talon = new WPI_TalonSRX(canID);

      // set mode as percent output
      m_talon.set(ControlMode.PercentOutput, 0);
      m_talon.setInverted(false);
      m_talon.setSafetyEnabled(false);

      /* configure both nominal, peak outputs and deadbands */
      m_talon.configNominalOutputForward(0, 0);
      m_talon.configNominalOutputReverse(0, 0);
      m_talon.configPeakOutputForward(1, 0);
      m_talon.configPeakOutputReverse(-1, 0);
      m_talon.configNeutralDeadband(0.04, 0); // 4% deadband (default)
   }

   public void raise()
   {
      m_talon.set(0.80);
   }

   public void stop()
   {
      m_talon.set(0);
   }
}
