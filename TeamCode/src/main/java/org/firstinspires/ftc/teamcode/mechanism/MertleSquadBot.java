package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MertleSquadBot {

    private DcMotor intake;

    public void init(HardwareMap hwMap) {


        intake = hwMap.get(DcMotor.class, "Intake");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setMotorSpeed(double speed) {
        intake.setPower(speed);
    }

}
