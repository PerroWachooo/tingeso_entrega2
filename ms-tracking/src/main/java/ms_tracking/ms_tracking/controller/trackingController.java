package ms_tracking.ms_tracking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import ms_tracking.ms_tracking.services.trackingService;
import ms_tracking.ms_tracking.models.LoanAplicationEntity;

@RestController
@RequestMapping("/api/v1/tracking")
@CrossOrigin("*")
public class trackingController {

    @Autowired
    trackingService trackingService;

    @GetMapping("/")
    public ResponseEntity<List<LoanAplicationEntity>> listAllAplications() {
        List<LoanAplicationEntity> loansAplications = trackingService.getLoans();
        return ResponseEntity.ok(loansAplications);
    }

}
