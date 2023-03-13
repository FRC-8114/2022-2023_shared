// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.Constants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveTest extends SequentialCommandGroup {
  /** Creates a new DriveTest. */
  public DriveTest() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveBackwardsAuto(2),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck5),
      new DriveBackwardsAuto(8),
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
