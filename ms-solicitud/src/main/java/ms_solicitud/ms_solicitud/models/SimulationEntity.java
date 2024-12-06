package ms_solicitud.ms_solicitud.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SimulationEntity {
    private int amount;
    private int term;
    private Float anualInterestRate;

}
