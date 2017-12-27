package org.waterpicker.particlefx;

import com.flowpowered.math.matrix.Matrix4d;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector4d;
import com.google.common.collect.Multimap;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ComponentUtil {
    public static void render(Viewer viewer, Multimap<ParticleEffect, Vector3d> data, Matrix4d... matrices) {
        for (Map.Entry<ParticleEffect, Collection<Vector3d>> entry : data.asMap().entrySet()) {
            render(viewer, entry.getKey(), entry.getValue(), matrices);
        }
    }

    public static void render(Viewer viewer, ParticleEffect effect, Collection<Vector3d> data, Matrix4d... matrices) {
        data.stream().map(vector -> vector.toVector4(1)).map(vector -> {
            for (Matrix4d m : matrices) {
                vector = m.transform(vector);
            }

            return vector;
        }).forEach(vector -> {
            viewer.spawnParticles(effect, vector.toVector3());
        });
    }
}
