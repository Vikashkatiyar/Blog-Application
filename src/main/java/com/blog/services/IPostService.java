package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface IPostService {
		
//	create post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
//	update post
	Post updatePost(PostDto postDto, Integer postId);
	
//	delete
	void deletePost(Integer postId);
	
//	get all posts
	List<Post> getAllPost();
	
//	get Single post by Id
	Post getPostById(Integer postId);
	
//	get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
//	get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);
	
	
}
