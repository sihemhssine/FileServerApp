package boutons;


import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicButtonUI;
import javax.swing.plaf.basic.BasicGraphicsUtils;
import jcool.utils.JCoolUtils;
/**
 * @author Eneko
 */
public class JCButtonUI extends BasicButtonUI {

    // The default background colors used in the multi-stop gradient
    private static final Color bgColorTop = new Color(0xffffff);
    private static final Color bgColorBottom = new Color(0xdbdbdb);
    
    // The default background colors used in the multi-stop gradient on rollover
    private static final Color bgColorTopRollover = new Color(0xdedede);
    private static final Color bgColorBottomRollover = new Color(0xc0c0c0);

    private static final Color bgColorTopPressed = new Color(0xd0d0d0);
    
    // Background gradients (customizable)
    private LinearGradientPaint bgGradient;
    private LinearGradientPaint bgGradientRollover;
    private LinearGradientPaint bgGradientPressed;

    // The color to use for the top and bottom border
    private Color borderColor = new Color(0x838383);

    // The button's roundness
    private int roundness = 8;

    @Override
    protected void installDefaults(AbstractButton b) {
        super.installDefaults(b);
        b.setForeground(Color.darkGray);
        b.setHorizontalTextPosition(AbstractButton.CENTER);
        b.setBorder(null);
        b.setOpaque(false);
        b.setFont(JCoolUtils.getJCoolFont());
        createDefaultGradients(1);
        b.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                adjustGradients(e.getComponent().getHeight());
            }
        });
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        AbstractButton button = (AbstractButton) c;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING,
                            RenderingHints.VALUE_RENDER_QUALITY);

        g2.setColor(new Color(0xcecece));
        g2.setClip(new Rectangle(0,5,c.getWidth(),c.getHeight()));
        g2.drawRoundRect(0, 3, c.getWidth() - 1, c.getHeight() - 4,
                                             roundness, roundness);
        g2.setClip(null);
        if (button.getModel().isRollover()) {
            g2.setPaint(bgGradientRollover);
            g2.fillRoundRect(3, 3, c.getWidth() - 6, c.getHeight() - 5,
                                          roundness - 3, roundness - 3);
        }  else {
            g2.setPaint(bgGradient);
            g2.fillRoundRect(1, 1, c.getWidth() - 2, c.getHeight() - 2,
                                                  roundness, roundness);
        }
        g2.setColor(borderColor);
        g2.drawRoundRect(1, 1, c.getWidth() - 3, c.getHeight() - 3,
                                              roundness, roundness);
        
        super.paint(g, c);
    }

    @Override
    protected void paintButtonPressed(Graphics g, AbstractButton b) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(bgGradientPressed);
        g2.fillRoundRect(3, 3, b.getWidth() - 6, b.getHeight() - 5,
                                              roundness, roundness);
    }

    @Override
    protected void paintText(Graphics g, AbstractButton button, Rectangle textRect,
                                                                    String text) {
        FontMetrics fontMetrics = g.getFontMetrics(button.getFont());
        int mnemonicIndex = button.getDisplayedMnemonicIndex();

        g.setColor(button.getForeground());
        BasicGraphicsUtils.drawStringUnderlineCharAt(g, text, mnemonicIndex,
                textRect.x + getTextShiftOffset(),
                textRect.y + fontMetrics.getAscent() + getTextShiftOffset());
    }

    public void setRoundness(int roundness) {
        this.roundness = roundness;
    }

    public void setBackgroundGradient(Color topColor, Color bottomColor) {
        int height = (int) bgGradient.getEndPoint().getY();
        bgGradient = newGradient(height, topColor, bottomColor);
    }

    public void setBackgroundRolloverGradient(Color topColor, Color bottomColor) {
        int height = (int) bgGradientRollover.getEndPoint().getY();
        bgGradientRollover = newGradient(height, topColor, bottomColor);
    }

    public void setBackgroundPressedGradient(Color topColor, Color bottomColor) {
        int height = (int) bgGradientPressed.getEndPoint().getY();
        bgGradientPressed = newGradient(height, topColor, bottomColor);
    }

    private void createDefaultGradients(int height) {
        bgGradient = newGradient(height, bgColorTop, bgColorBottom);
        bgGradientRollover = newGradient(height, bgColorTopRollover,
                                                 bgColorBottomRollover);
        bgGradientPressed = newGradient(height, bgColorTopPressed,
                                                bgColorTopRollover);
    }
    
    private void adjustGradients(int newHeight) {
        Color[] bgColors = bgGradient.getColors();
        Color[] bgColorsRollover = bgGradientRollover.getColors();
        Color[] bgColorsPressed = bgGradientPressed.getColors();
        bgGradient = newGradient(newHeight, bgColors[0], bgColors[1]);
        bgGradientRollover = newGradient(newHeight, bgColorsRollover[0],
                                                    bgColorsRollover[1]);
        bgGradientPressed = newGradient(newHeight, bgColorsPressed[0],
                                                   bgColorsPressed[1]);
    }
 
    private LinearGradientPaint newGradient(int height, Color topColor,
                                                        Color bottomColor) {
        return new LinearGradientPaint(0, 0, 0, height,
                                       new float[] {0f, 1f},
                                       new Color[] {topColor, bottomColor});
    }
}
