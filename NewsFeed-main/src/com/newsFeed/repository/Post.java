package com.newsFeed.repository;

import com.newsFeed.entity.Users;

import java.util.ArrayList;
import java.util.List;

public class Post extends Content {

    private Integer upVotes;
    private Integer downVotes;
    List<Reply> replyList;


    public Post(String text, Users contentOwner) {
        super(text, contentOwner);
        this.upVotes = 0;
        this.downVotes = 0;
        replyList = new ArrayList<>();
    }

    public Integer getUpVotes() {
        return upVotes;
    }

    public Integer getDownVotes() {
        return downVotes;
    }

    public void addUpVotes(){
        this.upVotes = this.upVotes + 1;
    }
    public void addDownVotes(){
        this.downVotes = this.downVotes + 1;
    }

    public void addReply(Reply reply){
        replyList.add(reply);
    }

    public Integer getScore(){
        return this.upVotes - this.downVotes;
    }

    public List<Reply> getReplyList() {
        return replyList;
    }

    @Override
    public String toString() {
        return "Post{" +
                super.toString() + "," +
                "upVotes=" + upVotes +
                ", downVotes=" + downVotes +
                ", replyList=" + replyList +
                '}';
    }

    public int compareTo(Post post){
        if(this.getScore() == post.getScore()){

            if(this.getReplyList().size() == post.getReplyList().size()){
                return post.getLocalDateTime().compareTo(this.getLocalDateTime());
            }
            return post.getReplyList().size() - this.getReplyList().size();
        }
        return  post.getScore() -  this.getScore();
    }

}
