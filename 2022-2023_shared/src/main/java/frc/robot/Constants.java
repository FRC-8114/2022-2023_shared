package frc.robot;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import frc.robot.subsystems.ArmSystem;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.hardwareChecks;

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
        public static final boolean ARM_DEPLOY_INVERSED = false;
        public static final double ARM_DEPLOY_CONVERSTION_FACTOR = 0;

        public static DoubleSupplier dartPosition = () -> ArmSystem.ArmDeployController.getEncoder().getPosition();

    }

    public static final class TeleOp {
        //Arm Constants
        public static final double ARM_RUNNER_INITIAL_RUN_SPEED = .2;
        public static final double ARM_RUNNER_INITIAL_REVERSE_SPEED = .2;
        public static final double ARM_DEPLOYER_INITIAL_RUN_SPEED = .2;
        public static final double ARM_DEPLOYER_INITIAL_REVERSE_SPEED = .4;

        //Claw Constants
        public static final double CLAW_SPEED = .25;

        //Turtle Constant
        public static double TURTLE_SPEED = 10;

      }

    public static final class Pigeon2stuff {
        public static BooleanSupplier rollCheck5 = () -> hardwareChecks.rollCheck(-12.0);
        public static BooleanSupplier rollCheck0 = () -> hardwareChecks.rollCheckBetween(10.5,-10.5);
        public static BooleanSupplier rollCheck2 = () -> hardwareChecks.rollCheckGreater(12);
    }   

    public static final class DriveConstants {
        public static final String CANIVORE = "canivore";
    }

    public static final class AutoConstants {
        public static final int ClawAutoRunTime = 0;
        public static final int ArmAutoRunTime = 0;
        public static final int DartAutoRunTime = 0;
        public static final int AutoRunTime = 0;

    }

    public static final class shuffleButtons {
        public static Boolean armUp = false;
        public static Boolean armDown = false;
        public static Boolean dartOut = false;
        public static Boolean dartIn = false;

        public static Boolean clawout = false;
        public static Boolean clawin = false;

        public static BooleanSupplier turtle = () -> DriveSystem.turtleCurrent();
    }
}
    