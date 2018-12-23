import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.Random;

public class TubeTest
{
    public Image [] tTube;
 public TubeTest()
 {
 tTube= new Image [8];

 }

 public void level (int nLevel)
 {
 int numbers [] = new int [31];
 for(int i=0; i<31;i++)
  numbers[i]=i;
     Random r = new Random();
    Image [] tTube1= new Image[8];

         if(nLevel==1)
         {

             int i = 0;
             int xmaas;
             while (i!=4)
             {
                 xmaas=r.nextInt(16);
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             while (i!=8)
             {
                 xmaas=r.nextInt(15)+16;
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
         }

         if(nLevel==2)
         {

             int i = 0;
             int xmaas;
             while (i!=4)
             {
                 xmaas=r.nextInt(9);
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             while (i!=8)
             {
                 xmaas=r.nextInt(22)+9;
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
         }

         if(nLevel==3)
         {
             int i = 0;
             int xmaas;
             while (i!=3)
             {
                 xmaas=r.nextInt(7)+9;
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             while (i!=8)
             {
                 xmaas=r.nextInt(31);
                 if(numbers[xmaas]!=-1)
                 {
                     if(xmaas<9||xmaas>15) {
                         tTube1[i+3] = Parameters.testTubes[xmaas];
                         numbers[xmaas]=-1;

                         i++;
                     }

                 }
             }
         }

         if(nLevel==4)
         {

             int i = 0;
             int xmaas;
             while (i!=3)
             {
                 xmaas=r.nextInt(7);
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             while (i!=8)
             {
                 xmaas=r.nextInt(24)+7;
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
         }

         if(nLevel==5)
         {
             int i = 0;
             int xmaas;
             while (i!=2)
             {
                 xmaas=r.nextInt(5)*2;
                 if(numbers[xmaas]!=-1)
                 {
                     tTube1[0]=Parameters.testTubes[xmaas+16];
                     tTube1[1]=Parameters.testTubes[xmaas+17];
                     numbers[xmaas]=-1;
                 }
             }
             while (i!=8)
             {
                 xmaas=r.nextInt(31);
                 if(numbers[xmaas]!=-1)
                 {
                     if(xmaas<16||xmaas>25) {
                         tTube1[i + 2] = Parameters.testTubes[xmaas];
                         numbers[xmaas] = -1;
                         i++;
                     }
                 }
             }
         }
tTube = tTube1;
 }
}