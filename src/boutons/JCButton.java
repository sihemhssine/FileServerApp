package boutons;




import java.awt.Color;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.plaf.ButtonUI;

/**
 *
 * @author Eneko
 */
public class JCButton extends JButton implements Serializable {

    public JCButton(String string) {
        super(string);
        super.setUI(new JCButtonUI());
    }

    /**
     * The roundness is the width and height in pixels of the arc in this
     * component's corners.
     *
     * @param roundness
     */
    public void setRoundness(int roundness) {
        ((JCButtonUI)getUI()).setRoundness(roundness);
    }

    public void setBackgroundGradient(Color topColor, Color bottomColor) {
        ((JCButtonUI)getUI()).setBackgroundGradient(topColor, bottomColor);
    }

    public void setBackgroundRolloverGradient(Color topColor, Color bottomColor) {
        ((JCButtonUI)getUI()).setBackgroundRolloverGradient(topColor,
                                                            bottomColor);
    }

    public void setBackgroundPressedGradient(Color topColor, Color bottomColor) {
        ((JCButtonUI)getUI()).setBackgroundPressedGradient(topColor,
                                                           bottomColor);
    }

    /**
     * JCBUtton should only be used with JCButtonUI.
     *
     * This method does nothing and should not be used.
     *
     * @param ui
     */
    @Override
    public void setUI(ButtonUI ui) {
        super.setUI(ui);
    }


}
