// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.dartAutos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Constants.TeleOp;
import frc.robot.subsystems.ArmSystem;

public class DartInAuto extends CommandBase {
  /** Creates a new DartInAuto. */
  Boolean stop = false;
  public DartInAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ArmSystem.ArmDeployerUp(0.25);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() > 10)) {
      ArmSystem.ArmDeployerUp(0.3); }
    else if (RobotContainer.dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() < 9) && (Constants.ArmConstants.dartPosition.getAsDouble() > 6)) {
      ArmSystem.ArmDeployerUp(0.2); }
      else if (RobotContainer.dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() < 6)) {
      ArmSystem.ArmDeployerUp(0.15); }
    else if (!RobotContainer.dartLim.get()) {
      RobotContainer.armSystem.ArmDeployController.getEncoder().setPosition(0);
      ArmSystem.ArmDeployerDown(0.1); 
      stop = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return stop;
  }
}
