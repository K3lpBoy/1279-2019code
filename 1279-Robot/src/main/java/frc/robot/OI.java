/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

//import static org.junit.Assert.fail;

//import com.sun.org.apache.xml.internal.utils.BoolStack;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.HatchMechCommand;
import frc.robot.commands.HatchTabDown;
import frc.robot.commands.HatchTabUp;
import frc.robot.commands.LinkageIn;
import frc.robot.commands.LinkageOut;
import frc.robot.commands.MainRobotGroup;
import frc.robot.commands.SetDrivetrainForward;
import frc.robot.commands.SetDrivetrainReverse;
import frc.robot.commands.SlowDrivetrain;
import frc.robot.commands.CargoArmDown;
import frc.robot.commands.CargoArmUp;
import frc.robot.commands.CargoIn;
import frc.robot.commands.CargoOut;
import frc.robot.commands.Climb;
import frc.robot.commands.FourBarLinkageCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
/**
 * THIS IS DAN
 * What the OI allows us to do is to assign different button controls to each mechanism
 * As well, this is where we have the Robot do the desired commands
 * (An example from this year is that when the "A button" on the XBOX controller is pressed,
 * the hatch will spin either forward-to pick up the hatch-or back-to let it go.)
 */
public class OI 
{
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  // THESE ARE 1 (ONE) indexed
  static Joystick driverStick = new Joystick(0); // xbox button -- This is where we declare where the joystick is ported to on the drive train. (LOOK ON DRIVER STATION)
  Button aButton = new JoystickButton(driverStick, 1); //One indexed. Check the Driver Station for what button is which
  Button bButton = new JoystickButton(driverStick, 2);
  Button xButton = new JoystickButton(driverStick, 3);
  Button yButton = new JoystickButton(driverStick, 4);
  Button leftShoulder = new JoystickButton(driverStick, 5);
  Button rightShoulder = new JoystickButton(driverStick, 6);
  Button select = new JoystickButton(driverStick, 7);
  Button start = new JoystickButton(driverStick, 8);
  Button leftStickPress = new JoystickButton(driverStick, 9);
  Button rightStickPress = new JoystickButton(driverStick, 10);

  public static Joystick operatorStick = new Joystick(1); // This is the operator joystick. Goes into port one beneath driver controller
  Button triangle = new JoystickButton(operatorStick, 1);
  Button circle = new JoystickButton(operatorStick, 2);
  Button operatorXButton = new JoystickButton(operatorStick, 3);
  Button square = new JoystickButton(operatorStick, 4);
  Button l2 = new JoystickButton(operatorStick, 5);
  Button r2 = new JoystickButton(operatorStick, 6);
  Button l1 = new JoystickButton(operatorStick, 7);
  Button r1 = new JoystickButton(operatorStick, 8);
  Button operatorStart = new JoystickButton(operatorStick, 9);
  Button operatorSelect = new JoystickButton(operatorStick, 10);
  Button l3 = new JoystickButton(operatorStick, 11);
  Button r3 = new JoystickButton(operatorStick, 12);
  Button dpadUp = new JoystickButton(operatorStick, 13);
  Button dpadRight = new JoystickButton(operatorStick, 14);
  Button dpadDown = new JoystickButton(operatorStick, 15);
  Button dpadLeft = new JoystickButton(operatorStick, 16);

  int oiDebugCounter = 0;

  static boolean UpDown = true;

  /**
   * THIS IS DAN
   * This is where we say what commands should be executed when any button is pressed
   */
  public OI()
  {
    //boolean UpDown = true; // Initialize hatch to Up = true
    //while (Flag)
    //{
      //System.out.println("HI");
      square.whenPressed(new HatchMechCommand()); // this runs the hatch mechanism, MOVE THIS TO OPERATOR OR JUST ASK NICK LATER
      //bButton.whenPressed(new FourBarLinkageCommand()); // runs the hatch extender thing
      dpadRight.whileHeld(new CargoArmDown()); // this actually goes up now
      dpadLeft.whileHeld(new CargoArmUp()); // this actually goes down now
      triangle.whileHeld(new CargoIn());
      circle.whileHeld(new CargoOut());
      dpadUp.whileHeld(new LinkageOut());
      dpadDown.whileHeld(new LinkageIn());
      operatorSelect.whileHeld(new Climb());
      //l2.whenPressed(new HatchTabUp());
      //r2.whenPressed(new HatchTabDown());

      yButton.whenPressed(new SetDrivetrainForward());
      bButton.whenPressed(new SetDrivetrainReverse());
      rightShoulder.whileHeld(new SlowDrivetrain());
            
      //l2.whileHeld(new ClimbingCommand()); //CHANGE LATER. JUST THERE TO TEST
      //somebutton.whenPressed(new FlipDriveDirection());

      //Flag = false;
    //}
    // aButton.whenPressed(new MainRobotGroup()); uncomment this and comment out the line above it if it doesn't work
  }

  public static Joystick getGamepad(int stickId)
  {
    if (stickId == 0) return driverStick;
    if (stickId == 1) return operatorStick;
    return driverStick; // failsafe
  }

  /* public static HatchMechCommand callHatchMechCommand(boolean UD)
  {
    System.out.println("UD = " + UD);
    UpDown = !UpDown;
    System.out.println("UpDown = " + UpDown);
    return new HatchMechCommand(UD);
  } */
}
