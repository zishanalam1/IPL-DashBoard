package com.example.IPLDataReaderUsingHSql;

import com.example.IPLDataReaderUsingHSql.Services.PushDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.IPLDataReaderUsingHSql")
public class IplDataReaderUsingHSqlApplication {

	public static void main(String[] args) {


		//SpringApplication.run(IplDataReaderUsingHSqlApplication.class, args);
		ConfigurableApplicationContext context = SpringApplication.run(IplDataReaderUsingHSqlApplication.class, args);
		PushDB pushDB = context.getBean(PushDB.class);

		pushDB.processCSVData();
		pushDB.InsertBatsmanRecrd();
		pushDB.InsertBowlingRecord();

	}


}
