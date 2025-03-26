package com.springbootacademy.pos.controller;

import com.springbootacademy.pos.dto.CustomerDTO;
import com.springbootacademy.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springbootacademy.pos.dto.request.ItemSaveRequestDTO;
import com.springbootacademy.pos.dto.response.ItemGetResponseDTO;
import com.springbootacademy.pos.service.ItemService;
import com.springbootacademy.pos.util.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//this is combine of @Controller @ResponseBody //@ResponseBody help to convert java object to json object
@RequestMapping("api/v1/item")
@Validated // when is use like @Max(50) int size
//we have to explicitly validated

public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("/save")
    public ResponseEntity<StandardResponse> saveItem(@RequestBody ItemSaveRequestDTO itemSaveRequestDTO) {
        String message = itemService.saveItem(itemSaveRequestDTO);
//        return "saved  successfully "+ message;
//        ResponseEntity<StandardResponse> response=new ResponseEntity<StandardResponse>(
//                new StandardResponse(201,"Success",message), HttpStatus.CREATED
//        );
//        return response;
        // we can do return directly without assign in to object
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Success", message),
                HttpStatus.CREATED
        );
    }

    @GetMapping(
            path = "get-by-name",
            params = "name"
    )
    public List<ItemGetResponseDTO> getItemByNameAndStatus(@RequestParam(value = "name") String itemName) {
        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemByNameAndStatus(itemName);
        return itemGetResponseDTOS;
    }
    @GetMapping(
            path = "get-all-by-state-paginated",
            params = {"activeStatus","page","size"}
    )
    ResponseEntity<StandardResponse> getItemByActiveState(
            @RequestParam(value = "activeStatus") boolean status,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size")@Max(5) int size) {
//        List<ItemGetResponseDTO> itemGetResponseDTOS = itemService.getItemByStatus(status);
//        size=3; if we dont want to user to input the page size you can do this
        PaginatedResponseItemDTO paginatedResponseItemDTO=itemService.getItemByActiveStateWithPaginated(status,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", paginatedResponseItemDTO),
                HttpStatus.CREATED
        );
    }

}
