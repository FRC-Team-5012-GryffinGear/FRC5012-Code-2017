package com.gryffingear.y2017.auton.commands;

import com.gryffingear.y2017.systems.SuperSystem;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraightUntilGyroChangeCommand extends Command{

	private double speed = 0.0;
	private double angle = 0.0;
	private double timeout = 8.0;
	
	public DriveStraightUntilGyroChangeCommand(double speed, double angle) {
		
		this.speed = -speed;
		this.angle = 0.0;
		this.timeout = timeout;
		this.setTimeout(timeout);
	}
	
	protected void initialize() {
		SuperSystem.getInstance().drivetrain.resetGyro();
	}
	
	protected boolean isFinished() {
		return (Math.abs(SuperSystem.getInstance().drivetrain.getRawRate()) > 9 || this.isTimedOut());
		
	}
	
	protected void execute() {
		
		double p = -0.04;
		double error = SuperSystem.getInstance().drivetrain.getYaw() - this.angle;
		SuperSystem.getInstance().drivetrain.tankDrive(speed + (p * error), speed - (p * error));
		
	}
	
	protected void end() {
		SuperSystem.getInstance().drivetrain.tankDrive(0.0, 0.0);
	}
	
	protected void interrupted() {
		SuperSystem.getInstance().drivetrain.tankDrive(0.0, 0.0);
	}
	
}
