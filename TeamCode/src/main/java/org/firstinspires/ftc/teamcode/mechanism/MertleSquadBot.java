package org.firstinspires.ftc.teamcode.mechanism;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MertleSquadBot {

    private DcMotor intake;
    private DcMotor shooterL;
    private DcMotor shooterR;

    public void init(HardwareMap hwMap) {


        intake = hwMap.get(DcMotor.class, "Intake");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterL = hwMap.get(DcMotor.class, "Left Shooter");
        shooterL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        shooterR = hwMap.get(DcMotor.class, "Right Shooter");
        shooterR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void setMotorSpeed(double speed) {
        intake.setPower(speed);
    }

    public void setShooterLSpeed(double shootLSpeed) { shooterL.setPower(shootLSpeed); }

    public void setShooterRSpeed(double shootRSpeed) { shooterR.setPower(shootRSpeed); }

}
