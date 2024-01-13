package fr.ensitech.golfloc.utils;

import java.text.SimpleDateFormat;

public final class Dates {
	
	private static final String formatDate = "dd/MM/yyyy";
	
	private Dates() {
		
	}

	public static final java.util.Date convertStringToDate(String dateStr) throws Exception {
		
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		return formatter.parse(dateStr);
	}
	
	public static final String convertDateToString(java.util.Date date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(formatDate);
		return formatter.format(date);
	}

	public static final java.util.Date convertDateSqlToDateUtil(java.sql.Date dateSql) {
		return new java.util.Date(dateSql.getTime());
	}
	
	public static final java.sql.Date convertDateUtilToDateSql(java.util.Date dateUtil) {
		return new java.sql.Date(dateUtil.getTime());
	}

}

