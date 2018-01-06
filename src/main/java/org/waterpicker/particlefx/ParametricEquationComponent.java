package org.waterpicker.particlefx;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ParametricEquationComponent {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Function<Double, Vector3d> function = value -> Vector3d.ZERO;
        private double interval = 1;
        private double increment = 1;
        private String name = "";

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder function(Function<Double, Vector3d> funciton) {
            this.function = funciton;
            return this;
        }

        public Builder increment(double increment) {
            this.increment = increment;
            return this;
        }

        public Builder interval(int interval) {
            this.increment = interval;
            return this;
        }

        public ParticleComponent build() {
            List<Vector3d> data = new ArrayList<>();
            for (double i = 0d; i <= interval; i += 1 / increment) {
                Vector3d current = function.apply(i);

                if(!data.contains(current)) data.add(current);
            }

            return ParticleComponent.builder().name(name).stage(0, data).build();
        }
    }
}
