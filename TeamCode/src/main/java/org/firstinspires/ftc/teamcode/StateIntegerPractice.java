package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.mechanism.TestBench;
@Autonomous
public class StateIntegerPractice extends OpMode {


    TestBench bench = new TestBench();
    TestBench.DetectedColor detectedColor;
    enum State {
        WAIT_FOR_TOUCH,
        DRIVE_FOREWARD,
        WAIT_FOR_REVS,
        FINISHED
    }
    State state = State.WAIT_FOR_TOUCH;

    @Override
    public void init() {
        bench.init(hardwareMap);
        state = State.WAIT_FOR_TOUCH;
    }

    @Override
    public void loop() {

        detectedColor = bench.getDetectedColor(telemetry);
        telemetry.addData("Cur State", state);
        telemetry.addData("Revs", bench.getMotorRevs());
        switch (state) {
            case WAIT_FOR_TOUCH:
                telemetry.addLine("To exit state, press touch sensor");
                if (bench.getTouchSensorState()) {
                    state = State.DRIVE_FOREWARD;
                    bench.setMotorSpeed(0.5);
                }
                break;
            case DRIVE_FOREWARD:
                telemetry.addLine("To exit state, see red");
                if (bench.getMotorRevs() > 2) {
                    state = State.WAIT_FOR_REVS;
                    bench.setMotorMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                    bench.setMotorMode(DcMotor.RunMode.RUN_USING_ENCODER);
                    bench.setMotorZeroBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                    bench.setMotorSpeed(-1);
                }
                break;
            case WAIT_FOR_REVS:
                telemetry.addLine("To exit state, keep driving");
                if (bench.getMotorRevs() < -10) {
                    state = State.FINISHED;
                    bench.setMotorSpeed(0);
                }
                break;
            default:
                telemetry.addLine("Auto state machine finished.");
        }
    }
}
