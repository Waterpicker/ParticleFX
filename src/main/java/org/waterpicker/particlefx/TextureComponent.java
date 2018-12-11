package org.waterpicker.particlefx;

import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector3d;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import org.spongepowered.api.effect.particle.ParticleEffect;

import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;

public class TextureComponent extends ParticleComponent {
    private Map<Integer, Vector2d> sizes = Maps.newHashMap();

    private TextureComponent(Map<Integer, BufferedImage> images, Anchor anchor, double ratio) {
        super(convert(images, anchor, ratio));

        images.forEach((i, image) -> sizes.put(i, Vector2i.from(image.getWidth(), image.getHeight()).toDouble().mul(ratio)));
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

    private static Map<Integer,Multimap<ParticleEffect, Vector3d>> convert(Map<Integer, BufferedImage> images, Anchor anchor, double ratio) {
        Map<Integer,Multimap<ParticleEffect, Vector3d>> map = Maps.newHashMap();
        images.forEach((i, image) -> map.put(i, TextureUtil.convert(image, anchor, ratio)));

        return map;
    }


    public static TextureComponent.Builder builder() {
        return new TextureComponent.Builder();
    }

    public static class Builder extends ParticleComponent.Builder {
        private double ratio = 0.125f;
        private Map<Integer,BufferedImage> images = Maps.newHashMap();
        private Anchor anchor = Anchor.BOTTOM_LEFT;

        public Builder ratio(double ratio) {
            this.ratio = ratio;
            return this;
        }

        public Builder image(int stage, BufferedImage image) {
            this.images.put(stage, image);
            return this;
        }

        public Builder images(Map<Integer,BufferedImage> images) {
            this.images = images;
            return this;
        }

        public Builder anchor(Anchor anchor) {
            this.anchor = anchor;
            return this;
        }

        public TextureComponent build() {
            return new TextureComponent(images, anchor, ratio);
        }
    }

    public static class PNG {
        public static Builder builder() {
            return new Builder();
        }

        public static class Builder {
            private Path path = Paths.get("");
            private double ratio = 0.125f;
            private Anchor anchor = Anchor.BOTTOM_LEFT;

            public Builder path(Path path) {
                this.path = path;
                return this;
            }

            public Builder ratio(double ratio) {
                this.ratio = ratio;
                return this;
            }

            public Builder anchor(Anchor anchor) {
                this.anchor = anchor;
                return this;
            }

            public TextureComponent build() {
                TextureComponent.Builder component = TextureComponent.builder().anchor(anchor);
                TextureUtil.image.apply(path).ifPresent(image -> component.image(0, image));
                component.ratio(ratio);

                return component.build();
            }
        }
    }
}
