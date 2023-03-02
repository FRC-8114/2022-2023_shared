package frc.robot.subsystems;

import edu.wpi.first.networktables.*;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.*;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.DriveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.controls.CoastOut;
import com.ctre.phoenixpro.controls.NeutralOut;
//import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class ArmSystem extends SubsystemBase {
    // Creates the motor controllers for the dart and the arm
    TalonFXConfiguration TalonConfig = new TalonFXConfiguration();
    public static TalonFX ArmRunController = new TalonFX(ArmConstants.DART_ID, DriveConstants.CANIVORE);
    public static CANSparkMax ArmDeployController = new CANSparkMax(ArmConstants.ARM_DEPLOY_ID, MotorType.kBrushless);

    // Creates a dart encoder
    final RelativeEncoder ArmDeployControllerEncoder = ArmDeployController.getEncoder();
    final static NeutralOut neutralMode = new NeutralOut();


    // Initializes a potentiometer
    final AnalogPotentiometer potentiometer = new AnalogPotentiometer(0);

    public ArmSystem() {

        // set to factory default and idle so we know what we're working with
        TalonConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        ArmRunController.getConfigurator().apply(TalonConfig);
        ArmRunController.setInverted(Constants.ArmConstants.ARM_DEPLOY_INVERSED);
        ArmRunController.setControl(neutralMode);

        ArmDeployControllerEncoder.setPositionConversionFactor(Constants.ArmConstants.ARM_DEPLOY_CONVERSTION_FACTOR);

        ArmDeployController.restoreFactoryDefaults();
        ArmDeployController.setInverted(Constants.ArmConstants.ARM_DEPLOY_INVERSED);
        ArmDeployController.setIdleMode(IdleMode.kBrake);
       // ArmDeployController.stopMotor();

        //Shuffleboard.getTab("Deployer velocity").add("Change deployer velocity", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry()
        //    .addListener(event -> {ArmDeployerUp(event.value.getDouble());}, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        
    }

    // Runs the arm at a speed from 0 to 1.0
    public static void ArmRunnerUp (double speed) {
        ArmRunController.set(speed);
    }

    // Runs the arm at a given voltage
    public void ArmRunnerUpVoltage (double voltage) {
        ArmRunController.setVoltage(voltage);
    }

    // Reverses the arm at a speed from 0 to 1.0
    public static void ArmRunnerDown (double speed) {
        ArmRunController.set(-speed);
    }

    // Reverses the arm at a given voltage
    public void ArmRunnerDownVoltage (double voltage) {
        ArmRunController.setVoltage(-voltage);
    }

    // Stops all motion on the arm
    public static void ArmStop() {
        ArmRunController.setVoltage(0);
    }

    // Runs the dart at a speed from 0 to 1.0
    public static void ArmDeployerUp (double speed) {
        ArmDeployController.set(speed);
    }

    // Runs the dart at a given voltage
    public void ArmDeployerUpVoltage (double voltage) {
        ArmDeployController.setVoltage(voltage);
    }

    // Reverses the dart at a speed from 0 to 1.0
    public static void ArmDeployerDown (double speed) {
        ArmDeployController.set(-speed);
    }

    // Reverses the dart at a given voltage
    public void ArmDeployerDownVoltage (double voltage) {
        ArmDeployController.setVoltage(-voltage);
    }

    // Stops all motion on the dart
    public static void ArmDeployerStop() {
        ArmDeployController.setVoltage(0);
    }

    // Get the current angle of the potentiometer
    public double getPotentiometerAngle() {
        return potentiometer.get();
    }

    // Get the current extended length of the dart
    public double getDartPosition() {
        return 6 + getPotentiometerAngle() * (6/4.4);
    }

    // Setters for shuffleboard
    public void setArmRunInversed(boolean inverted) {
        ArmRunController.setInverted(inverted);
    }

    public void setArmDeployInversed(boolean inverted) {
        ArmDeployController.setInverted(inverted);
    }
}
