package cl.cla.web.firma.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class DateUtil {

    public DateUtil() {
        super();
    }

    public static String dateNumberToDateFormat(String date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyyMMdd");
            return formatterFrom.format(formatterFrom.parse(date));
        } catch (ParseException e) {
            return "";
        }
    }

    public static String dateNumberToDateFormat2(String date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatterTo = new SimpleDateFormat("dd/MM/yyy");
            return formatterTo.format(formatterFrom.parse("" + date));
        } catch (ParseException e) {
            return "";
        }
    }

    public static String dateNumberToDateFormat3(String date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatterTo = new SimpleDateFormat("yyyyMMdd");
            return formatterTo.format(formatterFrom.parse("" + date));
        } catch (ParseException e) {
            return "";
        }
    }

    public static String dateNumberToDateFormat(int date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat formatterTo = new SimpleDateFormat("dd/MM/yyy");
            return formatterTo.format(formatterFrom.parse("" + date));
        } catch (ParseException e) {
            return "";
        }
    }

    public static Date dateNumberToDate(int date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyyMMdd");
            return formatterFrom.parse("" + date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static int dateToNumber(Date date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("yyyyMMdd");
            return Integer.parseInt(formatterFrom.format(date));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static int dateStringFormatToNumber(String date) {
        try {
            SimpleDateFormat formatterFrom = new SimpleDateFormat("dd/MM/yyy");
            SimpleDateFormat formatterTo = new SimpleDateFormat("yyyyMMdd");
            return Integer.parseInt(formatterTo.format(formatterFrom.parse(date)));
        } catch (ParseException e) {
            return 0;
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String dateToStringFormat(Date date) {
        if (date != null) {
            SimpleDateFormat formatterTo = new SimpleDateFormat("dd/MM/yyy");
            return formatterTo.format(date);
        } else {
            return "";
        }
    }

    public static List<String> generateYearList() {
        List<String> years = new ArrayList<String>();
        int yearToday = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = 1900; i <= yearToday; i++) {
            years.add("" + i);
        }
        return years;
    }

    public static Date xmlGregorianCalendarToDate(XMLGregorianCalendar calendar) {
        if (calendar == null) {
            return null;
        }
        return calendar.toGregorianCalendar().getTime();

    }

    public static Date stringDateToDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date result = null;
        try {

            if (date != null) {
                if (date.length() > 9) {
                    result = sdf.parse(date.substring(0, 10));
                }
            }
        } catch (ParseException ex) {
            return null;
        }
        return result;
    }

    public static XMLGregorianCalendar dateToxmlGregorianCalendar(Date date) {
        if (date == null) {
            return null;
        }
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(date);
        XMLGregorianCalendar date2 = null;
        try {
            date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
        } catch (DatatypeConfigurationException ex) {
            return null;
        }
        return date2;
    }

    public static int calcularEdad(String fechaNacimiento) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar today = Calendar.getInstance();
        Calendar fechaNac = Calendar.getInstance();;
        try {
            fechaNac.setTime(formatter.parse(fechaNacimiento));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int diffYear = today.get(Calendar.YEAR) - fechaNac.get(Calendar.YEAR);
        int diffMonth = today.get(Calendar.MONTH) - fechaNac.get(Calendar.MONTH);
        int diffDay = today.get(Calendar.DAY_OF_MONTH) - fechaNac.get(Calendar.DAY_OF_MONTH);
        // Si este en ese año pero todavia no los ha cumplido
        if (diffMonth < 0 || (diffMonth == 0 && diffDay < 0)) {
            diffYear = diffYear - 1; // no aparecian los dos guiones del
            // postincremento <img draggable="false" class="emoji" alt="?" src="https://s.w.org/images/core/emoji/72x72/1f610.png">
        }
        return diffYear;
    }

    public static void main(String[] args) {
        System.out.println(DateUtil.stringDateToDate("2017-01-09T19:34:28.000-03:00"));
//        logger.debug("DateUtil: " + DateUtil.getDateDiffMonths2("04/10/1951"));
//        logger.debug("DateUtil: " + DateUtil.calcularEdad("26/10/1951"));
    }
}
