package com.electronicvoting;


import com.electronicvoting.helper.ApplicationContextProvider;
import com.electronicvoting.helper.ApplicationContextProviderStatic;
import com.electronicvoting.timer.VotingSessionCheckTimer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Timer;

@SpringBootApplication
@Slf4j
public class BackendApplication {
	@Autowired
	private ApplicationContextProviderStatic applicationContextProviderStatic;
	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		Timer t = new Timer();
		t.scheduleAtFixedRate(ApplicationContextProviderStatic.getApplicationContext().getBean(VotingSessionCheckTimer.class), 0, 30000);
		System.out.println("sakjhgdkjshgfjdshgfjk");

	}
}
