package com.sajidtech.easytrip.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private String name;
    private int age;
    private String email;
}
