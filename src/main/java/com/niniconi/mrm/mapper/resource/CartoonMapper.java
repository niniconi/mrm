package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.resource.CartoonEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartoonMapper{

    /**
     * get the record count
     * @return int of count
     */
    int count();

    void createCartoon(@Param("rid")String rid);

    CartoonEntity getCartoon(@Param("rid")String rid);
    List<CartoonEntity> getAllCartoon(int limit, int offset);
    void delete(@Param("rid")String rid);
}
