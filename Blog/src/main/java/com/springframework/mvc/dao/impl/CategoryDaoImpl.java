package com.springframework.mvc.dao.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.springframework.mvc.dao.CategoryDao;

@Component
public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
}
	
	
	public Map<String, Map<String, String>> findAllCategory(){
			
			jdbcTemplate = new JdbcTemplate(dataSource);
	        String sql = "SELECT * FROM categories";
	        Map<String, Map<String, String>> data = new HashMap<String, Map<String, String>>();
	        Map<String,String> category = new LinkedHashMap<String,String>();
	        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
	        for (Map<String, Object> row : rows) {
	        	category.put(String.valueOf(row.get("Id")),(String)row.get("CategoryName"));
	        }
	        
			data.put("category",category);
			return data;
		}

}
