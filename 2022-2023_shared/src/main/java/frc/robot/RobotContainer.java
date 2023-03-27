// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Autos.CubeBalance;
import frc.robot.Autos.CubeLong;
import frc.robot.Autos.CubeShort;
import frc.robot.Autos.driveAuto.ComBalance;
import frc.robot.Autos.driveAuto.noComBalance;
import frc.robot.Constants.TeleOp;
import frc.robot.subsystems.ArmSystem;
import frc.robot.subsystems.Claw;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.hardwareChecks;
import frc.robot.subsystems.shuffle;

import com.ctre.phoenixpro.hardware.Pigeon2;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;


public class RobotContainer {
  public XboxController controller = new XboxController(0);
  //private int oldLeftTriggerAxis, oldRightTriggerAxis, oldPOV;
  public static DriveSystem m_DriveSystem = new DriveSystem();
  shuffle shuffle1 = new shuffle();
  DigitalInput armLim = new DigitalInput(0);
  public static DigitalInput dartLim = new DigitalInput(1);
  public static Boolean limit = false;
  public static Pigeon2 pig = new Pigeon2(5, "canivore");
  GenericHID keypad = new GenericHID(1);

  public static double ArmRunnerRunSpeed = TeleOp.ARM_RUNNER_INITIAL_RUN_SPEED;
  public static double ArmRunnerReverseSpeed = TeleOp.ARM_RUNNER_INITIAL_REVERSE_SPEED;
  public static double ArmDeployerRunSpeed = TeleOp.ARM_DEPLOYER_INITIAL_RUN_SPEED;
  public static double ArmDeployerReverseSpeed = TeleOp.ARM_DEPLOYER_INITIAL_REVERSE_SPEED;

  public static ArmSystem armSystem = ArmSystem.getInstance();
  public static DriveSystem driveSystem = DriveSystem.getInstance();
  
  public RobotContainer() {
    configureBindings();
  }

  private void configureBindings() {}

  public Command getAutonomousCommand() {
    return new CubeShort();
    //return new noComBalance();
    //return new DriveTest();
  }

  public void initializeInstanceVariables() {
    //oldLeftTriggerAxis = oldRightTriggerAxis = 0;
    //oldPOV = -1;
    //oldRightStickButton = false;
    
  }

