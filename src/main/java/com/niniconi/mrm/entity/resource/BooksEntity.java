package com.niniconi.mrm.entity.resource;

import com.niniconi.mrm.entity.enumeration.BookType;
import lombok.Data;

@Data
public class BooksEntity extends ResourceEntity{
    BookType fileType;
}
