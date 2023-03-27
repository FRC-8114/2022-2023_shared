// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos.driveAuto;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.RobotContainer;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class noComBalance extends SequentialCommandGroup {
  /** Creates a new noComBalance. */
  public noComBalance() {
    addRequirements(RobotContainer.driveSystem);
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new DriveBackwardsAuto(3.05,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck5),
      //new DriveBackwardsAuto(2.55,0,0),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      new DriveStopAuto(),
      //new balanceCheck(),
      Commands.waitUntil(Constants.Pigeon2stuff.rollCheck0),
      //new balanceCheck(),                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
      new DriveStopAuto()
    );
  }
}
