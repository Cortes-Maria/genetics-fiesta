import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class Image {
    private BufferedImage bufImage = null;
    public int imageData[] = {};
    public ArrayList<Zone> zones;


    public void makingSomething(int pZoneCantXY){
        for(int actualY = 0; actualY < pZoneCantXY; actualY++){
            for(int actualX = 0; actualX < pZoneCantXY;  actualX++){
                Zone nuevaZ = new Zone(actualX*256,actualY*256);
                this.zones.add(nuevaZ);
            }
        }
    }

    private void loadImage (String pPath){
        try
        {
            bufImage = ImageIO.read(new File(pPath));
            System.out.println("Image correctly charged! ");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void getImageData(){
        BufferedImage subImage = bufImage.getSubimage(0,0,256,256);
        int actualRGB = subImage.getRGB(200,150);
        System.out.print(actualRGB);
    }

    /**
     * @param pPercent percent of the zone to be analyzed: must be multiple of 2
     */
    public void makeSamples(int pPercent){
        int times = pPercent / 2;
        int cantSamples = ((1024*1024)*pPercent)/100;
        for(int perCent = 0; perCent<times; perCent++){ // for each percent
            for(int zoneAct = 0; zoneAct<this.zones.size(); zoneAct++){ // for each zone in the array
                boolean color = false;
                if (Math.random() < this.zones.get(zoneAct).getProbability()){
                    for(int count = 0; count < cantSamples; count++){

                        Sample newSample = Sample.getMuestra(this.zones.get(zoneAct).getX1() + (int)(Math.random()*255)
                                ,this.zones.get(zoneAct).getY1() + (int)(Math.random()*255),this.bufImage);

                        this.zones.get(zoneAct).getSamples().add(newSample);

                        if (newSample.r != 255 || newSample.g != 255 || newSample.b != 255)
                            color = true;
                    }
                }
                if(color == false){
                    this.zones.get(zoneAct).setProbability(this.zones.get(zoneAct).getProbability()-0.07);
                }
            }
        }

    }

    public BufferedImage getBufImage() {
        return bufImage;
    }

    public void setBufImage(BufferedImage pNewImg) {
        this.bufImage =pNewImg;
    }

    public void setImageData(int[] imageData) {
        this.imageData = imageData;
    }

    public ArrayList<Zone> getZones() {
        return zones;
    }

    public void setZones(ArrayList<Zone> zones) {
        this.zones = zones;
    }



    public Image(String pPath, int pZoneCant){
        loadImage(pPath);
        this.zones = new ArrayList<Zone>();
        makingSomething(pZoneCant);
    }

}