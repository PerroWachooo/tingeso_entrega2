package ms_tracking.ms_tracking.services;

import ms_tracking.ms_tracking.models.LoanAplicationEntity;
import org.springframework.stereotype.Service;
import ms_tracking.ms_tracking.clients.LoanAplicationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;

@Service
public class trackingService {
    @Autowired
    LoanAplicationFeignClient loanAplicationFeignClient;

    public ArrayList<LoanAplicationEntity> getLoans() {
        return (ArrayList<LoanAplicationEntity>) loanAplicationFeignClient.getLoans();
    }

}
