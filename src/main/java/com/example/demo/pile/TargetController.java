package com.example.demo.pile;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TargetController {

	@Autowired
	TargetRepository repository;
	
	@RequestMapping("/target_write/{thismonth}")
	public ModelAndView target_write(@PathVariable(required=false)Integer thismonth,ModelAndView mv) {
		mv.addObject("title","今月の目標書き込み");
		mv.addObject("thismonth",thismonth);
		mv.addObject("target_form",new TargetEntity());
		mv.setViewName("target_write");
		return mv;
	}
	
	@RequestMapping("/target_insert")
	public ModelAndView target_insert(@Validated @ModelAttribute("target_form")TargetEntity entity,
			BindingResult result,ModelAndView mv) {
		if(result.hasErrors()) {
			mv.setViewName("target_write");
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/pile");
	}
	
	@RequestMapping("/target_update")
	public ModelAndView target_update(@RequestParam(name="id",required=false) Integer id,
			@RequestParam(name="month",required=false) Integer up_month,ModelAndView mv) {
		List<TargetEntity> month_target = repository.findByMonth(up_month);
		mv.addObject("month_target",month_target);
		return set(mv,id,"update_target","target_write");
	}
	
	private ModelAndView set(ModelAndView mv,Integer id,String title,String template) {
		Optional<TargetEntity> data = repository.findById(id);
		if(!data.isPresent()) {
			return new ModelAndView("redirect:/");
		}
		mv.addObject("target_form",data.get());
		mv.addObject("title",title);
		mv.setViewName(template);
		return mv;
	}
}
