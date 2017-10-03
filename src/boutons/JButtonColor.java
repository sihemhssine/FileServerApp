package boutons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class JButtonColor extends JButton {
	String ch;
	Color c;
    public JButtonColor(String c) {
        super(c);
        setContentAreaFilled(false);
        setFocusPainted(false);
        
      
		// used for demonstration
    }
public void setColor(Color co)
{c=co;}
    public void paintComponent(Graphics g) {
        final Graphics2D g2 = (Graphics2D) g.create();
        g2.setPaint(new GradientPaint(
                new Point(0, 0), 
                c, 
                new Point(0, getHeight()), 
               c.brighter()));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();

        super.paintComponent(g);
    }


    }