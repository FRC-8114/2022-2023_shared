// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.dartAutos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Autos.ConstantsAuto.dartConstantsAuto;
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
    ArmSystem.ArmDeployerUp(dartConstantsAuto.dartUpConstantAuto);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (RobotContainer.dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() > 10)) {
      ArmSystem.ArmDeployerUp(dartConstantsAuto.dartUpConstantAuto1); }
    else if (RobotContainer.dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() < 9) && (Constants.ArmConstants.dartPosition.getAsDouble() > 6)) {
      ArmSystem.ArmDeployerUp(dartConstantsAuto.dartUpConstantAuto2); }
      else if (RobotContainer.dartLim.get() && (Constants.ArmConstants.dartPosition.getAsDouble() < 6)) {
      ArmSystem.ArmDeployerUp(dartConstantsAuto.dartUpConstantAuto3); }
    else if (!RobotContainer.dartLim.get()) {
      ArmSystem.ArmDeployController.getEncoder().setPosition(0);
      ArmSystem.ArmDeployerDown(dartConstantsAuto.dartDownConstantAuto1); 
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
