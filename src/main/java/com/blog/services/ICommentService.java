package com.blog.services;

import com.blog.payloads.CommentDto;

public interface ICommentService {
   
	CommentDto createComment(CommentDto commentDto, Integer postId);
	
	void deleteComment(Integer commentId);
}
