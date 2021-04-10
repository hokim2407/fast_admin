package com.example.study.ifs;


import com.example.study.network.Header;

public interface CrudInterface<req, res> {
    Header<res> create(Header<req> request);
    Header<res> read(Long id);

    Header<res> update(Header<req> request);

    Header delete(Long id);
}
