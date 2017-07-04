package com.sunpal.mock;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
	
	private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // http://localhost:8080/greeting?name=User
    // http://localhost:8080/greeting
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }

    // http://localhost:8080/mocking?name=User&age=21
    @RequestMapping("/mocking")
    public Greeting mocking(
    		@RequestParam(value="name", defaultValue="World") String name, 
    		@RequestParam(value="age", defaultValue="20") String age) {
        return new Greeting(counter.incrementAndGet(),
                            String.format("%s, %s", name + "$", age + "$$"));
    }
}
