package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class TestBench {
    private DigitalChannel touchSensor;
    private DcMotor motor;
    private double ticksPerRev;
    private Servo servoPos;
    private CRServo servoRot;
    private NormalizedColorSensor colorSensor;
    public enum DetectedColor {
        YELLOW,
        BLUE,
        RED,
        UNKNOWN,
    }
    private IMU imu;

    public void init(HardwareMap hwMap) {
        touchSensor = hwMap.get(DigitalChannel.class, "touch_sensor");
        touchSensor.setMode(DigitalChannel.Mode.INPUT);

        motor = hwMap.get(DcMotor.class, "Intake");
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        ticksPerRev = motor.getMotorType().getTicksPerRev();

        servoPos = hwMap.get(Servo.class, "pos_servo");
        servoRot = hwMap.get(CRServo.class, "cont_servo");
        servoPos.scaleRange(0.5,1.0);
        servoPos.setDirection(Servo.Direction.REVERSE);
        servoRot.setDirection(CRServo.Direction.REVERSE);

        colorSensor = hwMap.get(NormalizedColorSensor.class, "color_sensor");
        colorSensor.setGain(10);

        imu = hwMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot RevOrientation = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(RevOrientation));

    }
    public boolean getTouchSensorState() {
        return !touchSensor.getState();
    }
    public boolean isTouchSensorReleased() {
        return touchSensor.getState();
    }

    public void setMotorSpeed(double speed) { motor.setPower(speed); }

    public double getMotorRevs() {
        return motor.getCurrentPosition() / ticksPerRev;
    }

    public void setMotorZeroBehavior(DcMotor.ZeroPowerBehavior zeroBehavior) {
        motor.setZeroPowerBehavior(zeroBehavior);
    }

    public void setMotorMode(DcMotor.RunMode runMode) {
        motor.setMode(runMode);
    }

    public void setServoPos(double angle) {
        servoPos.setPosition(angle);
    }
    public void setServoRot(double power) {
        servoRot.setPower(power);
    }

    public DetectedColor getDetectedColor(Telemetry telemetry) {
        NormalizedRGBA colors = colorSensor.getNormalizedColors();

        float normRed, normGreen, normBlue;
        normRed = colors.red / colors.alpha;
        normGreen = colors.green / colors.alpha;
        normBlue = colors.blue / colors.alpha;

        telemetry.addData("red", normRed);
        telemetry.addData("green", normGreen);
        telemetry.addData("blue", normBlue);

        if (normRed > 0.2 && normGreen < 0.2 && normBlue < 0.1) {
            return DetectedColor.RED;
        } else if (normRed < 0.1 && normGreen < 0.1 && normBlue > 0.25) {
            return DetectedColor.BLUE;
        } else if (normRed > 0.6 && normGreen > 0.9 && normBlue < 0.45) {
            return DetectedColor.YELLOW;
        } else {
            return DetectedColor.UNKNOWN;
        }


    }
    public double getHeading(AngleUnit angleUnit) {
        return imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }


}