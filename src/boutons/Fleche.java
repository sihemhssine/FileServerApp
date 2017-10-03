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
import javax.swing.JFrame;

import boutons.Orientation;

/**
 * 
 * @author julien
 *
 */
public class Fleche extends JButton{

	/** Couleur de la fleche*/
	private Color couleurBase = new Color(46, 139, 87).darker();
	/** Couleur de la fleche lorqu'elle est selectionnee*/
	private Color couleurBaseB = new Color(46, 139, 87);
	/** Couleur courante de la fleche*/
	private Color couleur = couleurBase;
	/** position X de la fleche*/
	private int x = 0;
	/** position Y de la fleche*/
	private int y = 0;
	/** booleen définissant l'orientation de la fleche*/
	private Orientation orientation = Orientation.gauche;

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de base
	 */
	public Fleche(){
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

	/**
	 * Constructeur de Fleche
	 * @param versLaGauche : booleen vrai si la fleche va vers la gauche
	 */
	public Fleche(Orientation orientation){
		this();
		this.orientation = orientation;	
	}

	/**
	 * Surcharge de paintComponent
	 */
	protected void paintComponent(Graphics arg0){
		Graphics2D g = (Graphics2D) arg0;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(couleur);
		g.setStroke(new BasicStroke(3));
		dessinerFleche(g, true);
		float[] dist = {0.0f, 1.0f};
		Color[] colors = {couleur, Color.white};
		Paint gp = new LinearGradientPaint(new Point2D.Float(0, 0), 
				new Point2D.Float(0, getHeight()), dist, colors);
		g.setPaint(gp);
		dessinerFleche(g, false);
	}

	/**
	 * Dessine une fleche
	 * @param g : l'element graphique sur lequel on dessine
	 * @param bordure : booleen vrai si on ne dessine que la bordure
	 */
	private void dessinerFleche(Graphics2D g, boolean bordure){
		int xFleche = getWidth()/2;
		int fin = getWidth();
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
		ypoints[0] = y+getHeight()/3;
		ypoints[1] = y+getHeight()/3;
		ypoints[2] = y+debut;
		ypoints[3] = y+getHeight()/2;
		ypoints[4] = y+getHeight()-debut;
		ypoints[5] = y+2*getHeight()/3;;
		ypoints[6] = y+2*getHeight()/3;

		Polygon p = new Polygon(xpoints, ypoints, 7);
		Polygon p2 = null;
		switch(orientation){
		case droite :
			p2=p;
			break;
		case gauche :
			p2=rotate(p, Math.PI, getWidth()/2, getHeight()/2);
			break;
		case bas :
			p2=rotate(p, Math.PI/2, getWidth()/2, getHeight()/2);
			break;
		case haut :
			p2=rotate(p, 3*Math.PI/2, getWidth()/2, getHeight()/2);
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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(4, 1));
		JButton b = new Fleche();
		frame.add(b);
		JButton b2 = new Fleche(Orientation.haut);
		frame.add(b2);
		JButton b3 = new Fleche(Orientation.droite);
		frame.add(b3);
		JButton b4 = new Fleche(Orientation.bas);
		frame.add(b4);
		frame.setSize(100, 400);
		frame.setVisible(true);
	}
}
