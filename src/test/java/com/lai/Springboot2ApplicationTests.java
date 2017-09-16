package com.lai;

import com.lai.dao.UserRepository;
import com.lai.domain.secondary.MessageRepository;
import com.lai.model.User;
import com.lai.model.secondary.Message;
import com.lai.web.UserController;
import javafx.application.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class Springboot2ApplicationTests {

	@Test
	public void contextLoads() {
	}

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception{
		//mvc= MockMvcBuilders.standaloneSetup(new UserController()).build();
	}

	//单数据源的测试用例
//	@Autowired
//	private UserRepository userRepository;
//
//	@Test
//	public void testUserController() throws Exception{
//		RequestBuilder request=null;
//		request=get("/users/");
//		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));
//
//		request=post("/users/").param("id","1").param("name","测试").param("age","23");
//		mvc.perform(request).andExpect(content().string(equalTo("success")));
//
//		request=get("/users/");
//		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试\",\"age\":20}]")));
//
//		request=put("/users/1").param("name","开发").param("age","30");
//		mvc.perform(request).andExpect(content().string(equalTo("success")));
//
//		request=get("/users/1");
//		mvc.perform(request).andExpect(content().string(equalTo("{\"id\":1,\"name\":\"开发\",\"age\":30}")));
//
//		request=delete("/users/1");
//		mvc.perform(request).andExpect(content().string(equalTo("success")));
//
//		request=get("/users/");
//		mvc.perform(request).andExpect(status().isOk()).andExpect(content().string(equalTo("[]")));

		//创建10条数据
//		userRepository.save(new User("AAA",10));
//		userRepository.save(new User("BBB", 20));
//		userRepository.save(new User("CCC", 30));
//		userRepository.save(new User("DDD", 40));
//		userRepository.save(new User("EEE", 50));
//		userRepository.save(new User("FFF", 60));
//		userRepository.save(new User("GGG", 70));
//		userRepository.save(new User("HHH", 80));
//		userRepository.save(new User("III", 90));
//		userRepository.save(new User("JJJ", 100));
//
//		Assert.assertEquals(10, userRepository.findAll().size());
//		Assert.assertEquals(60, userRepository.findByName("FFF").getAge().longValue());
//		Assert.assertEquals(60,userRepository.findUser("FFF").getAge().longValue());
//		Assert.assertEquals("FFF",userRepository.findByNameAndAge("FFF", 60).getName());
//		userRepository.delete(userRepository.findByName("AAA"));
//		Assert.assertEquals(9,userRepository.findAll().size());
//	}

	//针对JdbcTmplate 原始的数据操作多数据源的测试用例
	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate1;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate2;

	@Before
	public void setUp2(){
		jdbcTemplate1.update("DELETE FROM j_user ");
		jdbcTemplate2.update("DELETE FROM j_user ");
	}
	@Test
	public void test() throws Exception{
		jdbcTemplate1.update("insert into j_user(name,age) values(?,?)","aaa",20);
		jdbcTemplate1.update("insert into j_user(name,age) values(?,?)","bbb",30);
		jdbcTemplate2.update("insert into j_user(name,age) values(?,?)","aaa",20);

		Assert.assertEquals("2", jdbcTemplate1.queryForObject("select count(1) from j_user", String.class));
		Assert.assertEquals("1",jdbcTemplate2.queryForObject("select count(1) from j_user",String.class));
	}



	//针对Jpa多数据源的测试用例
	@Autowired
	private com.lai.domain.primary.User2Repository userRepository2;

	@Autowired
	private MessageRepository messageRepository;

	@Test
	public void  test3() throws Exception{
		userRepository2.save(new com.lai.model.primary.User("aaa",10));
		userRepository2.save(new com.lai.model.primary.User("bbb",20));
		userRepository2.save(new com.lai.model.primary.User("ccc",30));
		userRepository2.save(new com.lai.model.primary.User("ddd",40));
		userRepository2.save(new com.lai.model.primary.User("eee",50));

		Assert.assertEquals(5, userRepository2.findAll().size());

		messageRepository.save(new Message("01", "aaaaaaaa"));
		messageRepository.save(new Message("02","bbbbbbbbbb"));
		messageRepository.save(new Message("03","cccccccc"));

		Assert.assertEquals(3,messageRepository.findAll().size());
	}

}
