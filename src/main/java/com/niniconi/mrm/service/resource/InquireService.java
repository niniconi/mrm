package com.niniconi.mrm.service.resource;

import com.niniconi.mrm.entity.resource.ResourceEntity;
import com.niniconi.mrm.entity.Tag;
import com.niniconi.mrm.mapper.TagMapper;
import com.niniconi.mrm.mapper.resource.ResourceMapper;
import com.niniconi.mrm.entity.enumeration.ResourceType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

import static com.niniconi.mrm.entity.enumeration.ResourceType.ALL;

@Slf4j
@Service
public class InquireService {
    @Resource
    public TagMapper tag;
    @Resource
    public ResourceMapper res;
    @Resource
    public ResourceService resourceService;

    /**
     * search by tags
     * @param tags some tags
     * @param resType the resource's Type ,such as PICTURE.
     * @return a list of ResourceEntity
     * @see ResourceType
     */
    public List<ResourceEntity> tagSearch(List<Tag> tags, ResourceType resType, int page, int limit) {
        if(resType == null)return null;
        List<List<String>> tagEntityLists = new LinkedList<>();
        int minIndex = 0, minSize = -1;
        if (tags.size() > 0){
            for (int i = 0; i < tags.size(); i++) {
                Tag t = tags.get(i);
                String tagType = t.getTag_type();
                String tagLabel = t.getTag();
                List<String> tagEntities;
                if (resType == ALL) {
                    tagEntities = tag.getResourceIDByNameNoType(tagType, tagLabel);
                } else {
                    tagEntities = tag.getResourceIDByName(resType, tagType, tagLabel);
                }
                tagEntityLists.add(tagEntities);
                int size = tagEntities.size();
                if (minSize == -1) {
                    minSize = size;
                } else {
                    if (size < minSize) {
                        minSize = size;
                        minIndex = i;
                    }
                }
            }
        }else{
            if(resType == ALL){
               return resourceService.getAllResource(page, limit) ;
            } else {
               return resourceService.getResources(res.getResourceIdByType(resType));
            }
        }
        List<String> resultResourceId = new LinkedList<>();
        List<String> standard = tagEntityLists.get(minIndex);
        for (String contrastValue  : standard) {

            boolean flag1 = true;//当前数值是否在所有的集中
            for (int j = 0; j < tagEntityLists.size(); j++) {
                if (j != minIndex) {

                    //寻找与contrastValue相同的数值
                    boolean flag2 = false;//此集合中是否有contrastValue
                    List<String> comparedList = tagEntityLists.get(j);
                    for (String comparedValue : comparedList) {
                        if (comparedValue.equals(contrastValue)) {
                            flag2 = true;
                            break;
                        }
                    }
                    if (!flag2) {
                        flag1 = false;
                        break;
                    }
                }
            }
            if (flag1) {
                resultResourceId.add(contrastValue);
            }
        }
        return resourceService.getResources(resultResourceId);
    }

    /**
     *
     * @param key
     * @param page [0,1,2,3,...]
     * @param pageLimit
     * @return
     */
    public List<ResourceEntity> nameSearch(String key,int page,int pageLimit){
        return res.searchByName("%"+key+"%",pageLimit,page*pageLimit);
    }
}