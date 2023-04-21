package com.niniconi.mrm.service.resource;

import com.niniconi.mrm.entity.FileType;
import com.niniconi.mrm.entity.resource.*;
import com.niniconi.mrm.entity.Tag;
import com.niniconi.mrm.entity.enumeration.BookType;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import com.niniconi.mrm.mapper.config.GlobalConfig;
import com.niniconi.mrm.service.files.FileService;
import com.niniconi.mrm.service.files.ImageToolService;
import com.niniconi.mrm.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class UploadService{
    @Resource
    public FileService fileService;
    @Resource
    public ResourceService resourceService;
    @Resource
    public TagService tagService;
    @Resource
    public GlobalConfig globalConfig;
    @Resource
    public ImageToolService imageTool;

    private String saveResources(ResourceDataEntity resourceData){
        String name = resourceData.getName();
        String description = resourceData.getDescription();
        String author = resourceData.getAuthor();
        ResourceType type = resourceData.getType();
        MultipartFile cover = resourceData.getCover();
        MultipartFile[] files = resourceData.getContent();
        List<Tag> tags = resourceData.getTags();

        //refuse ALL Type
        if(type == ResourceType.ALL) return null;

        ResourceEntity resource;
        String rid = Random.uuid();
        String contentFid,coverFid;

        contentFid = fileService.addFiles(rid, files);
        if(contentFid == null)return null;

        //init cover
        if(!cover.isEmpty()) {
            BufferedImage bufImage;
            try {
                bufImage = imageTool.imageCompression(cover.getInputStream(), ImageToolService.ImageQuality.MID);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ByteArrayOutputStream outputStream;
            try {
                outputStream = new ByteArrayOutputStream();
                ImageIO.write(bufImage,"png",outputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            coverFid = fileService.addFile(rid, "cover.jpg",outputStream);
        }else {
            switch (type) {
                case COMICS:
                case ILLUSTRATION:
                    coverFid = contentFid;
                    break;
                case BOOKS:
                    if(FileType.getType("pdf").equals(files[0].getContentType())) {
                        coverFid = resourceService.pdfToImage(files[0], rid, 1, 0);
                        break;
                    }
                case MUSIC:
                case CARTOON:
                    coverFid = resourceService.generateCover(rid,name);
                    break;
                default:
                    log.error("generate cover failed");
                    fileService.deleteFile(contentFid);
                    return null;
            }
        }

        switch (type){
            case ILLUSTRATION:
                resource = new ResourceEntity();
                break;
            case CARTOON:
                resource = new CartoonEntity();
                break;
            case COMICS:
                resource = new ComicsEntity();
                break;
            case SHORT:
                resource = new ShortEntity();
                break;
            case BOOKS:
                resource = new BooksEntity();
                break;
            case MUSIC:
                resource = new MusicEntity();
                break;
            default:
                log.error("Unknown resource type");
                return null;
        }
        resource.setRid(rid);
        if("".equals(name)){
            resource.setName(new Date().toString());
        }else {
            resource.setName(name);
        }
        resource.setDescription(description);
        resource.setAuthor(author);
        resource.setType(type);
        resource.setUploadDate(new Date());
        resource.setFilesSize(fileService.getFileSize(files));
        resource.setContentPath(contentFid);
        resource.setCoverPath(coverFid);
        boolean status = resourceService.createResource(resource);

        if(!status){
            fileService.deleteFile(contentFid);
            fileService.deleteFile(coverFid);
            return null;
        }
        //create tags
        tagService.createTag(rid,tags);
        log.info("resource:"+rid+" create success");
        return rid;
    }

    public boolean uploadBooks(ResourceDataEntity resourceData, BookType bookType){
        String rid = saveResources(resourceData);
        if(rid != null){
            return resourceService.createBooks(rid,bookType);
        }
        return false;
    }
    public boolean uploadIllustration(ResourceDataEntity resourceData){
        String rid = saveResources(resourceData);
        if(rid != null){
            return resourceService.createIllustration(rid);
        }
        return false;
    }
    public boolean uploadMusic(ResourceDataEntity resourceData,MultipartFile mv){
        String rid = saveResources(resourceData);
        if(rid != null){
            String mvFid = fileService.addFile(rid,mv);
            return resourceService.createMusic(rid,mvFid);
        }
        return false;
    }
    public boolean uploadShort(ResourceDataEntity resourceData){
        String rid = saveResources(resourceData);
        if(rid != null){
            return resourceService.createShort(rid);
        }
        return false;
    }
    public boolean uploadComics(ResourceDataEntity resourceData) {
        String rid = saveResources(resourceData);
        if (rid != null) {
            return resourceService.createComics(rid);
        }
        return false;
    }
    public boolean uploadCartoon(ResourceDataEntity resourceData){
        String rid = saveResources(resourceData);
        if(rid != null){
            return resourceService.createCartoon(rid);
        }
        return false;
    }
/*

    private static boolean createDirectory(String folder) {
        File dir = new File(folder);
        if (dir.exists()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }*/

}