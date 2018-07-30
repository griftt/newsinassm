package sinassm;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.redis.RedisDao;

import redis.clients.jedis.Jedis;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis/*.xml")
public class RedisTest {
	@Resource(name="redisDao")
	private RedisDao rd;
	
	@Test
	public void test() {
		System.err.println("1111111111111111111111111");
		System.err.println(rd.getJedisPool().getResource());
		//User u = um.selectById(1);
		//System.out.println(u);
		
	}

}
