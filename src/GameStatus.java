
   public class GameStatus{
        public int points;
        public int level;
        public double time;
        public void reset()
        {
            points=0;
            level=1;
            time=0.0;
        }

        public void resetPoints(){
            points=0;
        }
        public void nextLevel(){
            level++;
        }
    }


