package sinassm;

import javax.annotation.Resource;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.entity.Admin;

import redis.clients.jedis.Jedis;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:redis/*.xml")
public class RedisTest {
	@Resource(name="redisTemplate")
	private RedisTemplate rt;
	
	@Test
	public void test() {
		System.out.println(rt+"111111111111111111");
		Admin a = new Admin();
		a.setId(1);
		//Jedis jedis = new Jedis("192.168.1.105",6379);
		//jedis.auth("1415");
		//System.out.println(jedis.get("a"));
		
		rt.opsForValue().set("b", a);
		Admin b=(Admin) rt.opsForValue().get("b");
		System.out.println(b==a);
	}

}
