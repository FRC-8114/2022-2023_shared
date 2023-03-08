// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenixpro.hardware.Pigeon2;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class pigeon2checks extends SubsystemBase {
  /** Creates a new pigeon2checks. */
  private static Pigeon2 john = new Pigeon2(5, "canivore");
  public pigeon2checks() {

  }

  public static boolean rollCheck(double a) {
    if (john.getRoll().getValue() >= a) {
    return true; }
    else {
    return false; }
  }

  public static void resets() {
    john.setYaw(0);
  }

  @Override
  public void periodic() {
  }
}
