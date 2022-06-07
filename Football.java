import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;

import javax.swing.JPanel;

public class Football extends Thread {
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private final Random rnd = new Random();
 
    private JPanel panel;
    private InfoPanel infoPanel;
 
    private int x;
    private int y;
    private int dy;
 
    Graphics2D g2;
    private Color backgroundColor;
 
    private Goal goal;
    private boolean hit;
    private boolean kicked;

    public Football(JPanel p, InfoPanel infoPanel, Player player, Goal goal) {
      this.goal = goal;
      this.panel = p;
      this.infoPanel = infoPanel;
      this.hit = false;
      this.kicked = false;

      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;
      backgroundColor = panel.getBackground ();


      x = player.getx();
      y = player.gety() - 10;

      dy = -10;

      this.infoPanel.displayInfo();
    }
  
    public Rectangle2D.Double getBoundingRectangle() {	
      return new Rectangle2D.Double (x, y, XSIZE, YSIZE);
    }
 
    public void draw() {				
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;
      g2.setColor (new Color(255, 255, 255));
      g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
    }
     
    public void erase() {			
       Graphics g = panel.getGraphics ();
       g2 = (Graphics2D) g;
       g2.setColor (backgroundColor);
       g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
    }
 
    public void move() {
       if (!panel.isVisible()) return;

       y += dy;

       Rectangle2D.Double goalRect = goal.getBoundingRectangle();
       Rectangle2D.Double myRect = this.getBoundingRectangle();

      if (myRect.intersects(goalRect)) {
         this.hit = true;
         y = -5;
         infoPanel.decreasePoints(10);
         infoPanel.incrementHits();
         infoPanel.displayInfo();	
      }

      if ((y < 0) && hit == false && kicked == false)	{		
         infoPanel.incrementMisses();
         infoPanel.incrementPoints(10);
         infoPanel.displayInfo();	
         kicked = true;
      }
         
    }

     public void run() {
         try {
            draw();
            int random = rnd.nextInt(150 - 50 + 1) + 50 + 10;
            while (true) {
               erase();
               move ();
               draw();
               sleep(random);
            }
         }
         catch(InterruptedException e) {
            System.out.println(e +" bro idk nah");
         }
     }

     public int gety(){
        return this.y;
     }
     public void sety(int y){
        this.y = y;
     }
}
