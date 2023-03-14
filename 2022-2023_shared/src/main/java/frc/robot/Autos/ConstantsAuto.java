// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Autos;

/** Add your docs here. */
public final class ConstantsAuto {

    public static final class armConstantsAuto{
        
        public static final double armDownConstantAuto = 0.4;
        public static final double armUpConstantAuto = 0.3;
        // sets the speed for armUp and armDown

    }
    public static final class dartConstantsAuto{

        public static final double dartUpConstantAuto = 0.25;
        public static final double dartUpConstantAuto1 = 0.3;
        public static final double dartUpConstantAuto2 = 0.2;
        public static final double dartUpConstantAuto3 = 0.15;
        public static final double dartDownConstantAuto1 = 0.1;

        /* dartUp is the init speed
         * dartUp1 is the lim 1 speed
         * dartUp2 is the lim 2 speed
         * dartUp3 is the lim 3 speed
         * dartDown1 is the end of the lim cycle speed
         */
        public static final double dartDownConstantAuto = 0.4;
        // sets the dartOut speed

    }
    public static final class clawConstantsAuto{

        public static final double clawForwardConstantAuto = 0.2;
        //sets the speed of clawForward
        public static final double clawInwardConstantAuto = -0.2;

    }
    public static final class driveConstantsAuto{

        // add if needed, none for now.

    }
}
