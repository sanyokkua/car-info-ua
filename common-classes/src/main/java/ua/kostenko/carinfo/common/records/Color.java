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
public class Color implements Serializable {
    private Long colorId;
    private String colorName;
}
