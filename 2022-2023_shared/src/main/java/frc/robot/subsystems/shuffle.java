package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import com.ctre.phoenixpro.hardware.Pigeon2;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.*;
import frc.robot.CTRSwerve.CTRSwerveDrivetrain;

public class shuffle extends SubsystemBase {
    public ShuffleboardTab controls1 = Shuffleboard.getTab("Controls");
    Pigeon2 pig = new Pigeon2(5, "canivore");
    DoubleSupplier pigyaw = () -> pig.getYaw().getValue();
    DoubleSupplier pigpitch = () -> pig.getPitch().getValue();
    DoubleSupplier pigroll = () -> pig.getRoll().getValue();

    
    GenericEntry turtlespeed 
        = Shuffleboard.getTab("Controls").add("Turtle speed", 0.65).withWidget(BuiltInWidgets.kTextView).getEntry();

    GenericEntry armU
        = Shuffleboard.getTab("Controls").add("Arm Up", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    GenericEntry armD
        = Shuffleboard.getTab("Controls").add("Arm Down", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    GenericEntry dartO
        = Shuffleboard.getTab("Controls").add("Dart Out", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    GenericEntry dartI
        = Shuffleboard.getTab("Controls").add("Dart In", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();

    GenericEntry clawOut
        = Shuffleboard.getTab("Controls").add("Claw Out", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();
    GenericEntry clawIn
        = Shuffleboard.getTab("Controls").add("Claw In", false).withWidget(BuiltInWidgets.kToggleButton).getEntry();

    public shuffle() {
        Shuffleboard.getTab("Controls").addDouble("Pigeon Yaw", pigyaw);
        Shuffleboard.getTab("Controls").addDouble("Pigeon Pitch", pigpitch);
        Shuffleboard.getTab("Controls").addDouble("Pigeon Roll", pigroll);
        Shuffleboard.getTab("Controls").add(CTRSwerveDrivetrain.m_field);
        // Shuffleboard.getTab("Controls").add("Successful Daqs", CTRSwerveDrivetrain.getFailedDaqs());
        // Shuffleboard.getTab("Controls").add("Failed Daqs", CTRSwerveDrivetrain.getFailedDaqs());
        Shuffleboard.getTab("Controls").addDouble("X Pos", CTRSwerveDrivetrain.PosX);
        Shuffleboard.getTab("Controls").addDouble("Y Pos", CTRSwerveDrivetrain.PosY);
        Shuffleboard.getTab("Controls").addDouble("Angle", CTRSwerveDrivetrain.Rotation);
        Shuffleboard.getTab("Controls").addDouble("Dart Position", Constants.ArmConstants.dartPosition);
        Shuffleboard.getTab("Controls").addCamera("Seeing Camera", "Pi Cam", "10.81.14.20:1182/stream.mjpg?1678411575267");
    }
    private int i = 0;
    public void periodic() {
        
        if (i == 25) {
        Shuffleboard.update();
        Constants.TeleOp.TURTLE_SPEED = turtlespeed.getDouble(1);
        Constants.shuffleButtons.armUp = armU.getBoolean(false);
        Constants.shuffleButtons.armDown = armD.getBoolean(false);
        Constants.shuffleButtons.dartOut = dartO.getBoolean(false);
        Constants.shuffleButtons.dartIn = dartI.getBoolean(false);

        Constants.shuffleButtons.clawin = clawIn.getBoolean(false);
        Constants.shuffleButtons.clawout = clawOut.getBoolean(false);
        i = 0;
        }
        else {
        i++;
        }
    }
}
