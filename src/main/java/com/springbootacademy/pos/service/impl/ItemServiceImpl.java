package com.springbootacademy.pos.service.impl;

import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.entity.Customer;
import com.springbootacademy.pos.entity.Item;
import com.springbootacademy.pos.entity.enums.MeasuringType;
import com.springbootacademy.pos.exception.NotFoundException;
import com.springbootacademy.pos.repo.CustomerRepo;
import com.springbootacademy.pos.repo.ItemRepo;
import com.springbootacademy.pos.service.ItemService;
import com.springbootacademy.pos.util.mappers.ItemMapper;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // in here we want object from this class to controller class
//we do generally using defined that as bean using @component annotation
//but actually we're  using @Service ,because @component already in the @service

public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemRepo itemRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public String saveItem(ItemSaveRequestDTO itemSaveRequestDTO) {
//        Item item=new Item(
//                1,
//                itemSaveRequestDTO.getItemName(),
//                itemSaveRequestDTO.getMeasuringType(),
//                itemSaveRequestDTO.getBalanceQty(),
//                itemSaveRequestDTO.getSupplierPrice(),
//                itemSaveRequestDTO.getSellingPrice(),
//                true
//        );
//        itemRepo.save(item);
//        return itemSaveRequestDTO.getItemName();


        //this is how we use model mapper to map and convert the DTO to entity  types
        Item item=modelMapper.map(itemSaveRequestDTO,Item.class);

        if (!itemRepo.existsById(item.getItemId())){
            itemRepo.save(item);
            return item.getItemName();
        } else
            throw new DuplicateKeyException("already added");
    }

    @Override
    public List<ItemGetResponseDTO> getItemByNameAndStatus(String itemName) {
        boolean b=true;
        List<Item> items=itemRepo.findAllByItemNameAndActiveState(itemName,b);
        if (items.size()>0){
            //how use model mappers to map entity  list to DTO list
            // List<ItemGetResponseDTO> itemGetResponseDTOS=modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());

            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items); //how use map struct to map entity list to dto list
            return itemGetResponseDTOS;
        }
        else throw new RuntimeException("Item is not active");

    }

    @Override
    public List<ItemGetResponseDTO> getItemByStatus(boolean status) {
        List<Item> items=itemRepo.findAllByActiveState(status);
        if (items.size()>0){
            //how use model mappers to map entity  list to DTO list
            // List<ItemGetResponseDTO> itemGetResponseDTOS=modelMapper.map(items,new TypeToken<List<ItemGetResponseDTO>>(){}.getType());

            List<ItemGetResponseDTO> itemGetResponseDTOS=itemMapper.entityListToDtoList(items); //how use map struct to map entity list to dto list
            return itemGetResponseDTOS;
        }
        else throw new NotFoundException("Item is not active");
    }

    @Override
    public PaginatedResponseItemDTO getItemByActiveStateWithPaginated(boolean status, int page, int size) {
        Page<Item> items=itemRepo.findAllByActiveState(status, PageRequest.of(page,size));
        //int count=itemRepo.countAllByActiveState(status);
        if (items.getSize()<1){
            throw new NotFoundException("no data found");
        }

        PaginatedResponseItemDTO paginatedResponseItemDTO=new PaginatedResponseItemDTO(
                itemMapper.listDtoToPage(items),itemRepo.countAllByActiveState(status)
        );
        return paginatedResponseItemDTO;
    }

}