  public void periodic() {
    m_DriveSystem.teleopPeriodic();

    if (keypad.getRawButton(9)) { //high cone 
      armSystem.setDartSpeed(5);
      armSystem.usingPIDtoggle1(true);
      armSystem.setSetPointArm(2.05);
      armSystem.usingPIDtoggle(true);
      if(armSystem.getPositionArm()>=1.9)
      armSystem.setSetPoint(42);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else if (keypad.getRawButton(8)) { //high cube
      armSystem.setDartSpeed(5);
      armSystem.usingPIDtoggle1(true);
      armSystem.setSetPointArm(1.3);
      armSystem.usingPIDtoggle(true);
      if(armSystem.getPositionArm()>=1)
      armSystem.setSetPoint(45);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else if (keypad.getRawButton(7)) { // reset all
      armSystem.setDartSpeed(3.225);
      armSystem.usingPIDtoggle(true);
      armSystem.setSetPoint(0);
      armSystem.usingPIDtoggle1(true);
      //if(armSystem.getPosition()<=2.4)
      armSystem.setSetPointArm(0);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else if (keypad.getRawButton(4)) { // player station
      armSystem.setDartSpeed(3.5);
      armSystem.usingPIDtoggle1(true);
      armSystem.setSetPointArm(0.65);
      armSystem.usingPIDtoggle(true);
      if(armSystem.getPositionArm()>=0.6)
      armSystem.setSetPoint(32.5);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else if (keypad.getRawButton(6)) { // mid cone
      armSystem.setDartSpeed(2.5);
      armSystem.usingPIDtoggle1(true);
      armSystem.setSetPointArm(0.6);
      armSystem.usingPIDtoggle(true);
      if(armSystem.getPositionArm()>=0.3)
      armSystem.setSetPoint(44.5);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else if (keypad.getRawButton(5)) { // mid cube
      armSystem.setDartSpeed(2.5);
      armSystem.usingPIDtoggle(true);
      armSystem.setSetPoint(44.5);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else if (keypad.getRawButton(3)) { // shoot
      armSystem.setDartSpeed(2.25);
      armSystem.usingPIDtoggle1(true);
      armSystem.setSetPointArm(0.4);
      armSystem.usingPIDtoggle(true);
      if(armSystem.getPositionArm()>=0.1)
      armSystem.setSetPoint(0);
      //armSystem.setSetPoint(ArmSystem.ArmDeployController.getEncoder().getPosition());
    }
    else {
      armSystem.usingPIDtoggle(false);
      armSystem.usingPIDtoggle1(false);
    }

    // switch (controller.getPOV()) {
    //   case 0: // UP
    //     ArmSystem.ArmRunnerUp(ArmRunnerRunSpeed);
    //     break;
    //   case 90: // RIGHT
    //     ArmSystem.ArmDeployerDown(ArmDeployerRunSpeed);
    //     break;
    //   case 180: // DOWN3
    //     ArmSystem.ArmRunnerDown(ArmRunnerReverseSpeed);
    //     break;
    //   case 270: // LEFT
    //     ArmSystem.ArmDeployerUp(ArmDeployerReverseSpeed);
    //     break;
    //   default: // NONE
    //     if (oldPOV >= 0) {
    //       ArmSystem.ArmStop();
    //       ArmSystem.ArmDeployerStop();
    //     }
    //   }

  if ((controller.getAButton() || Constants.shuffleButtons.armDown || keypad.getRawButton(1)) && armLim.get()) {
      armSystem.retractArm(ArmRunnerRunSpeed); }
  else if (controller.getYButton() || Constants.shuffleButtons.armUp) {
      armSystem.extendArm(ArmRunnerReverseSpeed); }
  else if (!armLim.get()) {
    ArmSystem.ArmRunController.setRotorPosition(0);
    armSystem.stopArm();}   
  else { 
    armSystem.stopArm(); } if(controller.getPOV()==270){limit=true;}else{limit=false;}
  
  if ((controller.getBButton() || Constants.shuffleButtons.dartOut) && ((Constants.ArmConstants.dartPosition.getAsDouble() < 32.5) || limit)) {
      armSystem.extend(ArmDeployerRunSpeed); }
  else if ((controller.getXButton() || Constants.shuffleButtons.dartIn || keypad.getRawButton(2)) && dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() > 10)) {
      armSystem.retract(ArmDeployerReverseSpeed); }
  else if ((controller.getXButton() || Constants.shuffleButtons.dartIn || keypad.getRawButton(2)) && dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() < 9) && (Constants.ArmConstants.dartPosition.getAsDouble() > 6)) {
      armSystem.retract(ArmDeployerReverseSpeed/2.1); }
      else if ((controller.getXButton() || Constants.shuffleButtons.dartIn || keypad.getRawButton(2)) && dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() < 6)) {
        armSystem.retract(ArmDeployerReverseSpeed/2.5); }
  else if (!dartLim.get()) {
      armSystem.ArmDeployController.getEncoder().setPosition(0);
      armSystem.extend(ArmDeployerRunSpeed/3); }
  else {
      armSystem.stop(); }

      if (controller.getRightTriggerAxis() != 0 || Constants.shuffleButtons.clawin)
      {
              Claw.SetNeo(-Constants.TeleOp.CLAW_SPEED);
          
      }
      else if (controller.getRightTriggerAxis() == 0 && controller.getLeftTriggerAxis() == 0) {
          //Claw.SetNeo(-0.05);

          Claw.NeoLeft.set(0.08);
          Claw.NeoRight.set(-0.08);
      }
      

      if (controller.getLeftTriggerAxis() != 0 || Constants.shuffleButtons.clawout)
      {
          //Claw.SetNeo(Constants.TeleOp.CLAW_SPEED/2.5);
          
          Claw.NeoLeft.set(-.23);
          Claw.NeoRight.set(.23);

      }
      else if (controller.getPOV() == 180) {
          Claw.SetNeo(0.15);
      }
      else if (controller.getPOV() == 90) {
        Claw.SetNeo(-0.8);
      }
      else if (controller.getPOV() == 0) {
          Claw.SetNeo(1); 
      }
      else if (controller.getRightTriggerAxis() == 0 && controller.getLeftTriggerAxis() == 0 && !Constants.shuffleButtons.clawin && !Constants.shuffleButtons.clawout) {
          //Claw.SetNeo(-0.05);

          Claw.NeoLeft.set(0.08);
          Claw.NeoRight.set(-0.08);
      }
  }

    /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  //private void configureButtonBindings() {
    //buttons
  //}

  public XboxController getXboxController() {
    return controller;
  }
}
