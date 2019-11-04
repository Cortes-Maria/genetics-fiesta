import java.util.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        Image image1 = new Image("C:/Users/Esteban Jiménez/IdeaProjects/genetics-fiesta/JavaProyect/src/resources/dog.jpg",4);
        Image image2 = new Image("C:/Users/Esteban Jiménez/IdeaProjects/genetics-fiesta/JavaProyect/src/resources/tree.jpg",4);
        Image image3 = new Image("C:/Users/Esteban Jiménez/IdeaProjects/genetics-fiesta/JavaProyect/src/resources/Muppet.jpg",4);

        image1.sampling(16);
        image2.sampling(16);
        image3.sampling(16);
        image1.makeTarget(50);
        //image2.makeTarget();
        //image3.makeTarget();


/*
        System.out.println("--------------------------- Primera Imagen --------------------------- ");
        for(int actual = 0; actual < image1.getZones().size(); actual++) {
            System.out.println("Actual " + actual + "tiene "+ image1.getZones().get(actual).getSamples().size() + "muestras");
            System.out.println(image1.getZones().get(actual).getProbability());
        }
        System.out.println("--------------------------- Segunda Imagen --------------------------- ");
        for(int actual = 0; actual < image2.getZones().size(); actual++) {
            System.out.println("Actual " + actual + "tiene "+ image2.getZones().get(actual).getSamples().size() + "muestras");
            System.out.println(image2.getZones().get(actual).getProbability());
        }
        System.out.println("--------------------------- Tercera Imagen --------------------------- ");
        for(int actual = 0; actual < image3.getZones().size(); actual++) {
            System.out.println("Actual " + actual + "tiene "+ image3.getZones().get(actual).getSamples().size() + "muestras");
            System.out.println(image3.getZones().get(actual).getProbability());
        }*/
    }
}