 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.text.DecimalFormat;

 public class gPanel extends JPanel {

     public int sWidth;
     public int sHeight;
     public int barHeight;
     public GameStatus gStatus;
     public Font menuFont;
     public Font alertFont;
     public Chemist ch;
     public TubeTest tubes;


     public gPanel(int width, int height, JFrame jFrame) {
         ch = new Chemist(jFrame);
         gStatus = new GameStatus();
         tubes = new TubeTest();
         gStatus.reset();
         tubes.level(5);
         menuFont = new Font("Dialog", Font.BOLD, 36);
         alertFont = new Font("Dialog", Font.BOLD, 92);

         this.sWidth = width;
         this.sHeight = height;
         barHeight = 50;

         restartGame();


         addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent me) {
                 if (me.getX() > (900) && me.getY() > (sHeight -100)) {
                     Parameters.pause = !Parameters.pause;
                     return;
                 }

                 if (me.getX() < 300 && me.getY() > (sHeight - barHeight)) {
                     if (Parameters.pause) {
                         System.exit(1);
                     }
                 }
                 if (me.getX() > 500 && me.getX() < 800 && me.getY() > (sHeight - barHeight)) {
                     if (Parameters.pause) {
                         Parameters.MoveMODE = 1;
                         Parameters.end = false;
                         gStatus.reset();
                         Parameters.levelPause = false;
                         Parameters.bgImage = Parameters.loadImage("images/background.png");
                         restartGame();
                         repaint();
                     } else {
                         if (Parameters.levelPause) {
                             if (Parameters.MoveMODE < Parameters.NO_LEVELS) {
                                 Parameters.MoveMODE++;
                                 gStatus.time += Parameters.levelTime;
                                 Parameters.levelPause = false;
                                 Parameters.bgImage = Parameters.loadImage("images/background.png");
                                 gStatus.nextLevel();
                                 restartGame();
                                 Parameters.end = true;
                             } else {
                                 Parameters.end = true;
                                 gStatus.time += Parameters.levelTime;
                                 Parameters.pause = true;
                             }
                             repaint();
                         }
                     }
                 }

                 if(me.getX()> 30 && me.getX()<180 && me.getY()>420 && me.getY()<595)
                 {


                     gStatus.points++;
                 }

//                 for (int i = 0; i < fBalloon.length; i++) {
//                     if (me.getY() < (sHeight - barHeight)) {
//                         if (fBalloon[i].containsPoint(me.getX(), me.getY())) {//sprawdz czy balonik
//                             // zawiera te punkty któe zostaly wcisniete przez uzytkownika za pomocą myszki
//                             if (!fBalloon[i].hit) {
//                                 fBalloon[i].setHit();
//                                 gStatus.points++;
//                             }
//                         }
//                     }
//                 }//koniec for i
             }//koniec mouseClicked()
         });
     }

     @Override
     protected void paintComponent(Graphics gs) {

         Graphics2D g = (Graphics2D) gs;

         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g.drawImage(Parameters.bgImage, 0, 0, null);
         int d=40;
         int dy=90;
         int i=0;


         for (int x=30;x<320;x=x+d) {
             for (int y =410; y < 520; y =y+dy) {

                 if (i < 8) {

                   g.drawImage(tubes.tTube[i],x,y,null);
                     i++;
                 }
             }
         }

         g.drawImage(Parameters.chemist2,ch.wsp[0],ch.wsp[1],null);
         g.drawImage(tubes.title,1120,450,null);
         g.setColor(new Color(50, 47, 22));
         g.fillRect(0, sHeight - barHeight-30, sWidth, barHeight);
         g.setColor(Color.white);
         g.setFont(menuFont);

         if (Parameters.pause) {
             g.drawString("GRA", 1000, sHeight - 50);
             g.setColor(Color.red);
             g.drawString("KONIEC GRY!", 10, sHeight - 50);
             g.setColor(Color.white);
             g.drawString("O GRZE...", 300, sHeight - 50);
             g.drawString("NOWA GRA!", 600, sHeight - 50);
             if (Parameters.end) {
                 g.setColor(Color.RED);
                 g.setFont(alertFont);
                 DecimalFormat df = new DecimalFormat("#.##");
                 g.drawString("KONIEC GRY! ", 170, sHeight / 2);
                 g.drawString("CZAS RAZEM=" + df.format(gStatus.time) + "s", 10, sHeight / 2 + 100);
                 g.setColor(Color.white);
                 g.setFont(menuFont);
             }

         }
         else {
             g.drawString("POZIOM:", 10, sHeight - 50);

            g.drawString("" + gStatus.level, 200, sHeight - 50);
             g.drawString("PUNKTY:", 300, sHeight - 50);

//             if (gStatus.points == Parameters.n) {
//                 if (!Parameters.levelPause) {
//                     long stopTime = System.currentTimeMillis();
//                     Parameters.levelTime = (stopTime - Parameters.startTime) / 1000.0;
//                     Parameters.levelPause = true;
//                 }
//                 g.setColor(Color.RED);
//                 g.drawString("GRASZ DALEJ?", 500, sHeight - 10);
//                 g.setFont(alertFont);
//                 DecimalFormat df = new DecimalFormat("#.##");
//                // g.drawString("WYGRANA" + df.format(Parameters.levelTime) + "s", 150, sHeight / 2);
//                 g.setColor(Color.white);
//                 g.setFont(menuFont);
//
//            }
//            else
                 g.drawString("" + gStatus.points, 500, sHeight - 50);

             g.drawString("MENU", 900, sHeight - 50);

          }
         }

     private void restartGame() {
         gStatus.resetPoints();
         Parameters.startTime = System.currentTimeMillis();
         Parameters.pause = false;


     }
 }
