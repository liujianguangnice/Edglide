package com.ed.edglide.common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class CalendarGenerator {

    private static final String REG = "^(\\d+)[^\\d]+((0?[1-9])|(1[0-2]))$";
    private static final String PROMPTS = "输入年月(年和月用非数字隔开:如2018.10)(什么都不输入直接退出)";
    public static final String ERROR_NULL = "输入不能为空!";
    public static final String ERROR_BAD_FORMAT = "输入格式不符合要求！";
    private static String TITLE = "日\t一\t二\t三\t四\t五\t六\r\n";

    public CalendarGenerator() {
        super();
    }


    public String generator(String line) {
        List<String> dayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if ("".equals(line)) {
            return ERROR_NULL;
        }
        if (!line.matches(REG)) {
            return ERROR_BAD_FORMAT;
        }
        int year = Integer.parseInt(line.replaceAll(REG, "$1"));
        System.out.println("====年："+year);
        if (year == 0) {
            return ERROR_BAD_FORMAT;
        }
        int month = Integer.parseInt(line.replaceAll(REG, "$2"));

        System.out.println("====月："+month);
        System.out.println("====2019-3："+line.replaceAll(REG, "$0"));
        sb.append(TITLE);
        Calendar calendar = Calendar.getInstance();
        // 这个月的1号是星期几
        calendar.set(year, month - 1, 1);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int start = Calendar.SUNDAY;
        if (year != 1) {
            calendar.add(Calendar.DATE, -day + start);
        } else {
            calendar.add(Calendar.DATE, 0);
        }
        while (start < day) {
            sb.append(" \t");
            dayList.add("+");
            start++;
        }

        calendar.set(year, month - 1, 1);
        Date now = calendar.getTime();
        calendar.set(year, month, 1);
        Date next = calendar.getTime();
        for (Date cur = now; cur.before(next);) {
            calendar.setTime(cur);
            int x = calendar.get(Calendar.DATE);
            String tmp = x < 10 ? " " + x : x + "";
            sb.append(tmp + "\t");
            dayList.add(tmp+"");
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                sb.append("\r\n");
            }
            calendar.add(Calendar.DATE, 1);
            cur = calendar.getTime();
        }
        calendar.add(Calendar.DATE, -1);
        int to = calendar.get(Calendar.DAY_OF_WEEK);
        int end = Calendar.SATURDAY;
        while (to < end) {
            sb.append(" \t");
            dayList.add("+");
            to++;
        }
        System.out.println(dayList.toString());
        System.out.println(dayList.get(0));
        System.out.println(dayList.get(3));
        return sb.toString();
    }


    public static List<DataBean> getTimeList(String line) {
        List<DataBean> dayList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        int year = Integer.parseInt(line.replaceAll(REG, "$1"));
        System.out.println("====年："+year);

        int month = Integer.parseInt(line.replaceAll(REG, "$2"));

        String monthtmp = (month < 10 ? "0" + month : month)+"";

        System.out.println("====月："+month);
        System.out.println("====年月："+line.replaceAll(REG, "$0"));
        sb.append(TITLE);
        Calendar calendar = Calendar.getInstance();
        // 这个月的1号是星期几
        calendar.set(year, month - 1, 1);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int start = Calendar.SUNDAY;
        if (year != 1) {
            calendar.add(Calendar.DATE, -day + start);
        } else {
            calendar.add(Calendar.DATE, 0);
        }
        while (start < day) {
            sb.append(" \t");
            dayList.add(new DataBean(year+"-"+monthtmp+"-"+"+",year+"",month+"","+"));
            start++;
        }

        calendar.set(year, month - 1, 1);
        Date now = calendar.getTime();
        calendar.set(year, month, 1);
        Date next = calendar.getTime();
        for (Date cur = now; cur.before(next);) {
            calendar.setTime(cur);
            int x = calendar.get(Calendar.DATE);
            String tmpday = x < 10 ? "0" + x : x + "";
            sb.append(x + "\t");


            dayList.add(new DataBean(year+"-"+monthtmp+"-"+tmpday,year+"",month+"",x+""));
            if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                sb.append("\r\n");
            }
            calendar.add(Calendar.DATE, 1);
            cur = calendar.getTime();
        }
        calendar.add(Calendar.DATE, -1);
        int to = calendar.get(Calendar.DAY_OF_WEEK);
        int end = Calendar.SATURDAY;
        while (to < end) {
            sb.append(" \t");
            dayList.add(new DataBean(year+"-"+monthtmp+"-"+"+",year+"",monthtmp+"","+"));
            to++;
        }
        System.out.println(dayList.toString());
        System.out.println(dayList.get(0).toString());
        System.out.println(dayList.get(3).toString());
        return dayList;
    }


    public static class DataBean {
        private String allTime;
        private String year;
        private String month;
        private String day;

        public DataBean(String allTime, String year, String month, String day) {
            this.allTime = allTime;
            this.year = year;
            this.month = month;
            this.day = day;
        }

        public String getAllTime() {
            return allTime;
        }

        public void setAllTime(String allTime) {
            this.allTime = allTime;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "allTime='" + allTime + '\'' +
                    ", year='" + year + '\'' +
                    ", month='" + month + '\'' +
                    ", day='" + day + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        CalendarGenerator cg = new CalendarGenerator();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(PROMPTS);
            String line = scanner.nextLine().trim();
            String result = cg.generator(line);
            System.out.println(result);
            System.out.println("=============================================================================================================================");
            getTimeList(line);
            // scanner.close();
        }
    }
}
