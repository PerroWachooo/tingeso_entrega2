package ms_simulation.demo.controller;

import ms_simulation.demo.entities.SimulationLoanEntity;
import ms_simulation.demo.services.SimulationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/simulation")
@CrossOrigin("*")

public class SimulationController {

    @Autowired
    SimulationService simulationService;

    @PostMapping("/aplication-simulation")
    public ResponseEntity<Double> simulateLoan(@RequestBody SimulationLoanEntity simulation) {
        double result = simulationService.monthlyPaymentCal(simulation.getAmount(), simulation.getTerm(),
                simulation.getAnualInterestRate());
        return ResponseEntity.ok(result);
    }

}
