package com.niniconi.mrm.mapper;

import com.niniconi.mrm.entity.TagEntity;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TagMapper {

    /**
     * 判断是否存在(根据tag的所在的资源)
     * @param rid
     * @param tagType
     * @param tag
     * @return 为0存在，为1存在
     */
    int exists(@Param("rid") String rid,@Param("tag_type") String tagType,@Param("tag") String tag);

    String getResourceId(@Param("tagId")String tagId);

    /**
     * 判读
     * @param tagId
     * @return
     */
    int existsById(@Param("tag_id")String tagId);

    /**
     * 通过资源的ID号删除它的所有tag,<b>当删除资源时删除所有TAG</b>
     * @param resID 资源的ID
     * @author niniconi
     */
    void deleteByResID(@Param("res_id") String resID);

    /**
     * 通过ID删除
     * @param tagId
     */
    void deleteByTagID(@Param("tag_id") String tagId);

    /**
     * add a new tag
     * @param resID the resource's id
     * @param tagId tag's id
     * @param tagType
     * @param tag
     */
    void addTag(@Param("res_id") String resID,@Param("tag_id")String tagId,@Param("tag_type") String tagType,@Param("tag")String tag);


    // some api for tag select

    List<String> getResourceIDByName(@Param("type") ResourceType resType, @Param("tag_type")String tagType, @Param("tag")String tag);
    List<String> getResourceIDByNameNoType(@Param("tag_type")String tagType,@Param("tag") String tag);
    List<TagEntity> selectTagByNameNoType(@Param("tag_type")String tagType,@Param("tag") String tag);
    List<TagEntity> selectTagByName(@Param("type") ResourceType resType, @Param("tag_type")String tagType, @Param("tag")String tag);
    List<TagEntity> selectTagByRes(@Param("res_id")String resID);
    List<TagEntity> selectTagByOnlyName(@Param("tag")String tag);

    boolean updateTag(@Param("tagId") String tagId, @Param("tagType") String tagType,@Param("tag") String tag);
}
