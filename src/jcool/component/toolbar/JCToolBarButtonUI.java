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

import java.awt.*;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import jcool.utils.JCoolUtils;

/**
 *
 * @author eneko
 */
public class JCToolBarButtonUI extends BasicButtonUI {

    private static Color TEXT_COLOR = new Color(230, 230, 230);
    private static Color TEXT_SHADOW_COLOR = new Color(10, 10, 10);

    // the gradient colors for when the button is selected.
    private static Color SELECTED_BACKGROUND_COLOR_1 = new Color(25, 25, 25);
    private static Color SELECTED_BACKGROUND_COLOR_2 = new Color(35, 35, 35);
    private static Color SELECTED_BACKGROUND_COLOR_3 = new Color(30, 30, 30);
    private static Color SELECTED_BACKGROUND_COLOR_4 = new Color(35, 35, 35);

    // the border colors for the button.
    private static Color SELECTED_TOP_BORDER = new Color(8, 8, 8);
    private static Color SELECTED_BOTTOM_BORDER = new Color(46, 46, 46);

    // the border colors between buttons.
    private static Color LEFT_BORDER = new Color(255,255,255,21);
    private static Color RIGHT_BORDER = new Color(0,0,0,125);

    private static final Color SELECTED_INNER_SHADOW_COLOR_1 = new Color(27, 27, 27);
    private static final Color SELECTED_INNER_SHADOW_COLOR_2 = new Color(28, 28, 28);
    private static final Color SELECTED_INNER_SHADOW_COLOR_3 = new Color(30, 30, 30);

    @Override
    protected void installDefaults(AbstractButton button) {
        super.installDefaults(button);
        button.setBackground(new Color(0,0,0,0));
        button.setOpaque(false);
        button.setFont(JCoolUtils.getJCoolFont());
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        // if the button is selected, paint the dark background now.
        if (button.isSelected()) {
            paintButtonSelected(g, button);
        } else {
            // paint the border and border highlight if the button isn't
            // selected.
            g.setColor(LEFT_BORDER);
            g.drawLine(0, 1, 0, button.getHeight()-2);
            g.setColor(RIGHT_BORDER);
            g.drawLine(button.getWidth()-1, 1,
                    button.getWidth()-1, button.getHeight()-2);
        }

        super.paint(g, c);
    }

    @Override
    protected void paintText(Graphics g, AbstractButton button,
                             Rectangle textRect, String text) {

        ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                                         RenderingHints.VALUE_ANTIALIAS_ON);

        FontMetrics fontMetrics = g.getFontMetrics(button.getFont());
        int mnemonicIndex = button.getDisplayedMnemonicIndex();

        // paint the shadow text.
        g.setColor(TEXT_SHADOW_COLOR);
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemonicIndex,
                textRect.x + getTextShiftOffset(),
                textRect.y + fontMetrics.getAscent() + getTextShiftOffset() - 1);

        // paint the actual text.
        g.setColor(TEXT_COLOR);
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemonicIndex,
                textRect.x + getTextShiftOffset(),
                textRect.y + fontMetrics.getAscent() + getTextShiftOffset());
    }

    /**
     * Paints the selected buttons state, also used as the pressed state.
     */
    private void paintButtonSelected(Graphics graphics, AbstractButton button) {
        // calculate the middle of the area to paint.
        int midY = button.getHeight()/2;

        Paint topPaint = new GradientPaint(0, 0, SELECTED_BACKGROUND_COLOR_1,
                0, midY, SELECTED_BACKGROUND_COLOR_2);
        ((Graphics2D) graphics).setPaint(topPaint);
        graphics.fillRect(0, 0, button.getWidth(), midY);

        // paint the top half of the background with the corresponding
        // gradient.
        Paint bottomPaint =
                new GradientPaint(0, midY + 1, SELECTED_BACKGROUND_COLOR_3,
                        0, button.getHeight(), SELECTED_BACKGROUND_COLOR_4);
        ((Graphics2D) graphics).setPaint(bottomPaint);
        graphics.fillRect(0, midY, button.getWidth(), button.getHeight());

        // draw the top and bottom border.
        graphics.setColor(SELECTED_TOP_BORDER);
        graphics.drawLine(0, 0, button.getWidth(), 0);
        graphics.setColor(SELECTED_BOTTOM_BORDER);
        graphics.drawLine(0, button.getHeight() - 1,
                button.getWidth(), button.getHeight() - 1);

        // paint the outter part of the inner shadow.
        graphics.setColor(SELECTED_INNER_SHADOW_COLOR_1);
        graphics.drawLine(0, 1, 0, button.getHeight()-2);
        graphics.drawLine(0, 1, button.getWidth(), 1);
        graphics.drawLine(button.getWidth()-1, 1,
                button.getWidth()-1, button.getHeight()-2);

        // paint the middle part of the inner shadow.
        graphics.setColor(SELECTED_INNER_SHADOW_COLOR_2);
        graphics.drawLine(1, 1, 1, button.getHeight()-2);
        graphics.drawLine(0, 2, button.getWidth(), 2);
        graphics.drawLine(button.getWidth()-2, 1,
                button.getWidth()-2, button.getHeight()-2);

        // paint the inner part of the inner shadow.
        graphics.setColor(SELECTED_INNER_SHADOW_COLOR_3);
        graphics.drawLine(2, 1, 2, button.getHeight()-2);
        graphics.drawLine(0, 3, button.getWidth(), 3);
        graphics.drawLine(button.getWidth()-3, 1,
                button.getWidth()-3, button.getHeight()-2);
    }

    @Override
    protected void paintButtonPressed(Graphics graphics, AbstractButton button) {
        paintButtonSelected(graphics, button);
    }
}