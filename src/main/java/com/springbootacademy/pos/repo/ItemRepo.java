package com.springbootacademy.pos.repo;

import com.springbootacademy.pos.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ItemRepo extends JpaRepository<Item,Integer> {
    List<Item> findAllByItemNameAndActiveState(String itemName, boolean b);

    List<Item> findAllByActiveState(boolean status);
    Page<Item> findAllByActiveState(boolean status, Pageable pageable);

    int countAllByActiveState(boolean status);
}
