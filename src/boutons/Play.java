package boutons;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Play extends JButton{

	private static final long serialVersionUID = 1L;
	/** Couleur du bouton*/
	private Color couleurBase = Color.green;
	/** Couleur du bouton lorqu'il est selectionne*/
	private Color couleurBaseB = couleurBase.brighter();
	/** Couleur courante du bouton*/
	private Color couleur = couleurBase;
	/** position X du bouton*/
	private int x = 0;
	/** position Y du bouton*/
	private int y = 0;


	/**
	 * Constructeur de base
	 */
	public Play(){
		setOpaque(false);
		setSize(120,200);
		addMouseListener(new java.awt.event.MouseListener() {
			public void mouseEntered(java.awt.event.MouseEvent e) {
				if (isEnabled()) {
					couleur = couleurBaseB;
				}
			}

			public void mouseClicked(java.awt.event.MouseEvent e) {

			}

			public void mousePressed(java.awt.event.MouseEvent e) {
				x-=2;
				y-=2;
			}

			public void mouseReleased(java.awt.event.MouseEvent e) {
				x+=2;
				y+=2;
			}

			public void mouseExited(java.awt.event.MouseEvent e) {
				if (isEnabled()) {
					couleur=couleurBase;
				}
			}
		});
	}

	protected void paintComponent(Graphics arg0){
		Graphics2D g = (Graphics2D) arg0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(couleur);
		g.setStroke(new BasicStroke(5));
		dessinerCroix(g, true);
		float[] dist = {0.0f, 0.5f, 1.0f};
		Color[] colors = {Color.white,couleur, Color.white};
		Paint gp = new LinearGradientPaint(new Point2D.Float(0, 0), 
				new Point2D.Float(0, getHeight()), dist, colors);
		g.setPaint(gp);
		dessinerCroix(g, false);
	}

	private void dessinerCroix(Graphics2D g, boolean bordure){
		int marge = 10;
		int cote=Math.min(getWidth()-2*marge, getHeight()-2*marge);
		int hauteur = cote;
		int x0 = x+marge;
		int y0 = y+marge;
		int[] xpoints = new int[3];
		int[] ypoints = new int[3];
		xpoints[0]=x0;
		xpoints[1]=x0;
		xpoints[2]=x0+cote;
		ypoints[0]=y0+hauteur-marge;
		ypoints[1]=y0;
		ypoints[2]=cote/2;
		Polygon p =new Polygon(xpoints, ypoints, 3);
		if(bordure){
			g.drawPolygon(p);
		}
		else{
			g.fillPolygon(p);
		}
	}

	

	public static void main(String[] a){
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(2, 1));
		JButton b = new Play();
		frame.add(b);

		frame.setSize(100, 200);
		frame.setVisible(true);
	}
}
