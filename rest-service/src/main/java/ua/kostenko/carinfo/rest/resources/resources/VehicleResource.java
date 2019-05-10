package ua.kostenko.carinfo.rest.resources.resources;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.hateoas.ResourceSupport;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VehicleResource extends ResourceSupport {
    private String brandName;
    private String modelName;

}