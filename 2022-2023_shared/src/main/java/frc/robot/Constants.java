package frc.robot;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class Swerve {
        public static final int pigeonID = 1;
        public static final boolean invertGyro = false; // Always ensure Gyro is CCW+ CW-
        
    }
// will be renamed to arm/dart later once code works
    public static final class ArmConstants {

        public static final int DART_ID = 7;
        public static final int ARM_DEPLOY_ID = 1;
        public static final int ARM_RUN_PORT = 90;
        public static final boolean ARM_RUN_INVERSED = false;
        public static final boolean ARM_DEPLOY_INVERSED = true;
        public static final double ARM_DEPLOY_CONVERSTION_FACTOR = 0;

    }

    public static final class TeleOp {
        //Arm Constants
        public static final double ARM_RUNNER_INITIAL_RUN_SPEED = .2;
        public static final double ARM_RUNNER_INITIAL_REVERSE_SPEED = .2;
        public static final double ARM_DEPLOYER_INITIAL_RUN_SPEED = .3;
        public static final double ARM_DEPLOYER_INITIAL_REVERSE_SPEED = .3;

        //Claw Constants
        public static final double CLAW_SPEED = .3;

        //Turtle Constant
        public static double TURTLE_SPEED = .3;

      }

    public static final class DriveConstants {
        public static final String CANIVORE = "canivore";
    }
}
