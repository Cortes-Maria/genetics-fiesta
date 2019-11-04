import java.util.*;

public class SubZone {
        private int xSuperior;
        private int xInferior;
        private int yDerecha;
        private int yIzquierda;
        private int rSub;
        private int gSub;
        private int bSub;
        private int counter;
        private double percent;

    public int getxSuperior() {
        return xSuperior;
    }

    public void setxSuperior(int pxSuperior) {
        this.xSuperior = pxSuperior;
    }

    public int getxInferior() {
        return xInferior;
    }

    public void setxInferior(int pxInferior) {
        this.xInferior = pxInferior;
    }

    public int getyDerecha() {
        return yDerecha;
    }

    public void setyDerecha(int pyDerecha) {
        this.yDerecha = pyDerecha;
    }

    public int getyIzquierda() {
        return yIzquierda;
    }

    public void setyIzquierda(int pyIzquierda) {
        this.yIzquierda = pyIzquierda;
    }

    public int getrSub() {
        return rSub;
    }

    public void setrSub(int prSub) {
        this.rSub = prSub;
    }

    public int getgSub() {
        return gSub;
    }

    public void setgSub(int pgSub) {
        this.gSub = pgSub;
    }

    public int getbSub() {
        return bSub;
    }

    public void setbSub(int pbSub) {
        this.bSub = pbSub;
    }

    public int getcounter() {
        return counter;
    }

    public void setcounter(int pCounter) {
        this.counter = pCounter;
    }

    public void counterPlusOne(){ this.counter+=1; }

    public double getPercent(){ return percent; }

    public void setPercent(double pPercent){ this.percent = pPercent; }


    public SubZone(int pxSuperior, int pxInferior, int pyDerecha, int pyIzquierda, int prSub, int pgSub, int pbSub) {
        this.xSuperior = pxSuperior;
        this.xInferior = pxInferior;
        this.yDerecha = pyDerecha;
        this.yIzquierda = pyIzquierda;
        this.rSub = prSub;
        this.gSub = pgSub;
        this.bSub = pbSub;
        this.counter = 1;
        this.percent = 0;
    }


}

