package frc.robot.subsystems;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import com.ctre.phoenixpro.hardware.Pigeon2;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj.smartdashboard.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.*;
import frc.robot.CTRSwerve.CTRSwerveDrivetrain;

public class shuffle extends SubsystemBase {
    public ShuffleboardTab controls1 = Shuffleboard.getTab("Controls");
    Pigeon2 pig = new Pigeon2(5, "canivore");
    DoubleSupplier pigyaw = () -> pig.getYaw().getValue();
    GenericEntry turtlespeed 
        = Shuffleboard.getTab("Controls").add("Turtle speed", 0.3).withWidget(BuiltInWidgets.kTextView).getEntry();

    public shuffle() {
        Shuffleboard.getTab("Controls").addDouble("Pigeon Yaw", pigyaw);
    }

    public void periodic() {
        Shuffleboard.update();
        Constants.TeleOp.TURTLE_SPEED = turtlespeed.getDouble(1);
    }
}
