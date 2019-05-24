package br.com.luiz.redissample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import br.com.luiz.redissample.entity.Task;

/**
 * build: 'mvn install dockerfile:build' push: 'mvn install dockerfile:push'
 * 
 * buildar e fazer push: 'mvn dockerfile:push'
 * 
 * 
 * RUN: 
 * 
 * docker run -d -p 6379:6379 --name redis -i -t redis:3.2.5-alpine
 * docker run -d -p 8080:8080 --link redis:redis -t luizvilelamarques/redis-sample
 * 
 * chrome (local docker tool box): http://192.168.99.100:8080/task/1
 * 
 * @author Luiz
 *
 */
@Configuration
public class RedisConfig {

	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
		connectionFactory.setHostName("redis");
		connectionFactory.setPort(6379);
		return connectionFactory;
	}

	@Bean
	@Autowired
	public RedisTemplate<String, Task> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		RedisTemplate<String, Task> redisTemplate = new RedisTemplate<String, Task>();
		redisTemplate.setConnectionFactory(redisConnectionFactory);
		redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return redisTemplate;
	}
}