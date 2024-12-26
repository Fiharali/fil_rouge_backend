package com.ali.hunter.web.vm.agency;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AgencyRequest {
    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Address is mandatory")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;
}
