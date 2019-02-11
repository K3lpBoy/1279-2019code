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
import frc.robot.commands.MainRobotGroup;
import frc.robot.commands.CargoArmDown;
import frc.robot.commands.CargoArmUp;
import frc.robot.commands.FourBarLinkageCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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

  // these are 1 indexed
  Joystick driverStick = new Joystick(0); // xbox button
  Button aButton = new JoystickButton(driverStick, 1);
  Button bButton = new JoystickButton(driverStick, 2);
  Button xButton = new JoystickButton(driverStick, 3);
  Button yButton = new JoystickButton(driverStick, 4);
  Button leftShoulder = new JoystickButton(driverStick, 5);
  Button rightShoulder = new JoystickButton(driverStick, 6);
  Button select = new JoystickButton(driverStick, 7);
  Button start = new JoystickButton(driverStick, 8);
  Button leftStickPress = new JoystickButton(driverStick, 9);
  Button rightStickPress = new JoystickButton(driverStick, 10);

  Joystick operatorStick = new Joystick(1);
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

  public OI()
  {
    //boolean UpDown = true; // Initialize hatch to Up = true
    //while (Flag)
    //{
      //System.out.println("HI");
      aButton.whenPressed(new HatchMechCommand()); // this runs the hatch mechanism
      //bButton.whenPressed(new FourBarLinkageCommand()); // runs the hatch extender thing, leaving this commented for now
      operatorXbutton.whenPressed(new CargoArmDown());
      square.whenPressed(new CargoArmUp());
      //Flag = false;
    //}
    // aButton.whenPressed(new MainRobotGroup()); uncomment this and comment out the line above it if it doesn't work
  }

  /* public static HatchMechCommand callHatchMechCommand(boolean UD)
  {
    System.out.println("UD = " + UD);
    UpDown = !UpDown;
    System.out.println("UpDown = " + UpDown);
    return new HatchMechCommand(UD);
  } */
}
