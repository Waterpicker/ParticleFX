package org.waterpicker.particlefx;

import com.flowpowered.math.GenericMath;
import com.flowpowered.math.imaginary.Quaterniond;
import com.flowpowered.math.matrix.Matrix4d;

public class RotationUtil {
    private static Matrix4d[] pitch;
    private static Matrix4d[] yaw;
    private static Matrix4d[] roll;

    public static Matrix4d getPitch(int angle) {
        return pitch[wrap(angle)];
    }

    public static Matrix4d getYaw(int angle) {
        return yaw[wrap(angle)];
    }

    public static Matrix4d getRoll(int angle) {
        return roll[wrap(angle)];
    }

    private static int wrap(int angle) {
        int wrap = (int) GenericMath.wrapAngleDeg(angle);

        return wrap < 0 ? wrap+360 : wrap;
    }

    static {
        int step = 360;

        pitch = new Matrix4d[step];
        yaw = new Matrix4d[step];
        roll = new Matrix4d[step];

        for (int i = 0; i < step; i++) {
            pitch[i] = Matrix4d.createRotation(Quaterniond.fromAxesAnglesDeg(i,0,0));
            yaw[i] = Matrix4d.createRotation(Quaterniond.fromAxesAnglesDeg(0,i,0));
            roll[i] = Matrix4d.createRotation(Quaterniond.fromAxesAnglesDeg(0,0,i));
         }
    }
}
