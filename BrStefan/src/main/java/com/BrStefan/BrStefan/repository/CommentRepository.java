package com.BrStefan.BrStefan.repository;

import com.BrStefan.BrStefan.domain.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CommentRepository {

    private List<Comment> comments;

    private Comment add(Comment comment){
        comments.add(comment);
        return comment;
    }

    private Comment delete(Comment comment){
        comments.remove(comment);
        return comment;
    }

    private List<Comment> getComments(){
        return comments;
    }
}
