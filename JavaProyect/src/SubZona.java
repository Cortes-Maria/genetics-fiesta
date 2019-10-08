import java.util.*;

public class SubZona {
    int xSuperior;
    int xInferior;
    int yDerecha;
    int yIzquierda;
    int rSub;
    int gSub;
    int bSub;

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

    public SubZona(int pxSuperior, int pxInferior, int pyDerecha, int pyIzquierda, int prSub, int pgSub, int pbSub) {
        this.xSuperior = pxSuperior;
        this.xInferior = pxInferior;
        this.yDerecha = pyDerecha;
        this.yIzquierda = pyIzquierda;
        this.rSub = prSub;
        this.gSub = pgSub;
        this.bSub = pbSub;
    }

}
