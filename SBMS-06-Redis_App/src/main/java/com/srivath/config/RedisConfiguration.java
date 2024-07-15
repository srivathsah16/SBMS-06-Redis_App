package com.srivath.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.srivath.databinding.Country;

@Configuration
public class RedisConfiguration {

	@Bean
	public JedisConnectionFactory getJedisConFac() {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		// set the connection properties of redis server - needed when REDIS server is
		// running on different machine.
		// If the REDIS server is running in the same machine, it is not needed.
		return factory;
	}

	@Bean
	public RedisTemplate<String, Country> getRedisTemplate() {
		RedisTemplate<String, Country> template = new RedisTemplate<>();
		template.setConnectionFactory(getJedisConFac());
		return template;
	}
}
