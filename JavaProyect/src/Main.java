import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {


    public static ArrayList<Rango> generarProbInicial(int divX, int divY){
        ArrayList <Rango> probabilidad = new ArrayList <Rango>();
        double incremento = 100.00/(double)(divX * divY);
        double actual = 0;

        for (int campo = 0; campo < (divX*divY); campo++){
           probabilidad.add(new Rango(actual, actual+incremento));
           actual += incremento;
        }
        return probabilidad;
    }

    public static ArrayList<ArrayList<Muestra>> muestreo(ArrayList<Rango> pRangosProbabilisticos, int pCantMuestras,
                                                         BufferedImage pImage){
        ArrayList<ArrayList<Muestra>> contenedores = new ArrayList<ArrayList<Muestra>>();
        Random cuadranteR = new Random();


        for (int muestraAct = 0; muestraAct < pCantMuestras; muestraAct++){
            //sacar random para el cuadrante
            cuadranteR.nextInt(101);

            //sacar muestra random en el cuadrante --Falta

            Muestra muestraTomada = Muestra.getMuestra(1,2,pImage);

            //variar probabilidad
            if (muestraTomada.r == 255 && muestraTomada.g == 255 && muestraTomada.b == 255){
                // Identificar en cual cuadrante estamos // posible solucion con un id en el cuadrante
                //disminuir el rango del cuadrante actual 0.10
                //Aumentar los rangos de los otros cuadrantes
                //double hola = 0.10/(double)pRangosProbabilisticos.size()-1.00;
            } else {
                //aumentar el rango del cuadrante actual 0.10
                //decrementar los rangos de los otros cuadrantes
                //double hola = 0.10/(double)pRangosProbabilisticos.size()-1.00;
            }
        }



        return contenedores; // Lista de contenedores con muestras
    }

    public static void main(String[] args) throws IOException {
	// write your code here
        /*
        System.out.println("holi");
        BufferedImage image = ImageIO.read(new File("dog.jpg"));
        Muestra miMuestra = new Muestra();
        miMuestra.getMuestra(500,500,image);
        System.out.println("r "+ miMuestra.r +"  g: "+ miMuestra.g + "  b: " + miMuestra.b);*/


        // Pasos a implementar
        // Sacar muestras probabilistas 1/3
        // Con las muestras sacar subzonas
        ArrayList<Rango> probabilidades = generarProbInicial(4,4);
        //Sacar muestras ArrayList<ArrayList <Muestras>> = sacarMuestras(probabilidades);

    }
}
