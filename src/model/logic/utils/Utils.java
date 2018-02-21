package model.logic.utils;

import java.time.LocalDateTime;
import java.util.StringTokenizer;

import model.world.DateTimeRange;

public class Utils {
	public static LocalDateTime[] convertDateTimeRangeToLocalDateTimeArray(DateTimeRange range) {
		LocalDateTime array[] = new LocalDateTime[2];
		
		int year;
		int month;
		int day;
		int hour;
		int minutes;
		int seconds;
		int nanoSeconds;
		
		String aux;
		String date = range.getInitialDate();
		String time = range.getInitialHour();
		
		StringTokenizer tokenizer = new StringTokenizer(date, "-");
		year = Integer.parseInt(tokenizer.nextToken());
		month = Integer.parseInt(tokenizer.nextToken());
		day = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(time, ":");
		hour = Integer.parseInt(tokenizer.nextToken());
		minutes = Integer.parseInt(tokenizer.nextToken());
		aux = tokenizer.nextToken();
		tokenizer = new StringTokenizer(aux, ".");
		seconds = Integer.parseInt(tokenizer.nextToken());
		//System.out.println("SIGUIENTE TOKEN: "+tokenizer.nextToken());
		nanoSeconds = Integer.parseInt(tokenizer.nextToken());
		LocalDateTime initialDateTime = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoSeconds);
		
		array[0] = initialDateTime;
		
		date = range.getEndDate();
		time = range.getEndHour();
		
		tokenizer = new StringTokenizer(date, "-");
		year = Integer.parseInt(tokenizer.nextToken());
		month = Integer.parseInt(tokenizer.nextToken());
		day = Integer.parseInt(tokenizer.nextToken());
		tokenizer = new StringTokenizer(time, ":");
		hour = Integer.parseInt(tokenizer.nextToken());
		minutes = Integer.parseInt(tokenizer.nextToken());
		aux = tokenizer.nextToken();
		tokenizer = new StringTokenizer(aux, ".");
		seconds = Integer.parseInt(tokenizer.nextToken());
		//System.out.println("SIGUIENTE TOKEN: "+tokenizer.nextToken());
		nanoSeconds = Integer.parseInt(tokenizer.nextToken());
		LocalDateTime endDateTime = LocalDateTime.of(year, month, day, hour, minutes, seconds, nanoSeconds);
		
		array[1] = endDateTime;
		
		
		
		return array;
	}
}
