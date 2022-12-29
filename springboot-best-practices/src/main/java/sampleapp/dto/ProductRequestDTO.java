package sampleapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
//@EqualsAndHashCode
public class ProductRequestDTO {

    @NotBlank(message = "Product name should not be blank")
    private String name;
    private String description;

    @NotBlank(message = "Product type should not be blank")
    private String productType;

    @Min(value = 1, message = "Minimum 1 quantity should be there")
    private int quantity;

    @Min(value = 100)
    @Max(value = 1000)
    private double price;

    private String supplierName;

    @NotBlank(message = "Supplier code should not be blank")
    private String supplierCode;
}
