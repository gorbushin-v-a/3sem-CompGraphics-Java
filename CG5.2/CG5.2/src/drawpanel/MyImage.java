package drawpanel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author gorbushin_v_a
 */
public class MyImage {
    Image img;
    
    double x = 0;
    double y = 0;
    
    double i = 100;
    double j = 100;
    
    int figureHeight = 50;
    int figureWidth = 50;

    public MyImage() {
        try {
            this.img = ImageIO.read(new File("src/pictures/0.png"));
        } catch (IOException ex) {
            Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void paint(int height, int width){
        if(x > width-figureWidth) i = -i;
        if(y > height-figureHeight) j = -j;
        if(x < 0) i = -i;
        if(y < 0) j = -j;
        if(x > width-figureWidth || y > height-figureHeight || x < 0 || y < 0) setPicture();
        x+=i;
        y+=j;
    }
    
    public void currentAngle(double angle){
        i = Math.cos(angle);
        j = Math.sin(angle);
    }
    
    public void setPicture() {
        Random r = new Random();
        r.nextInt(7);
        try {
            this.img = ImageIO.read(new File("src/pictures/"+r.nextInt(6)+".png"));
        } catch (IOException ex) {
            Logger.getLogger(MyImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
