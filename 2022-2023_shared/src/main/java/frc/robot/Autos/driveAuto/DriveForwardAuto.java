// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;

public class DriveForwardAuto extends CommandBase {
  /** Creates a new DriveForwardAuto. */
  ChassisSpeeds directions = new ChassisSpeeds();
  Rotation2d target = new Rotation2d();
  
  public DriveForwardAuto(double speed,double rotationx, double rotationy) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(RobotContainer.driveSystem);
    directions.vxMetersPerSecond = speed;
    directions.vyMetersPerSecond = 0 * -2.5;
    directions.omegaRadiansPerSecond = 0 * -3;
    target = new Rotation2d(rotationx,rotationy);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.driveSystem.runFieldRemote(directions.vxMetersPerSecond,directions.vyMetersPerSecond,target);
    //RobotContainer.driveSystem.runRemote(directions);
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
