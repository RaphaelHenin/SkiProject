import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class DrawPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private ArrayList<Point> points;



	public DrawPanel() {
    	repaint();
        points = new ArrayList<Point>();
        /*addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                points.add(new Point(e.getX(), e.getY()));
                System.out.println("x : "+e.getX()+"\ny : "+ e.getY());
                repaint();
            }
        });*/
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(System.getProperty("user.dir") + "\\resources\\ski_station_map950_689.PNG"));
        } catch (IOException e) {
        }
        Image background = img;
        g.drawImage(background, 0, 0, null);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.red);
        if(points.size()!=0)
	        for (Point point : points) {
	            g2.fillOval(point.x, point.y, 20, 20);
	        }
        g2.dispose();
    }
    
    public ArrayList<Point> getPoints() {
    	return points;
    }

    public void setPoints(ArrayList<Point> points) {
    	this.points = points;
    }
}


