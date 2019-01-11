package com.haiyu;

import com.haiyu.entity.Seckill;
import com.haiyu.mapper.SeckillMapper;
import com.haiyu.mapper.SeckillOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootSeckillApplicationTests {

	@Autowired
	private SeckillMapper seckillMapper;

	@Autowired
	private SeckillOrderMapper seckillOrderMapper;

	@Test
	public void contextLoads() {
	}

	@Test
	public void test() {
		List<Seckill> seckills = seckillMapper.findAll();
		System.out.println(seckills);

	}

	@Test
	public void test2() {
		Long id = 1000L;
		Long phone = 13215638015L;
		BigDecimal money = new BigDecimal(100);
		int result = seckillOrderMapper.insertOrder(id,money,phone);
		System.out.println(result);

	}
}

