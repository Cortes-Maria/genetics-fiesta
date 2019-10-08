import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {


    public static void redimensionar(ArrayList<Zone> pZonas){

    }
    public static void disminuirRActual(ArrayList<Zone> pZonas, int aDisminuir){
        if (aDisminuir == 0){
            //Se disminuye 0.10 al final del rango
            pZonas.get(0).getMiRango().setTermina(pZonas.get(0).getMiRango().getTermina() - 0.10);
            //Estabilizar al resto
            for(int act = 1; act<pZonas.size(); act++){
                //Calcular diferencia actual
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                //Tomar el anterior.termina y hacer al actual.empieza igual
                pZonas.get(act).getMiRango().setEmpieza( pZonas.get(act-1).getMiRango().getTermina() );
                //diferencia actual + (0.10/pZonas.size()-1);
                difActual = difActual + (0.10/(double)pZonas.size()-1);
                // actual.termina =  actual. empieza + difActual;
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
            }
        }else if (aDisminuir == pZonas.size()){
            //se aumenta 0.10 al inicio
            pZonas.get(pZonas.size()).getMiRango().setEmpieza(
                    pZonas.get(pZonas.size()).getMiRango().getEmpieza() + 0.10);
        }else{
            //se disminuye 0.05 al final del rango y se aumenta 0.05 al inicio
        }
    }

    public static void aumentarRActual(ArrayList<Zone> pZonas, int aAumentar){
        if (aAumentar == 0){
            //se aumenta 0.10 al final del rango
            pZonas.get(0).getMiRango().setTermina(pZonas.get(0).getMiRango().getTermina() + 0.10);
            //Estabilizar al resto
            for(int act = 1; act<pZonas.size(); act++) {
                //Calcular diferencia actual
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                //Tomar el anterior.termina y hacer al actual.empieza igual
                pZonas.get(act).getMiRango().setEmpieza(pZonas.get(act - 1).getMiRango().getTermina());
                //diferencia actual + (0.10/pZonas.size()-1);
                difActual = difActual - (0.10 / (double) pZonas.size() - 1);
                // actual.termina =  actual. empieza + difActual;
                pZonas.get(act).getMiRango().setTermina(pZonas.get(act).getMiRango().getEmpieza() + difActual);
            }
        }else if (aAumentar == pZonas.size()){
            // se disminuye 0.10 al inicio del rango
            // se redimensionan los cuadrantes restandole
            //redimensionar todos

        }else{
            // se disminuye 0.05 al inicio del rango y se aumenta 0.05 al final
        }
    }


    public static void generarProbInicial(ArrayList<Zone> pZonas){
        double incremento = 100.00/(double)pZonas.size();
        double actual = 0;
        for (int campo = 0; campo < pZonas.size(); campo++){
            pZonas.get(campo).miRango.empieza = actual;
            actual += incremento;
            pZonas.get(campo).miRango.termina = actual;
        }
    }

    public static int buscarCuadrante(double pNumero, ArrayList<Zone> pZonas){
        int resultado = 0;
        for(int zoneAct = 0; zoneAct<pZonas.size();zoneAct++){
            if(pZonas.get(zoneAct).getMiRango().getEmpieza() < pNumero &&
                        pNumero <= pZonas.get(zoneAct).getMiRango().getTermina()){
                return zoneAct;
            }
        }
        return resultado;
    }

    public static void muestreo(ArrayList<Zone> pZonas, int pCantMuestras, BufferedImage pImage){

        Random randomUtil = new Random();

        for (int muestraAct = 0; muestraAct < pCantMuestras; muestraAct++){
            //sacar random para el cuadrante
            int cuadranteActualMuestra = buscarCuadrante(randomUtil.nextDouble()*100, pZonas);
            int comienzaX = pZonas.get(cuadranteActualMuestra).getX1();
            int comienzaY = pZonas.get(cuadranteActualMuestra).getY1();

            //sacar muestra random en el cuadrante indicado
            Muestra muestraTomada = Muestra.getMuestra(comienzaX + randomUtil.nextInt(1024/pZonas.size())
                    ,comienzaY + randomUtil.nextInt(1024/pZonas.size()),pImage);

            //variar probabilidad
            if (muestraTomada.r == 255 && muestraTomada.g == 255 && muestraTomada.b == 255){
                //disminuir el rango del cuadrante actual 0.10
                //Aumentar los rangos de los otros cuadrantes
                //double hola = 0.10/(double)pRangosProbabilisticos.size()-1.00;
                //disminuirRActual(pZonas, cuadranteActualMuestra);
            } else {
                //aumentar el rango del cuadrante actual 0.10
                //decrementar los rangos de los otros cuadrantes
                //double hola = 0.10/(double)pRangosProbabilisticos.size()-1.00;
                //aumentarRActual(pZonas, cuadranteActualMuestra);
            }
        }
    }

    public static void main(String[] args) throws IOException {
	// write your code here

        //Pasos


        /*
        System.out.println("holi");
        BufferedImage image = ImageIO.read(new File("dog.jpg"));
        Muestra miMuestra = new Muestra();
        miMuestra.getMuestra(500,500,image);
        System.out.println("r "+ miMuestra.r +"  g: "+ miMuestra.g + "  b: " + miMuestra.b);*/


        // Pasos a implementar
        // Sacar muestras probabilistas 1/3
        // Con las muestras sacar subzonas
        //ArrayList<Rango> probabilidades = generarProbInicial(4,4);
        //Sacar muestras ArrayList<ArrayList <Muestras>> = sacarMuestras(probabilidades);

    }
}
