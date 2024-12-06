package ms_evaluation.ms_evaluation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsEvaluationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsEvaluationApplication.class, args);
	}

}
