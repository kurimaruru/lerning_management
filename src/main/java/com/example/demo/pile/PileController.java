package com.example.demo.pile;

import java.util.Calendar;
import java.util.List;

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
public class PileController {
	
	@Autowired
	PileRepository repository;
	@Autowired
	TargetRepository target_repository;

	@RequestMapping({"/pile","/pile/{monthparam}"})
	public ModelAndView pile(@PathVariable(required=false)Integer monthparam,ModelAndView mv) {
		int year;
		int month;
		if(monthparam == null) { //今月のカレンダー
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month = calendar.get(Calendar.MONTH);
		    int thisMonth = calendar.get(Calendar.MONTH) + 1;
		    List<PileEntity> monthdata = repository.findByMonthOrderByDay(thisMonth);
		    List<TargetEntity> month_target = target_repository.findByMonth(thisMonth);
		    mv.addObject("target_form",new TargetEntity());
			mv.addObject("monthdata",monthdata);
			mv.addObject("month_target",month_target);
		    mv.addObject("year", year);
		    mv.addObject("month", thisMonth);
		    return setDate(mv,year,month);
		}else { //今月じゃないカレンダー
			Calendar calendar = Calendar.getInstance();
			year = calendar.get(Calendar.YEAR);
			month=(monthparam - 1);
			List<PileEntity> monthdata = repository.findByMonthOrderByDay(monthparam);
			List<TargetEntity> other_month_target = target_repository.findByMonth(monthparam);
			mv.addObject("target_form",new TargetEntity());
			mv.addObject("monthdata",monthdata);
			mv.addObject("month_target",other_month_target);
			mv.addObject("year", year);
			mv.addObject("month", monthparam);
			return setDate(mv,year,month);
		}
	}

	protected ModelAndView setDate(ModelAndView mv, int year, int month) {
		Calendar calendar = Calendar.getInstance();
		// 今月が何曜日から始まっているか
		calendar.set(year, month, 1);
		int startWeek = calendar.get(Calendar.DAY_OF_WEEK);
		// 先月が何日までだったか
		calendar.set(year, month, 0);
		int beforeMonthlastDay = calendar.get(Calendar.DATE);
		// 今月が何日までか
		calendar.set(year, month + 1, 0);
		int thisMonthlastDay = calendar.get(Calendar.DATE);
		// カレンダーの表示は最大４２日分
		int[] calendarDay = new int[42];
		int count = 0;
		// 先月分の日付を格納。例えば木曜始まりだったら、4-2=2で2が0になるまで先月の日付入れる。2,1,0
		for (int i = startWeek - 2; i >= 0; i--) {
			calendarDay[count++] = beforeMonthlastDay - i;
		}
		// 今月分の日付を格納
		for (int i = 1; i <= thisMonthlastDay; i++) {
			calendarDay[count++] = i;
		}
		// 翌月の日付を格納
		int nextMonthDay = 1;
		while (count % 7 != 0) {
			calendarDay[count++] = nextMonthDay++;
		}
		// 各週の日付を格納
		int[] farstWeek = new int[7];
		for (int i = 0; i < 7; i++) {
			farstWeek[i] = calendarDay[i];
		}
		int[] secondWeek = new int[7];
		for (int a = 0, b = 7; a < 7; a++) {
			secondWeek[a] = calendarDay[b++];
		}
		int[] thirdWeek = new int[7];
		for (int c = 0, d = 14; c < 7; c++) {
			thirdWeek[c] = calendarDay[d++];
		}
		int[] forthWeek = new int[7];
		for (int e = 0, f = 21; e < 7; e++) {
			forthWeek[e] = calendarDay[f++];
		}
		int[] fiveWeek = new int[7];
		for (int g = 0, h = 28; g < 7; g++) {
			fiveWeek[g] = calendarDay[h++];
		}
		int[] sixWeek = new int[7];
		for (int k = 0, l = 35; k < 7; k++) {
			sixWeek[k] = calendarDay[l++];
		}
		
		mv.addObject("farstweek", farstWeek);
		mv.addObject("secondweek", secondWeek);
		mv.addObject("thirdweek", thirdWeek);
		mv.addObject("forthweek", forthWeek);
		mv.addObject("fiveweek", fiveWeek);
		mv.addObject("sixweek", sixWeek);
		mv.setViewName("pile");
		return mv;
	}
	
	@RequestMapping("/day")
	public ModelAndView dat(@RequestParam(name="month",required=false) Integer mth,
			@RequestParam(name="day",required=false) Integer dy,ModelAndView mv) {
		Calendar calendar = Calendar.getInstance();
		int detailYear = calendar.get(Calendar.YEAR);
		int detailMonth = mth;
		int detailDay = dy;
		List<PileEntity> daydate = repository.findByMonthAndDay(detailMonth, detailDay);
		mv.addObject("detailYear",detailYear);
		mv.addObject("detailMonth",detailMonth);
		mv.addObject("detailDay",detailDay);
		mv.addObject("daydate",daydate);
		mv.setViewName("day");
		return mv;
	}
	
	@RequestMapping("/record")
	public ModelAndView record(@RequestParam(name="record_month",required=false)Integer recordmonth,
			@RequestParam(name="record_day",required=false)Integer recordday,ModelAndView mv) {
		int record_month=recordmonth;
		int record_day=recordday;
		mv.addObject("record_month",record_month);
		mv.addObject("title","一日の記録");
		mv.addObject("record_day",record_day);
		mv.addObject("form",new PileEntity());
		mv.setViewName("pile_record");
		return mv;
	}
	
	@RequestMapping("/day_record")
	public ModelAndView day_record(@Validated @ModelAttribute("form")PileEntity entity,
			BindingResult result,
			ModelAndView mv) {
		if(result.hasErrors()) {
			mv.addObject("title","一日の記録");
			mv.setViewName("pile_record");
			return mv;
		}
		repository.saveAndFlush(entity);
		return new ModelAndView("redirect:/pile");
	}
}









