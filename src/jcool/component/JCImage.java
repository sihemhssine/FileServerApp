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

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * @author Eneko
 */
public class JCImage extends JComponent implements Serializable {

    protected BufferedImage image = null;

    public JCImage() {
    }

    public JCImage(URL imageURL) {
        try {
            this.image = ImageIO.read(imageURL);
            this.setSize(image.getWidth(), image.getHeight());
            repaint();
        } catch (IOException ex) {
            Logger.getLogger("jcool").log(Level.SEVERE, "Couldn't load the image"
                                          + " from the URL.");
        }
    }

    public JCImage(BufferedImage image) {
        this.image = image;
        this.setSize(image.getWidth(), image.getHeight());
        repaint();
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
        this.setSize(image.getWidth(), image.getHeight());
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.drawImage(image, WIDTH, HEIGHT, null);
        g2.dispose();
    }

}