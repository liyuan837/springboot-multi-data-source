package com.liyuan.demo.util;

import com.liyuan.demo.domain.exception.MultiDataSourceException;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 时间处理工具类
 *
 * @see
 */
public final class DateUtil {
    public static final String FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_SHORT_PATTERN = "MM-dd";
    public static final String DATE_TIME_PATTERN = DATE_PATTERN + " HH:mm:ss";
    public static final String FORMAT_ONE = "yyyy/MM/dd HH:mm:ss";
    public static final BigDecimal MAN_DAY = new BigDecimal("0.5");//人天
    /**
     * 一周的天数为7天
     */
    private static final int DAYS_ONE_WEEK = 7;
    /**
     * 日期格式 年-月-日 时:分:秒， 年份为四位，其他为一位或者两位
     */
    private static final String FULL_DATE_FORMAT = "^(\\d{4})-(0?\\d{1}|1[0-2])-(0?\\d{1}|[12]\\d{1}|3[01])\\s(0?\\d{1}|1\\d{1}|2[0-3]):[0-5]?\\d{1}:([0-5]?\\d{1})$";
    private static final String[] constellationArr = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座"};
    private static final int[] constellationEdgeDay = {20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22};
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);

    /**
     * /**
     * 获得当前时间，格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String getCurrentDate() {
        return getCurrentDate(FORMAT);
    }

    /**
     * 获得当前时间，格式自定义
     *
     * @param format
     * @return
     */
    public static String getCurrentDate(String format) {
        Calendar day = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day.getTime());
    }

    /**
     * 获得当前时间减少一个月，格式自定义
     *
     * @param format
     * @return
     */
    public static String getYesterMonthDate(String format) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.MONTH, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day.getTime());
    }

    /**
     * 获得昨天时间，格式自定义
     *
     * @param format
     * @return
     */
    public static String getYesterdayDate(String format) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, -1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day.getTime());
    }

    /**
     * 获得明天时间
     *
     * @param format
     * @return
     */
    public static String getTomorrowDate(String format) {
        Calendar day = Calendar.getInstance();
        day.add(Calendar.DATE, 1);
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(day.getTime());
    }

    /**
     * 获取每个月的第一天时间
     *
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getMinimum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(c.getTime());
    }

    /**
     * 获取每个月的最后一天时间
     *
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, 1, 0, 0, 0);
        c.add(Calendar.MONTH, -1);
        c.set(Calendar.DAY_OF_MONTH, c.getMaximum(Calendar.DAY_OF_MONTH));
        SimpleDateFormat sdf = new SimpleDateFormat(FORMAT);
        return sdf.format(c.getTime());
    }

    /**
     * 修复代码质量加的注释，这个方法很绕- -
     *
     * @param date
     * @param format
     * @return
     * @author lihe 2013-7-4 下午5:21:33
     * @see
     */
    public static String formatDate(String date, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            return sdf.format(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            return "";
        }
    }

    /**
     * @param date
     * @param format
     * @return
     * @author shayankui 2014-6-26 下午5:21:33
     * @see
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }


    /**
     * 格式化时间
     *
     * @param calendar
     * @param format
     * @return
     */
    public static String formatDate(Calendar calendar, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(calendar.getTime());
    }

    /**
     * 按照时间字符串和格式转换成Date类
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     * @author lihe 2013-7-4 下午5:21:50
     * @see
     */
    public static Date getDate(String date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return new Date();
        }
    }

    /**
     * 按照时间字符串和格式转换成Date类
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     * @author lihe 2013-7-4 下午5:21:50
     * @see
     */
    public static Date getDateOrNull(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }


    public static Date getDateForTodayStart() {
        return getDateForTodayStart(new Date());
    }


    public static Date getDateForTodayStart(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }


    public static Date getDateForHourStart(Date date) {
        return DateUtils.truncate(date, Calendar.HOUR);
    }

    /**
     * 获取指定时间,精确到秒(年-月-日 时:分:秒)
     *
     * @param aDate 指定时间
     * @return
     */
    public static String getDateTime(Date aDate) {
        String returnValue = "";

        if (aDate != null) {
            SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 注释详见 dateBaseFormat
     * 入参：2014-3-8 8:38:59
     * 出参：2014-03-08 08:38:59
     *
     * @param dateString
     * @return
     */
    public static String formatFullDate(String dateString) {
        return dateBaseFormat(dateString).toString();
    }

    /**
     * 格式化日期，可作为参数校验<br/>
     * 入参格式要求：年-月-日 时:分:秒<br/>
     * <p/>
     * 入参：2014-3-8 8:38:59
     * 出参：2014-03-08 08:38:59
     * <p/>
     * 入参：2014-3-8 8:38: (或其他错误格式)
     * 出参：null
     *
     * @param dateStr
     * @return
     */
    public static StringBuilder dateBaseFormat(String dateStr) {
        String dateString = validFullDate(dateStr);
        if (dateString == null) {
            return null;
        }

        StringBuilder result = new StringBuilder();
        Pattern compile = Pattern.compile("([-:\\s]?\\d+)");
        Matcher matcher = compile.matcher(dateString);
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            if (start == 0 || end - start == 3) {
                result.append(dateString.substring(start, end));
                continue;
            }
            char startMark = dateString.charAt(start);
            if ('-' == startMark) {
                result.append("-0").append(dateString.charAt(end - 1));
            } else if (':' == startMark) {
                result.append(":0").append(dateString.charAt(end - 1));
            } else {
                result.append(" 0").append(dateString.charAt(end - 1));
            }
        }
        return result;
    }

    /**
     * 验证日期的正确性，并去除首尾的空白字符
     * 如果返回 null 则日期有误
     * 否则，返回去除首尾空白字符的字符串
     *
     * @param dateStr
     * @return
     */
    public static String validFullDate(String dateStr) {
        if (dateStr == null) {
            return null;
        }
        String target = dateStr.trim();
        Pattern dateFormat = Pattern.compile(FULL_DATE_FORMAT);
        Matcher dateMatcher = dateFormat.matcher(target);
        return dateMatcher.matches() ? target : null;
    }

    /**
     * 判断两个时间是否跨月
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static boolean isCrossMonth(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            // 格式化开始日期和结束日期
            Calendar objCalendarDate1 = Calendar.getInstance();
            objCalendarDate1.setTime(sdf.parse(startTime));

            Calendar objCalendarDate2 = Calendar.getInstance();
            objCalendarDate2.setTime(sdf.parse(endTime));

            // 开始时间结束时间大小比较
            long start = objCalendarDate1.getTimeInMillis();
            long end = objCalendarDate2.getTimeInMillis();
            if (start - end > 0) {
                return true;
            }

            int startYear = objCalendarDate1.get(Calendar.YEAR);
            int endYear = objCalendarDate2.get(Calendar.YEAR);

            // 判断是否跨年
            if (startYear - endYear != 0) {
                return true;
            }
            // 判断是否跨月
            int startMonth = objCalendarDate1.get(Calendar.MONTH);
            int endMonth = objCalendarDate2.get(Calendar.MONTH);
            if (startMonth - endMonth != 0) {
                return true;
            }

        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return true;
        }

        return false;
    }


    /**
     * 生成日期
     *
     * @param year
     * @param month  从0开始
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    private static Date parseDate(int year, int month, int day, int hour, int minute, int second) {
        Calendar currentDate = new GregorianCalendar();

        currentDate.set(Calendar.YEAR, year);
        currentDate.set(Calendar.MONTH, month);
        currentDate.set(Calendar.DAY_OF_MONTH, day);
        currentDate.set(Calendar.HOUR_OF_DAY, hour);
        currentDate.set(Calendar.MINUTE, minute);
        currentDate.set(Calendar.SECOND, second);
        currentDate.set(Calendar.MILLISECOND, 0);

        return currentDate.getTime();
    }

    /**
     * 转换字符串为时间返回，如果转换不成功则返回默认日期时间
     *
     * @param dateStr
     * @param pattern
     * @param defaultDate
     * @return
     */
    public static Date parseDate(String dateStr, String pattern, Date defaultDate) {

        if (StringUtils.isBlank(dateStr) || "null".equals(dateStr)) {
            return defaultDate;
        }

        DateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(dateStr.trim());
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return defaultDate;
        }
    }

    /**
     * 转换字符串为时间返回，如果转换不成功则返回默认日期时间 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param dateStr
     * @param defaultDate
     * @return
     */
    public static Date parseDate(String dateStr, Date defaultDate) {

        return parseDate(dateStr, "yyyy-MM-dd HH:mm:ss", defaultDate);
    }


    /**
     * 获得对应时间的当天结束时间
     *
     * @param
     * @return
     */
    public static Date getDayEnd(Date date) {

        Calendar calendar = Calendar.getInstance();
        if (date != null) { // 如果是空则用默认当前时间
            calendar.setTime(date);
        } else {
            calendar.setTime(new Date());
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = 23; // 23点
        int minute = 59; // 59分
        int second = 59; // 59秒

        return parseDate(year, month, day, hour, minute, second);
    }

    /**
     * 获得对应时间的当天开始时间
     *
     * @param
     * @return
     */
    public static Date getDayStart(Date date) {

        Calendar calendar = Calendar.getInstance();
        if (date != null) { // 如果是空则用默认当前时间
            calendar.setTime(date);
        } else {
            calendar.setTime(new Date());
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = 00; // 00点
        int minute = 00; // 00分
        int second = 00; // 00秒

        return parseDate(year, month, day, hour, minute, second);
    }


    /**
     * 获得对应时间的月初时间
     *
     * @param
     * @return
     */
    public static Date getMonthBegain(Date date) {

        Calendar calendar = Calendar.getInstance();
        if (date != null) { // 如果是空则用默认当前时间
            calendar.setTime(date);
        } else {
            calendar.setTime(new Date());
        }

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // 从0月开始
        int day = 1;
        int hour = 0; // 0点
        int minute = 0; // 0分
        int second = 0; // 0秒

        return parseDate(year, month, day, hour, minute, second);
    }


    /**
     * 获取时间的年份
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        return calendar.get(Calendar.YEAR);
    }

    /**
     * 根据生日获取生肖
     *
     * @param date
     * @return
     */
    public static String getZodiacById(Date date) { // 根据身份证号，自动返回对应的生肖

        String sSX[] = {"猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊", "猴", "鸡", "狗"};
        int year = getYear(date);
        int end = 3;
        int x = (year - end) % 12;
        return sSX[x];
    }

    /**
     * 增加时间对应的天数
     *
     * @param day
     * @return
     */
    public static Date addDateDay(int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 增加时间对应的天数
     *
     * @param date
     * @param day
     * @return
     */
    public static Date addDateDay(Date date, int day) {
        if (null == date) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 增加时间对应的天数
     *
     * @param date
     * @param year
     * @return
     */
    public static Date addDateYear(Date date, int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, year);
        return calendar.getTime();
    }

    /**
     * 修复开始时间
     *
     * @param startDate
     * @param weekDay
     * @return
     */
    public static Date repairStartDate(Date startDate, String weekDay) {
        return repairDate(startDate, weekDay, true);
    }

    /**
     * 修复结束时间
     *
     * @param endDate
     * @param weekDay
     * @return
     */
    public static Date repairEndDate(Date endDate, String weekDay) {
        return repairDate(endDate, weekDay, false);
    }

    /**
     * 根据单价计算总价
     *
     * @param price
     * @param priceUnit
     * @param startDate
     * @param endDate
     * @param weekDay
     * @param dayTime
     * @return
     * @throws MultiDataSourceException
     */
    public static BigDecimal sumServicePrice(BigDecimal price, String priceUnit,
                                             Date startDate, Date endDate, String weekDay,
                                             String dayTime) throws MultiDataSourceException {
        if (startDate == null || endDate == null) {
            return price;
        }
        if (!startDate.before(endDate)) {
            throw new MultiDataSourceException("开始时间不能在结束时间之后");
        }
        if (StringUtils.isNotBlank(weekDay) && weekDay.length() != 7) {
            throw new MultiDataSourceException("每周的工作日不符合要求");
        }

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        // 计算总工作天数
        int workDays = 0;
        if (StringUtils.isNotBlank(weekDay)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);
            boolean[] workStatus = getWorkStatus(weekDay);
            while (calendar.getTime().before(endDate)) {
                int day = calendar.get(Calendar.DAY_OF_WEEK);
                day = (day + 5) % DAYS_ONE_WEEK; //  修复日历里面的周1——周日为 0——6
                if (workStatus[day]) {
                    workDays++;
                }

                calendar.add(Calendar.HOUR, 24);
            }
        }

        double num;
        if ("元".equals(priceUnit) || "元/次".equals(priceUnit)) {
            num = 1;

        } else if ("元/月".equals(priceUnit)) {
            int years = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
            num = 12 * years + endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);
            double dayDiff = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH) + 1;
            num += dayDiff / 30;

        } else if ("元/天".equals(priceUnit)) {
            num = workDays;

        } else if ("元/26天".equals(priceUnit)) {
            num = (double) workDays / 26;

        } else if ("元/小时".equals(priceUnit)) {
            if (StringUtils.isBlank(dayTime)) {
                throw new MultiDataSourceException("需要确定每天的工作时间段");
            }

            // 每天的小时总数
            double hours = 0;
            // 所有时间段
            String[] dayDiffs = dayTime.split(",");
            for (String dayDiff : dayDiffs) {
                // 开始时间和结束时间
                String[] startEndTime = dayDiff.split("-");
                if (startEndTime.length < 2) {
                    continue;
                }
                String startTime = startEndTime[0];
                String endTime = startEndTime[1];
                String[] startHourMinute = startTime.split(":");
                String[] endHourMinute = endTime.split(":");
                double startHour = Integer.parseInt(startHourMinute[0]);
                double startMinute = startHourMinute.length >= 2 ? Integer.parseInt(startHourMinute[1]) : 0;
                double endHour = Integer.parseInt(endHourMinute[0]);
                double endMinute = endHourMinute.length >= 2 ? Integer.parseInt(endHourMinute[1]) : 0;
                hours = endHour - startHour + (endMinute - startMinute) / 60;
            }

            num = workDays * hours;
        } else if ("元/平米".equals(priceUnit)) {
            num = 1;
        } else {
            throw new MultiDataSourceException("价格单位不符合要求");
        }

        return price.multiply(BigDecimal.valueOf(num)).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 根据月份增减获得新的时间
     *
     * @param date
     * @param monthOffset
     * @return
     */
    public static Date getDateByMonthOffset(Date date, int monthOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, monthOffset);
        return calendar.getTime();
    }

    /**
     * 根据天数增减获得新的时间
     *
     * @param date
     * @param dateOffset
     * @return
     */
    public static Date getDateByDateOffset(Date date, int dateOffset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dateOffset);
        return calendar.getTime();
    }

    /**
     * 根据生日计算年龄
     *
     * @param birthday
     * @return
     */
    public static Integer getAge(Date birthday) {
        if (birthday == null) {
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(birthday);
        int startY = calendar.get(Calendar.YEAR);

        calendar.setTime(new Date());
        int endY = calendar.get(Calendar.YEAR);
        int age = endY - startY;
        return age >= 0 ? age : 0;
    }

    /**
     * 根据生日计算周岁年龄
     *
     * @param birthday
     * @return
     */
    public static int getFullAge(Date birthday) {
        if (birthday == null) {
            return 0;
        }

        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        int start = Integer.parseInt(df.format(birthday));
        int end = Integer.parseInt(df.format(new Date()));
        int fullAge = (end - start) / 10000;
        return fullAge;
    }

    /**
     * 根据年龄计算生日(一月一日起)
     *
     * @param age
     * @return
     */
    public static Date getBirthdayByAge(Integer age) {
        if (age == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        //一月一日12点
        calendar.set(calendar.get(Calendar.YEAR) - age, 1, 1, 12, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 根据年龄获得星座
     *
     * @param birthday
     * @return
     */
    public static String getConstellation(Date birthday) {
        if (birthday == null) {
            return "";
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(birthday);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        if (day < constellationEdgeDay[month]) {
            month = month - 1;
        }
        if (month >= 0) {
            return constellationArr[month];
        }
        // default to return 魔羯
        return constellationArr[11];
    }

    /**
     * 根据星座获得最小月日
     *
     * @param constellation
     * @return
     */
    public static Integer getMinDayByConstellation(String constellation) {
        int minMon = 0;
        int minDay = 0;

        minMon = ArrayUtils.indexOf(constellationArr, constellation);
        if (minMon < 0) {
            return null;
        }
        minDay = constellationEdgeDay[minMon];
        minMon = minMon + 1;
        return Integer.valueOf("" + minMon + minDay);
    }

    /**
     * 根据星座获得最大月日
     *
     * @param constellation
     * @return
     */
    public static Integer getMaxDayByConstellation(String constellation) {
        int maxMon = 0;
        int maxDay = 0;

        maxMon = ArrayUtils.indexOf(constellationArr, constellation);
        if (maxMon < 0) {
            return null;
        }
        maxMon = maxMon == 11 ? 1 : maxMon + 1;
        maxDay = constellationEdgeDay[maxMon];
        maxMon = maxMon + 1;
        return Integer.valueOf("" + maxMon + maxDay);
    }

    /**
     * 修复时间，废除掉非工作日
     *
     * @param date
     * @param weekDay
     * @param isAdd
     * @return
     */
    private static Date repairDate(Date date, String weekDay, boolean isAdd) {
        boolean[] workStatus = getWorkStatus(weekDay);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        for (int i = 0; i < DAYS_ONE_WEEK; i++) {
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            day = (day + 5) % DAYS_ONE_WEEK; //  修复日历里面的周1——周日为 0——6
            if (workStatus[day]) {
                break;
            }

            calendar.add(Calendar.HOUR, 24 * (isAdd ? 1 : -1));
        }

        return calendar.getTime();
    }


    /**
     * 获取一周里每天的工作情况，下标从0——6代表周1——周日，
     * 值true代表工作，false代表不工作
     *
     * @param weekday
     * @return
     */
    private static boolean[] getWorkStatus(String weekday) {
        boolean[] weekStates = new boolean[7];
        for (int i = 0; i < DAYS_ONE_WEEK; i++) {
            weekStates[i] = "1".equals(weekday.substring(i, i + 1));
        }
        return weekStates;
    }

    /**
     * 根据间隔秒数获得时间
     *
     * @param date
     * @param seconds
     * @return
     */
    public static Date getDateBySecondOffset(Date date, int seconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    public static boolean isWork(String weekday, Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = (calendar.get(Calendar.DAY_OF_WEEK) - 2 + 7) % 7;
        return "1".equals(weekday.substring(week, week + 1));
    }

    /**
     * 获取增加对应天数之后的日期
     *
     * @param dayAdd
     * @return
     */
    public static Date getDateAddDay(int dayAdd) {
        long data = System.currentTimeMillis() + dayAdd * 24 * 3600 * 1000;

        return new Date(data);
    }

    /**
     * 获得两个日期相隔的秒数
     *
     * @param minuendDate
     * @param subDate
     * @return
     */
    public static int getSubSeconds(Date minuendDate, Date subDate) {
        Calendar minuend = Calendar.getInstance();
        minuend.setTime(minuendDate);

        Calendar sub = Calendar.getInstance();
        sub.setTime(subDate);

        return minuend.get(Calendar.SECOND) - sub.get(Calendar.SECOND);

    }

    /**
     * 根据订单时间获取开始时间
     *
     * @param serviceStartDate
     * @param dayTime
     * @return
     */
    public static Date getStartTime(Date serviceStartDate, String dayTime) {
        if (serviceStartDate == null) {
            return null;
        }
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(serviceStartDate);
        if (StringUtils.isNotBlank(dayTime)) {
            int startHour = Integer.valueOf(dayTime.substring(0, 2));
            startDate.set(Calendar.HOUR_OF_DAY, startHour);
        }
        return startDate.getTime();
    }

    /**
     * 根据订单时间获取结束时间
     *
     * @param serviceEndDate
     * @param dayTime
     * @return
     */
    public static Date getEndTime(Date serviceEndDate, String dayTime) {
        if (serviceEndDate == null) {
            return null;
        }
        Calendar startDate = Calendar.getInstance();
        startDate.setTime(serviceEndDate);
        if (StringUtils.isNotBlank(dayTime)) {
            String endTime = dayTime.split(":")[1];
            int endHour = Integer.valueOf(endTime.substring(0, 2));
            startDate.set(Calendar.HOUR_OF_DAY, endHour);
        }
        return startDate.getTime();
    }

    /**
     * 根据订单和人天获得结束时间
     *
     * @param
     * @return
     */
    public static Date getStartTimeByManDay(Date serviceStartTime) {
        if (serviceStartTime == null) {
            return null;
        }
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(serviceStartTime);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        int startHour = startTime.get(Calendar.HOUR_OF_DAY);
        if (startHour >= 12) {
            startTime.set(Calendar.HOUR_OF_DAY, 12);
        } else {
            startTime.set(Calendar.HOUR_OF_DAY, 0);
        }
        return startTime.getTime();
    }

    /**
     * 根据订单和人天获得结束时间
     *
     * @param
     * @return
     */
    public static Date getEndTimeByManDay(Date serviceStartTime, BigDecimal manDay) {
        if (serviceStartTime == null) {
            return null;
        }
        Calendar startTime = Calendar.getInstance();
        startTime.setTime(serviceStartTime);
        startTime.set(Calendar.MINUTE, 0);
        startTime.set(Calendar.SECOND, 0);
        startTime.set(Calendar.MILLISECOND, 0);
        int startHour = startTime.get(Calendar.HOUR_OF_DAY);
        if (startHour >= 12) {
            startTime.set(Calendar.HOUR_OF_DAY, 12);
        } else {
            startTime.set(Calendar.HOUR_OF_DAY, 0);
        }
        int hourAdd = (manDay.divide(MAN_DAY).intValue()) * 12;
        startTime.add(Calendar.HOUR_OF_DAY, hourAdd);
        return startTime.getTime();
    }

    /**
     * 获得周一为起始的周几
     *
     * @param date
     * @return
     */
    public static int getMondayStartDateOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int week = (calendar.get(Calendar.DAY_OF_WEEK) + 6) % 7;
        return week == 0 ? 7 : week;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getMondayStartDateOfWeek(DateUtil.getDate("2017-07-30", DateUtil.DATE_PATTERN)));
    }

    public BigDecimal getManDay(Date serviceStartDate, Date serviceEndDate) {
        int hour = (int) (serviceEndDate.getTime() - serviceEndDate.getTime()) / 60 * 60 * 1000;
        int day = hour / 24;
        if (hour % 24 != 0) {
            return new BigDecimal(day + ".5");
        }
        return new BigDecimal(day);
    }

    /**
     * 格式化当前时间
     */
    public static Date getCurrentTime(String format){
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date now = new Date();
        try {
            return sdf.parse(sdf.format(now));
        } catch (ParseException e) {
            return null;
        }
    }
}
