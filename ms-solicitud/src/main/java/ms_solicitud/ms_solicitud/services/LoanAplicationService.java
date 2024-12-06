package ms_solicitud.ms_solicitud.services;

import ms_solicitud.ms_solicitud.models.UserEntity;
import ms_solicitud.ms_solicitud.entities.LoanAplicationEntity;
import ms_solicitud.ms_solicitud.repositories.LoanAplicationRepository;
import ms_solicitud.ms_solicitud.clients.SimulationFeignClient;
import ms_solicitud.ms_solicitud.models.SimulationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class LoanAplicationService {
    @Autowired
    SimulationFeignClient simulationFeignClient;
    @Autowired
    LoanAplicationRepository loanAplicationRepository;

    public ArrayList<LoanAplicationEntity> getLoans() {
        return (ArrayList<LoanAplicationEntity>) loanAplicationRepository.findAll();

    }

    public Optional<LoanAplicationEntity> getLoanAplicationById(Long id) {
        return loanAplicationRepository.findById(id);
    }

    public ArrayList<LoanAplicationEntity> getLoansByRut(String rut) {
        return (ArrayList<LoanAplicationEntity>) loanAplicationRepository.findAllByRutUser(rut);
    }

    public LoanAplicationEntity saveLoan(LoanAplicationEntity loan) {
        return loanAplicationRepository.save(loan);
    }

    public LoanAplicationEntity updateLoan(LoanAplicationEntity loan) {
        return loanAplicationRepository.save(loan);
    }

    public LoanAplicationEntity updateStateLoan(LoanAplicationEntity loan, String newState) {
        loan.setState(newState);
        return loanAplicationRepository.save(loan);
    }

    public boolean deleteLoan(Long id) throws Exception {
        try {
            loanAplicationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            throw new Exception("Delete failed", e);
        }

    }

    public double feeIncomeRelation(LoanAplicationEntity loan, UserEntity user) {
        double fee = loan.getFee();
        int income = user.getIncome();
        return (fee / income) * 100;

    }

    public boolean debtIncomeRelationCheck(LoanAplicationEntity loan, UserEntity user) {
        double totalDebt = user.getDebt() + loan.getFee();
        int halfIncome = user.getIncome() / 2;
        if (halfIncome < totalDebt) {

            System.out.println("Loan Aplication Rejected, Debt Income Relation didnt meet the requirment");
            return false;
        } else {
            return true;
        }
    }

    public boolean ageCheck(UserEntity user) {
        if (user.getAge() >= 70) {
            System.out.println("Loan Aplication Rejected, too old");
            return false;
        } else {
            return true;
        }
    }

    public boolean yearsWorkCheck(UserEntity user) {
        if (user.getWork() != "Independiente" && user.getYears_working() >= 1) {
            return true;

        } else {
            return false; // If the job is indepdent or have less than 1 year working
        }

    }

    // R7.1
    public boolean minBalanceRequiredCheck(UserEntity user, LoanAplicationEntity loan) {
        if (user.getBalanceAccount() >= loan.getAmount() * 0.1) { // The client need at leas 10% in their acount
            return true;
        } else {
            return false;
        }
    }

    // R7.4
    public boolean balanceYearsWorkingRelationCheck(UserEntity user, LoanAplicationEntity loan) {
        if (user.getYearsAccount() < 2) {
            if (user.getBalanceAccount() >= loan.getAmount() * 0.2) {
                return true;
            }
        } else {
            if (user.getBalanceAccount() >= loan.getAmount() * 0.1) {
                return true;
            }
        }

        return false;
    }

    // R7
    // Desc: Check all the requirment from R7
    public boolean saveCapacityCheck(LoanAplicationEntity loan, UserEntity user) {
        int evaluation = 0;
        if (minBalanceRequiredCheck(user, loan)) {
            evaluation += 1;
        }
        if (loan.isConsistentSaveCheck()) {
            evaluation += 1;
        }
        if (loan.isPeriodicDepositsCheck()) {
            evaluation += 1;
        }
        if (balanceYearsWorkingRelationCheck(user, loan)) {
            evaluation += 1;
        }
        if (!loan.isRecentWithdrawCheck()) {
            evaluation += 1;
        }

        if (evaluation == 5) {
            loan.setSave_capacity("solida");
            updateLoan(loan);
            return true;
        } else if (evaluation == 3 || evaluation == 4) {
            loan.setSave_capacity("moderada");
            updateLoan(loan);
            return true;
        } else {
            loan.setSave_capacity("insuficiente");
            updateLoan(loan);
            return false;
        }
    }

    // P6
    // Desc: Calculate total costs

    public double totalCostCal(LoanAplicationEntity loan) {

        SimulationEntity simulation = new SimulationEntity(loan.getAmount(), loan.getTerm(),
                loan.getAnualInterestRate());
        double monthlyPayment = simulationFeignClient.simulateLoan(simulation);
        double monthlyCreditInsurance = loan.getCreditInsuarance() * loan.getAmount();
        double AdministrationCommission = loan.getAdministrationCommission() * loan.getAmount();

        double monthlyCost = monthlyPayment + monthlyCreditInsurance + loan.getMonthlyFireInsurance();
        int months = loan.getTerm() / 12;

        return monthlyCost * months + AdministrationCommission;

    }

}
