package com.macys.sdt.projects.Discovery.Regression.utils;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by YH03402 on 9/7/2017.
 */
public class ImageValidator {
    /*Variables
    logger - loggerFactory object for ImageValidator class
*/
    private static final Logger logger = LoggerFactory.getLogger(ReadPreviewExcelData.class);
    
    /*
   Method Name: getSavedImagesDiffValues
   Description: Return the diff value between two images
   Return type: void
   */
    public static void VerifySavedImagesValues(String imagepath1, String imagepath2) {
     IDCal idc=new IDCal(imagepath1,imagepath2);
    logger.info(idc.getDiffofSavedImages().toString());
    }

   /*
  Method Name: getWebImagesDiffValues
  Description: Return the diff value between two images. Where one is on Web and other is saved
  Return type: Double
  */
    public static void verifyWebImagesValues(String imagepath1, String imagepath2) throws MalformedURLException {
        IDCal idc=new IDCal(new URL(imagepath1),imagepath2);
        double diff_percent=idc.getDiffofWebAndSavedImage();
        logger.info("diff percent::"+diff_percent);
        Assert.assertTrue("Image is not a complete Match",(diff_percent==0.0));
    }

}
