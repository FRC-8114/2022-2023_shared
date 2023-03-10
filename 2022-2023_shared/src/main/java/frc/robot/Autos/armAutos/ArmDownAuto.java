// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.armAutos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.TeleOp;
import frc.robot.subsystems.ArmSystem;

public class ArmDownAuto extends CommandBase {
  /** Creates a new ArmDownAuto. */
  public ArmDownAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ArmSystem.ArmRunnerDown(TeleOp.ARM_DEPLOYER_INITIAL_RUN_SPEED);
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