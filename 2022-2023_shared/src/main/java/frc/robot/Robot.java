// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenixpro.configs.Slot0Configs;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.CTRSwerve.CTRSwerveDrivetrain;
import frc.robot.CTRSwerve.SwerveDriveConstantsCreator;
import frc.robot.CTRSwerve.SwerveDriveTrainConstants;
import frc.robot.CTRSwerve.SwerveModuleConstants;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

    SwerveDriveTrainConstants drivetrain =
            new SwerveDriveTrainConstants().withPigeon2Id(5).withCANbusName("canivore").withTurnKp(5);

    Slot0Configs steerGains = new Slot0Configs();
    Slot0Configs driveGains = new Slot0Configs();

    public boolean turtleToggle = false;

    {
        steerGains.kP = 30;
        steerGains.kD = 0.2;
        driveGains.kP = 1;
    }

    SwerveDriveConstantsCreator m_constantsCreator =
            new SwerveDriveConstantsCreator(10, 12.8, 3, 17, steerGains, driveGains);

    /**
     * Note: WPI's coordinate system is X forward, Y to the left so make sure all locations are with
     * respect to this coordinate system
     *
     * <p>This particular drive base is 22" x 22"
     */
    SwerveModuleConstants frontRight =
            m_constantsCreator.createModuleConstants(
                    13, 11, 12, -0.066650390625, Units.inchesToMeters(21.4 / 2.0), Units.inchesToMeters(-21.4 / 2.0));
    SwerveModuleConstants frontLeft =
            m_constantsCreator.createModuleConstants(
                    43, 41, 42, -0.752685546875, Units.inchesToMeters(21.4 / 2.0), Units.inchesToMeters(21.4 / 2.0));
    SwerveModuleConstants backRight =
            m_constantsCreator.createModuleConstants(
                    23, 21, 22, -0.771484375, Units.inchesToMeters(-21.4 / 2.0), Units.inchesToMeters(-21.4 / 2.0));
    SwerveModuleConstants backLeft =
            m_constantsCreator.createModuleConstants(
                    33, 31, 32, -0.96240234375, Units.inchesToMeters(-21.4 / 2.0), Units.inchesToMeters(21.4 / 2.0));

    CTRSwerveDrivetrain m_drivetrain =
            new CTRSwerveDrivetrain(drivetrain, frontLeft, frontRight, backLeft, backRight);

    XboxController m_joystick = new XboxController(0);

  
    
    Rotation2d m_lastTargetAngle = new Rotation2d();

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {}

    @Override
    public void robotPeriodic() {
        double leftY = -m_joystick.getLeftY();
        double leftX = m_joystick.getLeftX();
        double rightX = m_joystick.getRightX();
        double rightY = -m_joystick.getRightY();

        if (Math.abs(leftY) < 0.1 && Math.abs(leftX) < 0.1) {
            leftY = 0;
            leftX = 0;
        }
        if (Math.abs(rightX) < 0.1 && Math.abs(rightY) < 0.1) {
            rightX = 0;
            rightY = 0;
        }

        if (m_joystick.getStartButtonPressed()) {
            turtleToggle = !turtleToggle;
        }


        var directions = new ChassisSpeeds();

        if (!turtleToggle) {
        directions.vxMetersPerSecond = leftY * 1;
        directions.vyMetersPerSecond = leftX * -1;
        directions.omegaRadiansPerSecond = rightX * -4;
        }
        else if (turtleToggle) {
        directions.vxMetersPerSecond = leftY * (1 * 0.25);
        directions.vyMetersPerSecond = leftX * (-1 * 0.25);
        directions.omegaRadiansPerSecond = rightX * (-4 * 0.25);
        }
        

        /* If we're pressing Y, don't move, otherwise do normal movement */
        if (m_joystick.getYButton()) {
            m_drivetrain.driveStopMotion();
        } else {
            /* If we're fully field centric, we need to be pretty deflected to target an angle */
            if (Math.abs(rightX) > 0.7 || Math.abs(rightY) > 0.7) {
                m_lastTargetAngle = new Rotation2d(rightY, -rightX);
            } else {
                m_lastTargetAngle = new Rotation2d();
            }
            m_drivetrain.driveFieldCentric(directions);
        }

        if (m_joystick.getRightBumper()) {
            m_drivetrain.seedFieldRelative();
            // Make us target forward now to avoid jumps
            m_lastTargetAngle = new Rotation2d();
        }


      
    }

    @Override
    public void autonomousInit() {}

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void teleopInit() {
        m_lastTargetAngle = m_drivetrain.getPoseMeters().getRotation();
    }

    @Override
    public void teleopPeriodic() {}

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}
