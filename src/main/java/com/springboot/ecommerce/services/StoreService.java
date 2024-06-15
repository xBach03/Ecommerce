package com.springboot.ecommerce.services;

import com.springboot.ecommerce.dtos.store.StoreDto;
import com.springboot.ecommerce.models.Store;
import com.springboot.ecommerce.repositories.StoreRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StoreService {
    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public StoreDto toStoreDto(Store store) {
        return new StoreDto(
                store.getName(),
                store.getLocation(),
                store.getRate(),
                store.getJoiningDate()
        );
    }

    public List<StoreDto> findAll() {
        return storeRepository.findAll().stream()
                .map(this::toStoreDto)
                .collect(Collectors.toList());
    }

    public List<StoreDto> findAllByName(String name) {
        return storeRepository.findAllByNameContaining(name).stream()
                .map(this::toStoreDto)
                .collect(Collectors.toList());
    }

    public StoreDto save(Store store) {
        store.setJoiningDate(LocalDate.now());
        return toStoreDto(storeRepository.save(store));
    }
}
