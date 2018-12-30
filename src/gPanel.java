 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.text.DecimalFormat;

 public class gPanel extends JPanel {

     public int sWidth;
     public int sHeight;

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
         tubes.level(gStatus.level);
         menuFont = new Font("Dialog", Font.ITALIC , 28);
         alertFont = new Font("Dialog", Font.BOLD, 92);
         this.sWidth = width;
         this.sHeight = height;
         restartGame();

         addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent me) {
                 //wybranie MENU
                 if (me.getX() > (1130)&& me.getX() < (1250) && me.getY() > 10  && me.getY() <80) {
                     Parameters.pause = !Parameters.pause;
                     return;
                 }
                 //wybranie koniec gry
                 if (me.getX() > 1036 && me.getX() < 1236 && me.getY() > 80&& me.getY() < 115) {
                     if (Parameters.pause) {
                         System.exit(1);
                     }
                 }
                 //wybranie nowa gra
                 if (me.getX() > 1053 && me.getX() < 1253 && me.getY() > 115 && me.getY() <250 && Parameters.pause) {
                         Parameters.MoveMODE = 1;
                         Parameters.end = false;
                         gStatus.reset();
                         Parameters.levelPause = false;
                         Parameters.bgImage = Parameters.loadImage("images/background.png");
                         restartGame();
                         repaint();

                 }
                for (int i=0;i<8;i++) {
                    if (me.getX() > 30 && me.getX() < 180 && me.getY() > 420 && me.getY() < 595) {

                    Parameters.hit = true;
                    gStatus.points++;
                    }
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
         int dx=40;
         int dy=90;
         int d=20;
         int i=0;
         int b=0;

         for (int x=30;x<320;x=x+dx) {
             for (int y =410; y < 520; y =y+dy) {

                 if (i < 8) {

                   g.drawImage(tubes.tTube[i],x,y,null);
                     i++;
                 }
             }
         }
        for(int a=0;a<8; a++)
        {
            if (ch.wsp[0] + 150 < 400) {
                g.drawImage(tubes.tTube[a], ch.wsp[0] + 780, ch.wsp[1] + 150, null);
            }
        }
        if(ch.wsp[0]+150>400)
        {
            for (int x=1080;x<1390;x=x+d) {
                int y =330;

                    if (b < 8) {

                                g.drawImage(tubes.tTube[b],x,y,null);
                                b++;
                    }
            }
        }

         g.drawImage(Parameters.chemist2,ch.wsp[0],ch.wsp[1],null);
         g.drawImage(tubes.title,1100,440,null);

         g.setColor(Color.black);
         g.setFont(menuFont);

         if (Parameters.pause) {
             g.drawImage(Parameters.play,1130,10,null);
             g.drawImage(Parameters.theEnd,1036,70,null);
             g.drawImage(Parameters.newGame ,1053,115,null);

             g.drawImage(Parameters.level,300,10,null);
             g.drawString("" + gStatus.level, 420, 44);
             g.drawImage(Parameters.time ,670,630,null);
             g.drawString(""+ (gStatus.time), 770, 660);
             g.drawImage(Parameters.points,440,625,null);
             g.drawString("" + gStatus.points, 560, 660);

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
             g.drawImage(Parameters.level,300,10,null);
             g.drawString("" + gStatus.level, 420, 44);
             g.drawImage(Parameters.time ,670,630,null);
             g.drawString(""+ (gStatus.time), 770, 660);
             g.drawImage(Parameters.points,440,625,null);


  //czy ukończono poziom
             if (ch.wsp[0]+150>430) {
                 if (!Parameters.levelPause) {
                     long stopTime = System.currentTimeMillis();
                     Parameters.levelTime = (stopTime - Parameters.startTime) / 1000.0;
                     Parameters.levelPause = true;
                     if (Parameters.MoveMODE < Parameters.n_levels) {
                         Parameters.MoveMODE++;
                         gStatus.time += Parameters.levelTime;
                         Parameters.levelPause = false;
                         Parameters.bgImage = Parameters.loadImage("images/background.png");
                         gStatus.nextLevel();
                         restartGame();
                         Parameters.end = true;
                     } else
                     {
                         Parameters.end = true;
                         gStatus.time += Parameters.levelTime;
                         Parameters.pause = true;
                     }
                     repaint();
                 }



            }
            else
                 g.drawString("" + gStatus.points, 560, 660);
                g.drawImage(Parameters.menuImage,1130,10,null);


          }
         }

     private void restartGame()
     {
         gStatus.resetPoints();
         Parameters.startTime = System.currentTimeMillis();
         Parameters.pause = false;

     }
 }
