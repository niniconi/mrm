package com.niniconi.mrm.service.resource;

import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.entity.enumeration.BookType;
import com.niniconi.mrm.entity.enumeration.MessageStatus;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import com.niniconi.mrm.entity.resource.ResourceEntity;
import com.niniconi.mrm.entity.TagEntity;
import com.niniconi.mrm.mapper.FilesMapper;
import com.niniconi.mrm.mapper.TagMapper;
import com.niniconi.mrm.mapper.config.Config;
import com.niniconi.mrm.mapper.config.GlobalConfig;
import com.niniconi.mrm.mapper.resource.*;
import com.niniconi.mrm.service.files.ImageToolService;
import com.niniconi.mrm.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@Service
public class ResourceService {
    @Resource
    public BooksMapper books;
    @Resource
    public ComicsMapper comics;
    @Resource
    public CartoonMapper cartoon;
    @Resource
    public IllustrationMapper illustration;
    @Resource
    public MusicMapper music;
    @Resource
    public ShortMapper shortV;
    @Resource
    public TagMapper tag;
    @Resource
    public ResourceMapper resource;
    @Resource
    public FilesMapper filesMapper;
    @Resource
    public GlobalConfig globalConfig;
    @Resource
    public ImageToolService imageTool;
    @Resource
    public Config config;

    public ResourceEntity getResource(String rid){
        ResourceType type = resource.getType(rid);
        if(type == null)return null;
        switch (type){
            case BOOKS:
                return books.getBook(rid);
            case MUSIC:
                return music.getMusic(rid);
            case SHORT:
                return shortV.getShort(rid);
            case CARTOON:
                return cartoon.getCartoon(rid);
            case COMICS:
                return comics.getComics(rid);
            case ILLUSTRATION:
                return illustration.getIllustration(rid);
        }
        return null;
    }
    public MessageEntity deleteResource(String rid){
        ResourceEntity resourceEntity = resource.getResource(rid);
        if(resourceEntity == null)
            return new MessageEntity(MessageStatus.FAILED,"can't delete the resource is not exists");
        resource.delete(rid);
        switch(resourceEntity.getType()){
            case BOOKS:
                books.delete(rid);
                break;
            case COMICS:
                comics.delete(rid);
                break;
            case SHORT:
                shortV.delete(rid);
                break;
            case MUSIC:
                music.delete(rid);
                break;
            case ILLUSTRATION:
                illustration.delete(rid);
                break;
            case CARTOON:
                cartoon.delete(rid);
                break;
            default:
                return new MessageEntity(MessageStatus.FAILED,"Unknown type");
        }
        return new MessageEntity(MessageStatus.SUCCESS,"delete success");
    }
    public BookType getBookType(String rid){
       return books.getType(rid);
    }

    public List<TagEntity> getAllTags(String id){
        return tag.selectTagByRes(id);
    }
    public List<ResourceEntity> getResources(List<String> ids){
        List<ResourceEntity> ret = new LinkedList<>();
        for (String id:ids){
            ret.add(resource.getResource(id));
        }
        return ret;
    }

    public boolean createResource(ResourceEntity resource) {
        try {
            this.resource.createResource(
                    resource.getRid(),
                    resource.getName(),
                    resource.getDescription(),
                    resource.getFilesSize(),
                    resource.getUploadDate(),
                    resource.getCoverPath(),
                    resource.getContentPath(),
                    resource.getAuthor(),
                    resource.getType());
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createBooks(String rid,BookType type){
        try {
            books.createBook(rid, type);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createComics(String rid){
        try {
            comics.createComics(rid);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createCartoon(String rid){
        try {
            cartoon.createCartoon(rid);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createMusic(String rid,String mv){
        try{
            music.createMusic(rid,mv);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createShort(String rid){
        try{
            shortV.createShort(rid);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean createIllustration(String rid){
        try {
            illustration.createIllustration(rid);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public long getResourceCount() {
        return resource.count();
    }
    public long getResourceSizeCount(){
        return resource.sizeCount();
    }

    public List<ResourceEntity> getAllResource(int page,int limit){
        return resource.getAllResource(limit,page*limit);
    }

    public ResourceType getType(String rid) {
        return resource.getType(rid);
    }

    public String generateCover(String path,String name){
        BufferedImage image;
        String fid = Random.uuid();
        String absolutePath =config.getProperty(-1,"content-path") + '/' + path + '/' + "cover.png";
        try {
            image = ImageIO.read(new File(config.getProperty(-1,"default-cover") + "/.data/default.jpg"));

            int fontSize = image.getHeight()/6;
            int x = image.getWidth()/2;
            int y = image.getHeight()/2;

            Graphics2D g2d = (Graphics2D) image.getGraphics();
            g2d.setFont(new Font(null, Font.PLAIN,fontSize));
            g2d.drawString(name,x,y);

            image = imageTool.imageCompression(image, ImageToolService.ImageQuality.MID);//compression generate image

            ImageIO.write(image,"png",new File(absolutePath));
            filesMapper.addFile(fid,path + "/cover.png");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return fid;
    }

    /**
     * pdf to image
     * @param pdfFile
     * @param path
     * @param scale
     *  600 dpi give good image clarity but size of each image is 2x times of 300 dpi.
     *  Ex:  1. For 300dpi 04-Request-Headers_2.png expected size is 797 KB
     *       2. For 600dpi 04-Request-Headers_2.png expected size is 2.42 MB
     * @param page render page
     * @return fid
     */
    public String pdfToImage(MultipartFile pdfFile, String path, int  scale, int page) {
        String fid = Random.uuid();
        File returnFile = null;
        String fileExtension= "png";
        String absolutePath = config.getProperty(-1,"content-path") + '/' + path + '/' + "cover.png";
        try {
            returnFile = new File(absolutePath);
            if (!returnFile.exists()) {
                returnFile.mkdirs();
                log.info("Folder Created -> "+returnFile.getAbsolutePath());
            }

            if (!pdfFile.isEmpty()) {
                log.info("Images copied to Folder Location: "+returnFile.getAbsolutePath());
                PDDocument document = PDDocument.load(pdfFile.getBytes());
                PDFRenderer pdfRenderer = new PDFRenderer(document);

                int numberOfPages = document.getNumberOfPages();
                if(numberOfPages <= page){
                    log.error("page is over than numberOfPage");
                    return null;
                }

                log.info("Total files to be converting -> "+ numberOfPages);
                BufferedImage bImage = pdfRenderer.renderImage(page,scale,ImageType.RGB);
                ImageIO.write(bImage,fileExtension,returnFile);
                document.close();
                log.info("Converted Images are saved at -> "+returnFile.getAbsolutePath());

                filesMapper.addFile(fid,path + '/' + "cover.png");
            } else {
                log.error(pdfFile.getOriginalFilename()+" File is empty");
                return null;
            }
        } catch (Exception e) {
            log.error("pdfToImageFailed:"+e.getMessage());
            e.printStackTrace();
            return null;
        }
        return fid;
    }

    public MessageEntity editResource(String rid, String description) {
        return null;
    }

    public MessageEntity renameResource(String rid, String name) {
        return null;
    }
}
