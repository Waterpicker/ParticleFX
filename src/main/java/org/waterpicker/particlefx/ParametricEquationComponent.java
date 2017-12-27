package org.waterpicker.particlefx;

import com.flowpowered.math.vector.Vector3d;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ParametricEquationComponent extends SingleParticleComponent {

    public ParametricEquationComponent(ParticleEffect effect, Function<Double, Vector3d> function, double interval, double increment) {
        List<Vector3d> data = new ArrayList<>();
        for (double i = 0d; i <= interval; i += 1 / increment) {
            Vector3d current = function.apply(i);

            data.add(current);
        }

        setGeometry(data);
    }
}
