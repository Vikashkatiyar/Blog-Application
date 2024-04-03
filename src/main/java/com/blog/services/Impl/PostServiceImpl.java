package com.blog.services.Impl;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.blog.entities.Category;
import com.blog.entities.Post;
import com.blog.entities.User;
import com.blog.exceptions.ResourceNotFoundException;
import com.blog.payloads.PostDto;
import com.blog.payloads.PostResponse;
import com.blog.repositories.ICategoryRepo;
import com.blog.repositories.IPostRepo;
import com.blog.repositories.IUserRepo;
import com.blog.services.IPostService;

@Service
public class PostServiceImpl implements IPostService{
    
	@Autowired
	private IPostRepo  postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IUserRepo userRepo;
	
	@Autowired
	private  ICategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","Category Id", categoryId));
		
		
		 Post post = modelMapper.map(postDto,Post.class);
		 post.setImageName("default.png");
		 post.setAddedDate(new Date());
		 post.setUser(user);
		 post.setCategory(category);
		 
		 Post createdPost = postRepo.save(post);
		return modelMapper.map(createdPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id",postId));
		
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = postRepo.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "Post Id",postId));
		postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
		Pageable p = PageRequest.of(pageNumber, pageSize);
	    Page<Post> pagePost = postRepo.findAll(p);
		List<Post> allPosts = pagePost.getContent();
		
//		List<Post> allPosts = postRepo.findAll();
		List<PostDto> postDtos = allPosts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		
		PostResponse postResponse=new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		
		return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Post Id", postId));
		return modelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) {
	    Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)-> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	    return postDtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) {
		User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "User Id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<Post> searchPosts(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
