// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.TeleOp;
import frc.robot.subsystems.ArmSystem;


public class RobotContainer {
  ArmSystem armSystem = new ArmSystem();
  public XboxController controller = new XboxController(0);
  private int oldLeftTriggerAxis, oldRightTriggerAxis, oldPOV;

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
