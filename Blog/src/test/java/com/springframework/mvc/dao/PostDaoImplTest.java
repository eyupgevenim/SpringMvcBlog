package com.springframework.mvc.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springframework.mvc.AbstractContextControllerTests;
import com.springframework.mvc.models.Comment;
import com.springframework.mvc.models.Post;

@RunWith(SpringJUnit4ClassRunner.class)
public class PostDaoImplTest extends AbstractContextControllerTests {
	
	@Autowired
    private PostDao postDAO;
	
	
	@Test
	public void testSavePost(){
		Post p = new Post(){
			{
				setPostTite("Test Title");
				setPostContent("this test post content");
				setRequestMapping("Test-Title");
				setBlogMenuId(15);
			}
		};
		
		assertTrue(postDAO.savePost(p));
	}
	
	@Test
	public void testGetPost(){
		Post post = postDAO.getPost("Test-Title");
		assertNotNull(post);
		assertEquals(post.getPostTite(), "Test Title");
	}
	
	
	@Test
	public void testIsThereSameRequestMapping(){
		assertTrue(postDAO.isThereSameRequestMapping("Test-Title"));
		assertFalse(postDAO.isThereSameRequestMapping("Test Title"));
	}
	
	@Test
	public void testGetBlogPosts(){
		List<Post> posts = new ArrayList<Post>();
		posts =  postDAO.getBlogPosts("eyupgevenim");
		assertTrue(posts.size() >= 1);
	}
	
	
	@Test
	public void testGetBlogPostByMenuNameAndBlogUrl(){
		List<Post> posts = new ArrayList<Post>();
		posts =  postDAO.getBlogPosts("Anasayfa","eyupgevenim");
		assertTrue(posts.size() >= 1);
	}
	
	@Test
	public void testGetBlogPostByMenuIdAndBlogId(){
		List<Post> posts = new ArrayList<Post>();
		posts =  postDAO.getBlogPosts(1,3);
		assertTrue(posts.size() >= 1);
	}
	
	@Test
	public void testGetBlogPost(){
		Post post =  postDAO.getBlogPost("Ýletiþim","eyupgevenim");
		assertNotNull(post);
	}
	
	@Test
	public void testGetComents(){
		List<Comment> comments = new ArrayList<Comment>();
		comments =  postDAO.getComents("Test-Title");
		assertTrue(comments.size() == 0);
	}
	
	@Test
	public void testGetBlogComents(){
		List<Comment> comments = new ArrayList<Comment>();
		comments =  postDAO.getBlogComents("eyupgevenim");
		assertTrue(comments.size() >= 1);
	}
	
	
}
