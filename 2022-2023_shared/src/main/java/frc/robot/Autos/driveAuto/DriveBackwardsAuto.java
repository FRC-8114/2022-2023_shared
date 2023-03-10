// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystem;

public class DriveBackwardsAuto extends CommandBase {
  /** Creates a new DriveBackwardsAuto. */
  ChassisSpeeds directions = new ChassisSpeeds();
  private DriveSystem m_DriveSystem = new DriveSystem();
  public DriveBackwardsAuto(double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    directions.vxMetersPerSecond = -(speed);
    directions.vyMetersPerSecond = 0;
    directions.omegaRadiansPerSecond = 0;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_DriveSystem.runRemote(directions);
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
