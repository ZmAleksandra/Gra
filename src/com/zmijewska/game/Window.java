package com.zmijewska.game;

 import java.awt.GridLayout;
 import java.awt.Toolkit;
 import java.io.File;
 import javax.swing.JFrame;

 /**
  * Glowne okno gry,zarzadzanie czasem gry
  * @author Aleksandra Żmijewksa
  */
 public class Window extends JFrame {
     /**
      * Glowny konstruktor klasy - ustawienie parametrow i rozpoczecia akcji
      *
      * @param width  szerokość okna
      * @param height wysokość okna
      * @param x      pozycja x lewego górnego narożnika okna
      * @param y      pozycja y lewego górnego narożnika okna
      */
     public Window(int width, int height, int x, int y) {
         super();//tworzenie okna
         setSize(width, height);//ustawienie wymierów okna
         setLocation(x, y);//ustawienie pozycji okna
         setResizable(false);//zablokowanie możliwości zmian rozmiaru okna
         initGUI(width, height, this);//wywołanie metody budowy inetrfejsu graficznego
         setVisible(true);//pokazanie okna
         animationLoop();//uruchomienie pętli gry
         createBufferStrategy(2);
     }

     /**
      * Utwórz interfejs graficzny użytkownika
      *
      * @param width  szerokosc okna
      * @param height wysokosc okna
      */
     private void initGUI(int width, int height, JFrame jFrame) {
         setLayout(new GridLayout(1, 1));
         Parameters.loadInitialImages();
         Toolkit tk = Toolkit.getDefaultToolkit();
         add(new gPanel(jFrame));
         Parameters.playSound(new File("sounds/milioners.wav"));//odtwarzanie dźwięku gry
     }

     /**
      * Glowna petla gry, zarzadzanie czasem gry, wstrzymanie liczenia czasu gdy wybrano menu
      */
     private void animationLoop() {
         for (int i = 1; 1 > 0; i++) {
             if (!Parameters.pause) {

                 Parameters.elapsedTime = System.currentTimeMillis() - Parameters.startTime - Parameters.T_break;
                 repaint();

                 try {
                     Thread.sleep(80);
                 } catch (InterruptedException ex) {
                     System.out.println("Wyjątek: " + ex);

                 }
             }
             if (Parameters.pause) {
                 Parameters.T_pause = System.currentTimeMillis();
                 try {
                     Thread.sleep(80);
                 } catch (InterruptedException ex) {
                     System.out.println("Wyjątek: " + ex);
                 }
                 Parameters.T_break += System.currentTimeMillis() - Parameters.T_pause;
             }
         }
     }
 }
