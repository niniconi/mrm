package com.niniconi.mrm.servlet;

import com.niniconi.mrm.entity.PageInfoEntity;
import com.niniconi.mrm.entity.enumeration.BookType;
import com.niniconi.mrm.entity.resource.MusicEntity;
import com.niniconi.mrm.entity.resource.ResourceEntity;
import com.niniconi.mrm.entity.TagEntity;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import com.niniconi.mrm.service.files.FileService;
import com.niniconi.mrm.service.resource.InquireService;
import com.niniconi.mrm.service.resource.ResourceService;
import com.niniconi.mrm.service.resource.TagService;
import com.niniconi.mrm.service.files.FileTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@RequestMapping("/resource")
public class ResourcePage {

    @Resource
    public ResourceService resourceService;
    @Resource
    public TagService tagService;
    @Resource
    public InquireService inquireService;
    @Resource
    public FileService fileService;
    @Resource
    public FileTypeService fileTypeService;
    @GetMapping("/page/{id}")
    public String resourceGet(@PathVariable String id, @RequestParam(name = "page",required = false,defaultValue = "0")int page, HttpServletResponse response, Model model){
        List<TagEntity> tags =  resourceService.getAllTags(id);
        ResourceEntity resource;
        if((resource = resourceService.getResource(id)) != null){
            model.addAttribute("resource",resource);
            model.addAttribute("tags",tagService.formatTagList(tags));
        }else{
            response.setStatus(404);
            return "error/404.html";
        }
        String fid = resource.getContentPath();
        switch (resource.getType()) {
            case ILLUSTRATION:
                //set url
                int limit = 40;
                int count = fileService.listFilesCount(fid);//file count
                int i = page * limit;
                int end = i + limit;
                List<Hashtable<String,String>> urls = new LinkedList<>();
                if (page > -1){
                    for (; i < end && i < count; i++) {
                        Hashtable<String,String> hashtable = new Hashtable<>();
                        hashtable.put("view","/resource/view/"+fid+"?type=ILLUSTRATION&page="+i);
                        hashtable.put("img","/imgs/" + fid + "?page=" + i);
                        urls.add(hashtable);
                    }
                }
                model.addAttribute("contentUrls",urls);

                model.addAttribute("pageInfo",new PageInfoEntity((count % limit == 0)?(count/limit):(count/limit + 1),page));
                return "resource/illustration.html";
            case MUSIC:
                String mvFid = ((MusicEntity)resource).getMv();
                return "resource/music.html";
            case BOOKS:
                BookType type = resourceService.getBookType(id);
                switch(type){
                    case TXT:
                        String txt = fileService.readOfString(fid,0);
                        String txtHtml = fileTypeService.txtToHTML(txt);
                        model.addAttribute("text",txtHtml);
                        break;
                    case MD:
                        String markdown = fileService.readOfString(fid,0);
                        String markdownHtml = fileTypeService.markdownToHTML(markdown);
                        model.addAttribute("text", markdownHtml);
                        break;
                }
                model.addAttribute("type",type.toString());
                return "resource/books.html";
            case CARTOON:
                int countOfCartoon = fileService.listFilesCount(fid);
                List<Map<String,String>> cartoonInfo = new LinkedList<>();
                for (int j = 0; j < countOfCartoon; j++) {
                    Map<String,String> map = new HashMap<>();
                    map.put("label","第"+j+"集");
                    map.put("url","/resource/view/"+fid+"?type=CARTOON&page="+j);
                    cartoonInfo.add(map);
                }
                model.addAttribute("cartoonInfo",cartoonInfo);
                return "resource/cartoon.html";
            case SHORT:
                return "resource/short.html";
            case COMICS:
                return "resource/comics.html";
            default:
                response.setStatus(404);
                return "error/404.html";
        }
    }
    @GetMapping("/view/{fid}")
    public String resourceView(@PathVariable String fid, @RequestParam(name = "type") ResourceType type, @RequestParam(name = "page",required = false,defaultValue = "0") int page,HttpServletResponse response, Model model){
        switch (type) {
            case ILLUSTRATION:
                int count = fileService.listFilesCount(fid);

                if(count == -1)return "error/404.html";
                if(page < 0 || page >= count)return "error/404.html";

                model.addAttribute("imgSrc","/imgs/"+fid+"?page="+page);
                model.addAttribute("pageInfo",new PageInfoEntity(count,page));
                return "resource/view/illustration.html";
            case BOOKS:
                break;
            case CARTOON:
                model.addAttribute("src","/video/"+fid);
                return "resource/view/video.html";
        }
        response.setStatus(404);
        return "error/404.html";
    }

    @GetMapping("/list")
    public String resourceList(@RequestParam(name = "page",required = false,defaultValue = "0")int page, @RequestParam(name = "type",required = false,defaultValue = "ALL") ResourceType type, @RequestParam(name = "tag",required = false,defaultValue = "")String[] tags, Model model) {
        int limit = 20;
        long count = resourceService.getResourceCount();
        if(page < 0 || page > count/limit) /*return "invalid.html"*/return "error/404.html";
        List<ResourceEntity> resources = inquireService.tagSearch(tagService.stringArrToTag(tags), type, page, 20);
        //List<ResourceEntity> resources = resourceService.getAllResource(page,20);

        model.addAttribute("resources", resources);
        model.addAttribute("pageInfo",new PageInfoEntity((count % limit == 0)?(count/limit):(count/limit + 1),page));
        return "list.html";
    }
}
