package ms_solicitud.ms_solicitud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsSolicitudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsSolicitudApplication.class, args);
	}

}
