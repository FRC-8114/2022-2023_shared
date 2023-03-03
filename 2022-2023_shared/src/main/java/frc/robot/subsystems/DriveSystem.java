package frc.robot.subsystems;

import com.ctre.phoenixpro.configs.Slot0Configs;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.CTRSwerve.CTRSwerveDrivetrain;
import frc.robot.CTRSwerve.SwerveDriveConstantsCreator;
import frc.robot.CTRSwerve.SwerveDriveTrainConstants;
import frc.robot.CTRSwerve.SwerveModuleConstants;

public class DriveSystem extends SubsystemBase {
    public static final ChassisSpeeds directions = new ChassisSpeeds();;
    private static boolean turtleToggle = false;
    public static double leftX;
    public static double leftY;
    public static double rightX;
    public static double rightY;
    public static XboxController m_joystick = RobotContainer.m_joystick;

    

    public static SwerveDriveTrainConstants drivetrain =
            new SwerveDriveTrainConstants().withPigeon2Id(5).withCANbusName("canivore").withTurnKp(5);

    static Slot0Configs steerGains = new Slot0Configs();
    static Slot0Configs driveGains = new Slot0Configs();

    {
        steerGains.kP = 30;
        steerGains.kD = 0.2;
        driveGains.kP = 1;
    }

    static SwerveDriveConstantsCreator m_constantsCreator =
            new SwerveDriveConstantsCreator(10, 12.8, 3, 17, steerGains, driveGains);

    /**
     * Note: WPI's coordinate system is X forward, Y to the left so make sure all locations are with
     * respect to this coordinate system
     *
     * <p>This particular drive base is 22" x 22"
     */
    public static SwerveModuleConstants frontRight =
            m_constantsCreator.createModuleConstants(
                    13, 11, 12, -0.066650390625, Units.inchesToMeters(21.4 / 2.0), Units.inchesToMeters(-21.4 / 2.0));
    public static SwerveModuleConstants frontLeft =
            m_constantsCreator.createModuleConstants(
                    43, 41, 42, -0.752685546875, Units.inchesToMeters(21.4 / 2.0), Units.inchesToMeters(21.4 / 2.0));
    public static SwerveModuleConstants backRight =
            m_constantsCreator.createModuleConstants(
                    23, 21, 22, -0.771484375, Units.inchesToMeters(-21.4 / 2.0), Units.inchesToMeters(-21.4 / 2.0));
    public static SwerveModuleConstants backLeft =
            m_constantsCreator.createModuleConstants(
                    33, 31, 32, -0.96240234375, Units.inchesToMeters(-21.4 / 2.0), Units.inchesToMeters(21.4 / 2.0));

    public static CTRSwerveDrivetrain m_drivetrain =
            new CTRSwerveDrivetrain(drivetrain, frontLeft, frontRight, backLeft, backRight);



  
    
    static Rotation2d m_lastTargetAngle = new Rotation2d();

    public DriveSystem() {
        
    }
    public static void fieldCentric() {
        leftX = RobotContainer.leftX;
        leftY = RobotContainer.leftY;
        rightX = RobotContainer.rightX;
        rightY = RobotContainer.rightY;

        if (Math.abs(leftY) < 0.1 && Math.abs(leftX) < 0.1) {
            leftY = 0;
            leftX = 0;
        }
        if (Math.abs(rightX) < 0.1 && Math.abs(rightY) < 0.1) {
            rightX = 0;
            rightY = 0;
        }
        if (RobotContainer.m_joystick.getStartButtonPressed()) {
            turtleToggle = !turtleToggle;
        }

        if (!turtleToggle) {
        directions.vxMetersPerSecond = leftY * 1.5;
        directions.vyMetersPerSecond = leftX * -1.5;
        directions.omegaRadiansPerSecond = rightX * -4;
        }
        else if (turtleToggle) {
        directions.vxMetersPerSecond = leftY * (1 * Constants.TeleOp.TURTLE_SPEED);
        directions.vyMetersPerSecond = leftX * (-1 * Constants.TeleOp.TURTLE_SPEED);
        directions.omegaRadiansPerSecond = rightX * (-4 * Constants.TeleOp.TURTLE_SPEED);
        }
        

        

        if (m_joystick.getYButton()) {  /* If we're pressing Y, don't move, otherwise do normal movement */
            m_drivetrain.driveStopMotion();
        } else {
            /* If we're fully field centric, we need to be pretty deflected to target an angle */
            if (Math.abs(rightX) > 0.7 || Math.abs(rightY) > 0.7) {
                m_lastTargetAngle = new Rotation2d(rightY, -rightX);
            } else {
                m_lastTargetAngle = new Rotation2d();
            }
            m_drivetrain.driveFieldCentric(DriveSystem.directions);
        }
    

    }
}
