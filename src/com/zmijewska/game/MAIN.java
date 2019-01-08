package com.zmijewska.game;
import java.awt.*;

/**
 * Prosta gra edukacyjna sprzwadzajaca znajomosc podstawowych zwiazkow chemicznych
 * @author Aleksandra Żmijewska
 */
public class MAIN{

    /**
     * Metoda uruchamia gre. Najpierw pobierane se parametry ekranu
     * i po ustaleniu rozmiaru gry (1280/720) obliczany jest punkt (x,y)
     * gornego, lewego naroznika panelu gry tak, aby pole gry było wysrodkowane
     * na ekranie.
     */
public static void main(String[] args) {
    //ustaw szerokosc i wysokosc ekranu
         int gameWidth=1280;
         int gameHeight=720;
    //pobieranie rozmiaru ekranu
         int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
         int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    //obliczanie wspolrzednych narożnika, tak by pole gry było wysrodkowane
         int xCenter=(screenWidth-gameWidth)/2;
         int yCenter=(screenHeight-gameHeight)/2;
    //tworzenie obiektu klasy Window i wywoływanie jej
         Window gw=new Window(gameWidth,gameHeight,xCenter,yCenter);

     }
 }
