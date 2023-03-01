// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import javax.swing.text.Position;

import com.ctre.phoenixpro.*;
import com.ctre.phoenixpro.hardware.CANcoder;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.controls.*;
import com.ctre.phoenixpro.configs.*;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final TalonFX john13 = new TalonFX(13,"canivore");
  private final CANcoder john12 = new CANcoder(12,"canivore");
  private final CANcoder john22 = new CANcoder(22,"canivore");
  private final CANcoder john32 = new CANcoder(32,"canivore");
  private final CANcoder john42 = new CANcoder(42,"canivore");
  private final DutyCycleOut regularturn =  new DutyCycleOut(0.2);

  private RobotContainer m_robotContainer;

  @Override
  public void robotInit() {
    m_robotContainer = new RobotContainer();
    System.out.println("hiamoghus");
    System.out.println("amongus\n\n\n\n");

    CANcoderConfiguration canconfig = new CANcoderConfiguration();
    canconfig.MagnetSensor.MagnetOffset = 0;
    john12.getConfigurator().apply(canconfig);
  }

  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void disabledExit() {}

  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void autonomousExit() {}

  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
    }
  }

  @Override
  public void teleopPeriodic() {
    System.out.println(john12.getPosition() + " | " + john22.getPosition() + " | " + john32.getPosition() + " | " + john42.getPosition() + "\n");
  }

  @Override
  public void teleopExit() {}

  @Override
  public void testInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  @Override
  public void testPeriodic() {}

  @Override
  public void testExit() {}
}
