import java.awt.*;

 public class MAIN{
public static void main(String[] args) {
         int gameWidth=1280;
         int gameHeight=720;
         int screenWidth=Toolkit.getDefaultToolkit().getScreenSize().width;
         int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
         int xCenter=(screenWidth-gameWidth)/2;
         int yCenter=(screenHeight-gameHeight)/2;
         Window gw=new Window(gameWidth,gameHeight,xCenter,yCenter);
     }
 }
