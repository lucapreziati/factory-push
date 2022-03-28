package it.test;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class AsyncHelloController {

	private static final Logger logger = LoggerFactory.getLogger(AsyncHelloController.class);

	@Autowired
	private AsyncService asyncService;

	@GetMapping("/async")
	public CompletableFuture<String> index() throws InterruptedException {
		logger.info("entrato");
		return asyncService.getData();
	}

	@GetMapping("/async1")
	public DeferredResult<String> indexDeferred() throws InterruptedException {
		DeferredResult<String> deferredResult = new DeferredResult<String>();

		ForkJoinPool.commonPool().submit(() -> {
			try {
				logger.info("entrato async");
				CompletableFuture<String> result = asyncService.getData();

				while (!result.isDone()) {
					logger.info("entrato async: non completo");
					Thread.sleep(1000L);
				}
				logger.info("entrato risultato settato");
				deferredResult.setResult(result.get());
			} catch (Exception e) {
				deferredResult.setErrorResult(e.getMessage());
				// TODO Auto-generated catch block
			} finally {

			}
		});

		return deferredResult;

	}

	@GetMapping(path = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public SseEmitter handle() {
		SseEmitter emitter = new SseEmitter();
		// Save the emitter somewhere..

		ForkJoinPool.commonPool().submit(() -> {
			try {
				logger.info("entrato events");
				emitter.send("wait data" + " @ " + new Date());
				CompletableFuture<String> result = asyncService.getData();

				while (!result.isDone()) {
					logger.info("entrato events: non completo");
					emitter.send("not yet completed " + " @ " + new Date());
					emitter.send("/not yet completed" + " @ " + new Date());
					Thread.sleep(500L);
				}
				logger.info("entrato risultato settato");
				emitter.send(result.get());
				emitter.complete();
			} catch (Exception e) {
				emitter.completeWithError(e);
//				e.printStackTrace();
			} finally {}
		});

		return emitter;
	}

	private ExecutorService executor = Executors.newCachedThreadPool();

	@GetMapping("/events1")
	public ResponseEntity<ResponseBodyEmitter> handleRbe() throws InterruptedException {
		ResponseBodyEmitter emitter = new ResponseBodyEmitter();
		executor.execute(() -> {
			try {
				emitter.send("vivo");
				CompletableFuture<String> result = asyncService.getData();
//				emitter.send("/rbe" + " @ " + new Date(), MediaType.APPLICATION_JSON);

				emitter.send("/rbe" + " @ " + new Date() + result.get(), MediaType.TEXT_PLAIN);
				throw new RuntimeException("MORTTOOO");
//				emitter.complete();
			} catch (Exception ex) {
				emitter.completeWithError(ex);
			}
		});
		return new ResponseEntity(emitter, HttpStatus.OK);

		// In some other thread
	}
}