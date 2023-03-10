// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveSystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoPointAuto extends SequentialCommandGroup {
  /** Creates a new TwoPointAuto. */
  private DriveSystem m_DriveSystem = new DriveSystem();
  //private double hi = 0.0;

  public TwoPointAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());


    

    addCommands(
      new HighCubePointAuto(),
      Commands.waitSeconds(0.2),
      m_DriveSystem.followTrajectoryCommand(PathPlanner.loadPath("New Path", new PathConstraints(6, 4)), true )
      
      
      );
  }
}