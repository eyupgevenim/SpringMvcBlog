package com.springframework.mvc.dao;

import java.util.Map;

public interface CategoryDao {
	
	Map<String, Map<String, String>> findAllCategory();
}
