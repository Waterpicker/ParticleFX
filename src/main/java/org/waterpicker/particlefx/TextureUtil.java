package org.waterpicker.particlefx;

import com.flowpowered.math.matrix.Matrix4d;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector4d;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.effect.Viewer;
import org.spongepowered.api.effect.particle.ParticleEffect;
import org.spongepowered.api.effect.particle.ParticleOptions;
import org.spongepowered.api.effect.particle.ParticleTypes;
import org.spongepowered.api.entity.Transform;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.util.Color;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Stream;

public class TextureUtil {
    public static Multimap<ParticleEffect, Vector3d> convert(BufferedImage image, Anchor anchor, double ratio) {
        ParticleEffect.Builder effect = ParticleEffect.builder().type(ParticleTypes.REDSTONE_DUST).velocity(Vector3d.ZERO);

        Multimap<Color, Vector3d> temp = ArrayListMultimap.create();

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Optional<Color> color = getColor(image.getRGB(image.getWidth()-1-x , image.getHeight()-1-y));
                if(color.isPresent()) {
                    temp.put(color.get(), anchor.translate(new Vector3d(x, y, 0), image.getWidth()-1, image.getHeight()-1).mul(ratio));
                }
            }
        }

        Multimap<ParticleEffect, Vector3d> data = ArrayListMultimap.create();

        for(Color color : temp.keySet()) {
            data.putAll(effect.option(ParticleOptions.COLOR, color).build(), temp.get(color));
        }

        return data;
    }

    private static Optional<Color> getColor(int pixel) {
        return Optional.of(new java.awt.Color(pixel, true)).filter(c -> c.getAlpha() != 0).map(Color::of);
    }

    public static Function<Path, Optional<BufferedImage>> image = (path) -> {
        try {
            return Optional.of(ImageIO.read(Files.newInputStream(path)));
        } catch (NullPointerException | IOException e) {
            return Optional.empty();
        }
    };
}
