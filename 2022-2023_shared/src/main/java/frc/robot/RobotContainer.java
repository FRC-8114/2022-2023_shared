// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.CTRSwerve.CTRSwerveDrivetrain;
import frc.robot.CTRSwerve.SwerveDriveConstantsCreator;
import frc.robot.CTRSwerve.SwerveDriveTrainConstants;
import frc.robot.Constants.TeleOp;
import frc.robot.subsystems.ArmSystem;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveSystem;


public class RobotContainer {
  public static SwerveDriveTrainConstants drivetrain = DriveSystem.drivetrain;
  static Rotation2d m_lastTargetAngle = new Rotation2d();
  ArmSystem armSystem = new ArmSystem();
  public XboxController controller = new XboxController(0);

  public static XboxController m_joystick = new XboxController(0);
  public static double leftY, leftX, rightY, rightX;
  private int oldLeftTriggerAxis, oldRightTriggerAxis, oldPOV;

  CTRSwerveDrivetrain m_drivetrain =
    new CTRSwerveDrivetrain(drivetrain, DriveSystem.frontLeft, DriveSystem.frontRight, DriveSystem.backLeft, DriveSystem.backRight);

  public static double ArmRunnerRunSpeed = TeleOp.ARM_RUNNER_INITIAL_RUN_SPEED;
  public static double ArmRunnerReverseSpeed = TeleOp.ARM_RUNNER_INITIAL_REVERSE_SPEED;
  public static double ArmDeployerRunSpeed = TeleOp.ARM_DEPLOYER_INITIAL_RUN_SPEED;
  public static double ArmDeployerReverseSpeed = TeleOp.ARM_DEPLOYER_INITIAL_REVERSE_SPEED;
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }

  public void initializeInstanceVariables() {
    //oldLeftTriggerAxis = oldRightTriggerAxis = 0;
    oldPOV = -1;
    //oldRightStickButton = false;
    
  }

  public void periodic() {
    leftX = m_joystick.getLeftX();
    leftY = -m_joystick.getLeftY();
    rightX = m_joystick.getRightX();
    rightY = -m_joystick.getRightY();

    
    switch (controller.getPOV()) {
      case 0: // UP
        ArmSystem.ArmRunnerUp(ArmRunnerRunSpeed);
        break;
      case 90: // RIGHT
        ArmSystem.ArmDeployerDown(ArmDeployerRunSpeed);
        break;
      case 180: // DOWN
        ArmSystem.ArmRunnerDown(ArmRunnerReverseSpeed);
        break;
      case 270: // LEFT
        ArmSystem.ArmDeployerUp(ArmDeployerReverseSpeed);
        break;
      default: // NONE
        if (oldPOV >= 0) {
          ArmSystem.ArmStop();
          ArmSystem.ArmDeployerStop();
        }
      }
      oldPOV = ((int)controller.getPOV());

      DriveSystem.fieldCentric();
      
    if (m_joystick.getRightBumper()) {
        m_drivetrain.seedFieldRelative();
        // Make us target forward now to avoid jumps
        m_lastTargetAngle = new Rotation2d();
    }

    if (m_joystick.getRightTriggerAxis() != 0)
    {
            Claw.SetNeo(-Constants.TeleOp.CLAW_SPEED);
        
    }
    else if (m_joystick.getRightTriggerAxis() == 0 && m_joystick.getLeftTriggerAxis() == 0) {
        Claw.SetNeo(0.0);
    }
    

    if (m_joystick.getLeftTriggerAxis() != 0)
    {
        Claw.SetNeo(Constants.TeleOp.CLAW_SPEED);

    }
    else if (m_joystick.getRightTriggerAxis() == 0 && m_joystick.getLeftTriggerAxis() == 0) {
        Claw.SetNeo(0.0);
    }
  }

    /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    //buttons
  }

  public XboxController getXboxController() {
    return controller;
  }
}
