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
		AccountControllerTests.class,
		BlogControllerTests.class,
		HomeControllerTests.class,
		UserControllerTests.class,
		BlogDaoImplTests.class,
		CategoryDaoImplTests.class,
		UserDaoImplTests.class,
		EmailValidationTests.class,
		BlogValidationTests.class,
		UserValidationTests.class
})
public class AllTests1 {
	
	 	@BeforeClass
	    public static void setUp() {
	        System.out.println("setUp start");
	    }

	    @AfterClass
	    public static void tearDown() {
	        System.out.println("tearing down");
	    }
}
