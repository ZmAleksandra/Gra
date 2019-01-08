import java.awt.*;

/**
 * Prosta gra (bez optymalizacji) jako przykład aplikacji interaktywnej
 * @author Aleksandra Żmijewska
 */
public class MAIN{

    /**
     * Metoda uruchamia grę. Najpierw pobierane są parametry ekranu
     * i po ustaleniu rozmiaru gry (1280/720) obliczany jest punkt (x,y)
     * górnego, lewego narożnika panelu gry tak, aby pole gry było wyśrodkowane
     * na ekranie.
     */
public static void main(String[] args) {
    //ustaw szerokośc i wysokośc ekranu
         int gameWidth=1280;
         int gameHeight=720;
    //pobieranie rozmiaru ekranu
         int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
         int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    //obliczanie współrzędnych narożnika, tak by pole gry było wysrodkowane
         int xCenter=(screenWidth-gameWidth)/2;
         int yCenter=(screenHeight-gameHeight)/2;
    //tworzenie obiektu klasy Window i wywoływanie jej
         Window gw=new Window(gameWidth,gameHeight,xCenter,yCenter);

     }
 }
