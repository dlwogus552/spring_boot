package com.fitness.spring_boot.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Controller
public class CalendarController {
    @RequestMapping(value = "/")
    public String home() {
        return "calendar";
    }
}
//    @RequestMapping(value = "calendar.do", method = RequestMethod.GET)
//    public String calendar(Model model, HttpServletRequest request,DateData dateData){

//        Calendar cal = Calendar.getInstance();
//        DateData calendarData;

//        return null;
//    }

//    public Map<String, Integer> today_info(DateData dateData) {  // 캘린더 함수 삽입
//        Map<String, Integer> today_Data = new HashMap<String, Integer>(); //hashmap
//        Calendar cal = Calendar.getInstance();
//        cal.set(Integer.parseInt(dateData.getYear()), Integer.parseInt(dateData.getMonth()), 1);
//
//        int startDay = cal.getMinimum(java.util.Calendar.DATE);
//        int endDay = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
//        int start = cal.get(java.util.Calendar.DAY_OF_WEEK);
//
//        Calendar todayCal = Calendar.getInstance();
//        SimpleDateFormat ysdf = new SimpleDateFormat("yyyy"); //표현방식
//        SimpleDateFormat msdf = new SimpleDateFormat("M");
//
//    //오늘 년월
//        int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));
//        int today_month = Integer.parseInt(msdf.format(todayCal.getTime()));
//
//        int search_year = Integer.parseInt(dateData.getYear());
//        int search_month = Integer.parseInt(dateData.getMonth()) + 1;
//
//        int today = -1; //0부터시작되기떄문
//        if (today_year == search_year && today_month == search_month) {
//            SimpleDateFormat dsdf = new SimpleDateFormat("dd");
//            today = Integer.parseInt(dsdf.format(todayCal.getTime()));
//        }
//
//        search_month = search_month-1;
//
//        Map<String, Integer> before_after_calendar = before_after_calendar(search_year,search_month);
//
//
//        System.out.println("search_month : " + search_month);
//
//        today_Data.put("start", start);
//        today_Data.put("startDay", startDay);
//        today_Data.put("endDay", endDay);
//        today_Data.put("today", today);
//        today_Data.put("search_year", search_year);
//        today_Data.put("search_month", search_month+1);
//        today_Data.put("before_year", before_after_calendar.get("before_year"));
//        today_Data.put("before_month", before_after_calendar.get("before_month"));
//        today_Data.put("after_year", before_after_calendar.get("after_year"));
//        today_Data.put("after_month", before_after_calendar.get("after_month"));
//        return today_Data;
//
//        if(dateData.getDate().equals("")&&dateData.getMonth().equals("")){
//            dateData = new DateData(String.valueOf(cal.get(Calendar.YEAR)),String.valueOf(cal.get(Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)),null);
//        }
//
//        Map<String, Integer> today_info =  dateData.today_info(dateData);
//        List<DateData> dateList = new ArrayList<DateData>();
//
//        for(int i=1; i<today_info.get("start"); i++){
//            calendarData= new DateData(null, null, null, null);
//            dateList.add(calendarData);
//        }
//
//        for (int i = today_info.get("startDay"); i <= today_info.get("endDay"); i++) {
//            if(i==today_info.get("today")){
//                calendarData= new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "today");
//            }else{
//                calendarData= new DateData(String.valueOf(dateData.getYear()), String.valueOf(dateData.getMonth()), String.valueOf(i), "normal_date");
//            }
//            dateList.add(calendarData);
//        }
//
//        int index = 7-dateList.size()%7;
//
//        if(dateList.size()%7!=0){
//
//            for (int i = 0; i < index; i++) {
//                calendarData= new DateData(null, null, null, null);
//                dateList.add(calendarData);
//            }
//        }
//        System.out.println(dateList);
//
////배열에 담음
//        model.addAttribute("dateList", dateList); //날짜 데이터 배열
//        model.addAttribute("today_info", today_info);
//        return "calendar";
//    }

//    private Map<String, Integer> before_after_calendar(int search_year, int search_month){
//        Map<String, Integer> before_after_data = new HashMap<String, Integer>();
//        int before_year = search_year;
//        int before_month = search_month-1;
//        int after_year = search_year;
//        int after_month = search_month+1;
//
//        if(before_month<0){
//            before_month=11;
//            before_year=search_year-1;
//        }
//
//        if(after_month>11){
//            after_month=0;
//            after_year=search_year+1;
//        }
//
//        before_after_data.put("before_year", before_year);
//        before_after_data.put("before_month", before_month);
//        before_after_data.put("after_year", after_year);
//        before_after_data.put("after_month", after_month);
//
//        return before_after_data;
//    }
//
//}