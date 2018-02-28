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

import org.usfirst.frc4505.Robot18.Robot;
import org.usfirst.frc4505.Robot18.RobotMap;
import org.usfirst.frc4505.Robot18.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.SensorCollection;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final WPI_TalonSRX lB = RobotMap.drivetrainLB;
    private final WPI_TalonSRX lS = RobotMap.drivetrainLS;
    private final SpeedControllerGroup left = RobotMap.drivetrainleft;
    private final WPI_TalonSRX rB = RobotMap.drivetrainRB;
    private final WPI_TalonSRX rS = RobotMap.drivetrainRS;
    private final SpeedControllerGroup right = RobotMap.drivetrainright;
    private final DifferentialDrive drivetrain = RobotMap.drivetraindrivetrain;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final ADXRS450_Gyro gyro = RobotMap.gyro;
    public final WPI_TalonSRX[] talons = {rB, rS, lB, lS};
    public final double radius = 3.0;//radius of wheels in inches (6 inch wheel = 6 inch diameter)
    public final double ticksPerRev = 1024.0;

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop
    	putDataOnDash();
    	//if gyro is acting up, uncomment next block
    	if (Robot.oi.A.get() && Robot.oi.LB.get() && Robot.oi.RB.get()) {
    		gyro.calibrate();
    	}
    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void putDataOnDash() {
    	putGyroData();
    	putEncData();
    }
    
    public void resetSensors() {
    	resetGyro();
    	resetAllTalons();
    }
    
    public void putGyroData() {
    	double angle = getAngle();
    	SmartDashboard.putNumber("gyro", (Math.abs(angle) > 0.001) ? angle : 0);
//    	SmartDashboard.putNumber("gyro", angle);
    }
    
    public void resetGyro() {
    	gyro.reset();
    }
    
    public double getAngle() {
    	return gyro.getAngle();
    }
    
    public void putEncData() {
    	SmartDashboard.putNumber("Lrevs", getRevs(3));
    	SmartDashboard.putNumber("Lrevsec", getRevPerSec(3));
    	
    	SmartDashboard.putNumber("Rrevs", getRevs(1));
    	SmartDashboard.putNumber("Rrevsec", getRevPerSec(1));
    	
    	SmartDashboard.putNumber("Lins", getInches(3));
    	SmartDashboard.putNumber("Rins", getInches(1));
    }
    
    public WPI_TalonSRX getTalon(int num) {
    	if (num>0 && num<=4)
    		return talons[num-1];
    	return null;
    }
    
    public void resetTalon(int motor) {
    	getTalon(motor).setSelectedSensorPosition(0, 0, 0);
    }
    
    public void resetAllTalons() {
    	for(int i = 1; i < 5; i++) {
    		resetTalon(i);
    	}
    }
    
    public double getTicksPerSec(int motor) {
    	//multiply by 10 bc method gets ticks/0.1s
    	return getTalon(motor).getSelectedSensorVelocity(0)*10.0;
    }
    
    public double getRevPerSec(int motor) {
    	double spd = ((double)getTicksPerSec(motor))/ticksPerRev;
    	return (Math.abs(spd) > 0.01) ? spd : 0.0;
    }
    
    public double getInPerSec(int motor) {
    	return getRevPerSec(motor) * 2 * Math.PI * radius;
    }
    
    public double getTicks(int motor) {
    	return getTalon(motor).getSelectedSensorPosition(0);
    }
    
    public double getRevs(int motor) {
    	double pos = ((double)getTicks(motor))/ticksPerRev;
    	return (Math.abs(pos) > 0.01) ? pos : 0.0;
    }
    
    public double getInches(int motor) {
    	return getRevs(motor) * 2 * Math.PI * radius;
    }
    
    public boolean goneInches(double dist) {
    	return getInches(1) >= dist || getInches(3) >= dist;
    }
    
    public void drive(double pow, double rot, boolean flip) {
    	drive((flip) ? -pow : pow, rot);
    }
    
    public void drive(double pow, double rot) {
    	drivetrain.arcadeDrive(pow,rot);
    }
    
    public void stop() {
    	drive(0, 0);
    }
    
    public void driveStraight(double pow, double initAng, double currAng, double factor) {
    	drive(-pow,(initAng-currAng)*factor);
    }
    
    public void driveStraight(double pow, double initAng, double currAng, double factor, boolean flip) {
    	driveStraight((flip) ? -pow : pow, initAng, currAng, factor);
    }
    
    public void sendByteToI2C() {
    	sendByteToI2C(RobotMap.toSend[0]);
    }
    
    public void sendByteToI2C(byte data) {
    	RobotMap.i2c.write(84, data);
    }
    
    public void sendByteToI2C(int data) {
    	RobotMap.i2c.write(84, data);
    }
    
    public boolean readFromI2C(int bytes) {
    	//false = success, true = aborted
    	if (bytes > RobotMap.readIn.length) {
    		RobotMap.readIn = new byte[bytes];
    	}
    	return RobotMap.i2c.read(84, bytes, RobotMap.readIn);
    }

    public void updateSafeguard() {
    	RobotMap.safeguard = RobotMap.prefs.getBoolean("safeguard", RobotMap.safeguard);
    }
}

