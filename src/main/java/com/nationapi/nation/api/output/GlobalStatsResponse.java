package com.nationapi.nation.api.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalStatsResponse {
    protected String message;
    protected Object data; // Change the type to Object
    protected List<String> errors;
}
