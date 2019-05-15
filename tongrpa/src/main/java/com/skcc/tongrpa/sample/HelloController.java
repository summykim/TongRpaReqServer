package com.skcc.tongrpa.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/hello")
public class HelloController {

	@Autowired
	private MapMapper mapper;

    @GetMapping
    public ModelAndView hello(Model model) {
        ModelAndView view = new ModelAndView("hello");
        view.addObject("list", mapper.listmap());
        return view;
    }	
}
