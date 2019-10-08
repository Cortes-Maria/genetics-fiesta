import java.lang.reflect.GenericArrayType;
import java.util.*;
import java.io.*;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Main {


    //Poner todos estos metodos en una clase imagen // Creo que con la clase que creó ud se puede

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
            //aqui reacomodo todos los anteriores
            for(int act = 0; act<pZonas.size()-1; act++){
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                difActual = difActual + (0.10/(double)pZonas.size()-1);
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            pZonas.get(pZonas.size()-1).getMiRango().setTermina( pZonas.get(pZonas.size()).getMiRango().getEmpieza() );

        }else{
            for(int act = 0; act<aDisminuir; act++){
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                difActual = difActual + (0.10/(double)pZonas.size()-1);
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            pZonas.get(aDisminuir).getMiRango().setTermina( pZonas.get(aDisminuir).getMiRango().getEmpieza() - 0.10 );
            for(int act = aDisminuir+1; act<pZonas.size(); act++) {
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                pZonas.get(act).getMiRango().setEmpieza(pZonas.get(act - 1).getMiRango().getTermina());
                difActual = difActual + (0.10 / (double) pZonas.size() - 1);
                pZonas.get(act).getMiRango().setTermina(pZonas.get(act).getMiRango().getEmpieza() + difActual);
            }

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
            // se redimensionan los cuadrantes restandole (0.10/(double)pZonas.size()-1)

            pZonas.get(pZonas.size()).getMiRango().setEmpieza(
                    pZonas.get(pZonas.size()).getMiRango().getEmpieza()- 0.10);
            //aqui reacomodo todos los anteriores
            for(int act = 0; act<pZonas.size()-1; act++){
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                difActual = difActual - (0.10/(double)pZonas.size()-1);
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            pZonas.get(pZonas.size()-1).getMiRango().setTermina( pZonas.get(pZonas.size()).getMiRango().getEmpieza() );
        }else{
            // se disminuye 0.05 al inicio del rango y se aumenta 0.05 al final
            for(int act = 0; act<aAumentar; act++){
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                difActual = difActual - (0.10/(double)pZonas.size()-1);
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            pZonas.get(aAumentar).getMiRango().setTermina( pZonas.get(aAumentar).getMiRango().getEmpieza() + 0.10 );

            for(int act = aAumentar+1; act<pZonas.size(); act++) {
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                pZonas.get(act).getMiRango().setEmpieza(pZonas.get(act - 1).getMiRango().getTermina());
                difActual = difActual - (0.10 / (double) pZonas.size() - 1);
                pZonas.get(act).getMiRango().setTermina(pZonas.get(act).getMiRango().getEmpieza() + difActual);
            }
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

            //variar probabilidad <-- depende de la muestra anterior
            if (muestraTomada.r == 255 && muestraTomada.g == 255 && muestraTomada.b == 255){
                disminuirRActual(pZonas,cuadranteActualMuestra);
            } else {
                aumentarRActual(pZonas,cuadranteActualMuestra);

            }
        }
    }

    public static void main(String[] args) throws IOException {

        //Pasos
        // 1. generar el array de zonas ArrayList<Zone> cuadrantes = new ArrayList<Zone>();

        //2. BufferedImage image = ImageIO.read(new File("dog.jpg"));

        //3. muestreo(cuadrantes, cantDeMuestras, image);


    }
}
