package com.springbootacademy.pos.dto.paginated;

import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PaginatedResponseItemDTO {
    List<ItemGetResponseDTO> itemGetResponseDTOS;
    private long dataCount;
}
