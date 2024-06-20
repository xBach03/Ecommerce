package com.springboot.ecommerce.controllers;

import com.springboot.ecommerce.dtos.store.StoreDto;
import com.springboot.ecommerce.models.Store;
import com.springboot.ecommerce.services.StoreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/stores")
    public ResponseEntity<List<StoreDto>> findAll() {
        List<StoreDto> storeDtoList = storeService.findAll();
        return storeDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(storeDtoList, HttpStatus.OK);
    }

    @GetMapping("/stores/{name}")
    public ResponseEntity<List<StoreDto>> findAllByName(@PathVariable("name") String name) {
        List<StoreDto> storeDtoList = storeService.findAllByName(name);
        return storeDtoList == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(storeDtoList, HttpStatus.OK);
    }

    @PostMapping("/stores")
    public ResponseEntity<StoreDto> save(@RequestBody Store store) {
        StoreDto savedStore = storeService.save(store);
        return savedStore == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(savedStore, HttpStatus.OK);
    }

    @DeleteMapping("/stores/delete/{store-id}")
    public ResponseEntity<StoreDto> delete(@PathVariable("store-id") Integer id) {
        StoreDto deletedStore = storeService.delete(id);
        return deletedStore == null ? new ResponseEntity<>(HttpStatus.BAD_REQUEST) : new ResponseEntity<>(deletedStore, HttpStatus.OK);
    }
}
