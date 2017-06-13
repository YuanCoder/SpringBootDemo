package com.yuan.demo;

import com.yuan.demo.message.Receiver;
import com.yuan.demo.storage.StorageProperties;
import com.yuan.demo.storage.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

import static com.sun.xml.internal.ws.spi.db.BindingContextFactory.LOGGER;

/**
 * ① 利用@EnableAsync注解开启异步任务支持。
 ② 配置类实现AsyncConfigurer接口并重写getAsyncExecutor方法，并返回ThreadPoolTaskExecutor ，这样就获得了一个基于线程池的TaskExecutor。
 */
@SpringBootApplication
@EnableScheduling //开启调度任务
@EnableAsync //开启异步任务；并且配置AsyncConfigurerSupport，比如最大的线程池为2

@EnableConfigurationProperties(StorageProperties.class)
public class SpringBootDemoApplication extends AsyncConfigurerSupport {

	public static void main(String[] args) throws InterruptedException {

		//springboot中用redis实现消息队列
		ApplicationContext ctx =  SpringApplication.run(SpringBootDemoApplication.class, args);
		//先用redisTemplate发送一条消息，接收者接收到后，打印出来
		StringRedisTemplate template = ctx.getBean(StringRedisTemplate.class);
		CountDownLatch latch = ctx.getBean(CountDownLatch.class);

		LOGGER.info("Sending message...");
		template.convertAndSend("chat", "Hello from Redis!");

		latch.await();

// 		System.exit(0);//exit(0)方法把内存都释放了，也就是说连JVM都关闭了，内存里根本不可能还有什么东西    而System.exit(1)或者说非0表示非正常退出程序
	}

	/*在spring data redis中，利用redis发送一条消息和接受一条消息，需要三样东西：

	1 一个连接工厂
	2 一个消息监听容器
	3 Redis template
	*/

	//注入消息接收者    连接工厂
	@Bean
	Receiver receiver(CountDownLatch latch) {
		return new Receiver(latch);
	}

	@Bean
	CountDownLatch latch() {
		return new CountDownLatch(1);
	}

	//Redis template
	@Bean
	StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}


	//注入消息监听容器
	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("chat"));

		return container;
	}

	@Bean
	MessageListenerAdapter listenerAdapter(Receiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
	}

	/**
	 * 上传下载文件  初始化
	 * @param storageService
	 * @return
     */
	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}


	//开启异步任务
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}
}
