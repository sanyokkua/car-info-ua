package ua.kostenko.carinfo.common.records;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department implements Serializable {
    private Long departmentCode;
    private String departmentName;
    private String departmentAddress;
    private String departmentEmail;
}