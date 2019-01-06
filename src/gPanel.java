 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.text.DecimalFormat;

 public class gPanel extends JPanel {

     public boolean help;
     public boolean [] clicked;
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
         alertFont = new Font("Dialog", Font.BOLD, 52);;
         help=false;
         clicked=new boolean[8];
         for(int i=0;i<8;i++)
             clicked[i]=false;
         restartGame();

         addMouseListener(new MouseAdapter()//wciśnięcie myszki
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
                     if (Parameters.pause && !help) {
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

                 //wybranie help
                 if (me.getX() > (1130)&& me.getX() < (1250) && me.getY() > 80  && me.getY() <115) {

                     if(!help) {
                         help = true;
                         Parameters.pause = true;
                     }
                     else {
                         help = false;
                         Parameters.pause = false;
                     }
                 }
                    //wybranie probowki
                    if (me.getX() > 30 && me.getX() < 180 && me.getY() > 420 && me.getY() < 595 && !clicked[tubes.getindex(me.getX(),me.getY())]) {

                        clicked[tubes.getindex(me.getX(),me.getY())]=true;
                        if(tubes.answers[tubes.getindex(me.getX(),me.getY())])
                             gStatus.points++;
                        else
                            gStatus.points--;
                    }
                 //kolejny level
                 if(Parameters.levelPause && !Parameters.pause) {
                     if (Parameters.MoveMODE < Parameters.n_levels) {
                         Parameters.MoveMODE++;
                         gStatus.time += Parameters.levelTime;
                         Parameters.levelPause = false;
                         Parameters.bgImage = Parameters.loadImage("images/background.png");
                         gStatus.nextLevel();
                         restartGame();

                     } else {
                         //jesli nie ma juz wiecej poziomow to koniec gry
                         Parameters.end = true;
                         gStatus.time += Parameters.levelTime;
                         Parameters.pause = true;
                     }
                     repaint();
                 }
             }//koniec mouseClicked()
         });
     }

     @Override
     protected void paintComponent(Graphics gs) {//rysowanie

         Graphics2D g = (Graphics2D) gs;

         g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         g.drawImage(Parameters.bgImage, 0, 0, null);
         int dx=40;
         int dy=90;
         int d=20;
         int i=0;
         int b=0;
         //rysowanie probowek w wiadrze
         for (int y =410; y < 520; y =y+dy) {
            for (int x=30;x<180;x=x+dx)
              {
                 if (i < 8 && !clicked[i])
                     g.drawImage(tubes.tTube[i],x,y,null);
                     i++;
             }
         }

         //przenoszenie probowek

         for(int a=0;a<8; a++)
         {
             if (ch.wsp[0] < 192 && clicked[a]) {
                 g.drawImage(tubes.tTube[a], 163, 350, null);
             }
         }
        for(int a=0;a<8; a++)
        {
            if (ch.wsp[0] >192 && ch.wsp[0] < 980 && clicked[a]) {
                g.drawImage(tubes.tTube[a], ch.wsp[0] + 80, ch.wsp[1] + 150, null);
            }
        }
        //umieszczenie probowek w wiadrze
        if(ch.wsp[0]>980 )
        {
            int x=1080;
            while(x<1390 && b<8) {
                int y =330;

                    if (b < 8 && clicked[b]) {
                        g.drawImage(tubes.tTube[b], x, y, null);
                        x = x + d;
                    }
                        b++;

            }
        }
         //rysowanie stosownego ludzika
         if (ch.wsp[0]>192)
             g.drawImage(Parameters.chemist2,ch.wsp[0],ch.wsp[1],null);
         else
             g.drawImage(Parameters.chemist1,ch.wsp[0],ch.wsp[1],null);
         //rysowanie tytulu grupy zwiazkow
         g.drawImage(tubes.title,1100,440,null);

        //opis postepowania w grze
        if(help)
        {
            g.setColor(new Color(161, 148, 111));
            g.fillRect(300, 180, 880, 230);
            g.setColor(Color.black);
            g.setFont(menuFont);
            g.drawString("Klikając myszką wybierz probówki, które Twoim ", 330, 230);
            g.drawString("zdaniem pasują do kategorii podanej na zbiorniku na mecie.",330, 260);
            g.drawString("Po wybraniu wszystkich, Twoim zdaniem prawdziwych probówek, ", 330, 290);
            g.drawString("dostarcz je na metę poruszając się strzałkami na klawiaturze.", 330, 320);
            g.drawString("Poziom 1 i 2: max 4 probówki, poziom 3 i 4: max 3 probówki,", 330, 350);
            g.drawString("poziom 5: dwie probówki.", 330, 380);
        }


         g.setColor(Color.black);
         g.setFont(menuFont);
         DecimalFormat df = new DecimalFormat("#.##");
            //jesli wybrano MENU
         if (Parameters.pause && !help) {
             //rysuj MENU
             g.drawImage(Parameters.play,1130,10,null);
             g.drawImage(Parameters.theEnd,1036,70,null);
             g.drawImage(Parameters.newGame ,1053,115,null);
             //rysuj informacje o statusie gry
             g.drawImage(Parameters.level,300,10,null);
             g.drawString("" + gStatus.level, 420, 44);
             g.drawImage(Parameters.time ,670,630,null);
             g.drawString(""+ df.format(gStatus.time)+"s", 755, 660);
             g.drawImage(Parameters.points,440,625,null);
             g.drawString("" + gStatus.points, 560, 660);
            //jesli zakonczono gre
             if (Parameters.end) {
                 g.setColor(new Color(32, 75, 172));
                 g.fillRect(300, 180, 620, 280);
                 g.setColor(Color.white);
                 g.setFont(alertFont);
                 g.drawString("KONIEC GRY! ", 390, 270);
                 g.drawString("Łączny czas gry:" + df.format(gStatus.time) + "s", 330, 340);
                 g.drawString("Punkty razem:" + df.format(gStatus.points), 330, 410);
             }

         }
         //jesli nie wybrano menu
         else {
             g.drawImage(Parameters.level,300,10,null);
             g.drawString("" + gStatus.level, 420, 44);
             g.drawImage(Parameters.time ,665,630,null);
             g.drawString(""+ df.format((System.currentTimeMillis()-Parameters.startTime)/1000.0)+"s", 755, 660);
             g.drawImage(Parameters.points,440,625,null);


        //czy ukończono poziom
             if (ch.wsp[0]>950) {
                 if (!Parameters.levelPause) {
                     long stopTime = System.currentTimeMillis();
                     Parameters.levelTime = (stopTime - Parameters.startTime) / 1000.0;
                     Parameters.levelPause = true;
                 }
//                     //uspienie
//                     try {
//                         wait(1000);
//                     } catch (InterruptedException e)  {
//                         Thread.currentThread().interrupt();
//
//                     }

                 g.drawString("" + gStatus.points, 560, 660);

            }
                g.drawImage(Parameters.menuImage,1130,10,null);
            //jesli nie wybrano POMOC
            if(!help)
                g.drawImage(Parameters.help,1130,70,null);
            //jesli wybrano POMOC
            else
                g.drawImage(Parameters.closehelp,1130,70,null);

          }
         }

     private void restartGame()
     {

         Parameters.startTime = System.currentTimeMillis();
         Parameters.pause = false;


     }
 }
