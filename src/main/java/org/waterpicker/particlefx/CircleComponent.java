package org.waterpicker.particlefx;

import com.flowpowered.math.TrigMath;
import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.effect.particle.ParticleEffect;

public class CircleComponent extends ParametricEquationComponent {
    public CircleComponent(ParticleEffect effect, double radius, double increment) {
        super(effect, (angle) -> {
            return new Vector3d(radius * TrigMath.cos(TrigMath.TWO_PI * angle), 0, radius * TrigMath.sin(TrigMath.TWO_PI * angle));
        }, 1, increment);
    }
}
