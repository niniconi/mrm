package com.niniconi.mrm.servlet.transfer;

import com.niniconi.mrm.entity.MessageEntity;
import com.niniconi.mrm.entity.NullMultipartFileEntity;
import com.niniconi.mrm.entity.resource.ResourceDataEntity;
import com.niniconi.mrm.entity.enumeration.BookType;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import com.niniconi.mrm.service.files.FileService;
import com.niniconi.mrm.service.resource.TagService;
import com.niniconi.mrm.service.resource.UploadService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static com.niniconi.mrm.entity.enumeration.MessageStatus.FAILED;
import static com.niniconi.mrm.entity.enumeration.MessageStatus.SUCCESS;

@RestController
public class TransferRest {
    @Resource
    public UploadService uploadService;
    @Resource
    public FileService fileService;
    @Resource
    public TagService tagService;
    @PostMapping("/upload/illustration")
    public MessageEntity uploadIllustration(@RequestParam(name = "name")String name,
                                            @RequestParam(name = "description") String description,
                                            @RequestParam(name = "author") String author,
                                            @RequestParam(name = "tag") String tag,
                                            @RequestParam(name = "file") MultipartFile[] files,
                                            @RequestParam(name = "cover",required = false) MultipartFile cover,HttpServletResponse response){
        if(cover == null)cover = new NullMultipartFileEntity();
        boolean isSuccess = uploadService.uploadIllustration(new ResourceDataEntity(name,description,cover,files,author, ResourceType.ILLUSTRATION,tagService.stringToTag(tag)));
        if(isSuccess){
            return new MessageEntity(SUCCESS,"upload success");
        } else {
            return new MessageEntity(FAILED,"upload failed");
        }
    }
    @PostMapping("/upload/books")
    public MessageEntity uploadBooks(@RequestParam(name = "name")String name,
                                     @RequestParam(name = "description") String description,
                                     @RequestParam(name = "author") String author,
                                     @RequestParam(name = "tag") String tag,
                                     @RequestParam(name = "file") MultipartFile[] files,
                                     @RequestParam(name = "cover",required = false) MultipartFile cover,
                                     @RequestParam(name = "bookType")BookType bookType, HttpServletResponse response){
        if(cover == null)cover = new NullMultipartFileEntity();
        boolean isSuccess = uploadService.uploadBooks(new ResourceDataEntity(name,description,cover,files,author, ResourceType.BOOKS,tagService.stringToTag(tag)),bookType);
        if(isSuccess){
            return new MessageEntity(SUCCESS,"upload success");
        } else {
            return new MessageEntity(FAILED,"upload failed");
        }
    }

    @PostMapping("/upload/music")
    public MessageEntity uploadMusic(@RequestParam(name = "name")String name,
                                     @RequestParam(name = "description") String description,
                                     @RequestParam(name = "author") String author,
                                     @RequestParam(name = "tag") String tag,
                                     @RequestParam(name = "file") MultipartFile[] files,
                                     @RequestParam(name = "cover",required = false) MultipartFile cover,
                                     @RequestParam(name = "mv",required = false) MultipartFile mv, HttpServletResponse response){
        if(cover == null)cover = new NullMultipartFileEntity();
        if(mv == null)mv = new NullMultipartFileEntity();
        boolean isSuccess = uploadService.uploadMusic(new ResourceDataEntity(name,description,cover,files,author,ResourceType.MUSIC,tagService.stringToTag(tag)),mv);
        if(isSuccess){
            return new MessageEntity(SUCCESS,"upload success");
        } else {
            return new MessageEntity(FAILED,"upload failed");
        }
    }

    @PostMapping("/upload/cartoon")
    public MessageEntity uploadCartoon(@RequestParam(name = "name")String name,
                                       @RequestParam(name = "description") String description,
                                       @RequestParam(name = "author") String author,
                                       @RequestParam(name = "tag") String tag,
                                       @RequestParam(name = "file") MultipartFile[] files,
                                       @RequestParam(name = "cover",required = false) MultipartFile cover, HttpServletResponse response){
        if(cover == null)cover = new NullMultipartFileEntity();
        boolean isSuccess = uploadService.uploadCartoon(new ResourceDataEntity(name,description,cover,files,author,ResourceType.CARTOON,tagService.stringToTag(tag)));
        if(isSuccess){
            return new MessageEntity(SUCCESS,"upload success");
        } else {
            return new MessageEntity(FAILED,"upload failed");
        }
    }

    @PostMapping("/upload/comics")
    public MessageEntity uploadComics(@RequestParam(name = "name")String name,
                                      @RequestParam(name = "description") String description,
                                      @RequestParam(name = "author") String author,
                                      @RequestParam(name = "tag") String tag,
                                      @RequestParam(name = "file") MultipartFile[] files,
                                      @RequestParam(name = "cover",required = false) MultipartFile cover, HttpServletResponse response){
        if(cover == null)cover = new NullMultipartFileEntity();
        boolean isSuccess = uploadService.uploadComics(new ResourceDataEntity(name,description,cover,files,author,ResourceType.CARTOON,tagService.stringToTag(tag)));
        if(isSuccess){
            return new MessageEntity(SUCCESS,"upload success");
        } else {
            return new MessageEntity(FAILED,"upload failed");
        }
    }

    @PostMapping("/upload/short")
    public MessageEntity uploadShort(@RequestParam(name = "name")String name,
                                     @RequestParam(name = "description") String description,
                                     @RequestParam(name = "author") String author,
                                     @RequestParam(name = "tag") String tag,
                                     @RequestParam(name = "file") MultipartFile[] files,
                                     @RequestParam(name = "cover",required = false) MultipartFile cover, HttpServletResponse response){
        if(cover == null)cover = new NullMultipartFileEntity();
        boolean isSuccess = uploadService.uploadShort(new ResourceDataEntity(name,description,cover,files,author,ResourceType.CARTOON,tagService.stringToTag(tag)));
        if(isSuccess){
            return new MessageEntity(SUCCESS,"upload success");
        } else {
            return new MessageEntity(FAILED,"upload failed");
        }
    }


    @GetMapping("/img/{fid}")
    @ResponseBody
    public void getImage(@PathVariable String fid, HttpServletResponse response) throws IOException {
        fileService.readOfBinData(fid, 0, "image", response);
    }

    @GetMapping("/imgs/{fid}")
    @ResponseBody
    public void getImages(@PathVariable String fid,@RequestParam(name = "page",required = false,defaultValue = "0") int page, HttpServletResponse response) throws IOException {
        fileService.readOfBinData(fid, page,"image", response);
    }

    @GetMapping("/music/{fid}")
    @ResponseBody
    public void getImages(@PathVariable String fid,HttpServletResponse response) throws IOException {
        fileService.readOfBinData(fid,0, "audio",response);
    }

    @GetMapping("/PDF/{fid}.pdf")
    @ResponseBody
    public void getPDF(@PathVariable String fid,HttpServletResponse response) throws IOException {
        fileService.readOfBinData(fid, 0,"application/pdf",response);
    }

    @GetMapping("/video/{fid}")
    public void getVideo(@PathVariable String fid,@RequestParam(name = "page", required = false, defaultValue = "0")int page,HttpServletResponse response) throws IOException{
        fileService.readOfBinData(fid, page,"mp4",response);
    }
}