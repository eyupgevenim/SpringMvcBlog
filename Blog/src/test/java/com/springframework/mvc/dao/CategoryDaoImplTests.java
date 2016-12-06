package com.springframework.mvc.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springframework.mvc.AbstractContextControllerTests;

@RunWith(SpringJUnit4ClassRunner.class)
public class CategoryDaoImplTests extends AbstractContextControllerTests {
	
	@Autowired
    private CategoryDao categoryDAO;
	
	@Test
	public void testFindAllCategory(){
		
		 Map<String, Map<String, String>> category = new HashMap<String, Map<String, String>>();
		category = categoryDAO.findAllCategory();
		assertNotNull(category);
		assertTrue(category.get("category").size() >= 4);
	}
	
}
