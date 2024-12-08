package ms_solicitud.ms_solicitud.controller;

import ms_solicitud.ms_solicitud.entities.LoanAplicationEntity;
import ms_solicitud.ms_solicitud.models.SimulationEntity;
import ms_solicitud.ms_solicitud.services.LoanAplicationService;
import ms_solicitud.ms_solicitud.clients.SimulationFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("api/v1/loanaplication")
@CrossOrigin("*")

public class LoanAplicationController {
    @Autowired
    LoanAplicationService loanAplicationService;
    @Autowired
    SimulationFeignClient simulationFeignClient;

    @GetMapping("/")
    public ResponseEntity<List<LoanAplicationEntity>> listAllAplications() {
        List<LoanAplicationEntity> loansAplications = loanAplicationService.getLoans();
        return ResponseEntity.ok(loansAplications);
    }

    @GetMapping("/by-user/{rut}")
    public ResponseEntity<List<LoanAplicationEntity>> listAplicationsByRut(@PathVariable String rut) {
        List<LoanAplicationEntity> loansAplications = loanAplicationService.getLoansByRut(rut);
        return ResponseEntity.ok(loansAplications);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<LoanAplicationEntity> getLoanAplicationById(@PathVariable Long id) {
        LoanAplicationEntity loan = loanAplicationService.getLoanAplicationById(id)
                .orElseThrow(() -> new ResourceAccessException("Loan application not found"));
        return ResponseEntity.ok(loan);
    }

    @PostMapping("/")
    public ResponseEntity<LoanAplicationEntity> registerLoanAplication(
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
            @RequestParam(name = "certificadoAvaluoActualizado", required = false) MultipartFile certificadoAvaluoActualizado) {

        try {
            // Crear una nueva instancia de LoanAplicactionEntity y asignar los campos
            LoanAplicationEntity loanAplicaction = new LoanAplicationEntity();
            loanAplicaction.setRutUser(rutUser);
            loanAplicaction.setState(state);
            loanAplicaction.setAmount(amount);
            loanAplicaction.setAnualInterestRate(anualInterestRate);
            loanAplicaction.setTerm(term);
            loanAplicaction.setLoan_type(loan_type);
            loanAplicaction.setFee(fee);
            loanAplicaction.setCreditInsuarance(creditInsurance);
            loanAplicaction.setMonthlyFireInsurance(monthlyFireInsurance);
            loanAplicaction.setAdministrationCommission(administrationCommission);
            loanAplicaction.setPropertyValue(propertyValue);
            loanAplicaction.setConsistentSaveCheck(consistentSaveCheck);
            loanAplicaction.setPeriodicDepositsCheck(periodicDepositsCheck);
            loanAplicaction.setRecentWithdrawCheck(recentWithdrawCheck);
            loanAplicaction.setSave_capacity(saveCapacity);

            // Convertir archivos a byte[] si no son nulos
            if (income_file != null && !income_file.isEmpty()) {
                loanAplicaction.setIncome_file(income_file.getBytes());
            }
            if (certificadoAvaluo != null && !certificadoAvaluo.isEmpty()) {
                loanAplicaction.setCertificadoAvaluo(certificadoAvaluo.getBytes());
            }
            if (historialCrediticio != null && !historialCrediticio.isEmpty()) {
                loanAplicaction.setHistorialCrediticio(historialCrediticio.getBytes());
            }
            if (escrituraPrimeraVivienda != null && !escrituraPrimeraVivienda.isEmpty()) {
                loanAplicaction.setEscrituraPrimeraVivienda(escrituraPrimeraVivienda.getBytes());
            }
            if (estadoFinancieroNegocio != null && !estadoFinancieroNegocio.isEmpty()) {
                loanAplicaction.setEstadoFinancieroNegocio(estadoFinancieroNegocio.getBytes());
            }
            if (planNegocios != null && !planNegocios.isEmpty()) {
                loanAplicaction.setPlanNegocios(planNegocios.getBytes());
            }
            if (presupuestoRemodelacion != null && !presupuestoRemodelacion.isEmpty()) {
                loanAplicaction.setPresupuestoRemodelacion(presupuestoRemodelacion.getBytes());
            }
            if (certificadoAvaluoActualizado != null && !certificadoAvaluoActualizado.isEmpty()) {
                loanAplicaction.setCertificadoAvaluoActualizado(certificadoAvaluoActualizado.getBytes());
            }

            // Guardar la entidad en la base de datos
            LoanAplicationEntity newLoanAplication = loanAplicationService.saveLoan(loanAplicaction);
            return ResponseEntity.ok(newLoanAplication);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar archivos", e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LoanAplicationEntity> updateLoanAplication(
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
            @RequestParam(name = "certificadoAvaluoActualizado", required = false) MultipartFile certificadoAvaluoActualizado) {

        try {
            // Buscar la entidad por su ID y lanzar una excepción si no existe
            LoanAplicationEntity loanAplicaction = loanAplicationService.getLoanAplicationById(id)
                    .orElseThrow(() -> new ResourceAccessException("Loan application not found"));

            // Actualizar los campos de la entidad
            loanAplicaction.setRutUser(rutUser);
            loanAplicaction.setState(state);
            loanAplicaction.setAmount(amount);
            loanAplicaction.setAnualInterestRate(anualInterestRate);
            loanAplicaction.setTerm(term);
            loanAplicaction.setLoan_type(loan_type);
            loanAplicaction.setFee(fee);
            loanAplicaction.setCreditInsuarance(creditInsurance);
            loanAplicaction.setMonthlyFireInsurance(monthlyFireInsurance);
            loanAplicaction.setAdministrationCommission(administrationCommission);
            loanAplicaction.setPropertyValue(propertyValue);
            loanAplicaction.setConsistentSaveCheck(consistentSaveCheck);
            loanAplicaction.setPeriodicDepositsCheck(periodicDepositsCheck);
            loanAplicaction.setRecentWithdrawCheck(recentWithdrawCheck);
            loanAplicaction.setSave_capacity(saveCapacity);

            // Convertir archivos a byte[] si no son nulos
            if (income_file != null && !income_file.isEmpty()) {
                loanAplicaction.setIncome_file(income_file.getBytes());
            }
            if (certificadoAvaluo != null && !certificadoAvaluo.isEmpty()) {
                loanAplicaction.setCertificadoAvaluo(certificadoAvaluo.getBytes());
            }
            if (historialCrediticio != null && !historialCrediticio.isEmpty()) {
                loanAplicaction.setHistorialCrediticio(historialCrediticio.getBytes());
            }
            if (escrituraPrimeraVivienda != null && !escrituraPrimeraVivienda.isEmpty()) {
                loanAplicaction.setEscrituraPrimeraVivienda(escrituraPrimeraVivienda.getBytes());
            }
            if (estadoFinancieroNegocio != null && !estadoFinancieroNegocio.isEmpty()) {
                loanAplicaction.setEstadoFinancieroNegocio(estadoFinancieroNegocio.getBytes());
            }
            if (planNegocios != null && !planNegocios.isEmpty()) {
                loanAplicaction.setPlanNegocios(planNegocios.getBytes());
            }
            if (presupuestoRemodelacion != null && !presupuestoRemodelacion.isEmpty()) {
                loanAplicaction.setPresupuestoRemodelacion(presupuestoRemodelacion.getBytes());
            }
            if (certificadoAvaluoActualizado != null && !certificadoAvaluoActualizado.isEmpty()) {
                loanAplicaction.setCertificadoAvaluoActualizado(certificadoAvaluoActualizado.getBytes());
            }

            // Guardar la entidad actualizada en la base de datos
            LoanAplicationEntity updatedLoanAplication = loanAplicationService.saveLoan(loanAplicaction);
            return ResponseEntity.ok(updatedLoanAplication);
        } catch (IOException e) {
            throw new RuntimeException("Error al procesar archivos", e);
        }
    }

    @PostMapping("/calculateFee")
    public ResponseEntity<Double> calcualteFee(@RequestBody SimulationEntity loanAplicaction) {
        double result = simulationFeignClient.simulateLoan(loanAplicaction);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/calculateTotalCost/{id}")
    public ResponseEntity<Double> calculateTotalCost(@PathVariable Long id) {
        return loanAplicationService.getLoanAplicationById(id)
                .map(loanApplication -> {
                    double totalCost = loanAplicationService.totalCostCal(loanApplication);
                    return ResponseEntity.ok(totalCost);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteUserById(@PathVariable Long id) throws Exception {
        loanAplicationService.deleteLoan(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<String> uploadFiles(
            @PathVariable Long id,
            @RequestParam(name = "income_file", required = false) MultipartFile income_file,
            @RequestParam(name = "certificadoAvaluo", required = false) MultipartFile certificadoAvaluo,
            @RequestParam(name = "historialCrediticio", required = false) MultipartFile historialCrediticio,
            @RequestParam(name = "escrituraPrimeraVivienda", required = false) MultipartFile escrituraPrimeraVivienda,
            @RequestParam(name = "estadoFinancieroNegocio", required = false) MultipartFile estadoFinancieroNegocio,
            @RequestParam(name = "planNegocios", required = false) MultipartFile planNegocios,
            @RequestParam(name = "presupuestoRemodelacion", required = false) MultipartFile presupuestoRemodelacion,
            @RequestParam(name = "certificadoAvaluoActualizado", required = false) MultipartFile certificadoAvaluoActualizado) {

        try {

            // Buscar la entidad por su ID y lanzar una excepción si no existe
            LoanAplicationEntity loanApplication = loanAplicationService.getLoanAplicationById(id)
                    .orElseThrow(() -> new ResourceAccessException("Loan application not found"));

            // Convertir cada archivo a byte[] y asignarlo a los atributos si no son nulos
            if (income_file != null && !income_file.isEmpty()) {
                loanApplication.setIncome_file(income_file.getBytes());
                System.out.println(income_file);
            } else {
                System.out.println("NO PASOOOO NULLL");
                ;
            }
            if (certificadoAvaluo != null && !certificadoAvaluo.isEmpty()) {
                loanApplication.setCertificadoAvaluo(certificadoAvaluo.getBytes());
            }
            if (historialCrediticio != null && !historialCrediticio.isEmpty()) {
                loanApplication.setHistorialCrediticio(historialCrediticio.getBytes());
            }
            if (escrituraPrimeraVivienda != null && !escrituraPrimeraVivienda.isEmpty()) {
                loanApplication.setEscrituraPrimeraVivienda(escrituraPrimeraVivienda.getBytes());
            }
            if (estadoFinancieroNegocio != null && !estadoFinancieroNegocio.isEmpty()) {
                loanApplication.setEstadoFinancieroNegocio(estadoFinancieroNegocio.getBytes());
            }
            if (planNegocios != null && !planNegocios.isEmpty()) {
                loanApplication.setPlanNegocios(planNegocios.getBytes());
            }
            if (presupuestoRemodelacion != null && !presupuestoRemodelacion.isEmpty()) {
                loanApplication.setPresupuestoRemodelacion(presupuestoRemodelacion.getBytes());
            }
            if (certificadoAvaluoActualizado != null && !certificadoAvaluoActualizado.isEmpty()) {
                loanApplication.setCertificadoAvaluoActualizado(certificadoAvaluoActualizado.getBytes());
            }

            // Guardar los cambios en la base de datos
            loanAplicationService.saveLoan(loanApplication);

            return ResponseEntity.ok("Files uploaded successfully");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
