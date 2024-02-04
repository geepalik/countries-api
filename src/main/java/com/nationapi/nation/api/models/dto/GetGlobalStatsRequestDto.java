package com.nationapi.nation.api.models.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetGlobalStatsRequestDto {
    @NotNull(message = "Region is required")
    @Min(value = 1, message = "Region cannot be empty")
    private Long regionId;
    @NotNull(message = "From date is required")
    @Min(value = 1900, message = "From year must be greater than or equal to 1900")
    private int from;
    @NotNull(message = "To date is required")
    @Min(value = 1900, message = "From year must be greater than or equal to 1900")
    private int to;
    @Min(value = 1, message = "Page must be greater than or equal to 1")
    private int page = 1;
    @Min(value = 0, message = "Limit must be greater than or equal to 0")
    private int limit = 100;
}
