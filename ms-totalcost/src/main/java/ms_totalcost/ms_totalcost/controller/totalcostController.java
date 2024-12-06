package ms_totalcost.ms_totalcost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ms_totalcost.ms_totalcost.services.totalcostService;

public class totalcostController {

    @Autowired
    totalcostService totalcostService;

    @GetMapping("calculateTotalCost/{id}")
    public ResponseEntity<Double> calculateTotalCost(@PathVariable Long id) {
        return ResponseEntity.ok(totalcostService.calculateTotalCost(id));
    }

}
