package ms_totalcost.ms_totalcost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients

public class MsTotalcostApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsTotalcostApplication.class, args);
	}

}
