package org.waterpicker.particlefx;

import com.flowpowered.math.matrix.Matrix4d;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector4d;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.particle.ParticleEffect;

public class MultiParticleComponent {
    private Multimap<ParticleEffect, Vector3d> data = ArrayListMultimap.create();

    protected void setData(Multimap<ParticleEffect, Vector3d> data) {
        this.data = data;
    }

    public void render(Viewer viewer, Matrix4d... matrices) {
        ComponentUtil.render(viewer, data, matrices);
    }
}
