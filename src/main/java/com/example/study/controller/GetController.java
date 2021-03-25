package com.example.study.controller;

import com.example.study.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path="/getMethod")
    //8080/api/getMethod 에 들어가면 호출됨
    public String GetRequest(){
        return "hi getMethod";
    }

    @GetMapping("/getParameter")
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id : "+ id);
        System.out.println("password : "+ password);
        return  id +" \n "+ password;
    }

    @GetMapping("/multiParameter")
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println("Account : "+ searchParam.getAccount());
        System.out.println("Email : "+ searchParam.getEmail());
        System.out.println("Page : "+ searchParam.getPage());
        return searchParam;
    }

}
