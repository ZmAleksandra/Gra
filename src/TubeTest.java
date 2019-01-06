import java.awt.*;
import java.util.Random;

public class TubeTest
{
    public Image [] tTube;
    public Image title;
    public boolean [] answers;

    public TubeTest()//konstruktor klasy
    {
        tTube= new Image [8];
        answers = new boolean[8];
    }

    // metoda odpowiedzialna za losowanie właściwych probówek w zależnosci od poziomu gry
 public void level (int nLevel)
 {
// tworzenie tablicy typu int
 int numbers [] = new int [31];
 for(int i=0; i<31;i++)
  numbers[i]=i;

     Random r = new Random();
     Image []good = null;
     Image []bad = null ;
//w zależności od poziomu losuj właściwe probówki
         if(nLevel==1)
         {
             int i = 0;
             int indeks;
             good= new Image[4];//4 probówki dobre
             bad = new Image[4];//4 probowki złe
             while (i!=4)//losowanie 4 dorych probówek
             {
                 indeks=r.nextInt(16);
                 if(numbers[indeks]!=-1)//zapobieganie ponownemu wylosowaniu tego samego obrazka
                 {
                     good[i]=Parameters.testTubes[indeks];//przypisanie wylosowanego indeksu do tablicy obrazków
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=4)
             {
                 indeks=r.nextInt(15)+16;
                 if(numbers[indeks]!=-1)
                 {
                     bad[i]=Parameters.testTubes[indeks];
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             title = Parameters.title1;// przypisanie kategorii wyświetlanej na wiadrze dla danego lewelu

         }

         if(nLevel==2)
         {
             int i = 0;
             int indeks;
             good= new Image[4];
             bad = new Image[4];
             while (i!=4)
             {
                 indeks=r.nextInt(9);
                 if(numbers[indeks]!=-1)
                 {
                     good[i]=Parameters.testTubes[indeks];
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=4)
             {
                 indeks=r.nextInt(22)+9;
                 if(numbers[indeks]!=-1)
                 {
                     bad[i]=Parameters.testTubes[indeks];
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             title = Parameters.title2;
         }

         if(nLevel==3)
         {
             int i = 0;
             int indeks;
           good= new Image[3];
            bad = new Image[5];
             while (i!=3)
             {
                 indeks=r.nextInt(7)+9;
                 if(numbers[indeks]!=-1)
                 {
                     good[i]=Parameters.testTubes[indeks];
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=5)
             {
                 indeks=r.nextInt(31);
                 if(numbers[indeks]!=-1)
                 {
                     if(indeks<9||indeks>15) {
                         bad[i] = Parameters.testTubes[indeks];
                         numbers[indeks]=-1;
                         i++;
                     }

                 }
             }
             title = Parameters.title3;
         }
         if(nLevel==4)
         {
             int i = 0;
             int indeks;
             good= new Image[3];
             bad = new Image[5];
             while (i!=3)
             {
                 indeks=r.nextInt(7);
                 if(numbers[indeks]!=-1)
                 {
                     good[i]=Parameters.testTubes[indeks];
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             i=0;
             while (i!=5)
             {
                 indeks=r.nextInt(24)+7;
                 if(numbers[indeks]!=-1)
                 {
                     bad[i]=Parameters.testTubes[indeks];
                     numbers[indeks]=-1;
                     i++;
                 }
             }
             title = Parameters.title4;
         }
         if(nLevel==5)
         {
             int i = 0;
             int indeks;
             good= new Image[2];
             bad = new Image[6];
             while (i!=2)
             {
                 indeks=r.nextInt(5)*2;
                 if(numbers[indeks]!=-1)
                     {
                     good[0]=Parameters.testTubes[indeks+16];
                     good[1]=Parameters.testTubes[indeks+17];
                     numbers[indeks]=-1;
                     i+=2;
                 }

             }
             i=0;
             while (i!=6)
             {
                 indeks=r.nextInt(31);
                 if(numbers[indeks]!=-1)
                 {
                     if(indeks<16||indeks>25) {
                         bad[i] = Parameters.testTubes[indeks];
                         numbers[indeks]=-1;
                         i++;
                     }
                 }
             }
             title = Parameters.title5;
         }
         this.hash (good,bad);
 }

//metoda losująca położenie probówek we wiadrze na starcie
 public  void hash(Image[] good,Image[] bad)
 {
     Image [] tTube1 = new Image[8];
     Random r = new Random();
    int counter = 0;
    int countgood=0;
    int countbad=0;

while ( counter<8)
{
 int value = r.nextInt(2);//losowanie wartości 0 lub 1
//jesli wylosowano 0 i ilośc dobrych probówek jest mniejsza od maksymalnego rozmiaru tablicy
 if(value == 0  && countgood < good.length)
 {
     tTube1 [counter]= good[countgood];
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
    // metoda przypisująca indeks w zależności od położenia probówki
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