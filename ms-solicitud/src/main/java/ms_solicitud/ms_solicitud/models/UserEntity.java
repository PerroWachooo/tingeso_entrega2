package ms_solicitud.ms_solicitud.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity {
    private Long id;
    private String rut; // xx.xxx.xxx-x
    private String password;
    private String mail;
    private String name;
    private int age;
    private String state_hist; // ?? Que wea era esto?
    private String work;
    private int years_working;
    private int income;
    private int debt;
    private int yearsAccount;
    private int balanceAccount;

}
