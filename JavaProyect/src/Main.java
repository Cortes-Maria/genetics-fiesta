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
                difActual = difActual + (0.10/(double)(pZonas.size()-1));
                // actual.termina =  actual. empieza + difActual;
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
            }

        }else if (aDisminuir == pZonas.size()){
            //se aumenta 0.10 al inicio
            pZonas.get(pZonas.size()).getMiRango().setEmpieza(
                    pZonas.get(pZonas.size()).getMiRango().getEmpieza() + 0.10);
            for(int act = 0; act<pZonas.size()-1; act++){
                double difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                difActual = difActual + (0.10/(double)(pZonas.size()-1));
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            pZonas.get(pZonas.size()-1).getMiRango().setTermina( pZonas.get(pZonas.size()).getMiRango().getEmpieza() );

        }else{
            double difActual = pZonas.get(0).getMiRango().getTermina() - pZonas.get(0).getMiRango().getEmpieza();
            for(int act = 1; act<aDisminuir; act++){
                difActual = difActual + (0.10/(double)(pZonas.size()-1));
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                difActual = pZonas.get(act+1).getMiRango().getTermina() - pZonas.get(act+1).getMiRango().getEmpieza();
                pZonas.get(act+1).getMiRango().setEmpieza(pZonas.get(act).getMiRango().getTermina());
            }
            pZonas.get(aDisminuir).getMiRango().setTermina( pZonas.get(aDisminuir).getMiRango().getEmpieza() + (difActual - 0.10) );
            for(int act = aDisminuir+1; act<pZonas.size(); act++) {
                difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                pZonas.get(act).getMiRango().setEmpieza(pZonas.get(act - 1).getMiRango().getTermina());
                difActual = difActual + (0.10 / (double) (pZonas.size() - 1));
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
                difActual = difActual - (0.10 / (double)(pZonas.size() - 1));
                // actual.termina =  actual. empieza + difActual;
                pZonas.get(act).getMiRango().setTermina(pZonas.get(act).getMiRango().getEmpieza() + difActual);
            }
        }else if (aAumentar == pZonas.size()){
            double difActual = pZonas.get(0).getMiRango().getTermina() - pZonas.get(0).getMiRango().getEmpieza();
            for(int act = 0; act<pZonas.size()-1; act++){
                difActual = difActual - (0.10/(double)(pZonas.size()-1));
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                difActual = pZonas.get(act+1).getMiRango().getTermina() - pZonas.get(act+1).getMiRango().getEmpieza();
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            //pZonas.get(pZonas.size()-1).getMiRango().setTermina( pZonas.get(pZonas.size()).getMiRango().getEmpieza() );
        }else{
            // se disminuye 0.05 al inicio del rango y se aumenta 0.05 al final
            double difActual = pZonas.get(0).getMiRango().getTermina() - pZonas.get(0).getMiRango().getEmpieza();
            for(int act = 0; act<aAumentar; act++){
                difActual = difActual - (0.10/(double)(pZonas.size()-1));
                pZonas.get(act).getMiRango().setTermina( pZonas.get(act).getMiRango().getEmpieza() + difActual );
                difActual = pZonas.get(act+1).getMiRango().getTermina() - pZonas.get(act+1).getMiRango().getEmpieza();
                pZonas.get(act+1).getMiRango().setEmpieza( pZonas.get(act).getMiRango().getTermina() );
            }
            pZonas.get(aAumentar).getMiRango().setTermina( pZonas.get(aAumentar).getMiRango().getEmpieza() + difActual + 0.10 );

            for(int act = aAumentar+1; act<pZonas.size(); act++) {
                difActual = pZonas.get(act).getMiRango().getTermina() - pZonas.get(act).getMiRango().getEmpieza();
                pZonas.get(act).getMiRango().setEmpieza(pZonas.get(act - 1).getMiRango().getTermina());
                difActual = difActual - (0.10 / (double)(pZonas.size() - 1));
                pZonas.get(act).getMiRango().setTermina(pZonas.get(act).getMiRango().getEmpieza() + difActual);
            }
        }
    }

    public static void generarProbInicial(ArrayList<Zone> pZonas){ // Esta esta bien
        double incremento = 100.00/(double)pZonas.size();
        double actual = (double)0;
        for (int campo = 0; campo < pZonas.size(); campo++){
            pZonas.get(campo).getMiRango().setEmpieza(actual);
            //System.out.println(actual);
            actual += incremento;
            pZonas.get(campo).getMiRango().setTermina(actual);
        }
    }

    public static int buscarCuadrante(double pNumero, ArrayList<Zone> pZonas){
        int resultado = 0;
        for(int zoneAct = 0; zoneAct<pZonas.size();zoneAct++){
            if(pZonas.get(zoneAct).getMiRango().getEmpieza() < pNumero &&
                    pNumero < pZonas.get(zoneAct).getMiRango().getTermina()){
                return zoneAct;
            }
        }
        return resultado;
    }

    public static void muestreo(ArrayList<Zone> pZonas, int pCantMuestras, BufferedImage pImage){

        Random randomUtil = new Random();

        for (int muestraAct = 0; muestraAct < pCantMuestras; muestraAct++){
            //sacar random para el cuadrante
            int cuadranteActualMuestra = buscarCuadrante(randomUtil.nextInt(101), pZonas);

            System.out.println("CuadranteActual: "+ cuadranteActualMuestra);

            int comienzaX = pZonas.get(cuadranteActualMuestra).getX1();
            int comienzaY = pZonas.get(cuadranteActualMuestra).getY1();


            //sacar muestra random en el cuadrante indicado
            Muestra muestraTomada = Muestra.getMuestra(comienzaX + randomUtil.nextInt(1024/pZonas.size())
                    ,comienzaY + randomUtil.nextInt(1024/pZonas.size()),pImage);

            pZonas.get(cuadranteActualMuestra).getMuestras().add(muestraTomada);
            //variar probabilidad <-- depende de la muestra anterior
            if (muestraTomada.r == 255 && muestraTomada.g == 255 && muestraTomada.b == 255){
                System.out.println("Blanco");
                disminuirRActual(pZonas,cuadranteActualMuestra);
            } else {
                System.out.println("Color");
                aumentarRActual(pZonas,cuadranteActualMuestra);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        ImageProcessor prueba = new ImageProcessor();
        prueba.getImageData();

        //Constantes en el proceso
        ArrayList<Zone> listaZ = new ArrayList<Zone>();
        int total = 16; //cantidad de cuadrantes

        for(int actualY = 0; actualY<4; actualY++){
            for(int actualX = 0; actualX<4; actualX++){
                Zone nueva = new Zone(actualX*256,actualY*256);
                listaZ.add(nueva);
            }
        }

        generarProbInicial(listaZ);
        for(int actual = 0; actual < listaZ.size(); actual++) {
            System.out.println(listaZ.get(actual).getMiRango().getEmpieza()+ "termina"+ listaZ.get(actual).getMiRango().getTermina());
        }


        muestreo(listaZ,100, prueba.getTreeImg());
        System.out.println("cONTROL "+ listaZ.get(0).getMuestras().size());
        for(int actual = 0; actual < listaZ.size(); actual++) {
            System.out.println("Actual " + actual + "tiene "+ listaZ.get(actual).getMuestras().size() + "muestras");
            System.out.println(listaZ.get(actual).getMiRango().getEmpieza()+ "termina"+ listaZ.get(actual).getMiRango().getTermina());
        }



    }
}
