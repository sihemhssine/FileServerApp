package boutons;



import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.LinearGradientPaint;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.plaf.basic.BasicButtonUI;

import boutons.Orientation;

public class FlecheUI extends BasicButtonUI{
	
	/** Couleur de la fleche*/
	private Color couleurBase = Color.cyan.darker();
	/** Couleur de la fleche lorqu'elle est selectionnee*/
	private Color couleurBaseB = Color.cyan;
	/** Couleur courante de la fleche*/
	private Color couleur = couleurBase;
	/** position X de la fleche*/
	private int x = 0;
	/** position Y de la fleche*/
	private int y = 0;
	/** booleen définissant l'orientation de la fleche*/
	private Orientation orientation = Orientation.droite;
	
	public void paint(Graphics arg0, JComponent c) {
		c.setOpaque(false);
		Graphics2D g = (Graphics2D) arg0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(couleur);
		g.setStroke(new BasicStroke(3));
		dessinerFleche(c, g, true);
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {couleur, Color.white};
		Paint gp = new LinearGradientPaint(new Point2D.Float(0, 0), 
				new Point2D.Float(0, c.getHeight()), dist, colors);
		g.setPaint(gp);
		dessinerFleche(c, g, false);
	}
	
	/**
	 * Dessine une fleche
	 * @param g : l'element graphique sur lequel on dessine
	 * @param bordure : booleen vrai si on ne dessine que la bordure
	 */
	private void dessinerFleche(JComponent c, Graphics2D g, boolean bordure){
		int xFleche = c.getWidth()/2;
		int fin = c.getWidth();
		int debut = 10;
		int[] xpoints = new int[7];
		int[] ypoints = new int[7];
		xpoints[0] = x+debut;
		xpoints[1] = x+xFleche;
		xpoints[2] = x+xFleche;
		xpoints[3] = x+fin-debut;
		xpoints[4] = x+xFleche;
		xpoints[5] = x+xFleche;
		xpoints[6] = x+debut;
		ypoints[0] = y+c.getHeight()/3;
		ypoints[1] = y+c.getHeight()/3;
		ypoints[2] = y+debut;
		ypoints[3] = y+c.getHeight()/2;
		ypoints[4] = y+c.getHeight()-debut;
		ypoints[5] = y+2*c.getHeight()/3;;
		ypoints[6] = y+2*c.getHeight()/3;

		Polygon p = new Polygon(xpoints, ypoints, 7);
		Polygon p2 = null;
		switch(orientation){
		case droite :
			p2=p;
			break;
		case gauche :
			p2=rotate(p, Math.PI, c.getWidth()/2, c.getHeight()/2);
			break;
		case bas :
			p2=rotate(p, Math.PI/2, c.getWidth()/2, c.getHeight()/2);
			break;
		case haut :
			p2=rotate(p, 3*Math.PI/2, c.getWidth()/2, c.getHeight()/2);
			break;
		}

		if(!bordure){
			g.fillPolygon(p2);
		}
		else{
			g.drawPolygon(p2);
		}
	}

	/**
	 * Fonction permettant de faire tourner un polygone
	 * @param p : le polygone à tourner
	 * @param angle : l'angle de rotation
	 * @param x : l'abscisse du centre
	 * @param y : l'ordonnées du centre
	 * @return le polygone retourne
	 */
	private static Polygon rotate (Polygon p, double angle, int x, int y){
		Polygon polygone = new Polygon();
		int[] pointx = p.xpoints;
		int[] pointy = p.ypoints;
		AffineTransform rotation = AffineTransform.getRotateInstance (angle, x, y);
		for (int k=0; k<p.npoints; k++){
			Point point = new Point();
			rotation.transform (new Point(pointx[k], pointy[k]), point);
			polygone.addPoint(point.x, point.y);
		}
		return polygone;
	}
	
	public static void main(String[] a){
		JFrame frame = new JFrame();
		frame.setSize(200, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));
		JButton b = new JButton("ee");
		b.setUI(new FlecheUI());
		frame.add(b);
		frame.setVisible(true);
	}

}
