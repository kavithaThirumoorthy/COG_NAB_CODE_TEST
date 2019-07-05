package com.nab.challenge.currencyAnalyser.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class CurrencyAnalyserConstant {
	
	public static final DecimalFormat TWO_DECIMAL = new DecimalFormat("0.00");
	public static final DateFormat DATE_ONLY_FORMAT = new SimpleDateFormat("yyyy/MM/dd");
	public static final DateFormat TIME_ONLY_FORMAT = new SimpleDateFormat("hhmm");
	public static final DateFormat TIME_ONLY_FORMAT_AMPM = new SimpleDateFormat("hh:mm a");
	public static final String DOLLOR_SYMBOL="$";
	public static final String ZERO_APPEND="0";
	public static final int MAX_TIME_DIGIT=4;
	
	

}
