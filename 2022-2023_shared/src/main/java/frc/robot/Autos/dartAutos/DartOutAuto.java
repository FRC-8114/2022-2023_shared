// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.dartAutos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Autos.ConstantsAuto.dartConstantsAuto;
import frc.robot.subsystems.ArmSystem;

public class DartOutAuto extends CommandBase {
  /** Creates a new DartOutAuto. */
  public DartOutAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ArmSystem.ArmDeployerDown(dartConstantsAuto.dartDownConstantAuto);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
