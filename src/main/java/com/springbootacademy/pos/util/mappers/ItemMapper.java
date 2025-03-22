package com.springbootacademy.pos.util.mappers;

import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.entity.Item;
import org.mapstruct.Mapper;

import java.util.List;
@Mapper(componentModel = "spring") //Enables Spring integration

public interface ItemMapper {
    List<ItemGetResponseDTO>  entityListToDtoList(List<Item> items); //convert entityList to ----->DtoList
}
