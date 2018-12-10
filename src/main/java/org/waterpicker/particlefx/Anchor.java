package org.waterpicker.particlefx;

import com.flowpowered.math.vector.Vector3d;

public enum Anchor {
    TOP_LEFT((v, w, h) -> v.sub(0, h, 0)),
    TOP_CENTER((v, w, h) -> v.sub(w*0.5, h, 0)),
    TOP_RIGHT((v, w, h) -> v.sub(w, h, 0)),
    CENTER_LEFT((v, w, h) -> v.sub(0, h*0.5, 0)),
    CENTER_CENTER((v, w, h) -> v.sub(w*0.5, h*0.5, 0)),
    CENTER_RIGHT((v, w, h) -> v.sub(w, h*0.5, 0)),
    BOTTOM_LEFT((v, w, h) -> v.sub(0,0,0)),
    BOTTOM_CENTER((v, w, h) -> v.sub(w*0.5, 0, 0)),
    BOTTOM_RIGHT((v, w, h) -> v.sub(w, 0, 0));

    private final Func func;

    Anchor(Func func) {
        this.func = func;
    }

    public Vector3d translate(Vector3d vector, double width, double height) {
        return func.apply(vector, width, height);
    }

    @FunctionalInterface
    private interface Func{
        Vector3d apply(Vector3d t1, Double t2, Double t3);
    }

}
