package com.skcc.tongrpa.sample;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.skcc.tongrpa.mq.MqSenderService;

@Controller
public class MapController {

	@Autowired
	private MapMapper mapper;

	
	@RequestMapping("listmap")
	public ModelAndView listmap(Model model) {
		
        ModelAndView view = new ModelAndView("listmap");
        view.addObject("list", mapper.listmap());
        return view;
	}
	
	


}
