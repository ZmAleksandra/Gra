import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

/**
 * zarządzanie akcją kliknięcia klawiatury
 * @author Aleksandra Żmijewska
 */
  public class Chemist {

    private boolean klawisze[];
    int position[];
    private Timer  zegar;

    /**
     *  zmiana położenia bohatera w zależnosci od wciśniętego klawisza
     */
  class Run extends TimerTask{
      public void run()
      {
          if(klawisze[0])
              position[1]-=1;
          if(klawisze[1])
              position[1]+=1;

          if(klawisze[2])
              position[0]-=1;
          if(klawisze[3])
              position[0]+=1;

          //zakres obszaru poruszania się bohatera gry
          position[0] = (position[0]<190)?190:position[0];
          position[0] = (position[0]>1000)?990:position[0];
          position[1] = (position[1]<200)?200:position[1];
          position[1] = (position[1]>220)?220:position[1];
      }
  }

    /**
     * konstruktor klasy
     * @param jFrame
     */
  Chemist(JFrame jFrame){

      klawisze  = new boolean[4];
      position    = new int[2];
      //początkowe położenie bohatera gry
      position[0] = 190;
      position[1] = 200;

          zegar = new Timer();
          //określenie okresu przeuswania się bohatera gry po naciśnięciu strzałki na klawiaturze
          zegar.scheduleAtFixedRate(new Run(),1,8);
          jFrame.addKeyListener(new KeyListener(){
          //postępowanie gdy wciśnięta strzałka
          public void keyPressed(KeyEvent e){
             switch(e.getKeyCode()){
              case KeyEvent.VK_UP:
                  klawisze[0] = true; break;
              case KeyEvent.VK_DOWN:
                  klawisze[1] = true; break;
              case KeyEvent.VK_LEFT:
                  klawisze[2] = true; break;
              case KeyEvent.VK_RIGHT:
                  klawisze[3] = true; break;
            }
          }
         //postępowanie gdy puszczona strzałka
          public void keyReleased(KeyEvent e){
          switch(e.getKeyCode()){
          case KeyEvent.VK_UP:
                 klawisze[0] = false; break;
          case KeyEvent.VK_DOWN:
                 klawisze[1] = false; break;
          case KeyEvent.VK_LEFT:
                 klawisze[2] = false; break;
          case KeyEvent.VK_RIGHT:
                 klawisze[3] = false; break;
            }
          }

          public void keyTyped(KeyEvent e){
                }
          }
          );
  }
  }


