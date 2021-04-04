package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class ItemRepositoryTest extends StudyApplicationTests {
@Autowired
    private ItemRepository itemRepository;

    @Test
    public void create() {
        Item item = new Item();
        item.setName("Mac m1");

        item.setTitle("notebook");
        item.setPrice(new BigDecimal(3200000));
        item.setContent("애플 노트북");
        itemRepository.save(item);
    }
    @Test
    public void read(@RequestParam Long id) {
        Optional<Item> item =  itemRepository.findById(id);
        item.ifPresent(selectedItem -> { System.out.println(selectedItem);});

    }
    @Test
    public void update() {

        Optional <Item> item =  itemRepository.findById(1L);
        item.ifPresent(selectedItem -> {
            selectedItem.setPrice(new BigDecimal(1500000));
            itemRepository.save(selectedItem);
        });
    }
    @Test
    @Transactional//롤백시켜줌
    public void delete() {
        Optional <Item> item =  itemRepository.findById(1L);

        item.ifPresent(selectedItem -> {
            itemRepository.delete(selectedItem);

        });
    }
}
