package org.waterpicker.particlefx;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.google.common.collect.Multimap;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TextureComponent extends ParticleComponent {
    private List<Vector2d> sizes;

    private TextureComponent(List<BufferedImage> images, double ratio) {
        super(images.stream().map(image -> TextureUtil.convert(image, ratio)).collect(Collectors.toList()));
        sizes = images.stream().map(image -> Vector2i.from(image.getWidth(), image.getHeight()).toDouble().mul(ratio)).collect(Collectors.toList());
    }

    public double getWidth() {
        return sizes.get(0).getX();
    }

    public double getWidth(int stage) {
        return sizes.get(stage).getX();
    }

    public double getHeight() {
        return sizes.get(0).getY();
    }

    public double getHeight(int stage) {
        return sizes.get(stage).getY();
    }

    public Vector2d getDimensions() {
        return sizes.get(0);
    }

    public Vector2d getDimensions(int stage) {
        return sizes.get(stage);
    }

    public static TextureComponent.Builder builder() {
        return new TextureComponent.Builder();
    }

    public static class Builder extends ParticleComponent.Builder {
        private double ratio = 0.125f;
        private List<BufferedImage> images = new ArrayList<>();

        public Builder ratio(double ratio) {
            this.ratio = ratio;
            return this;
        }

        public Builder image(int stage, BufferedImage image) {
            images.get(stage);
            return this;
        }

        public Builder images(List<BufferedImage> images) {
            this.images = images;
            return this;
        }

        public TextureComponent build() {
            return new TextureComponent(images, ratio);
        }
    }

    public static class PNG {
        public static class Builder {
            private Path path = Paths.get("");
            private double ratio = 0.125f;

            public Builder path(Path path) {
                this.path = path;
                return this;
            }

            public Builder ratio(double ratio) {
                this.ratio = ratio;
                return this;
            }

            public TextureComponent build() {
                TextureComponent.Builder component = TextureComponent.builder();
                TextureUtil.image.apply(path).ifPresent(image -> component.image(0, image));
                component.ratio(ratio);

                return component.build();
            }
        }
    }

}
