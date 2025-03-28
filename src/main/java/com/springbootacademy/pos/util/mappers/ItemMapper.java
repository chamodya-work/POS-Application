package com.springbootacademy.pos.util.mappers;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.RequestOrderDetailsSave;
import com.springbootacademy.pos.dto.request.RequestOrderSaveDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.entity.Item;
import com.springbootacademy.pos.entity.OrderDetails;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
@Mapper(componentModel = "spring") //Enables Spring integration

public interface ItemMapper {
    List<ItemGetResponseDTO>  entityListToDtoList(List<Item> items); //convert entityList to ----->DtoList
    List<ItemGetResponseDTO> listDtoToPage(Page<Item> items);
    //Page<Item> items ------> List<ItemGetResponseDTO> itemGetResponseDTOS

}
