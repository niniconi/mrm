package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.enumeration.ResourceType;
import com.niniconi.mrm.entity.resource.ResourceEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ResourceMapper {
    /**
     * get the record count
     * @return int of count
     */
    int count();

    /**
     * create a new resource
     * @param rid resource id
     * @param name name
     * @param description description
     * @param fileSize size of content
     * @param uploadDate the date of upload
     * @param coverFid cover file's id
     * @param contentFid content file's id
     * @param author author
     * @param type resource type
     */
    void createResource(@Param("rid") String rid,
                        @Param("name") String name,
                        @Param("description") String description,
                        @Param("file_size") long fileSize,
                        @Param("upload_date") Date uploadDate,
                        @Param("cover") String coverFid,
                        @Param("content") String contentFid,
                        @Param("author") String author,
                        @Param("type") ResourceType type);

    /**
     * get the resource by id
     * @return ResourceEntity
     */
    ResourceEntity getResource(@Param("rid") String id);


    /**
     * search the Resource Entity by name
     * @param key it is a search key of name
     * @param limit
     * @param offset
     * @return a list of Resources
     */
    List<ResourceEntity> searchByName(@Param("key") String key,@Param("limit") int limit,@Param("offset") int offset);

    /**
     * get all resource
     * @param limit
     * @param offset
     * @return
     */
    List<ResourceEntity> getAllResource(long limit, long offset);

    List<ResourceEntity> saveAllResource();

    /**
     * get the type of this resource
     * @param rid resource id
     * @return resource type
     */
    ResourceType getType(@Param("rid") String rid);

    List<String> getResourceIdByType(@Param("type") ResourceType type);

    void delete(@Param("rid")String rid);

    long sizeCount();
}
