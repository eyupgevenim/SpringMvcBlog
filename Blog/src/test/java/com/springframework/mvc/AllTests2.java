package com.springframework.mvc;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.springframework.mvc.controllers.*;
import com.springframework.mvc.dao.*;
import com.springframework.mvc.validation.*;

@RunWith(Suite.class)
@SuiteClasses({
		BlogControllerTests.class,
		BlogDaoImplTests.class,
		CategoryDaoImplTests.class,
		EmailValidationTests.class,
		BlogValidationTests.class,
		PostDaoImplTest.class
})
public class AllTests2 {
	
	 	@BeforeClass
	    public static void setUp() {
	        System.out.println("setUp start");
	    }

	    @AfterClass
	    public static void tearDown() {
	        System.out.println("tearing down");
	    }
}
