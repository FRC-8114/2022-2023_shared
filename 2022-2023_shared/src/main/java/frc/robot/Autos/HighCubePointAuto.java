// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Autos.armAutos.ArmDownAuto;
import frc.robot.Autos.armAutos.ArmStopAuto;
import frc.robot.Autos.armAutos.ArmUpAuto;
import frc.robot.Autos.clawAutos.ClawAuto;
import frc.robot.Autos.dartAutos.DartInAuto;
import frc.robot.Autos.dartAutos.DartOutAuto;
import frc.robot.Autos.dartAutos.DartStopAuto;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HighCubePointAuto extends SequentialCommandGroup {
  /** Creates a new HighCubePointAuto. */
  public HighCubePointAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
    new ArmUpAuto(),
    Commands.waitSeconds(0.5),
    new ArmStopAuto(),
    new DartOutAuto(),
    Commands.waitSeconds(0.75),
    new DartStopAuto(),
    new ClawAuto(0.75),
    new ArmDownAuto(),
    Commands.waitSeconds(0.75),
    new ArmStopAuto(),
    new DartInAuto(),
    Commands.waitSeconds(0.70),
    new DartStopAuto()



    

    );
  }
}
