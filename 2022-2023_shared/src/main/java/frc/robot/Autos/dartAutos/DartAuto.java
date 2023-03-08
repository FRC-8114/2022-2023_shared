// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.dartAutos;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DartAuto extends SequentialCommandGroup {
  /** Creates a new DartAuto. */
  public DartAuto(double seconds, double secondarySeconds) {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DartOutAuto(),
      Commands.waitSeconds(seconds),
      new DartInAuto(),
      Commands.waitSeconds(secondarySeconds),
      new DartStopAuto()

    );
  }
}
