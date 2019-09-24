package com.java.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.java.model.MyUploadForm;

@Controller
public class Upload {
	
	@GetMapping(value = {"/upload"})
	public String home1(ModelMap model) {
		model.addAttribute("myUploadForm", new MyUploadForm(""));
		return "upload";
	}
	
	@PostMapping(value = {"/uploadFile"})
	public String home123(@ModelAttribute("myUploadForm") MyUploadForm myUploadForm, ModelMap model, HttpServletRequest request) throws IOException {
		model.addAttribute("description", myUploadForm.getDescription());
		model.addAttribute("uploadedFiles",myUploadForm.getFileDatas());
		File name =new File(request.getServletContext().getRealPath("static\\images")+File.separator+myUploadForm.getFileDatas().getOriginalFilename());
		System.out.println(name);
		BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(name));
		out.write(myUploadForm.getFileDatas().getBytes());
		out.close();
		return "result";
	}

}
