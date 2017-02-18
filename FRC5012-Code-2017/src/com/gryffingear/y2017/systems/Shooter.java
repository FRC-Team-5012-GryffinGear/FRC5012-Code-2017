package com.gryffingear.y2017.systems;

import com.ctre.CANTalon;
import com.gryffingear.y2017.config.Constants;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.StatusFrameRate;

public class Shooter {

	private CANTalon shooterMotor = null;
	private AnalogInput shooterEnc = null;

	private CANTalon preshooterMotor = null;

	private CANTalon turretMotor = null;
	private AnalogInput turretEnc = null;
	private DigitalInput turretABump = null;
	private DigitalInput turretBBump = null;

	public Shooter(int sm, int se, int psm, int tm, int te, int tab, int tbb) {

		shooterMotor = configureTalon(new CANTalon(sm), CANTalon.TalonControlMode.PercentVbus, false,
				Constants.Shooter.SHOOTER_RAMP_RATE);
		shooterEnc = new AnalogInput(se);

		preshooterMotor = configureTalon(new CANTalon(psm), CANTalon.TalonControlMode.PercentVbus, false,
				Constants.Shooter.SHOOTER_RAMP_RATE);

		turretMotor = configureTalon(new CANTalon(tm), CANTalon.TalonControlMode.PercentVbus, false,
				Constants.Shooter.TURRET_RAMP_RATE);
		// turretEnc = new AnalogInput(te);
		// turretABump = new DigitalInput(tab);
		// turretBBump = new DigitalInput(tbb);

		int absolutePosition = turretMotor.getPulseWidthPosition();
		turretMotor.setEncPosition(absolutePosition);
		turretMotor.setFeedbackDevice(CANTalon.FeedbackDevice.CtreMagEncoder_Relative);
		turretMotor.reverseSensor(false);
		turretMotor.configNominalOutputVoltage(0.0, 12.0);
		turretMotor.configPeakOutputVoltage(12.0, -12.0);
		turretMotor.setAllowableClosedLoopErr(0);
		turretMotor.setProfile(0);
		turretMotor.setF(0.0);
		turretMotor.setP(0.1);
		turretMotor.setI(0.0);
		turretMotor.setD(0.0);

	}

	public void runShooter(double shooterv, double preshooterv) {

		shooterMotor.set(shooterv);
		preshooterMotor.set(preshooterv);

	}

	public void runTurret(double turretv) {

		turretMotor.set(turretv);

	}

	public void setPosition(double position) {
		turretMotor.changeControlMode(CANTalon.TalonControlMode.Position);
		turretMotor.set(position);
	}

	private CANTalon configureTalon(CANTalon in, CANTalon.TalonControlMode mode, boolean brakeState, double rampRate) {
		in.changeControlMode(mode);
		in.setVoltageRampRate(rampRate);
		in.enableBrakeMode(brakeState);
		in.enableControl();
		in.clearStickyFaults();

		System.out.println("[CANTalon]" + in.getDescription() + " Initialized at device ID: " + in.getDeviceID());
		return in;
	}
}
