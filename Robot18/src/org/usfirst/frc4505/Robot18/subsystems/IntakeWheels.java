// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4505.Robot18.subsystems;

import org.usfirst.frc4505.Robot18.RobotMap;
import org.usfirst.frc4505.Robot18.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class IntakeWheels extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftIn = RobotMap.intakeWheelsleftIn;
    private final SpeedController rightIn = RobotMap.intakeWheelsrightIn;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void set(double pow) {
    	leftIn.set(-pow);
    	rightIn.set(pow*RobotMap.prefs.getDouble("differential", 1.0));
    }
    
    public void set(double pow, boolean flip) {
    	set((flip) ? pow : -pow);
    }
    
    public void stop() {
    	set(0);
    }
    
    
    public void updateIntakeConstants() {
    	RobotMap.intakeSpd = RobotMap.prefs.getDouble("intakeSpd", RobotMap.intakeSpd);
    }
    
    
    
    public void updateSafeguard() {
    	RobotMap.safeguard = RobotMap.prefs.getBoolean("safeguard", RobotMap.safeguard);
    }
}

