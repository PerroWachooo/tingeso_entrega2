package ms_totalcost.ms_totalcost.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import ms_totalcost.ms_totalcost.models.LoanAplicationEntity;
import ms_totalcost.ms_totalcost.clients.LoanAplicationFeignClient;

@Service
public class totalcostService {

    @Autowired
    LoanAplicationFeignClient loanAplicationFeignClient;

    public Double calculateTotalCost(Long id) {
        return loanAplicationFeignClient.calculateTotalCost(id).getBody();
    }

}
