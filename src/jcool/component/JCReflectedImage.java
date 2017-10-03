/*
 * This file is part of JCool.
 *
 * JCool is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JCool is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JCool.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Copyright © 2011 Eneko Sanz Blanco <nkogear@gmail.com>
 *
 */

package jcool.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 * @author Eneko
 */
public class JCReflectedImage extends JCImage implements Serializable {

    public JCReflectedImage() {
    }

    public JCReflectedImage(URL imageURL) {
        try {
            this.image = createReflectedImage(ImageIO.read(imageURL));
            this.setSize(image.getWidth(), image.getHeight());
            repaint();
        } catch (IOException ex) {
            Logger.getLogger("jcool").log(Level.SEVERE, "Couldn't load the image"
                                          + " from the URL.");
        }
    }

    public JCReflectedImage(BufferedImage image) {
        super(createReflectedImage(image));
    }

    @Override
    public void setImage(BufferedImage image) {
        super.setImage(createReflectedImage(image));
    }

    public static BufferedImage createReflectedImage(BufferedImage image) {
        BufferedImage result = new BufferedImage(image.getWidth(),
                image.getHeight() * 6 / 4, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.scale(1.0, -1.0);
        g.drawImage(image, 0, -image.getHeight() * 2, null);
        g.scale(1.0, -1.0);
        g.translate(0, image.getHeight());
        GradientPaint mask = new GradientPaint(0, 0, new Color(1f, 1f, 1f, 0.5f),
                            0, image.getHeight() / 2, new Color(1f, 1f, 1f, 0f));
        g.setPaint(mask);
        g.setComposite(AlphaComposite.DstIn);
        g.fillRect(0, 0, image.getWidth(), image.getHeight());
        g.dispose();
        return result;
    }

}
