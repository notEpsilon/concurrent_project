import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
   
   int x;
   int y;
   int controller;
   Goal goal;
   Player[] p4;
   Thread[] players;
   Football football;
   InfoPanel infoPanel;
   
   public GamePanel(InfoPanel infoPanel) {
      setBackground(new Color(0,100,0));
      this.infoPanel = infoPanel;
      this.controller = 0;
   }

   public void createGameEntities() {
      p4 = new Player[] {
         new Player(this, 130, 375, Color.MAGENTA),
         new Player(this, 90, 375, Color.BLACK),
         new Player(this, 50, 375, Color.RED),
         new Player(this, 10, 375, Color.ORANGE)
      };

      players = new Thread[] {
         new Thread(p4[0]),
         new Thread(p4[1]),
         new Thread(p4[2]),
         new Thread(p4[3])
      };

      for (int i = 0; i < players.length; i++)
         players[i].start();
   
      goal = new Goal(this,150,5);
   }

   public void kickBall() {
      if (football != null) {
         if(football.gety() < 0){
            football = new Football(this, infoPanel, p4[controller].getPlayer(), this.goal);
            football.start();
            this.controller = (this.controller + 1) % 4;
         }
      }
      else {
         football = new Football(this, infoPanel, p4[controller].getPlayer(), this.goal);
         football.start();
         this.controller = (this.controller + 1) % 4;
      }
   }

   public void startGoal() {
      goal = new Goal(this,150,5);
      goal.start();
   }

   public void drawGameEntities() {
      if (players[0] != null && goal != null) {
         for (int i = 0; i < 4; i++) {
            p4[i].draw();
         }
         goal.draw();
      }
   }

  public void updateGameEntities(int direction) {
      if (p4[controller] == null)
         return;

      if (direction == 1) {
         p4[controller].erase();
         p4[controller].moveLeft();
      }
      else if (direction == 3) {
         p4[controller].erase();
         p4[controller].moveRight();
      }
 }

   public void setX (int xPos) {
	   x = xPos;
   }

   public void setY (int yPos) {
	   y = yPos;
   }

   public void paintComponent(Graphics g) {
      super.paintComponent(g);
   }
}
