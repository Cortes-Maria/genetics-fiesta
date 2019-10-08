import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
public class ImageProcessor {
    private BufferedImage treeImg = null;
    public int imageData[] = {};
    private BufferedImage img2 = null;
    private BufferedImage img3 = null;


    public ImageProcessor(){
        loadImage();

    }
    public void getImageData(){
        BufferedImage subImage = treeImg.getSubimage(0,0,256,256);
                int actualRGB = subImage.getRGB(200,150);
                System.out.print(actualRGB);

    }

    public BufferedImage getTreeImg() {
        return treeImg;
    }

    public void setTreeImg(BufferedImage treeImg) {
        this.treeImg = treeImg;
    }

    private void loadImage (){
        try
        {
            treeImg = ImageIO.read(new File("C:/Users/gollo/Documents/Universidad/2019 Semestre II/Analisis de Algoritmos/genetics-fiesta/JavaProyect/src/resources/tree.jpg"));
            System.out.println("funciono prro");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
