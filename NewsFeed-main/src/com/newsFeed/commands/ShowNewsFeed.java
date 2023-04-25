package com.newsFeed.commands;

import com.newsFeed.repository.NewsFeedRepo;
import com.newsFeed.entity.Users;
import com.newsFeed.repository.Post;
import com.newsFeed.exception.BadCommandException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ShowNewsFeed implements ICommand {

    private final LocalDateTime localDateTime;
    private static ShowNewsFeed showNewsFeedInstance;
    private NewsFeedRepo newsFeedRepo;


    private ShowNewsFeed() {
        localDateTime=LocalDateTime.now();
        newsFeedRepo = NewsFeedRepo.getInstance();
    }

    public static synchronized ShowNewsFeed getInstance() {
        if (showNewsFeedInstance == null) {
            showNewsFeedInstance = new ShowNewsFeed();
        }
        return showNewsFeedInstance;
    }

    @Override
    public void executeCommand(String[] cmd) throws BadCommandException {

        if(cmd.length != 1) {
            throw new BadCommandException("Incorrect Command Format");
        }
        List<Post> allPost = newsFeedRepo.getAllPost();
        Users activeUser = newsFeedRepo.getActiveUser();
        Collections.sort(allPost, new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {

                if(activeUser.checkForFollower(o1.getContentOwner().getuId()) && activeUser.checkForFollower(o2.getContentOwner().getuId())){
                    return o1.compareTo(o2);
                }
                else if(activeUser.checkForFollower(o1.getContentOwner().getuId()) || activeUser.checkForFollower(o2.getContentOwner().getuId())){

                    if(activeUser.checkForFollower(o1.getContentOwner().getuId())){
                        return -1;
                    }
                    return 1;
                }

                return o1.compareTo(o2);
            }
        });

        for(Post post : allPost){
            if(post.getContentOwner().getuId() != activeUser.getuId()){
                System.out.println(post.toString());
            }
        }
    }
}
