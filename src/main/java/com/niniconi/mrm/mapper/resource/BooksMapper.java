package com.niniconi.mrm.mapper.resource;

import com.niniconi.mrm.entity.enumeration.BookType;
import com.niniconi.mrm.entity.resource.BooksEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BooksMapper{
    /**
     * get the record count
     * @return int of count
     */
    int count();

    /**
     * create book resource
     * @param rid resource id
     * @param type type of the book
     */
    void createBook(@Param("rid") String rid, @Param("type") BookType type);

    /**
     * get the book by rid
     * @param rid resource id
     * @return books entity
     * @see BooksEntity
     */
    BooksEntity getBook(@Param("rid") String rid);

    /**
     *
     * @param limit
     * @param offset
     * @return
     */
    List<BooksEntity> getAllBook(int limit,int offset);
    List<BooksEntity> saveAllBook();
    BookType getType(@Param("rid") String rid);

    void delete(@Param("rid")String rid);
}