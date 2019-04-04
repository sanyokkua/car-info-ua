package ua.kostenko.carinfo.common.database.raw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationDepartment implements Serializable {
    private Long departmentCode;
    private String departmentName;
    private String departmentAddress;
    private String departmentEmail;
}
