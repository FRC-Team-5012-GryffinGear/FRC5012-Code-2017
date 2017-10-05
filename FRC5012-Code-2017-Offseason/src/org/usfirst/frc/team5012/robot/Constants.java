package org.usfirst.frc.team5012.robot;

public class Constants {

	public static class Drivetrain {

	
		public static int DRIVETRAIN_RAMP_RATE = 36;   //edit purposes , original value was " 48 "
	}


    public static final double CD_SENS_HIGH         = 1;
    public static final double CD_SENS_LOW          = 1.111;
    public static final double CD_WHEEL_NONLIN_HIGH = 0.1;
    public static final double CD_WHEEL_NONLIN_LOW  = 0.8;
    public static final double CD_NEG_INERTIA       = 3.0;
    

	
	public static class SuperSystem {
		
		public static final double AUTO_AIM_KP = -.0015625;
		public static final double AUTO_AIM_TARGET = 0;
		public static final double AUTO_AIM_TARGET_OFFSET = 0;
		
	}
	
	public static class UtilityArm {
		public static final double UTILITY_ARM_STOW_POSITION = .81;
		public static final double UTILITY_ARM_SCORING_POSITION = .38;
		public static final double UTILITY_ARM_GROUND_POSITION = 0;
		public static final double UTILITY_ARM_SHUTTLING_POSITION = .21;
	}
}



    