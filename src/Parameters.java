 import javax.swing.*;
 import java.awt.*;

 public class Parameters {

     public static long GAME_TIME=Long.MAX_VALUE;
     public final static long n_levels=5;
     public static Image bgImage;
     public static Image chemist1;
     public static Image chemist2;
     public static Image title1;
     public static Image title2;
     public static Image title3;
     public static Image title4;
     public static Image title5;
     public static Image menuImage;
     public static Image theEnd;
     public static Image newGame;
     public static Image play;
     public static Image level;
     public static Image time;
     public static Image points;
     public static Image[] testTubes;
     public static boolean pause=false;
     public static boolean levelPause=false;
     public static boolean hit = false;
     public static long startTime;
     public static double levelTime;
     public static int MoveMODE=1;
     public static boolean end=false;
     public static int n=8;
     public static int gWidth=1280;
     public static int gHeight=720;

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
     public static Image loadImage(String fileName) {
         return new ImageIcon(fileName).getImage();
     }

 }



