package com.teamwatchers.buddybuddyclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"com.teamwatchers.buddybuddyclone.*"})
public class BuddyBuddyCloneApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuddyBuddyCloneApplication.class, args);
	}
}
