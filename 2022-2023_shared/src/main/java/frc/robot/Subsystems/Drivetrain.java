// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems;

import com.ctre.phoenixpro.*;
import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.controls.DutyCycleOut;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.sim.TalonFXSimState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;



public class Drivetrain extends SubsystemBase {
  
  final static TalonFX DriveTL = new TalonFX(DriveConstants.driveIDTL, DriveConstants.Canivore);
  final static TalonFX DriveBL = new TalonFX(DriveConstants.driveIDBL, DriveConstants.Canivore);
  final static TalonFX DriveTR = new TalonFX(DriveConstants.driveIDTR, DriveConstants.Canivore);
  final static TalonFX DriveBR = new TalonFX(DriveConstants.driveIDBR, DriveConstants.Canivore);


  final static TalonFX AngleTL = new TalonFX(DriveConstants.steerIDTL, DriveConstants.Canivore);
  final static TalonFX AngleBL = new TalonFX(DriveConstants.steerIDBL, DriveConstants.Canivore);
  final static TalonFX AngleTR = new TalonFX(DriveConstants.steerIDTR, DriveConstants.Canivore);
  final static TalonFX AngleBR = new TalonFX(DriveConstants.steerIDBR, DriveConstants.Canivore);

  private final static DutyCycleOut CTRDuty = new DutyCycleOut(0);
  
  /** Creates a new Drivetrain. */
  public Drivetrain() {
    
      var CTRConfig = new TalonFXConfiguration();


      DriveTL.getConfigurator().apply(CTRConfig);
      DriveBL.getConfigurator().apply(CTRConfig);
      DriveTR.getConfigurator().apply(CTRConfig);
      DriveBR.getConfigurator().apply(CTRConfig);

      AngleTL.getConfigurator().apply(CTRConfig);
      AngleBL.getConfigurator().apply(CTRConfig);
      AngleTR.getConfigurator().apply(CTRConfig);
      AngleBR.getConfigurator().apply(CTRConfig);

    
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }
  public static void Start(){

    CTRDuty.Output = 0.05;
    DriveTL.setControl(CTRDuty);
    DriveBL.setControl(CTRDuty);
    DriveTR.setControl(CTRDuty);
    DriveBR.setControl(CTRDuty);

    AngleTL.setControl(CTRDuty);
    AngleBL.setControl(CTRDuty);
    AngleTR.setControl(CTRDuty);
    AngleBR.setControl(CTRDuty);

  }


}
