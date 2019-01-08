package com.zmijewska.game;
 import javax.swing.*;
 import java.awt.*;
 import java.io.File;
 import javax.sound.sampled.AudioInputStream;
 import javax.sound.sampled.AudioSystem;
 import javax.sound.sampled.Clip;

 /**
  * Klasa - kontener parametrow
  * Parametrami sa pliki graficzne i zapisu stanu gry, aktualny czas, czas startu,
  * pausa itp. W klasi tej odbywa sie takze zarzadzanie plikami dzwiekowymi
  * @author Aleksandra Żmijewska
  */
 public class Parameters {
     public static long T_break;
     public static long T_pause;
     public static long elapsedTime;
     /**początek czasu*/
     public static long startTime = System.currentTimeMillis();
     /**liczba poziomow*/
     public final static long n_levels=5;
     /**aktualny poziom gry*/
     public static int MoveMODE=1;
     /**obraz tla*/
     public static Image bgImage;
     /**bohater gry w pozycji pierwszej*/
     public static Image chemist1;
     /**bohater gry w pozycji drugiej*/
     public static Image chemist2;
     /**tytul kategorii poziomu*/
     public static Image title1;
     public static Image title2;
     public static Image title3;
     public static Image title4;
     public static Image title5;
     /**obraz menu*/
     public static Image menuImage;
     /**obraz pomocy*/
     public static Image help;
     /**obraz zakmniecia pomocy*/
     public static Image closehelp;
     /**obraz końca gry*/
     public static Image theEnd;
     /**obraz nowej gry*/
     public static Image newGame;
     /**obraz powrotu do gry*/
     public static Image play;
     /**obraz poziomu*/
     public static Image level;
     /**obraz czasu*/
     public static Image time;
     /**obraz punktow*/
     public static Image points;
     /**tablica obrazków probowek*/
     public static Image[] testTubes;
     /**przerwa w grze*/
     public static boolean pause=false;
     /**czy wybrano menu*/
     public static boolean levelPause=false;
     /**koniec gry*/
     public static boolean end=false;

     /**
      * Funkcja odtwarzania dzwieku z pliku
      * @param f - obiekt klasy File reprezentujący scieżke do pliku MP3
      */
     public static synchronized void playSound(final File f) {
         new Thread(new Runnable() {
             public void run() {
                 try {
                     Clip clip = AudioSystem.getClip();
                     AudioInputStream inputStream = AudioSystem.getAudioInputStream(f);
                     clip.open(inputStream);
                     clip.start();
                 } catch (Exception e) {
                     System.err.println(e.getMessage());
                 }
             }
         }).start();
     }

     /**
      * Metoda ladowania początkowych zasobow gry
      */
     public static void loadInitialImages() {
         bgImage = loadImage("images/background.png");
         chemist1= loadImage("images/ludek1.png");
         chemist2= loadImage("images/ludek2.png");
         title1= loadImage("images/napis1.png");
         title2= loadImage("images/napis2.png");
         title3= loadImage("images/napis3.png");
         title4= loadImage("images/napis4.png");
         title5= loadImage("images/napis5.png");
         menuImage= loadImage("images/menu.png");
         help= loadImage("images/pomoc.png");
         closehelp= loadImage("images/koniec_pomocy.png");
         theEnd= loadImage("images/koniec.png");
         newGame= loadImage("images/nowa_gra.png");
         play= loadImage("images/graj.png");
         level= loadImage("images/poziom.png");
         time= loadImage("images/czas.png");
         points= loadImage("images/punkty.png");

         testTubes= new Image[31];
         testTubes[0]=loadImage("images/p1.png");
         testTubes[1]=loadImage("images/p6.png");
         testTubes[2]=loadImage("images/p7.png");
         testTubes[3]=loadImage("images/p8.png");
         testTubes[4]=loadImage("images/p16.png");
         testTubes[5]=loadImage("images/p19.png");
         testTubes[6]=loadImage("images/p23.png");
         testTubes[7]=loadImage("images/p4.png");
         testTubes[8]=loadImage("images/p9.png");
         testTubes[9]=loadImage("images/p3.png");
         testTubes[10]=loadImage("images/p17.png");
         testTubes[11]=loadImage("images/p18.png");
         testTubes[12]=loadImage("images/p20.png");
         testTubes[13]=loadImage("images/p21.png");
         testTubes[14]=loadImage("images/p22.png");
         testTubes[15]=loadImage("images/p26.png");
         testTubes[16]=loadImage("images/p10.png");
         testTubes[17]=loadImage("images/p27.png");
         testTubes[18]=loadImage("images/p13.png");
         testTubes[19]=loadImage("images/p30.png");
         testTubes[20]=loadImage("images/p12.png");
         testTubes[21]=loadImage("images/p14.png");
         testTubes[22]=loadImage("images/p14.png");
         testTubes[23]=loadImage("images/p31.png");
         testTubes[24]=loadImage("images/p25.png");
         testTubes[25]=loadImage("images/p29.png");
         testTubes[26]=loadImage("images/p2.png");
         testTubes[27]=loadImage("images/p5.png");
         testTubes[28]=loadImage("images/p11.png");
         testTubes[29]=loadImage("images/p15.png");
         testTubes[30]=loadImage("images/p28.png");

     }
     /**
      * Metoda pobierania obiektu klasy Image na podstawie sciezki
      * dostepu podanej jako String
      */
     public static Image loadImage(String fileName) {
         return new ImageIcon(fileName).getImage();
     }

 }



