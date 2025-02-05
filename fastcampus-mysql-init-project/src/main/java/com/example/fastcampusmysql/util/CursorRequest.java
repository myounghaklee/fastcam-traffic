package com.example.fastcampusmysql.util;

public record CursorRequest (Long key, int size){
    public static final Long NONE_KEY = -1L;

    public Boolean hasKey(){
        return key != null;
    }


    public static CursorRequest next(Long key){
        return new CursorRequest(key, size);
    }
}

