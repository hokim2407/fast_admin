package com.example.study.service;


import com.example.study.ifs.CrudInterface;
import com.example.study.network.Header;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public abstract class BaseService<Req, Res,Entity> implements CrudInterface<Req, Res> {

    @Autowired(required = false)
    protected JpaRepository<Entity,Long> baseRepository;

    public Header<Res> read(Long id) {
        return baseRepository.findById(id)
                .map(user -> Header.OK(response(user)))
                .orElseGet(() -> Header.ERROR("NO DATA"));
    }

    public Header delete(Long id) {
        Optional<Entity> optional = baseRepository.findById(id);
        return optional.map(data -> {
            baseRepository.delete(data);
            return Header.OK();
        }).orElseGet(()->Header.ERROR("NO DATA"));

    }


    public abstract Res response(Entity user);

}
