package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class MertleSquadBot {

    private DcMotor FLDrive;
    private DcMotor BLDrive;
    private DcMotor BRDrive;
    private DcMotor FRDrive;
    private DcMotor intake;
    private DcMotor storage;
    private DcMotorEx shooterL;
    private DcMotorEx shooterR;
    private CRServo feeder;
    private DigitalChannel touchSensor;
    private NormalizedColorSensor colorSensor;
    public enum DetectedColor {
        RED,
        BLUE,
        YELLOW,
        UNKNOWN,
    }



    public void init(HardwareMap hwMap) {

        FLDrive = hwMap.get(DcMotor.class, "frontLeftDrive");

        BLDrive = hwMap.get(DcMotor.class, "backLeftDrive");

        BRDrive = hwMap.get(DcMotor.class, "backRightDrive");

        FRDrive = hwMap.get(DcMotor.class, "frontRightDrive");

        intake = hwMap.get(DcMotor.class, "Intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        storage = hwMap.get(DcMotor.class, "Storage");

        shooterL = hwMap.get(DcMotorEx.class, "Left Shooter");
        shooterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterR = hwMap.get(DcMotorEx.class, "Right Shooter");
        shooterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        feeder = hwMap.get(CRServo.class, "Feeder");

        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");

        colorSensor = hwMap.get(NormalizedColorSensor.class, "color_sensor");
        colorSensor.setGain(15);


    }

    public void setIntakeSpeed(double power) {
        intake.setPower(power);
    }

    public void setShooterBehaviorAndMode(DcMotor.ZeroPowerBehavior behavior, DcMotor.RunMode mode) {
        shooterL.setZeroPowerBehavior(behavior); shooterL.setMode(mode);
        shooterR.setZeroPowerBehavior(behavior); shooterR.setMode(mode);
    }
    public void setFLPower(double power) { FLDrive.setPower(power); }
    public void setBLPower(double power) { BLDrive.setPower(power); }
    public void setBRPower(double power) { BRDrive.setPower(power); }
    public void setFRPower(double power) { FRDrive.setPower(power); }
    public void setShooterSpeed(double speed) { shooterL.setPower(-1 * speed); shooterR.setPower(speed); }
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

    public boolean touchSensorState () { return !touchSensor.getState(); }

    public DetectedColor getDetectedColor(Telemetry telemetry) {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        if (normRed > 0.6 && normGreen < 0.45 && normBlue < 0.2) {
            return MertleSquadBot.DetectedColor.RED;
        } else if (normRed < 0.15 && normGreen > 0.2 && normBlue > 0.6) {
            return MertleSquadBot.DetectedColor.BLUE;
        } else if (normRed > 0.95 && normGreen > 0.95 && normBlue < 0.75) {
            return MertleSquadBot.DetectedColor.YELLOW;
        } else {
            return MertleSquadBot.DetectedColor.UNKNOWN;
        }
    }

    public void setFeederPower(double power) {
        feeder.setPower(power);
    }

    public void initShooterPIDF() {
        shooterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterR.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterL.setDirection(DcMotorSimple.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(98, 0, 0, 14.1);
        shooterR.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        shooterL.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    public void runShooterPIDF(double targetVelocity) {
        shooterL.setVelocity(targetVelocity);
        shooterR.setVelocity(targetVelocity);
    }


}
