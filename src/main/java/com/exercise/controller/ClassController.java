
package com.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.exercise.dao.ClassDAOImpl;
import com.exercise.dto.ClassDto;
import com.exercise.model.ClassBean;

@Controller
public class ClassController {

	@Autowired
	private ClassDAOImpl dao;

	@GetMapping("/classform")
	public ModelAndView addclass() {
		return new ModelAndView("BUD003", "classes", new ClassBean());
	}

	@PostMapping("/setupaddclass")
	public String setupaddclass(@ModelAttribute("classes") @Validated ClassBean bean, BindingResult bs, ModelMap map) {
		if (bs.hasErrors()) {
			return "BUD003";
		}

		ClassDto dto = new ClassDto();
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		ClassDto obj = dao.selectOne(dto);

		System.out.println(obj);
		if (obj != null)
			map.addAttribute("err", "Class has been already exist!!");
		else  {
				int res = dao.insert(dto);
				System.out.println(res);
				if (res > 0)
					map.addAttribute("msg", "Insert successful");
				else
					map.addAttribute("err", "Insert fail");
		}
		return "BUD003";
	}
}
