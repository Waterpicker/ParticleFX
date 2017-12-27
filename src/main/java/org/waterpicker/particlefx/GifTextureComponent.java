package org.waterpicker.particlefx;

import com.flowpowered.math.matrix.Matrix4d;
import com.flowpowered.math.vector.Vector4d;
import com.google.common.collect.Multimap;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.util.List;

public class GifTextureComponent {
    private List<Multimap<ParticleEffect, Vector4d>> stages;

    public void render(Viewer viewer, int stage, Matrix4d... matrices) {
    }

    public int getStages() {
        return stages.size();
    }
}
