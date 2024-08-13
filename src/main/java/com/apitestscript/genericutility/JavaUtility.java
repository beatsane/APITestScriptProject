package com.apitestscript.genericutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * @author CHIDUSD
 */
public class JavaUtility {

	/**
	 * get the random number , in the range of 0-5000
	 * @return
	 */
	public int getRandomNumber() {
		Random random = new Random();
		int randomNumber = random.nextInt(5000);
		return randomNumber;
	}

	/**
	 * get the system date based on YYYY-DD-MM format
	 * @return
	 */
	public String getSystemDateYYYYDDMM() {
		Date date = new Date();
		SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String systemDate = simDateFormat.format(date);
		return systemDate;
	}

	/**
	 * get the TAT date based on YYYY-DD-MM format
	 * @param days
	 * @return
	 */
	public String getRequiredDate(int days) {
		SimpleDateFormat simDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = simDateFormat.getCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, days);
		String requiredDate = simDateFormat.format(calendar.getTime());
		return requiredDate;
	}
}
