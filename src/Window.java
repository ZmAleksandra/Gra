 import java.awt.GridLayout;
 import java.awt.Toolkit;
 import javax.swing.JFrame;

 public class Window extends JFrame{

     public Window(int width, int height, int x, int y){
         super();
         setSize(width, height);
         setLocation(x,y);
         setResizable(false);
         initGUI(width, height, this);
         setVisible(true);
         animationLoop();
         createBufferStrategy(2);
     }

     private void initGUI(int width, int height, JFrame jFrame){
         setLayout(new GridLayout(1,1));
         Parameters.loadInitialImages();
         Toolkit tk = Toolkit.getDefaultToolkit();
         add(new Panel(width, height, jFrame));
     }
     private void animationLoop() {
         Parameters.startTime = System.currentTimeMillis();
         long currTime = Parameters.startTime;
         while (currTime - Parameters.startTime < Parameters.GAME_TIME) {
             long elapsedTime = System.currentTimeMillis() - currTime;
             currTime += elapsedTime;
             repaint();
             try {
                 Thread.sleep(80);
             } catch (InterruptedException ex) {System.out.println("Wyjątek: "+ex);      }
         }
     }
 }
