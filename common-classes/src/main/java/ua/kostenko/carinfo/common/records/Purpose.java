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
public class Purpose implements Serializable {
    private Long purposeId;
    private String purposeName;
}
