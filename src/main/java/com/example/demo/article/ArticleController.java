package com.example.demo.article;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
	
	@Autowired
	ArticleRepository repository;
	
	@RequestMapping("/top")
	public ModelAndView top(ModelAndView mv) {
		mv.setViewName("top");
		return mv;
	}
	
	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv) {
		List<ArticleEntity> list= repository.findAll();
		mv.addObject("title","記事一覧");
		mv.addObject("list",list);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/search")
	public ModelAndView search(ModelAndView mv,@RequestParam("title")String title) {
		List<ArticleEntity> list = repository.findByTitleLike("%" + title + "%");
		mv.addObject("title","記事検索");
		mv.addObject("list",list);
		mv.setViewName("index");
		return mv;
	}
	
	@RequestMapping("/write")
	public ModelAndView write(UploadFile uploadfile,ModelAndView mv) {
		mv.addObject("title","書き込み");
		mv.addObject("form",new ArticleEntity());
		mv.setViewName("write");
		return mv;
	}
	
	@RequestMapping(value="/insert",method=RequestMethod.POST)
	@Transactional(readOnly=false)
	public ModelAndView insert(@Validated @ModelAttribute("uploadFile")UploadFile uploadFile,
		BindingResult result,ModelAndView mv) {
		if(result.hasErrors()) {
			mv.setViewName("write");
			return mv;
		}
		if(!uploadFile.getFile().isEmpty()) {
			ArticleEntity entity = new ArticleEntity();
			entity.setTitle(uploadFile.getTitle());
			entity.setBody(uploadFile.getBody());
			entity.setUrl(uploadFile.getUrl());
			MultipartFile upfile=uploadFile.getFile();
			
			String path = upfile.getOriginalFilename();
			String fname = String.format("%tF-%<tH%<tM%<tS-%s",
					new Date(),new File(path).getName());
			File file= new File("upload",fname);
			
			try(FileOutputStream out = new FileOutputStream(file)){
				out.write(upfile.getBytes());
			}
			catch(IOException e) {System.err.println("upload:"+e);}
			
			entity.setFile_path(fname);
			repository.saveAndFlush(entity);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/detail/{id}")
	public ModelAndView pic(@PathVariable Integer id,ModelAndView mv) {
		Optional<ArticleEntity> data = repository.findById(id);
		mv.addObject("data",data.get());
		mv.setViewName("detail");
		return mv;
	}
	
	@RequestMapping("/delete/{id}")
	@Transactional(readOnly=false)
	public String delete(@PathVariable Integer id,ModelAndView mv) {
		Optional<ArticleEntity> data = repository.findById(id);
		File file=new File("upload",data.get().getFile_path());
		file.delete();
		repository.deleteById(id);
		return "redirect:/";
	}
	
	//今回は画像処理があるので、改めてupdateメソッドを作り直さないとエラーが起きる。
	@RequestMapping("/update/{id}")
	public ModelAndView update(@PathVariable Integer id,ModelAndView mv) {
		Optional<ArticleEntity> updata = repository.findById(id);
		mv.addObject("uploadFile",updata.get());
		mv.addObject("title","更新");
		mv.setViewName("write");
		return mv;
	}
	
	@RequestMapping("/updatearticle")
	public ModelAndView updatearticle(@ModelAttribute("uploadFile")ArticleEntity entity,
			BindingResult result,ModelAndView mv) {
		if(result.hasErrors()) {
			mv.setViewName("write");
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/");
	}
}







