import java.awt.*;

public class MAIN{
public static void main(String[] args) {
    //ustaw szerokośc i wyokośc ekranu
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
