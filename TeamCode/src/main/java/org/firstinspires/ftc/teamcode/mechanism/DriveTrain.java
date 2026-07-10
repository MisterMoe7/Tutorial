package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {

    private DcMotor FLDrive;
    private DcMotor BLDrive;
    private DcMotor BRDrive;
    private DcMotor FRDrive;

    public void init(HardwareMap hwMap) {

        FLDrive = hwMap.get(DcMotor.class, "frontLeftDrive");

        BLDrive = hwMap.get(DcMotor.class, "backLeftDrive");

        BRDrive = hwMap.get(DcMotor.class, "backRightDrive");

        FRDrive = hwMap.get(DcMotor.class, "frontRightDrive");

    }

    public void setFLPower(double power) { FLDrive.setPower(power); }
    public void setBLPower(double power) { BLDrive.setPower(power); }
    public void setBRPower(double power) { BRDrive.setPower(power); }
    public void setFRPower(double power) { FRDrive.setPower(power); }

    public void setDriveBehaviorAndMode(DcMotor.ZeroPowerBehavior zeroPowerBehavior, DcMotor.RunMode runMode) {
        FLDrive.setZeroPowerBehavior(zeroPowerBehavior);
        FLDrive.setMode(runMode);
        BLDrive.setZeroPowerBehavior(zeroPowerBehavior);
        BLDrive.setMode(runMode);
        BRDrive.setZeroPowerBehavior(zeroPowerBehavior);
        BRDrive.setMode(runMode);
        FRDrive.setZeroPowerBehavior(zeroPowerBehavior);
        FRDrive.setMode(runMode);
    }

}
