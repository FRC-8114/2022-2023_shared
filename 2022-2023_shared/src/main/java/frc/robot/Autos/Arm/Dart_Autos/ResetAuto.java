// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.Arm.Dart_Autos;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;

public class ResetAuto extends CommandBase {
  /** Creates a new HighCubeDownAuto. */
  public ResetAuto() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.armSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    RobotContainer.armSystem.usingPIDtoggle(true);
    RobotContainer.armSystem.setSetPoint(0);
    RobotContainer.armSystem.usingPIDtoggle1(true);
    RobotContainer.armSystem.setSetPointArm(0);
    // if(RobotContainer.armSystem.getPosition()<=16)
    // RobotContainer.armSystem.setSetPointArm(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    // if(RobotContainer.armSystem.getPosition()<=48 && RobotContainer.armSystem.getPositionArm() <= 0.4)
    // return true;
    // else 
    // return false;
    return true;
  }
}
