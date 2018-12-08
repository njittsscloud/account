package com.tss.account.test;

import com.tss.basic.common.util.JsonUtil;
import com.tss.basic.site.user.annotation.StudentUser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testSetValue() {
		StudentUser studentUser = new StudentUser();
		studentUser.setStudentId(1);
		redisTemplate.opsForValue().set("202141524", studentUser);
	}

	@Test
	public void testGetValue() {
		Object studentUser = redisTemplate.opsForValue().get("202141524");
		System.out.println(studentUser);
	}

}
