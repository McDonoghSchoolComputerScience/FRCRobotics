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
import org.usfirst.frc4505.Robot18.RobotMap;

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
		boolean correctColor;
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		// default, drive 120 inches
		if (Timer.getFPGATimestamp() <= 15.0 && state != -1) {
			if (state == 0) {
				Robot.drivetrain.driveStraight(0.5, angle, Robot.drivetrain.getAngle(), 0.5);
				if (Robot.drivetrain.goneInches(80 - RobotMap.robotLen)) {
					state = 1;
				}
			} else if (state == 1) {
				boolean b = Robot.drivetrain.readFromI2C(1);
				if (!b) {
					Robot.drivetrain.stop();
					state++;
					if ((RobotMap.readIn[0] == 1 && RobotMap.isBlue) || (RobotMap.readIn[0] == 2 && !RobotMap.isBlue)) { //check that 1 is blue, 2 is red
						state = 2;//correct color
					} else if ((RobotMap.readIn[0] == 1 && !RobotMap.isBlue) || (RobotMap.readIn[0] == 2 && RobotMap.isBlue)){
						state=3;//wrong color
				    }
				}
			}else if (state==2) { //correctColor= true, drive up to switch and place box
				Robot.drivetrain.driveStraight(0.5, angle, Robot.drivetrain.getAngle(), 0.5);
				if(Robot.drivetrain.goneInches(140-RobotMap.robotLen)) {
					Robot.drivetrain.stop();
					
				}
				
			}else if (state==3) {
				
			}
			// do something here
			if ("a".equals("a")) {
				state = 2;
			}

		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return (Timer.getFPGATimestamp() - tStart) >= 15.0;
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
