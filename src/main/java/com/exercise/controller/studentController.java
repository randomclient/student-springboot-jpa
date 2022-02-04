
package com.exercise.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.exercise.dao.ClassDAOImpl;
import com.exercise.dao.StudentDAOImpl;
import com.exercise.dto.ClassDto;
import com.exercise.dto.StudentDto;
import com.exercise.model.StudentBean;

@Controller
public class studentController {

	@Autowired
	private StudentDAOImpl dao;

	@Autowired
	private ClassDAOImpl cdao;

	@GetMapping(value = "/addstudent")
	public ModelAndView addstudent(ModelMap map) {
		ClassDto dto = new ClassDto();
		dto.setId("");
		dto.setName("");
		List<ClassDto> list = cdao.select(dto);
		map.addAttribute("clist", list);
		return new ModelAndView("BUD002", "student", new StudentBean());
	}

	@PostMapping(value = "/setupaddstudent")
	public String setupaddstudent(@ModelAttribute("student") @Validated StudentBean bean, BindingResult bs,
			ModelMap map) {
		if (bs.hasErrors()) {
			return "BUD002";
		}
		StudentDto dto = new StudentDto();
		dto.setStudentId(bean.getStudentId());
		dto.setStudentName(bean.getStudentName());
		dto.setClassName(bean.getClassName());
		dto.setRegister(bean.getRegister());
		dto.setStatus(bean.getStatus());
		StudentDto record = dao.selectOne(dto);
		if (record != null)
			map.addAttribute("err", "StudentId has been already");
		else {
			int res = dao.insert(dto);
			if (res > 0)
				map.addAttribute("msg", "Insert successfully!!");
			else
				map.addAttribute("err", "Insert fail");
		}
		return "BUD002";
	}

	@GetMapping(value = "/searchstudent")
	public ModelAndView searchstudent() {
		return new ModelAndView("BUD001", "student", new StudentBean());
	}

	@SuppressWarnings("unused")
	@PostMapping(value = "/setupsearchstudent")
	public String setupsearchstudent(@ModelAttribute("student") StudentBean bean, ModelMap map) {
		StudentDto dto = new StudentDto();
		dto.setStudentId(bean.getStudentId());
		dto.setStudentName(bean.getStudentName());
		dto.setClassName(bean.getClassName());
		List<StudentDto> list = dao.select();
		StudentDto singleRow = dao.selectOne(dto);
		if (bean.getStudentId().equals("") && bean.getStudentName().equals("") && bean.getClassName().equals("")) {
			map.addAttribute("stulist", list);
		} else if (!bean.getStudentId().equals("") || !bean.getStudentName().equals("")
				|| !bean.getClassName().equals("")) {
			if (singleRow != null) {
				List<StudentDto> listOne = new ArrayList<>();
				listOne.add(singleRow);
				map.addAttribute("stulist", listOne);
			} else 
				map.addAttribute("err", "Student not found!!");
			
		}
		return "BUD001";

	}

	@GetMapping(value = "/studentupdate")
	public ModelAndView updatestudent(@RequestParam("id") String studentId, Model model) {
		ClassDto cdto = new ClassDto();
		cdto.setId("");
		cdto.setName("");
		List<ClassDto> list = cdao.select(cdto);
		model.addAttribute("clist", list);
		StudentDto dto = new StudentDto();
		dto.setStudentId(studentId);
		model.addAttribute("id", dto.getStudentId());
		return new ModelAndView("BUD002-01", "student", dao.selectOne(dto));
	}

	@PostMapping(value = "/setupupdatestudent")
	public String setupupdatestudent(@ModelAttribute("student") @Validated StudentBean bean, BindingResult bs,
			ModelMap map) {

		StudentDto dto = new StudentDto();
		dto.setStudentId(bean.getStudentId());
		dto.setStudentName(bean.getStudentName());
		dto.setClassName(bean.getClassName());
		dto.setRegister(bean.getRegister());
		dto.setStatus(bean.getStatus());
		dao.select(); // List<StudentResponseDto> list =
		
		int res = dao.update(dto);

		if (res > 0)
			map.addAttribute("msg", "Update successfully!!");
		else
			map.addAttribute("err", "Update fail");

		return "BUD002-01";
	}

	@GetMapping(value = "/deletestudent")
	public String deletestudent(@RequestParam("id") String studentId, ModelMap map) {
		StudentDto dto = new StudentDto();
		dto.setStudentId(studentId);
		int res = dao.delete(dto);
		if (res > 0)
			map.addAttribute("msg", "Delete successful!!");
		else
			map.addAttribute("err", "Delete fail");

		return "redirect:/searchstudent";
	}

	@GetMapping(value = "/studentreset")
	public String reset() {
		return "redirect:/searchstudent";
	}

}
