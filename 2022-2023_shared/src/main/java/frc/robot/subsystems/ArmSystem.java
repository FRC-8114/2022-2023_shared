package frc.robot.subsystems;


import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ArmConstants;
import frc.robot.Constants.DriveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.ctre.phoenixpro.StatusCode;
import com.ctre.phoenixpro.configs.FeedbackConfigs;
import com.ctre.phoenixpro.configs.MotionMagicConfigs;
import com.ctre.phoenixpro.configs.Slot0Configs;
import com.ctre.phoenixpro.configs.TalonFXConfiguration;
import com.ctre.phoenixpro.controls.MotionMagicVoltage;
import com.ctre.phoenixpro.controls.NeutralOut;
import com.ctre.phoenixpro.controls.PositionTorqueCurrentFOC;
import com.ctre.phoenixpro.controls.PositionVoltage;
//import com.ctre.phoenix.motorcontrol.can.TalonFXConfiguration;
import com.ctre.phoenixpro.hardware.TalonFX;
import com.ctre.phoenixpro.signals.NeutralModeValue;

public class ArmSystem extends SubsystemBase {
    // Creates the motor controllers for the dart and the arm
    TalonFXConfiguration TalonConfig = new TalonFXConfiguration();
    public static TalonFX ArmRunController = new TalonFX(ArmConstants.DART_ID, DriveConstants.CANIVORE);
    TalonFXConfiguration TalonFXConfig = new TalonFXConfiguration();
    public static CANSparkMax ArmDeployController = new CANSparkMax(ArmConstants.ARM_DEPLOY_ID, MotorType.kBrushless);
    private SparkMaxPIDController m_pidController;
    private RelativeEncoder m_encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    private final double minSetpoint = 0.1;
    private final double maxSetpoint = 65;
    private double setpoint = minSetpoint;
    private static boolean usingPID = false;
    private static boolean usingPID1 = false;
    private double setpointIncrementer = 0.8;
    private double kp = 0.07;
    private double rotations;
    private final PIDController pid = new PIDController(kp, 0.0, 0.0);
    private final MotionMagicVoltage MotionMagicVolt = new MotionMagicVoltage(0);
    public static boolean ArmInUse = false;
    private final double minSetpointArm = 0.1;
    private final double maxSetpointArm = 2;
    private double setPointArm = minSetpoint;
    private final PositionVoltage positionVoltage = new PositionVoltage(0, true, 0, 0, false);
    private final PositionTorqueCurrentFOC positionTorqueCurrentFOC = new PositionTorqueCurrentFOC(0,0,1,false);

    private static double position = 0.0;
    private static double dartSpeed = 2.5;

    GenericHID keypad = new GenericHID(1);

    // Creates a dart encoder
    final RelativeEncoder ArmDeployControllerEncoder = ArmDeployController.getEncoder();
    final static NeutralOut neutralMode = new NeutralOut();


    // Initializes a potentiometer
    final AnalogPotentiometer potentiometer = new AnalogPotentiometer(0);

    private static final ArmSystem armSystem = new ArmSystem();

    public static ArmSystem getInstance(){
        return armSystem;
    }

    public ArmSystem() {

        // set to factory default and idle so we know what we're working with
        TalonConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        ArmRunController.getConfigurator().apply(TalonConfig);
        ArmRunController.setInverted(Constants.ArmConstants.ARM_DEPLOY_INVERSED);

        ArmDeployControllerEncoder.setPositionConversionFactor(Constants.ArmConstants.ARM_DEPLOY_CONVERSTION_FACTOR);

        ArmDeployController.restoreFactoryDefaults();
        ArmDeployController.setInverted(Constants.ArmConstants.ARM_DEPLOY_INVERSED);
        ArmDeployController.setIdleMode(IdleMode.kBrake);
        m_pidController = ArmDeployController.getPIDController();
        m_encoder = ArmDeployController.getEncoder();

        
       // ArmDeployController.stopMotor();

        //Shuffleboard.getTab("Deployer velocity").add("Change deployer velocity", 0).withWidget(BuiltInWidgets.kNumberSlider).getEntry()
        //    .addListener(event -> {ArmDeployerUp(event.value.getDouble());}, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
        // PID coefficients
        TalonFXConfig.MotorOutput.NeutralMode = NeutralModeValue.Brake;
        kP = 0.07; 
        kI = 1e-4;
        kD = 0.2; 
        kIz = 0.1; 
        kFF = 0.1; 
        kMaxOutput = 0.3; 
        kMinOutput = -0.4;

        // set PID coefficients
        m_pidController.setP(kP);
        m_pidController.setI(kI);
        m_pidController.setD(kD);
        m_pidController.setIZone(kIz);
        m_pidController.setFF(kFF);
        m_pidController.setOutputRange(kMinOutput, kMaxOutput);


        // var Slot0Configs = new Slot0Configs();
        // Slot0Configs.kP = 35;
        // Slot0Configs.kD = 0.3;
        // Slot0Configs.kI = 0;



        // TalonFXConfig.Voltage.PeakForwardVoltage = 1.5;
        // TalonFXConfig.Voltage.PeakReverseVoltage = -1.5;

        // TalonFXConfig.Slot1.kP = 200;
        // TalonFXConfig.Slot1.kD = 12;

        // TalonFXConfig.TorqueCurrent.PeakForwardTorqueCurrent = 700;
        // TalonFXConfig.TorqueCurrent.PeakReverseTorqueCurrent = 700;





        
        MotionMagicConfigs MotionMagicCongifg = new MotionMagicConfigs();

        MotionMagicCongifg.MotionMagicCruiseVelocity = 3;
        MotionMagicCongifg.MotionMagicAcceleration = 32;
        
        MotionMagicCongifg.MotionMagicJerk = 75;
        TalonFXConfig.MotionMagic = MotionMagicCongifg;

        Slot0Configs slot0 = new Slot0Configs();
        slot0.kP = 30;
        slot0.kI = 0;
        slot0.kD = 0.4;
        slot0.kV = 0.15;
        slot0.kS = 0.75;
        TalonFXConfig.Slot0 = slot0;

        FeedbackConfigs feedBackConfig = new FeedbackConfigs();
        feedBackConfig.SensorToMechanismRatio = 12.8;
         
        TalonFXConfig.Feedback = feedBackConfig;
        
        StatusCode status = StatusCode.StatusCodeNotInitialized;
        for(int i = 0; i < 5; i++)
        {
            status = ArmRunController.getConfigurator().apply(TalonFXConfig);
            if(status.isOK()){break;}
            if(!status.isOK()){
                System.out.println("Could not config device." + status.toString());
            }
            
        }



    }

