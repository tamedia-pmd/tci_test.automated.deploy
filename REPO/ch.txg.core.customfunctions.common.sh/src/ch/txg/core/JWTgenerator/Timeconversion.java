package ch.txg.core.JWTgenerator;


import java.time.Instant;

/* Create unix timestamp */

public class Timeconversion {
	    
		public static long Gettimestamp()
		{			
			long iat = Instant.now().getEpochSecond();			
			return iat;


		}
	
		
    	
}