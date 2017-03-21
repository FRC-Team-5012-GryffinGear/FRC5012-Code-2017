
package com.gryffingear.robot;

import com.gryffingear.autonomous.TestAuton;
import com.gryffingear.y2017.config.Ports;
import com.gryffingear.y2017.systems.SuperSystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	SuperSystem bot = SuperSystem.getInstance();

	Joystick driverL = new Joystick(Ports.Controls.DRIVER_LEFT_PORT);
	Joystick driverR = new Joystick(Ports.Controls.DRIVER_RIGHT_PORT);
	Joystick gamepad = new Joystick(Ports.Controls.OPERATOR_PORT);

	AnalogInput pot = new AnalogInput(1);

	private CommandGroup currAuton = null;


	@Override
	public void robotInit() {

		CameraServer.getInstance().startAutomaticCapture("cam0", 0);

		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", new TestAuton());

	}


	@Override
	public void disabledInit() {

		cancelAuton();

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().disable();

		currAuton = new TestAuton();
		SmartDashboard.putString("Currently Selected Auton", currAuton.toString());

		System.out.println("Potentiometer Voltlage: " + pot.getVoltage());

		System.out.println("Gyro: " + bot.drivetrain.getYaw());

		bot.utilityarm.printPosition();

		if (driverR.getRawButton(1)) {
			bot.utilityarm.zeroArm();
		}

	}

	@Override
	public void autonomousInit() {

		cancelAuton();

		currAuton = new TestAuton();
		Scheduler.getInstance().add(currAuton);
		Scheduler.getInstance().enable();
	}


	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {

		cancelAuton();

	}


	@Override
	public void teleopPeriodic() {
		

		bot.drive(driverL.getRawAxis(1), driverR.getRawAxis(1), driverL.getRawButton(2));

		bot.operate(gamepad.getRawAxis(1), gamepad.getRawAxis(3), gamepad.getRawButton(7), gamepad.getRawButton(1),
				gamepad.getRawButton(8), gamepad.getRawButton(6));
	}

	@Override
	public void testInit() {

		cancelAuton();

	}


	@Override
	public void testPeriodic() {
		bot.utilityarm.printPosition();
	}

	public void cancelAuton() {
		if (currAuton != null) {
			System.out.println("[STATUS] Auton was running at this time. Cancelling...");
			currAuton.cancel();
			currAuton = null;
		}
	}
}
