package br.com.limac.sysmac.api.v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class IndexController {


    @ResponseBody
    @GetMapping
    public String getAll() {
        return "TESTE";
    }


}
