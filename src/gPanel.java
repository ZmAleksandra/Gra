 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.io.File;
 import java.text.DecimalFormat;
 import java.util.concurrent.TimeUnit;
 /**
  * Główna obszar graficzny gry, wyświetlanie obrazów, zarządzanie akcją klikania myszki
  * Klasa dziedzicząca po klasie JPanel
  * @author Aleksandra Żmijewska
  */
 public class gPanel extends JPanel {

     /**
      *    zmienna odpowiedzialna za kliknięcie pomocy
      */

     public boolean help;
     /**czy kliknięto probówkę*/
     public boolean [] clicked;
     /**obiekt klasy GameStatus*/
     public GameStatus gStatus;
     /** Czcionki stosowane w pasku Menu*/
     public Font menuFont;
     /** Czcionki stosowane jako alert w polu gry*/
     public Font alertFont;
     /** obiekt klasy Chemist*/
     public Chemist move;
     /** obiekt klasy TubeTest*/
     public TubeTest tubes;

 /**
  * Konstruktor klasy pola graficznego gry.
  * Ustawienia początkowe oraz ładowanie zasobów
  * Ponadto dodanie obsługi zdarzeń w polu graficznym gry
  */
     public gPanel(int width, int height, JFrame jFrame) {
         move = new Chemist(jFrame);
         gStatus = new GameStatus();
         tubes = new TubeTest();
         gStatus.reset();
         tubes.level(gStatus.level);
         //czcionka wyświetlania zmieniającego się stanu gry
         menuFont = new Font("Dialog", Font.ITALIC , 28);
         //czcionka zakończenia gry
         alertFont = new Font("Dialog", Font.BOLD, 52);
         help=false;
         clicked=new boolean[8];

         restartGame();
         /* Dodaj obsługę zdarzeń - wciśnięcie przycisku myszki*/
         addMouseListener(new MouseAdapter()
         {

             @Override
             public void mouseClicked(MouseEvent me) {

                 //wybranie MENU
                 if (me.getX() > (1130)&& me.getX() < (1250) && me.getY() > 10  && me.getY() <80) {
                     Parameters.pause = !Parameters.pause;
                     repaint();
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
                         repaint();
                     }
                     else {
                         help = false;
                         Parameters.pause = false;
                     }
                 }
                    //wybranie probowki
                    if (me.getX() > 30 && me.getX() < 180 && me.getY() > 420 && me.getY() < 595 && !clicked[tubes.getindex(me.getX(),me.getY())]&& move.position[0] <195)
                    {
                        clicked[tubes.getindex(me.getX(),me.getY())]=true;
                        Parameters.playSound(new File("sounds/click1.wav"));
                        if(tubes.answers[tubes.getindex(me.getX(),me.getY())])
                            gStatus.points++;
                        else
                            gStatus.points--;
                    }

             }//koniec mouseClicked()
         });
     }
     /**
      * Nadpisz metodę odpowiedzialną za odrysowanie panelu - własne wypełnienie
      * pola graficznego gry, zgodnie z wybraną treścią.
      *
      * @param gs
      */
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
             if (move.position[0] < 195 && clicked[a]) {
                 g.drawImage(tubes.tTube[a], 163, 330, null);
             }
         }
        for(int a=0;a<8; a++)
        {
            if (move.position[0] >195 && move.position[0] < 960 && clicked[a]) {
                g.drawImage(tubes.tTube[a], move.position[0] + 80, move.position[1] + 150, null);
            }
        }
        //umieszczenie probowek w wiadrze
        if(move.position[0]>960 )
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
         if (move.position[0]>195)
             g.drawImage(Parameters.chemist2,move.position[0],move.position[1],null);
         else
             g.drawImage(Parameters.chemist1,move.position[0],move.position[1],null);
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
            g.drawString("Po wybraniu wszystkich, Twoim zdaniem prawidłowych probówek, ", 330, 290);
            g.drawString("dostarcz je na metę poruszając się strzałkami na klawiaturze.", 330, 320);
            g.drawString("Poziom 1 i 2: są 4 prawidłowe probówki, poziom 3 i 4: są ", 330, 350);
            g.drawString("3 prawidłowe probówki, poziom 5: są dwie prawidłowe probówki.", 330, 380);
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
             g.drawImage(Parameters.time ,665,630,null);
             g.drawString(""+ df.format((Parameters.elapsedTime)/1000.0)+"s", 755, 660);
             g.drawImage(Parameters.points,440,625,null);
             g.drawString("" + gStatus.points, 560, 660);
            //jesli zakonczono gre
             if (Parameters.end) {
                 g.setColor(new Color(32, 75, 172));
                 g.fillRect(300, 180, 640, 280);
                 g.setColor(Color.white);
                 g.setFont(alertFont);
                 g.drawString("KONIEC GRY! ", 390, 270);
                 g.drawString("Laczny czas gry:" + df.format((gStatus.time)/1000.0) + "s", 330, 340);
                 g.drawString("Punkty razem:" + df.format(gStatus.points), 330, 410);
                 g.drawImage(Parameters.time ,665,630,null);
                 g.setColor(Color.black);
                 g.setFont(menuFont);
                 g.drawString(""+ df.format((gStatus.time)/1000.0)+"s", 755, 660);
             }

         }
         //jesli nie wybrano menu
         else {
             g.drawImage(Parameters.level,300,10,null);
             g.drawString("" + gStatus.level, 420, 44);
             g.drawImage(Parameters.time ,665,630,null);
             g.drawString(""+ df.format((Parameters.elapsedTime)/1000.0)+"s", 755, 660);
             g.drawImage(Parameters.points,440,625,null);


        //czy ukończono poziom
             if (move.position[0]>980) {
                 if (!Parameters.levelPause) {
                     Parameters.levelPause = true;
                     try {
                         TimeUnit.SECONDS.sleep(1);
                     } catch (InterruptedException e) {
                         e.printStackTrace();
                     }

                 }
                     //kolejny level
                 if (Parameters.levelPause) {
                     if (Parameters.MoveMODE < Parameters.n_levels) {
                         Parameters.MoveMODE++;
                         gStatus.time += Parameters.elapsedTime;
                         Parameters.levelPause = false;
                         Parameters.bgImage = Parameters.loadImage("images/background.png");
                         gStatus.nextLevel();
                         restartGame();

                     } else
                     {
                         //jesli nie ma juz wiecej poziomow to koniec gry
                         gStatus.time += Parameters.elapsedTime;
                         Parameters.end = true;
                         Parameters.pause = true;

                     }
                         repaint();
                     }

             }
             if (move.position[0]>960)
                 g.drawString("" + gStatus.points, 560, 660);
                 g.drawImage(Parameters.menuImage,1130,10,null);
            //jesli nie wybrano POMOC
            if(!help)
                g.drawImage(Parameters.help,1130,70,null);
            //jesli wybrano POMOC
            else
                g.drawImage(Parameters.closehelp,1130,70,null);

          }
         }
     /**
      * Restart gry - ustawienia parametrów oraz obiektów pierwszego planu
      */
     private void restartGame()
     {
         Parameters.startTime = System.currentTimeMillis();
         Parameters.elapsedTime=0;
         Parameters.T_break=0;
         Parameters.pause = false;
         tubes.level(gStatus.level);
         move.position[0] = 190;
         move.position[1] = 200;
         for(int i=0;i<8;i++)
             clicked[i]=false;
     }
 }

