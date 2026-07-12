package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.Sensors;

@TeleOp
public class LEDTest extends OpMode {

    Sensors sensors = new Sensors();

    @Override
    public void init() {
        sensors.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            sensors.setLEDColor(1);
        } else if (gamepad1.b) {
            sensors.setLEDColor(3);
        } else if (gamepad1.y) {
            sensors.setLEDColor(2);
        } else {
            sensors.setLEDColor(0);
        }
    }
}
