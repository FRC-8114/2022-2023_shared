// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import com.ctre.phoenixpro.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class rollCheck extends CommandBase {
  /** Creates a new rollCheck. */
  Boolean sus = false;
  Pigeon2 amongsus = new Pigeon2(5, "canivore");
  public rollCheck() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (amongsus.getRoll().getValue() >= 5) {
      sus = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return sus;
  }
}
