package smm.topScoreRestful.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

public class commonUtility {
	
	/**
	 * String to Date Object converter with yyyy-MM-dd HH:mm:ss(ie:2020-12-01 12:00:00) format
	 * @param strDate
	 * @return
	 * @throws ParseException
	 */
	public static Date stringToDate(String strDate) throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(strDate);
		return date;
	}
	
	/**
	 * all Strings in List convert to LowerCase
	 * @param strings
	 * @return
	 */
	public static List<String> stringListToLowerCase(List<String> strings)
	{
	    ListIterator<String> iterator = strings.listIterator();
	    while (iterator.hasNext())
	    {
	        iterator.set(iterator.next().toLowerCase());
	    }
	    return strings;
	}
}
