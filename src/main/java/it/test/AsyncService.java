package it.test;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {

	@Async
	public CompletableFuture<String> getData() throws InterruptedException {
		// Artificial delay of 1s for demonstration purposes
		Date start = new Date();

		Thread.sleep(5000L);
		return CompletableFuture.completedFuture(" ***** RESULT --> DATA -> Invocation at time " + start.toString());
	}

}
