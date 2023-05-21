package com.niniconi.mrm.service.resource;

import com.niniconi.mrm.entity.Tag;
import com.niniconi.mrm.entity.TagEntity;
import com.niniconi.mrm.mapper.TagMapper;
import com.niniconi.mrm.service.resource.ResourceService;
import com.niniconi.mrm.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 用于管理资源的Tag
 */
@Slf4j
@Service
public class TagService {
    @Resource
    public TagMapper tagMapper;
    @Resource
    public ResourceService resourceService;

    public boolean createTag(String rid, List<Tag> tags){
        if(tags == null)return false;
        for (Tag tag:tags) {
            if(tagMapper.exists(rid, tag.getTag_type(), tag.getTag()) == 0) {
                tagMapper.addTag(rid, Random.uuid(), tag.getTag_type(), tag.getTag());
            }
        }
        return true;
    }

    public boolean updateTag(String tagId, String tagType, String tag) {
        String rid = tagMapper.getResourceId(tagId);
        if(tagMapper.exists(rid,tagType,tag) == 0) {
            tagMapper.updateTag(tagId, tagType, tag);
            return true;
        }
        return false;
    }


    /**
     * 将字符串转换为List<Tag>
     * @param str 指定字符串
     *            <p>
     *            使用" ，"号隔开每一个Tag，每一个Tag又是由“:”隔开
     *            <br/>
     *            比如:
     *            <br/>
     *            <code>"tagType:tagName0,tagType:tagName1"</code>
     *            </p>
     * @return List<Tag>
     * @see com.niniconi.mrm.entity.Tag
     */
    public List<Tag> stringToTag(String str){
        String[] tags = str.split(",");
        return stringArrToTag(tags);
    }

    public List<Tag> stringArrToTag(String[] tags) {
        List<Tag> ret = new LinkedList<>();
        for (String t:tags) {
            Tag tag = new Tag();
            if(!t.matches("^[^:]+:[^:]+$")){
                return null;
            }
            String[] tmp = t.split(":");
            tag.setTag_type(tmp[0]);
            tag.setTag(tmp[1]);
            ret.add(tag);
        }
        return ret;
    }

    public void delete(String tagId) {
        tagMapper.deleteByTagID(tagId);
    }

    public Map<String, List<Map<String, String>>> formatTagList(List<TagEntity> tags){
        Map<String,List<Map<String,String>>> ret = new HashMap<>();
        for (TagEntity tag:tags) {
            String type = tag.getTagType();
            String tagId = tag.getTagId();
            String tagStr = tag.getTag();
            if(!ret.containsKey(type)) {
                List<Map<String,String>> tagMaps = new LinkedList<>();
                HashMap<String, String> entity = new HashMap<>();
                entity.put("id",tagId);
                entity.put("tag",tagStr);
                tagMaps.add(entity);
                ret.put(type, tagMaps);
            }else{
                HashMap<String, String> entity = new HashMap<>();
                entity.put("id",tagId);
                entity.put("tag",tagStr);
                ret.get(type).add(entity);
            }
        }
        return ret;
    }
}
