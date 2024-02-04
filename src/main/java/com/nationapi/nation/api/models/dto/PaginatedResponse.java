package com.nationapi.nation.api.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginatedResponse<T> {
    private List<T> stats;
    private int currentPage;
    private long totalPages;

    public PaginatedResponse(List<GlobalStatsDto> paginatedResults, int totalPages) {
    }
}
