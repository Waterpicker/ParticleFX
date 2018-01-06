package org.waterpicker.particlefx;

import com.flowpowered.math.matrix.Matrix4d;
import com.flowpowered.math.vector.Vector3d;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.util.Color;

import java.util.List;
public class ParticleComponent {
    private List<Multimap<ParticleEffect, Vector3d>> stages;

    protected ParticleComponent(List<Multimap<ParticleEffect, Vector3d>> stages) {
        this.stages = stages;
    }

    public int getNumStages() {
        return stages.size();
    }

    public void render(Viewer viewer, int stage, ParticleEffect effect, Matrix4d... matrices) {
        ComponentUtil.render(viewer, effect, stages.get(stage).values(), matrices);
    }

    public void render(Viewer viewer, int stage, Matrix4d... matrices) {
        ComponentUtil.render(viewer, stages.get(stage), matrices);
    }

    public void render(Viewer viewer, ParticleEffect effect, Matrix4d... matrices) {
        render(viewer, 0, effect, matrices);
    }

    public void render(Viewer viewer, Matrix4d... matrices) {
        render(viewer, 0, matrices);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static ParticleEffect DEFAULT = ParticleEffect.builder().type(ParticleTypes.REDSTONE_DUST).option(ParticleOptions.COLOR, Color.WHITE).build();

        private List<Multimap<ParticleEffect, Vector3d>> stages = Lists.newArrayList(ArrayListMultimap.create());

        public Builder stage(int stage, Multimap<ParticleEffect, Vector3d> contents) {
            stages.set(stage, contents);
            return this;
        }

        public Builder stages(List<Multimap<ParticleEffect, Vector3d>> stages) {
            this.stages = stages;
            return this;
        }

        public Builder stage(int stage,  List<Vector3d> list) {
            Multimap<ParticleEffect, Vector3d> map = ArrayListMultimap.create();
            map.putAll(DEFAULT, list);

            this.stages.set(stage, map);
            return this;
        }

        public ParticleComponent build() {
            return new ParticleComponent(stages);
        }
    }
}

