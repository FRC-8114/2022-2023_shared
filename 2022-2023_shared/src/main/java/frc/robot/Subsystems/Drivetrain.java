// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenixpro.*;
import com.ctre.phoenixpro.controls.DutyCycleOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.sim.TalonFXSimState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;



public class Drivetrain extends SubsystemBase {
  
  final static TalonFX CTR1 = new TalonFX(DriveConstants.driveIDTL, "jeff");

  private final TalonFXSimState CTR1Sim = CTR1.getSimState();

  private final static DutyCycleOut CTR1Duty = new DutyCycleOut(0.2);
  
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    
    
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }
  public static void Start(){
    System.out.println("AMONGUS2");
    CTR1.setControl(CTR1Duty);

  }


}
