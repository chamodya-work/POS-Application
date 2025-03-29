package com.springbootacademy.pos.dto.paginated;

import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.dto.response.ResponseOrderDetailsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponseOrderDetails {
    List<ResponseOrderDetailsDTO> responseOrderDetailsDTOS;
    private long dataCount;
}
