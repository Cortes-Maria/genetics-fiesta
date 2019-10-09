import java.awt.image.BufferedImage;

public class Sample {
    int x;
    int y;
    int r;
    int g;
    int b;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Sample(){

    }

    public Sample(int pX, int pY, int pR, int pG, int pB){
        setB(pB);
        setG(pG);
        setR(pR);
        setX(pX);
        setY(pY);
    }

    public static Sample getMuestra(int pXaxis, int pYaxis, BufferedImage image){
        Sample sampleTomada = new Sample();
        int clr=  image.getRGB(pXaxis,pYaxis);
        sampleTomada.r = (clr & 0x00ff0000) >> 16;
        sampleTomada.g = (clr & 0x0000ff00) >> 8;
        sampleTomada.b  =  clr & 0x000000ff;
        return sampleTomada;
    }
}

