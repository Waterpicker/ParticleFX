package org.waterpicker.particlefx;

import com.flowpowered.math.TrigMath;
import com.flowpowered.math.vector.Vector3d;

public class CircleComponent {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        public double radius = 1;
        public double increment = 8;

        public Builder radius(double radius) {
            this.radius = radius;
            return this;
        }

        public Builder increment(double increment) {
            this.increment = increment;
            return this;
        }

        public ParticleComponent build() {
            return ParametricEquationComponent.builder().interval(1).increment(increment).function((angle) -> new Vector3d(radius * TrigMath.cos(TrigMath.TWO_PI * angle), 0, radius * TrigMath.sin(TrigMath.TWO_PI * angle))).build();
        }
    }
}
