// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4505.Robot18.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4505.Robot18.Robot;

/**
 *
 */
public class AutoRight extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	public double tStart, angle;
	public int state;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public AutoRight() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    	state = 0;
    	tStart = Timer.getFPGATimestamp();
    	Robot.drivetrain.resetSensors();
    	angle = Robot.drivetrain.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	//default, drive 120 inches
    	while((Timer.getFPGATimestamp()-tStart) <= 15.0 && state != -1) {
    		if (state == 0) {
    			Robot.drivetrain.driveStraight(0.5, angle, Robot.drivetrain.getAngle(), 0.5);//check direction
    			if (Robot.drivetrain.getInches(1) >= 120.0) {//if finished
    				Robot.drivetrain.stop();
    				state++;
    			}
    		} else if (state == 1) {
    			state = -1;
    		}
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return (Timer.getFPGATimestamp()-tStart) >= 15.0;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
}
