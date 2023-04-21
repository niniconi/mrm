package com.niniconi.mrm.entity.resource;

import com.niniconi.mrm.entity.enumeration.ResourceType;
import lombok.Data;

import java.util.Date;

@Data
public class ResourceEntity {

    String rid;
    String name;
    long filesSize;//unit is byte
    String description;
    Date uploadDate;
    String coverPath;
    String contentPath;
    String author;
    ResourceType type;
}
