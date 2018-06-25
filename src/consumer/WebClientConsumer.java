package consumer;

import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;

public class WebClientConsumer {
	public static void main(String[] args) throws InterruptedException {
		Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		root.setLevel(Level.INFO);

		WebClient.create("http://localhost:8080")
		.get().uri("/webflux").accept(MediaType.TEXT_EVENT_STREAM)
		.retrieve()
		.bodyToFlux(String.class)
		.log()
		.blockLast();//Subscribe to this Flux and block indefinitely until the upstream signals its last value or completes.
	}
}
