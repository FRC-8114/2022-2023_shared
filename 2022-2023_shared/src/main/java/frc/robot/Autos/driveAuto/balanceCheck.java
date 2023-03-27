// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import com.ctre.phoenix.sensors.Pigeon2;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSystem;
import frc.robot.subsystems.hardwareChecks;

public class balanceCheck extends CommandBase {
  /** Creates a new balanceCheck. */
  boolean realBalance = false;
  public static int i = 0;
  private ChassisSpeeds directions = new ChassisSpeeds();
  public balanceCheck() {
    addRequirements(RobotContainer.driveSystem);
    directions.vxMetersPerSecond = 2;
    directions.vyMetersPerSecond = 0 * -2.5;
    directions.omegaRadiansPerSecond = 0 * -3;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (hardwareChecks.rollCheck(-12)) {
      directions.vxMetersPerSecond = -1.8;
      RobotContainer.driveSystem.runRemote(directions);
      i = 0;
    }
    else if (hardwareChecks.rollCheckGreater(12)) {
      directions.vxMetersPerSecond = 1.8;
      RobotContainer.driveSystem.runRemote(directions);
      i = 0;
      //new DriveStopAuto();
      //i++;
    }
    else if (hardwareChecks.rollCheckBetween(11.99, -11.99)) {
      new DriveStopAuto();
      i++;
    }
    else if (i >= 30) {
      new DriveStopAuto();
      realBalance = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return realBalance;
  }
}
