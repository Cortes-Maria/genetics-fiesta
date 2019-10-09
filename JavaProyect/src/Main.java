import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {
//Poner todos estos metodos en una clase imagen // Creo que con la clase que creó ud se puede

    /**
     *
     * @param pZones
     * @param pPercent percent of the zone to be analyzed: must be multiple of 2
     * @param pImage
     */
    public static void makeSamples(ArrayList<Zone> pZones, int pPercent, BufferedImage pImage){
        int times = pPercent / 2;
        int cantSamples = ((1024*1024)*pPercent)/100;
        for(int perCent = 0; perCent<times; perCent++){ // for each percent
            for(int zoneAct = 0; zoneAct<pZones.size(); zoneAct++){ // for each zone in the array
                boolean color = false;
                if (Math.random() < pZones.get(zoneAct).getProbability()){
                    for(int count = 0; count < cantSamples; count++){

                        Sample newSample = Sample.getMuestra(pZones.get(zoneAct).getX1() + (int)(Math.random()*255)
                                ,pZones.get(zoneAct).getY1() + (int)(Math.random()*255),pImage);

                        pZones.get(zoneAct).getSamples().add(newSample);

                        if (newSample.r != 255 || newSample.g != 255 || newSample.b != 255)
                            color = true;
                    }
                }
                if(color == false){
                    pZones.get(zoneAct).setProbability(pZones.get(zoneAct).getProbability()-0.07);
                }
            }
        }

    }


    public static void main(String[] args) {
        ImageProcessor prueba = new ImageProcessor();
        prueba.getImageData();

        //Constantes en el proceso
        ArrayList<Zone> listaZ = new ArrayList<Zone>();
        int total = 4; //quantity of zones in x , y

        for(int actualY = 0; actualY < total; actualY++){
            for(int actualX = 0; actualX < total;  actualX++){
                Zone nueva = new Zone(actualX*256,actualY*256);
                listaZ.add(nueva);
            }
        }

        makeSamples(listaZ, 20, prueba.getTreeImg());
        System.out.println("CONTROL "+ listaZ.get(0).getSamples().size());
        for(int actual = 0; actual < listaZ.size(); actual++) {
            System.out.println("Actual " + actual + "tiene "+ listaZ.get(actual).getSamples().size() + "muestras");
            System.out.println(listaZ.get(actual).getProbability());
        }
    }
}