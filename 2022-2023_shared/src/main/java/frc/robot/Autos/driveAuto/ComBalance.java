// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.Autos.Arm.Dart_Autos.HighCubeUpAuto;
import frc.robot.Autos.Arm.Dart_Autos.ResetAuto;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ComBalance extends SequentialCommandGroup {
  /** Creates a new DriveTest. */
  public ComBalance() {
    addRequirements(RobotContainer.driveSystem);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveBackwardsAuto(2.55,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck5),
      // new DriveStopAuto(),
      // Commands.waitSeconds(0.25),
      new DriveBackwardsAuto(2.35,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      new DriveStopAuto(),

      Commands.waitSeconds(0.125),
      new DriveBackwardsAuto(1,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck2),
      new DriveBackwardsAuto(1,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      new DriveStopAuto(),
      Commands.waitSeconds(0.125),
      new DriveBackwardsAuto(1.35,0,0),
      Commands.waitSeconds(0.95),
      new DriveStopAuto(),
      
      // RobotContainer.driveSystem.followTrajectoryCommand(PathPlanner.loadPath("Spin", new PathConstraints(6, 5)), true ),
      // new DriveBackwardsAuto(2),
      // Commands.waitUntil(Constants.Pigeon2stuff.rollCheck5),
      // new DriveBackwardsAuto(2),
      // Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      // new DriveStopAuto(),

      new DriveForwardAuto(0,0,0),

      new DriveStopAuto(),


      new DriveForwardAuto(2.15,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck2),
      Commands.waitSeconds(1),
      new DriveForwardAuto(2,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      
      new DriveStopAuto(),
      new balanceCheck(),
      //new DriveForwardAuto(2.2),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      new balanceCheck(),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
      new DriveStopAuto()
    );
  }
}
