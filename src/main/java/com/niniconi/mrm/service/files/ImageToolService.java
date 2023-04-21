package com.niniconi.mrm.service.files;

import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ImageToolService {
    public static final Map<String,Float> LOW = new HashMap<>();
    public static final Map<String,Float> MID = new HashMap<>();
    public static final Map<String,Float> HIGH = new HashMap<>();
    static {
        LOW.put("scale",0.3f);
        LOW.put("quality",0.9f);
        MID.put("scale",0.5f);
        MID.put("quality",1f);
        HIGH.put("scale",1f);
        HIGH.put("quality",1f);
    }
    public enum ImageQuality{
        LOW,
        MID,
        HIGH
    }
    public BufferedImage imageCompression(BufferedImage image,ImageQuality imageQuality){
        Map<String,Float> compressionRate = null;
        switch (imageQuality){
            case LOW: compressionRate = LOW;
                break;
            case MID: compressionRate = MID;
                break;
            case HIGH: compressionRate = HIGH;
                break;
        }
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = Thumbnails.of(image).scale(compressionRate.get("scale")).outputQuality(compressionRate.get("quality")).asBufferedImage();
        } catch (IOException e) {
            log.info("image Compression failed");
            e.printStackTrace();
        }
        return bufferedImage;
    }
    public BufferedImage imageCompression(String imagePath,ImageQuality imageQuality){
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            log.error("read image failed not found");
            e.printStackTrace();
            return null;
        }
        return  imageCompression(image,imageQuality);
    }
    public BufferedImage imageCompression(InputStream imageInput,ImageQuality imageQuality){
        BufferedImage image = null;
        try {
            image = ImageIO.read(imageInput);
        } catch (IOException e) {
            log.error("failed read image from inputStream");
            e.printStackTrace();
            return null;
        }
        return imageCompression(image,imageQuality);
    }
}
