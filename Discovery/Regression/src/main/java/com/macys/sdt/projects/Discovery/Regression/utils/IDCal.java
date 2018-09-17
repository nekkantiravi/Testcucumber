package com.macys.sdt.projects.Discovery.Regression.utils;

import org.junit.Assert;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by YH03402 on 9/10/2017.
 */
public class IDCal {

    String imagePath1;
    String imagePath2;
    URL webPath1;
    String webLocalImagePath;
    BufferedImage img1 = null;
    BufferedImage img2 = null;

   public IDCal(String imagePath1, String imagePath2) {
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
    }
    public IDCal(URL webPath1, String webLocalImagePath) {
        this.webPath1 = webPath1;
        this.webLocalImagePath = webLocalImagePath;
    }


    private void extractImagefromFile() {

        try {
            img1 = ImageIO.read(new File(imagePath1));
            img2 = ImageIO.read(new File(imagePath2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void extractImagefromUrl() {

        try {
            System.out.println(webPath1.toString());
            img1 = ImageIO.read(webPath1);
            img2 = ImageIO.read(new File(webLocalImagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Double getDiffofSavedImages() {
        extractImagefromFile();
       return getDiff(img1,img2);
    }
    public Double getDiffofWebAndSavedImage(){
        extractImagefromUrl();
        return getDiff(img1,img2);
    }
    /*
    Method Name: getDiff
    Description: calculated diff of two image buffers.
    Return type: Double
    */
    private Double getDiff(BufferedImage img1,BufferedImage im2) {
        int width1 = img1.getWidth(null);
        int width2 = img2.getWidth(null);
        int height1 = img1.getHeight(null);
        int height2 = img2.getHeight(null);
       Assert.assertFalse("Error: Images dimensions mismatch" + "1st image" + width1 + "::" + height1 +
               "2nd Image" + width2 + "::" + height2,(width1 != width2) || (height1 != height2));
        long diff = 0;
        for (int y = 0; y < height1; y++) {
            for (int x = 0; x < width1; x++) {
                int rgb1 = img1.getRGB(x, y);
                int rgb2 = img2.getRGB(x, y);
                int r1 = (rgb1 >> 16) & 0xff;
                int g1 = (rgb1 >> 8) & 0xff;
                int b1 = (rgb1) & 0xff;
                int r2 = (rgb2 >> 16) & 0xff;
                int g2 = (rgb2 >> 8) & 0xff;
                int b2 = (rgb2) & 0xff;
                diff += Math.abs(r1 - r2);
                diff += Math.abs(g1 - g2);
                diff += Math.abs(b1 - b2);
            }
        }
        double n = width1 * height1 * 3;
        double p = diff / n / 255.0;
        return((p * 100.0));
    }

}
