import java.util.*;

public class Zone {
    int x1, y1;
    ArrayList<Muestra> muestrasZona;
    Rango miRango;
    //ArrayList<SubZona> subZonas;


    public Rango getMiRango() {
        return miRango;
    }

    public void setMiRango(Rango miRango) {
        this.miRango = miRango;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public ArrayList<Muestra> getMuestras() {
        return muestrasZona;
    }

    public void setMuestras(ArrayList<Muestra> pMuestras) {
        this.muestrasZona = pMuestras;
    }

    public Zone(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
        this.miRango = new Rango(0,0);
        this.muestrasZona = new ArrayList<Muestra>();
    }
};


