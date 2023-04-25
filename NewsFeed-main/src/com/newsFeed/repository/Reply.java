package com.newsFeed.repository;

import com.newsFeed.entity.Users;

public class Reply extends Content{

    public Reply(String text, Users contentOwner) {
        super(text, contentOwner);
    }
}
