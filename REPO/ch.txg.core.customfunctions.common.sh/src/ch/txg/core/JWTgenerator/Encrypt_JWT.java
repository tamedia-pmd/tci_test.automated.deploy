package ch.txg.core.JWTgenerator;

import java.util.Base64;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.Signature;




public class Encrypt_JWT {	
	
	
	/* --------------Method for generating the JWT with RS256 algorithm.----------*/
	
	public static String signJWT(String base64jsonHeader, String base64JsonPayload, byte[] strPrivKey)
    {
        try {

    		

            	

	        String strAlgorithm    = "SHA256withRSA";
	      
	        
	        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(strPrivKey);
	        KeyFactory kf                   = KeyFactory.getInstance("RSA");
	        Signature signature             = Signature.getInstance(strAlgorithm);

	        signature.initSign(kf.generatePrivate(privKeySpec));
	        signature.update(new String(base64jsonHeader + "." + base64JsonPayload).getBytes());

	        return Base64.getUrlEncoder().encodeToString(signature.sign());

        } catch(Exception ex) {
        	return "";
        }
    }
	
	public static String getJWT(String base64JSONHeader, String base64JsonPayload, byte[] strPrivKey)
	{


		
    	return new String(base64JSONHeader + "." + base64JsonPayload + "." + signJWT(base64JSONHeader, base64JsonPayload, strPrivKey));

	}
	
}