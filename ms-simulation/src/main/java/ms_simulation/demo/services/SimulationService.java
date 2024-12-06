package ms_simulation.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.lang.Math.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.math.RoundingMode;
import static java.lang.Math.round;

@Service
public class SimulationService {

    // P1
    // Simulation of credit
    // p: monto del prestamo
    // n: Plazo del prestamo
    // r: tasa de intereses
    public double monthlyPaymentCal(int p, int n, float r) {
        int monthlypay = n * 12; // Número de meses
        float monthlyfee = r / 12 / 100; // Tasa de interés mensual

        double fracUp = monthlyfee * Math.pow(1 + monthlyfee, monthlypay);
        double fracDown = Math.pow(1 + monthlyfee, monthlypay) - 1;

        double m = p * (fracUp / fracDown);

        // Redondear el resultado a dos decimales
        BigDecimal bd = new BigDecimal(m).setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}