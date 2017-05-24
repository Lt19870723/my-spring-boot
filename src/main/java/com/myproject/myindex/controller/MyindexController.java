package com.myproject.myindex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sh00790
 * @version $Id: SampleController.java, v 0.1 2017年5月11日 下午9:28:36 sh00790 Exp $
 */

@Controller
@RequestMapping("/home")
public class MyindexController {

    @RequestMapping(value = "/index")
    public String index(ModelMap model) {
        model.put("index", "this is my first spring-web-boot project");
        return "index";
    }
}
