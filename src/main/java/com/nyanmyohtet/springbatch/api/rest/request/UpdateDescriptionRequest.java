package com.nyanmyohtet.springbatch.api.rest.request;

import lombok.Data;

@Data
public class UpdateDescriptionRequest {
    private Long id;
    private String description;
}
