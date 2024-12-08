package ms_tracking.ms_tracking.clients;

import ms_tracking.ms_tracking.models.LoanAplicationEntity;

import java.util.List;

import ms_tracking.ms_tracking.configurations.FeignClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "ms-solicitud", path = "api/v1/loanaplication", configuration = { FeignClientConfig.class })
public interface LoanAplicationFeignClient {

    @GetMapping("/")
    List<LoanAplicationEntity> getLoans();

    @PutMapping("/{id}")
    ResponseEntity<LoanAplicationEntity> updateLoanAplication(
            @PathVariable Long id,
            @RequestParam(name = "rutUser") String rutUser,
            @RequestParam(name = "state") String state,
            @RequestParam(name = "amount") int amount,
            @RequestParam(name = "anualInterestRate") float anualInterestRate,
            @RequestParam(name = "term") Integer term,
            @RequestParam(name = "loan_type") String loan_type,
            @RequestParam(name = "fee") Double fee,
            @RequestParam(name = "creditInsurance", required = false) Double creditInsurance,
            @RequestParam(name = "monthlyFireInsurance", required = false) Double monthlyFireInsurance,
            @RequestParam(name = "administrationCommission", required = false) Double administrationCommission,
            @RequestParam(name = "propertyValue", required = false) Double propertyValue,
            @RequestParam(name = "consistentSaveCheck", required = false) Boolean consistentSaveCheck,
            @RequestParam(name = "periodicDepositsCheck", required = false) Boolean periodicDepositsCheck,
            @RequestParam(name = "recentWithdrawCheck", required = false) Boolean recentWithdrawCheck,
            @RequestParam(name = "saveCapacity", required = false) String saveCapacity,
            @RequestParam(name = "income_file", required = false) MultipartFile income_file,
            @RequestParam(name = "certificadoAvaluo", required = false) MultipartFile certificadoAvaluo,
            @RequestParam(name = "historialCrediticio", required = false) MultipartFile historialCrediticio,
            @RequestParam(name = "escrituraPrimeraVivienda", required = false) MultipartFile escrituraPrimeraVivienda,
            @RequestParam(name = "estadoFinancieroNegocio", required = false) MultipartFile estadoFinancieroNegocio,
            @RequestParam(name = "planNegocios", required = false) MultipartFile planNegocios,
            @RequestParam(name = "presupuestoRemodelacion", required = false) MultipartFile presupuestoRemodelacion,
            @RequestParam(name = "certificadoAvaluoActualizado", required = false) MultipartFile certificadoAvaluoActualizado);

    @DeleteMapping("/{id}")
    ResponseEntity<Boolean> deleteLoanAplication(@PathVariable Long id);

}
