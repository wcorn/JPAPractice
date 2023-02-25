package com.base.project.domain.Item.service;


import com.base.project.domain.Item.entity.Item;
import com.base.project.domain.Item.repository.ItemRepository;
import com.base.project.global.common.api.ErrorCode;
import com.base.project.global.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public List<Item> findItem() {
        return itemRepository.findAll();
    }

    public Item findItem(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        if (item.isPresent()) {
            return item.get();
        } else
            throw new CustomException(ErrorCode.USER_NOT_FOUND);
    }
}
