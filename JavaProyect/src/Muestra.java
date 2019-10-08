<<<<<<< Updated upstream
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Muestra {
    int x;
    int y;
    int r;
    int g;
    int b;

    public Muestra(){

    }

    public static Muestra getMuestra(int pXaxis, int pYaxis, BufferedImage image){
        Muestra muestraTomada = new Muestra();
        int clr=  image.getRGB(pXaxis,pYaxis);
        muestraTomada.r = (clr & 0x00ff0000) >> 16;
        muestraTomada.g = (clr & 0x0000ff00) >> 8;
        muestraTomada.b  =  clr & 0x000000ff;
        return muestraTomada;
    }
}
=======
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Muestra {
    int x;
    int y;
    int r;
    int g;
    int b;

    public Muestra(){

    }

    public static Muestra getMuestra(int pXaxis, int pYaxis, BufferedImage image){
        Muestra muestraTomada = new Muestra();
        int clr=  image.getRGB(pXaxis,pYaxis);
        muestraTomada.r = (clr & 0x00ff0000) >> 16;
        muestraTomada.g = (clr & 0x0000ff00) >> 8;
        muestraTomada.b  =  clr & 0x000000ff;
        return muestraTomada;
    }
}
>>>>>>> Stashed changes
