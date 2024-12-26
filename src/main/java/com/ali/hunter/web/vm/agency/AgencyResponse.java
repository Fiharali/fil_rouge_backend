package com.ali.hunter.web.vm.agency;

import lombok.Data;
import java.util.UUID;

@Data
public class AgencyResponse {
    private UUID id;
    private String name;
    private String address;
}
