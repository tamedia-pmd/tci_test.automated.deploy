package ch.txg.core.unixtimestamp;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;						  
import java.text.ParseException;

 


public class UnixtimestampGenerator {
 
 
	public static long inputDateInString(String InputDate, String format) throws ParseException
	{
	
		long resultTs=0;
		
		TimeZone cestTimeZone = TimeZone.getTimeZone("CEST");
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setTimeZone(cestTimeZone);							   
		Date parsedDate = dateFormat.parse(InputDate);
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		resultTs=timestamp.getTime();
		
		return resultTs;
	}
																					 
  
  
																	   
													
																	 

														
  
							 
  
	
	
}