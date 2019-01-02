import java.awt.*;
import java.util.Random;

public class TubeTest
{
    public Image [] tTube;
    public Image title;
    public boolean [] answers;

    public TubeTest()
    {
        tTube= new Image [8];
        answers = new boolean[8];
    }

 public void level (int nLevel)
 {

 int numbers [] = new int [31];
 for(int i=0; i<31;i++)
  numbers[i]=i;

     Random r = new Random();
     Image []good = null;
     Image []bad = null ;

         if(nLevel==1)
         {
             int i = 0;
             int xmaas;
             good= new Image[4];
             bad = new Image[4];
             while (i!=4)
             {
                 xmaas=r.nextInt(16);
                 if(numbers[xmaas]!=-1)
                 {
                     good[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=4)
             {
                 xmaas=r.nextInt(15)+16;
                 if(numbers[xmaas]!=-1)
                 {
                     bad[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             title = Parameters.title1;

         }

         if(nLevel==2)
         {
             int i = 0;
             int xmaas;
             good= new Image[4];
             bad = new Image[4];
             while (i!=4)
             {
                 xmaas=r.nextInt(9);
                 if(numbers[xmaas]!=-1)
                 {
                     good[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=4)
             {
                 xmaas=r.nextInt(22)+9;
                 if(numbers[xmaas]!=-1)
                 {
                     bad[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             title = Parameters.title2;
         }

         if(nLevel==3)
         {
             int i = 0;
             int xmaas;
           good= new Image[3];
            bad = new Image[5];
             while (i!=3)
             {
                 xmaas=r.nextInt(7)+9;
                 if(numbers[xmaas]!=-1)
                 {
                     good[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=5)
             {
                 xmaas=r.nextInt(31);
                 if(numbers[xmaas]!=-1)
                 {
                     if(xmaas<9||xmaas>15) {
                         bad[i] = Parameters.testTubes[xmaas];
                         numbers[xmaas]=-1;
                         i++;
                     }

                 }
             }
             title = Parameters.title3;
         }
         if(nLevel==4)
         {
             int i = 0;
             int xmaas;
             good= new Image[3];
             bad = new Image[5];
             while (i!=3)
             {
                 xmaas=r.nextInt(7);
                 if(numbers[xmaas]!=-1)
                 {
                     good[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=5)
             {
                 xmaas=r.nextInt(24)+7;
                 if(numbers[xmaas]!=-1)
                 {
                     bad[i]=Parameters.testTubes[xmaas];
                     numbers[xmaas]=-1;
                     i++;
                 }
             }
             title = Parameters.title4;
         }
         if(nLevel==5)
         {
             int i = 0;
             int xmaas;
             good= new Image[2];
             bad = new Image[6];
             while (i!=2)
             {
                 xmaas=r.nextInt(5)*2;
                 if(numbers[xmaas]!=-1)
                 {
                     good[0]=Parameters.testTubes[xmaas+16];
                     good[1]=Parameters.testTubes[xmaas+17];
                     numbers[xmaas]=-1;
                     i+=2;
                 }

             }
             i=0;
             while (i!=6)
             {
                 xmaas=r.nextInt(31);
                 if(numbers[xmaas]!=-1)
                 {
                     if(xmaas<16||xmaas>25) {
                         bad[i] = Parameters.testTubes[xmaas];
                         numbers[xmaas]=-1;
                         i++;
                     }
                 }
             }
             title = Parameters.title5;
         }
         this.hash (good,bad);
 }

 public  void hash(Image[] good,Image[] bad)
 {
     Image [] tTube1 = new Image[8];
     int [] gAnswer = new int [good.length];
     Random r = new Random();
    int counter = 0;
    int countgood=0;
    int countbad=0;

while ( counter<8)
{
 int value = r.nextInt(2);

 if(value == 0  && countgood < good.length)
 {
  tTube1 [counter]= good[countgood];
 //gAnswer[countgood]=counter;
     answers[counter]=true;
  counter ++;
  countgood ++;

 }
 if(value == 1  && countbad < bad.length)
 {
     tTube1 [counter]= bad[countbad];
     answers[counter]=false;
     counter ++;
     countbad ++;
 }

    }

    tTube=tTube1;
    }
public  int getindex(int x, int y)
    {
     int index=0;
    if(y>508)
    {
        index+=4;

    }
    if(x>105)
    {
    index+=2;

        if( x>143)
        {
            index+=1;

        }
    }
       else if( x>68)
        {
            index+=1;

        }

return index;
    }

    }