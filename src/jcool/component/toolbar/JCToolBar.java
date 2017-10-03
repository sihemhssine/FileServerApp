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

package jcool.component.toolbar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.io.Serializable;
import javax.swing.JPanel;

/**
 *
 * @author Eneko
 */

public class JCToolBar extends JPanel implements Serializable {

    // the background colors used in the multi-stop gradient.
    private static Color BACKGROUND_COLOR_1 = new Color(67, 67, 67);
    private static Color BACKGROUND_COLOR_2 = new Color(56, 56, 56);
    private static Color BACKGROUND_COLOR_3 = new Color(45, 45, 45);
    private static Color BACKGROUND_COLOR_4 = new Color(50, 50, 50);

    // the color to use for the top and bottom border.
    private static Color BORDER_COLOR = new Color(33, 33, 33);

    // the inner shadow colors on the top of the header.
    private static Color TOP_SHADOW_COLOR_1 = new Color(51, 51, 51);
    private static Color TOP_SHADOW_COLOR_2 = new Color(63, 63, 63);
    private static Color TOP_SHADOW_COLOR_3 = new Color(66, 66, 66);

    // the inner shadow colors on the bottom of the header.
    private static Color BOTTOM_SHADOW_COLOR_1 = new Color(54, 54, 54);
    private static Color BOTTOM_SHADOW_COLOR_2 = new Color(64, 64, 64);

    private static float[] gradientFractions = new float[] {0f,0.49f, 0.5f,1f};
    private static Color[] gradientColors = new Color[] {BACKGROUND_COLOR_1,
                                                         BACKGROUND_COLOR_2,
                                                         BACKGROUND_COLOR_3,
                                                         BACKGROUND_COLOR_4};

    public JCToolBar() {
        super();
        setBorder(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);
        LinearGradientPaint gradientPaint =
                            new LinearGradientPaint(0, 0, 0, getHeight(),
                                                       gradientFractions,
                                                          gradientColors);
        g2.setPaint(gradientPaint);
        g2.fillRect(0, 0, getWidth(), getHeight());

        // draw the top inner shadow.
        g2.setColor(TOP_SHADOW_COLOR_1);
        g2.drawLine(0, 1, getWidth(), 1);
        g2.setColor(TOP_SHADOW_COLOR_2);
        g2.drawLine(0, 2, getWidth(), 2);
        g2.setColor(TOP_SHADOW_COLOR_3);
        g2.drawLine(0, 3, getWidth(), 3);

        // draw the bottom inner shadow.
        g2.setColor(BOTTOM_SHADOW_COLOR_1);
        g2.drawLine(0, getHeight() - 3, getWidth(), getHeight() - 3);
        g2.setColor(BOTTOM_SHADOW_COLOR_2);
        g2.drawLine(0, getHeight() - 2, getWidth(), getHeight() - 2);

        // draw the top and bottom border.
        g2.setColor(BORDER_COLOR);
        g2.drawLine(0, 0, getWidth(), 0);
        g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
    }
}
  
  