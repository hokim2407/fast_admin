package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {


    //json,xml, multipart-form,text-plain 등 데이터 형태를 지정 가능(기본값은 json)
    // @PostMapping("/postMethod", produces = {"application-json");
    //@RequestMapping(method = RequestMethod.POST, path="/postMethod") 라고 써도 됨
    @PostMapping(value = "/postMethod")
    public SearchParam postMethod(@RequestBody SearchParam searchParam){
        //post body 에 데이터를 넣어서 보내겠다는 어노테이션

        return searchParam;
    }
    @PutMapping("/putMaethod")
    public void put(@RequestBody SearchParam searchParam){
    }
    @PatchMapping("/patchMaethod")
    public void patch(@RequestBody SearchParam searchParam){
    }
}
