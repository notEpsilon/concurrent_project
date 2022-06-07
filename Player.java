import java.awt.geom.Rectangle2D;
import java.awt.geom.Ellipse2D;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Color;

public class Player implements Runnable {

   private static final int XSIZE = 35;
   private static final int YSIZE = 10;

   private JPanel panel;
   private int x;
   private int y;
   private int dx;
   private Color clr;

   Graphics2D g2;
   private Color backgroundColor;

   public Player(JPanel p, int xPos, int yPos, Color clr) {
      panel = p;
      backgroundColor = panel.getBackground ();
      x = xPos;
      y = yPos;
      dx = 5;
      this.clr = clr;
   }

   @Override
   public void run() {}

   public void draw() {
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;

      Ellipse2D bat =new Ellipse2D.Double (x, y, XSIZE, YSIZE);
      g2.setColor(this.clr);
      g2.fill(bat);
   }

   public Rectangle2D.Double getBoundingRectangle() {
      return new Rectangle2D.Double(x, y, XSIZE, YSIZE);
   }

   public void erase () {
      Graphics g = panel.getGraphics ();
      g2 = (Graphics2D) g;

      g2.setColor (backgroundColor);
      g2.fill (new Ellipse2D.Double(x, y, XSIZE, YSIZE));
   }

   public void moveLeft() {
      if (!panel.isVisible()) return;
      if (x - dx > 0) x = x - dx;
   }

   public void moveRight() {
      Dimension dimension;

      if (!panel.isVisible ()) return;

      dimension = panel.getSize();

      if (x + dx + XSIZE < dimension.width) x = x + dx;      
   }

   public int getx() {
      return this.x;
   }

   public int gety(){
      return this.y;
   }

   public Player getPlayer() {
      return this;
   }
}