    public void periodic() {
    
        //SmartDashboard.putNumber("SetPoint", rotations);
        SmartDashboard.putNumber("ProcessVariable", m_encoder.getPosition());

        double speed = pid.calculate(getPosition(), setpoint);
        if (usingPID) {
          ArmDeployController.set(speed/dartSpeed);
        }



        if(usingPID1) {
            //var request = new PositionVoltage(0).withSlot(0);

            //ArmRunController.setControl(request.withPosition(10));
        ArmRunController.setControl(MotionMagicVolt.withPosition(setPointArm));
        }

    }

    public void setSetPoint(double newSetpoint) {
        if (newSetpoint > maxSetpoint) {
            setpoint = maxSetpoint;
          } else if (newSetpoint < minSetpoint) {
            setpoint = minSetpoint;
          } else {
            setpoint = newSetpoint;
          }

    }

    public void setSetPointArm(double newSetpointArm) {
        if (newSetpointArm > maxSetpointArm) {
            setPointArm = maxSetpointArm;
          } else if (newSetpointArm < minSetpointArm) {
            setPointArm = minSetpointArm;
          } else {
            setPointArm = newSetpointArm;
          }

    }

    public void extend(double speed) {
        if (usingPID) {
          setSetPoint(setpoint += setpointIncrementer);
        } else {
          ArmDeployController.set(speed);
        }
      }
    
      public void retract(double speed) {
        if (usingPID) {
          setSetPoint(setpoint -= setpointIncrementer);
        } else {
          ArmDeployController.set(-speed);
        }
      }

      public void extendArm(double speed){
        if (usingPID1) {
         setSetPointArm( setPointArm += 0.0175);
          } else {
            ArmRunController.set(speed);
          }
      }
      public void retractArm(double speed){
        if (usingPID1) {
         setSetPointArm( setPointArm -= 0.0175);
        } else {
          ArmRunController.set(-speed);
        }
      }

      public void usingPIDtoggle (boolean onoff) {
        usingPID = onoff;
      }
      public void usingPIDtoggle1 (boolean onoff) {
        usingPID1 = onoff;
      }
    
      public void stop() {
        if (!usingPID) {
          ArmDeployController.set(0);
        }
      }

      public void stopArm() {
        if (!usingPID1) {
          ArmRunController.set(0);
        }
      }
    
      public double getPosition() {
        return m_encoder.getPosition();
      }
      public double getPositionArm() {
        return ArmRunController.getPosition().getValue();
      }

      public void setPositionArm(double pos){
        position = pos;
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
    // In
    public static void ArmDeployerUp (double speed) {
        ArmDeployController.set(-speed);
    }

    // Runs the dart at a given voltage
    public void ArmDeployerUpVoltage (double voltage) {
        ArmDeployController.setVoltage(voltage);
    }

    // Reverses the dart at a speed from 0 to 1.0
    // Out
    public static void ArmDeployerDown (double speed) {
        ArmDeployController.set(speed);
    }

    // Reverses the dart at a given voltage
    public void ArmDeployerDownVoltage (double voltage) {
        ArmDeployController.setVoltage(-voltage);
    }

    public void setDartPosition (double pos) {
        m_pidController.setReference(pos, CANSparkMax.ControlType.kPosition);
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

    public void setDartSpeed(double speed){
        dartSpeed = speed;

    }
}
