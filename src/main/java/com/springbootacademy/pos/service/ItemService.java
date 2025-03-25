package com.springbootacademy.pos.service;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;

import java.util.List;

public interface ItemService {
    String saveItem(ItemSaveRequestDTO itemSaveRequestDTO);

    List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName);

    List<ItemGetResponseDTO> getItemByStatus(boolean status);

    PaginatedResponseItemDTO getItemByActiveStateWithPaginated(boolean status, int page, int size);
}
