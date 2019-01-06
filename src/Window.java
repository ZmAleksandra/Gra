 import java.awt.GridLayout;
 import java.awt.Toolkit;
 import java.io.File;
 import javax.swing.JFrame;

 public class Window extends JFrame{

     public Window(int width, int height, int x, int y){
         super();//tworzenie okna
         setSize(width, height);//ustawienie wymierów okna
         setLocation(x,y);//ustawienie pozycji okna
         setResizable(false);//zablokowanie możliwości zmian rozmiaru okna
         initGUI(width, height, this);//wywołanie metody budowy inetrfejsu graficznego
         setVisible(true);//pokazanie okna
         animationLoop();//uruchomienie pętli gry
         createBufferStrategy(2);
     }

     private void initGUI(int width, int height, JFrame jFrame){
         setLayout(new GridLayout(1,1));
         Parameters.loadInitialImages();
         Toolkit tk = Toolkit.getDefaultToolkit();
         add(new gPanel(width, height, jFrame));
         Parameters.playSound(new File("sounds/milioners.wav"));
     }
     // główna pętla gry
     private void animationLoop() {
         //pobierz liczbe milisekund od daty referencyjnej
         Parameters.startTime = System.currentTimeMillis();
         long currTime = Parameters.startTime;
         while (currTime - Parameters.startTime < Parameters.GAME_TIME) {
             long elapsedTime = System.currentTimeMillis() - currTime;
             currTime += elapsedTime;
             repaint();
             //uśpienie na 80 milisekund
             try {
                 Thread.sleep(80);
             } catch (InterruptedException ex) {System.out.println("Wyjątek: "+ex);      }
         }
     }
 }
