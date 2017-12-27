package org.waterpicker.particlefx;

import java.awt.image.BufferedImage;
import java.nio.file.Path;

public class PngTextureComponent extends MultiParticleComponent {

    public PngTextureComponent(Path file) throws Exception {
        this(TextureUtil.image.apply(file).orElseThrow(() -> new Exception("Invalid Path for Image")), 1/8);
    }

    public PngTextureComponent(BufferedImage image, float ratio) {
        setData(TextureUtil.convert(image, ratio));
    }
}
