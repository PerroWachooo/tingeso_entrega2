package ms_totalcost.ms_totalcost.clients;

import ms_totalcost.ms_totalcost.models.LoanAplicationEntity;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "ms-totalcost", path = "api/v1/loanaplication")
public interface LoanAplicationFeignClient {

    @GetMapping("/")
    List<LoanAplicationEntity> getLoans();

    @GetMapping("calculateTotalCost/{id}")
    ResponseEntity<Double> calculateTotalCost(@PathVariable Long id);
}
