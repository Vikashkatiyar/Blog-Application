package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface IPostService {
		
//	create post
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
//	update post
	PostDto updatePost(PostDto postDto, Integer postId);
	
//	delete
	void deletePost(Integer postId);
	
//	get all posts
	List<PostDto> getAllPost();
	
//	get Single post by Id
	PostDto getPostById(Integer postId);
	
//	get all posts by category
	List<PostDto> getPostsByCategory(Integer categoryId);
	
//	get all posts by user
	List<PostDto> getPostsByUser(Integer userId);
	
	//search posts
	List<Post> searchPosts(String keyword);
	
	
}
