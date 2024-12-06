package ms_solicitud.ms_solicitud.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ms_solicitud.ms_solicitud.models.SimulationEntity;

@FeignClient(name = "ms-simulation", path = "api/v1/simulation")
public interface SimulationFeignClient {

    @PostMapping("/aplication-simulation")
    Double simulateLoan(@RequestBody SimulationEntity simulation);
}
