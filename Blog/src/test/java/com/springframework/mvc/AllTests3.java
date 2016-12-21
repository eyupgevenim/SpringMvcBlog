package com.springframework.mvc;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.springframework.mvc.controllers.*;
import com.springframework.mvc.dao.*;
import com.springframework.mvc.validation.*;

@RunWith(Suite.class)
@SuiteClasses({
		HomeControllerTests.class,
		BlogControllerTests.class,
		BlogValidationTests.class,
		BlogDaoImplTests.class,
		CategoryDaoImplTests.class,
		EmailValidationTests.class,
		UserValidationTests.class,
		PostDaoImplTest.class
})
public class AllTests3 {

}
