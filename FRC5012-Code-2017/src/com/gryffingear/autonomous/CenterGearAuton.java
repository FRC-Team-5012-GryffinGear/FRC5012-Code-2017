package com.gryffingear.autonomous;
import com.gryffingear.y2017.auton.commands.DriveStraightCommand;
import com.gryffingear.y2017.auton.commands.DriveStraightUntilGyroChangeCommand;
import com.gryffingear.y2017.auton.commands.ReleaseGearCommand;
import com.gryffingear.y2017.auton.commands.WaitCommand;
import com.gryffingear.y2017.auton.commands.setArmPositionCommand;
import com.gryffingear.y2017.config.Constants;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterGearAuton extends CommandGroup {

	public CenterGearAuton() {
		
		
		this.addSequential(new setArmPositionCommand(Constants.UtilityArm.UTILITY_ARM_STOW_POSITION, 1));
		this.addSequential(new DriveStraightCommand(.4, 0, 1));
		this.addSequential(new DriveStraightUntilGyroChangeCommand(.3, 0));
		this.addSequential(new DriveStraightCommand(-.2, 0, 1));
		this.addSequential(new DriveStraightCommand(0, 0, .5));
		this.addSequential(new setArmPositionCommand(Constants.UtilityArm.UTILITY_ARM_SCORING_POSITION, 1));
		this.addSequential(new DriveStraightCommand(.12, 0, .8));
		this.addSequential(new DriveStraightCommand(0, 0, .25));
		this.addSequential(new ReleaseGearCommand(Constants.UtilityArm.UTILITY_ARM_GROUND_POSITION, -1, .5));
		this.addSequential(new DriveStraightCommand(-.15, 0, 2));
		this.addSequential(new ReleaseGearCommand(Constants.UtilityArm.UTILITY_ARM_GROUND_POSITION, -1, .5));
		this.addSequential(new DriveStraightCommand(-.4, 0, 5));
		this.addSequential(new DriveStraightCommand(0, 0, 5));
		
	}
}