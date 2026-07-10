package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class MertleSquadBot {


    private DcMotor intake;
    private DcMotor storage;
    private DcMotorEx shooterL;
    private DcMotorEx shooterR;
    private CRServo feeder;
    private DigitalChannel touchSensor;


    public void init(HardwareMap hwMap) {


        intake = hwMap.get(DcMotor.class, "Intake");
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        storage = hwMap.get(DcMotor.class, "Storage");

        shooterL = hwMap.get(DcMotorEx.class, "Left Shooter");
        shooterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterR = hwMap.get(DcMotorEx.class, "Right Shooter");
        shooterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        feeder = hwMap.get(CRServo.class, "Feeder");

    }

    public void setIntakeSpeed(double power) {
        intake.setPower(power);
    }

    public void setShooterBehaviorAndMode(DcMotor.ZeroPowerBehavior behavior, DcMotor.RunMode mode) {
        shooterL.setZeroPowerBehavior(behavior); shooterL.setMode(mode);
        shooterR.setZeroPowerBehavior(behavior); shooterR.setMode(mode);
    }

    public void setShooterSpeed(double speed) { shooterL.setPower(-1 * speed); shooterR.setPower(speed); }


    public void setFeederPower(double power) {
        feeder.setPower(power);
    }

    public void initShooterPIDF() {
        shooterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterR.setDirection(DcMotorSimple.Direction.FORWARD);
        shooterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        shooterL.setDirection(DcMotorSimple.Direction.REVERSE);
        PIDFCoefficients pidfCoefficients = new PIDFCoefficients(80, 0, 0, 12.5);
        shooterR.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
        shooterL.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, pidfCoefficients);
    }

    public void runShooterPIDF(double targetVelocity) {
        shooterL.setVelocity(targetVelocity);
        shooterR.setVelocity(targetVelocity);
    }




}
