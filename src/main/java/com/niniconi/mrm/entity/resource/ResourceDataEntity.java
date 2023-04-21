package com.niniconi.mrm.entity.resource;

import com.niniconi.mrm.entity.Tag;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
public class ResourceDataEntity {
    String name;
    String description;
    MultipartFile cover;
    MultipartFile[] content;
    String author;
    ResourceType type;
    List<Tag> tags;

    public ResourceDataEntity(String name, String description, MultipartFile cover, MultipartFile[] content, String author, ResourceType type, List<Tag> tags) {
        this.name = name;
        this.description = description;
        this.cover = cover;
        this.content = content;
        this.author = author;
        this.type = type;
        this.tags = tags;
    }
}
