package com.zmijewska.game;
/**
 * Klasa pomocnicza z zapisem aktualnego stanu gry
 * @author Aleksandra Żmijewska
 */
   public class GameStatus{
       /** Liczba zgromadzonych punktow*/
        public int points;
       /** Numer poziomu */
        public int level;
       /** Czas gry na danym poziomie*/
       public double time;
       /**
        * Zeruj parametry gry
        */
        public void reset()
        {
            points=0;
            level=1;
            time=0.0;
        }
       /**
        * Zwiększ wskaznik poziomu
        */
        public void nextLevel(){
            level++;
        }
    }


