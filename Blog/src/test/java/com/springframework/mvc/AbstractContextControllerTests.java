package com.springframework.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
									"file:src/main/webapp/WEB-INF/spring/root-context.xml"})

//"file:src/main/webapp/WEB-INF/spring/spring-security.xml
public class AbstractContextControllerTests {

	@Autowired
	protected WebApplicationContext wac;

	public void tearDown() {
		this.tearDown();
	}
}
