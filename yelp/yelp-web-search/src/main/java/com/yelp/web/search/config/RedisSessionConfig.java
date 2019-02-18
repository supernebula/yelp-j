package com.yelp.web.search.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;
import org.springframework.session.web.http.DefaultCookieSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.io.Serializable;

@Configuration
@EnableCaching
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1800) // 30分钟
public class RedisSessionConfig {

	@Value("${spring.redis.host}")
	private String host;
	@Value("${spring.redis.port}")
	private int port;
	@Value("${spring.redis.password}")
	private String password;
	@Value("${spring.redis.timeout}")
	private int timeout;
	@Value("${app.env}")
	private String env;
	@Autowired
	private RedisHttpSessionConfiguration redisHttpSessionConfiguration;

	/*
	 * @Autowired private CacheKeyMessageListener cacheKeyMessageListener;
	 */

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory JedisConnectionFactory = new JedisConnectionFactory();
		JedisConnectionFactory.setHostName(host);
		JedisConnectionFactory.setPassword(password);
		JedisConnectionFactory.setPort(port);
		JedisConnectionFactory.setTimeout(timeout);

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setTestOnBorrow(true); // 获取连接的时候检查有效性
		poolConfig.setTestWhileIdle(false);// 空闲时检查有效性
		poolConfig.setTimeBetweenEvictionRunsMillis(-1l);// 逐出扫描的时间间隔（毫秒）
		poolConfig.setNumTestsPerEvictionRun(3); // 每次连接断开检查时，断开的最大数目
		poolConfig.setMinEvictableIdleTimeMillis(1000L * 300L);// 断开连接的最小空闲时间（毫秒）
		poolConfig.setBlockWhenExhausted(true);// 连接耗尽时是否阻塞,
												// false：报异常，ture：阻塞直到超时
		// 获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,默认-1
		poolConfig.setMaxWaitMillis(100);

		// 最大连接数, 默认8个
		poolConfig.setMaxTotal(300);
		// 最大空闲连接数, 默认8个
		poolConfig.setMaxIdle(8);

		poolConfig.setJmxEnabled(false);
		poolConfig.setLifo(true);

		JedisConnectionFactory.setPoolConfig(poolConfig);

		return JedisConnectionFactory;
	}

	@Bean
	public RedisTemplate<String, Serializable> redisTemplate(final RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Serializable> redisTemplate = new RedisTemplate<String, Serializable>();
		redisTemplate.setConnectionFactory(connectionFactory);
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new StringRedisSerializer());
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}

	@Bean
	public CacheManager cacheManager(final RedisTemplate<String, Serializable> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		cacheManager.setDefaultExpiration(60 * 30);

		return cacheManager;
	}

	@Bean
	public DefaultCookieSerializer defaultCookieSerializer() {

		DefaultCookieSerializer defaultCookieSerializer = new DefaultCookieSerializer();
//		defaultCookieSerializer.setCookieName("TESTDIANGUSESSIONID");
//		defaultCookieSerializer.setCookiePath("/");
////		defaultCookieSerializer.setDomainName("110.110.6.210");
//		 defaultCookieSerializer.setDomainName("test.yelp.com");
//		if (env.equals("dev")) {
//			// defaultCookieSerializer.setDomainName("110.110.6.200");
//			defaultCookieSerializer.setDomainName("127.0.0.1");
//			// defaultCookieSerializer.setDomainName("localhost");
//			// defaultCookieSerializer.setDomainName("IISDdwedwsiDWEdwD");
//		} else if (env.equals("prod")) {
//			defaultCookieSerializer.setCookieName("YELP_SESSIONID");
//			defaultCookieSerializer.setDomainName("yelp.com");
//		} else if(env.equals("online_test")) {
//			defaultCookieSerializer.setCookieName("YELP_SESSIONID");
//			defaultCookieSerializer.setDomainName("yelp.com");
//		}
		return defaultCookieSerializer;
	}

	@Bean
	public RedisHttpSessionConfiguration redisHttpSessionConfiguration() {
		redisHttpSessionConfiguration.setCookieSerializer(defaultCookieSerializer());
		return redisHttpSessionConfiguration;

	}

}
