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
public class RegistrationBrand implements Serializable {
    private Long brandId;
    private String brandName;
}
