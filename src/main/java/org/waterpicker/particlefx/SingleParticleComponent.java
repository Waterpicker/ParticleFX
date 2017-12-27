package org.waterpicker.particlefx;

import com.flowpowered.math.matrix.Matrix4d;
import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.List;

public class SingleParticleComponent {
    private List<Vector3d> geometry = new ArrayList<>();

    public void render(Viewer viewer, ParticleEffect effect, Matrix4d... matrices) {
        ComponentUtil.render(viewer, effect, geometry, matrices);
    }

    protected void setGeometry(List<Vector3d> geometry) {
        this.geometry = geometry;
    }
}
