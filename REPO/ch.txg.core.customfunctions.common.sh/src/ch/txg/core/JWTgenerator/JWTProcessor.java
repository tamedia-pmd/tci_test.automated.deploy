package ch.txg.core.JWTgenerator;

import java.util.Base64;
import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.security.Signature;

public class JWTProcessor
{
	static String privateKey = "-----BEGIN PRIVATE KEY-----"
                             + "MIIJQgIBADANBgkqhkiG9w0BAQEFAASCCSwwggkoAgEAAoICAQDTJgWnCJs7+Gcx"
                             + "Ns5p/i0k+mNhqblushClG77LPdS31txHFzvxDOMWSh7vQquTTlcWVHsYyjDr8QYJ"
                             + "8NrihkHGScP9vqBSlKy5SNOqoZqKbVoS4wflcKXcJkvzdcWHtXwFFUrgWtDegb7y"
                             + "GCXIk8y5MZKK+xEgKOxv8zRfmVaB607x+V5AcL6tLgYObSh1hQsUoPLnAVe0dkKY"
                             + "6Bhd89qDaXBWinhkKwiJqkHeK1x58OLeHzEBeBSXM5YaDfmLD21VF/4SGXYbHzfg"
                             + "hAHJxDxFE4LvlSlQzlyxCvr1qGLeid0tdymfU/O5kdezve7/D3UzQOj6Gru4ylxI"
                             + "dGb/Wq+nAZNcu02Jz9CLa1xkUF1OEsC/qOr3tMoRInTseO3rMUWBGdhwoHjM/7nA"
                             + "5GEZb3a8nJsCPO8250Ric2eV/kZgUFdGuQw20Oc4DFNADor3F/DAS71TT8tnoPx2"
                             + "cLPMY0b4LuZw4uhXJ9AuJgZSD82mvJjCuS/ET697YqUvYYQBa8m2M2AKGZwv7Sy1"
                             + "FUEKOJ4iXShSCEA/hFHLarvIPN5r827pSZXhQmQwFxvF+PwjYKUa5Ly9mvG/3WTN"
                             + "cud+j3jRTN4yYxCmRDHLO24O35kaYPBP2rWw6dKV6eWg3jqVRl7wI18BDPHOYkEA"
                             + "n2WYE8U2qMsHIY0u4pA45w5O56NgtQIDAQABAoICAB30Cd89MFBcl776iGOu5BaX"
                             + "0ptbKWwTpJrXicRpPBDsUQlguWcjrd9Omn7UlWRrO512VJQasr/1zCaC3Xo/E3ue"
                             + "oUgQz8uHPc13EFfnXvX+N0XwoQEibtUBNcsOzPvIehR6hcm3+GNoQn8zf8CpfuRS"
                             + "rPg8T5yhUZE8DhT1urjpeaSH/J/lnpcQrVlEkPXC7uR5RJwSq691C1OHsVU6r6fu"
                             + "wDn2oMeM90QYPAnZWuANlRFvmVMdBsbAUa3Wh6z7fkYRBkDpDcEhN5sI/vvxc59U"
                             + "+kEkhtzouWuAJEPPJF9vbUny2pu4nB2aG+BqtEnIlpazwZkhS7oWMuswYCAfbEVh"
                             + "0sa5Yvvyf4c16w9+bbmQUnYnd4ktZ9bLmHbAMD5R54pMFh7lPcoj5MkKNUNwjAmd"
                             + "fpqpxFOl/6uyj8WSikjQEhJwZWN80ZzS4i4adFQ0tPxt09SECc5k1pjt+QHv/siF"
                             + "RPqL7paB/9LlFYeXQWVR8hM+CbRfZ+2jhKn2BJRIRUmdeKlAuXNx9TlFWX7NIKZn"
                             + "GQj+kgYrm2vcfEuFyjWTE0iXJ16tcEqToCbpo5CG1Pt1prJz39TiKkcYH4N29k1R"
                             + "iVzTildIlSheMGK2KYqf5wWaXIdISwBGnRy3l/Zj9djJjEZQfRQafkldhEQYtvYQ"
                             + "cMOUVm/zRcdht5Ht2eytAoIBAQDrYLjMnoX8sQ5d/8Vi8rCOj2hM81gl818duvL+"
                             + "vyO+QU19PDKjfKYbQI2zYVGu4r/b9o2W5Rze59ZUUmtJrxFqc3sNufd8NxU/Xl+6"
                             + "Oq0Mu3hywZkvQ/vXjN+EIvg72d3IGaZ2s2TvuoeVPAGzP+2yq70NmcEYfqYGlPfp"
                             + "NQuSD/yGBI9FSMviwgT9dQUn8R2455J/4fwGCvq2VsM6Ib4STOQPHkpT+EMakBwF"
                             + "6IKNMG/6Xx0kJoSBhkXqiF1LJnAMFWVGUGsqcg7qBbRAqpe+YzspCfhDPiPbm9YU"
                             + "3LHxxDfilkXuJQOJ3erdLXtnqvKVwQEJjHSN9czCNTCxV/jfAoIBAQDlpdzKCqUP"
                             + "lBU65gNPMKAMYklmjK8lEsXcGqSfIsl7nIYegzZu9tO3NOnA8qC9QrmM86Tol/tn"
                             + "Z/upkxyZOXTjONbrIFsUxlbwj2hIkk1DtMuHXDPy67/dA5nFqNCaELTZJGoh8GzR"
                             + "1cxR1POggyIbGOizE8HQqrSbeEnmb8ZT8Oj9gZ/auOIIeIg0lAkRdkMuqcRcwEVE"
                             + "Ba4mXF+EtpVyhpANOhtbKULfcp55DXSl0KEKTYsGhdIlEZLWv8q1eQE9hDMbgdb0"
                             + "girBIIQTGR0uVRYyrBbM5t8320gxS6jm1xzUkRzL0uqiIX+qqKI/sVVM+qiGQW3R"
                             + "C3ddaGyDH5TrAoIBADfZJT/3xRbl8+C91Vna96V7xWWsv5eRnVnyh8GGHLcBy58R"
                             + "b3p3EvqYYIbVaooyZw+L+qB5EFabb7FmNOqwzVMSv3nMTDp+312/yLrpPTIuMTAZ"
                             + "W31phhHo7QfvPDMHSKY339V+T07KVW1FZangW0fvk9XsS3LCaiZZplEBMxOfE23B"
                             + "GzV3Rlkxa/mvkF+fyEudNpd9SU0twbsN7xiV9QFmmR41M7Gk0Fc3Jk8IkiFqAsfH"
                             + "VaTENuB8uY4iePBh2Gglk3gRkLvrgDpqzBAj6glUhi8AnJVn4x4yZaNhkh8pTZFH"
                             + "0qPkyGgn7Zg5OHSqjRCXUOWxG96rGcBi2oDZvMUCggEAcLH5bIjWwZNY68NuglEJ"
                             + "aapd/N4zIQ1aXY4RlSp8F5YTVyvq7X9hHDnyMEagzN/OsLXsClxy+ibUID5aPXMt"
                             + "wkflaBNDvykqBvlJIrvxALXbXzzHHYHFcM9QiegzHjful9S/5JHYxQyXLN1FBVL0"
                             + "zLlnkGG0rhLneJgKI/RcLls96xHOgYZTHfcQWEUBQjl39JFJFH8iPqRO9vJDyZvU"
                             + "MRSJ4oPOYukXsRHNZPhOcwSz0puFCxlmq9w44/vQnUeZbTfP4MLR2sF5+7ZaOgkM"
                             + "Ae2tbAZ5VepCRmXswowvgcC0nDfuGrQRgqjPvs7seDcs9g7X3rKDEp/+3q7c+Wfp"
                             + "KwKCAQEAg+nTC6ugeXZNd78puObo7XTzzuDAserfa/3mse6hR/b0syn33JgaHRgA"
                             + "u0HcHaLx1l9GKt+93JFztVOcAySSwHmYcpcEkXFnxcHfIf46SrPKRmzpAmPrEiYy"
                             + "tnovB+9W1xVgbEF3DOppHBt27VlhsjoCt9y+2nIzZvZRLMyMyC1KWxo/7ySaNJvP"
                             + "mPBd5maYIAa/iN495Sypsagcs6mjWwpkMwRLsCP3RroVianX4nJOr+CgMYcAcU2k"
                             + "6A9ll3rjff0KEWC6RodRqOm61aX2Krl2/VpKlVwfZY3lgc2Z5gSN7Q8ZQiZzR9Yj"
                             + "7Vg88Aj930BNO0ER2NCg8mjH3wUDsw=="
                             + "-----END PRIVATE KEY-----";

	static String publicKey  = "-----BEGIN PUBLIC KEY-----"
                             + "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA0yYFpwibO/hnMTbOaf4t"
                             + "JPpjYam5brIQpRu+yz3Ut9bcRxc78QzjFkoe70Krk05XFlR7GMow6/EGCfDa4oZB"
                             + "xknD/b6gUpSsuUjTqqGaim1aEuMH5XCl3CZL83XFh7V8BRVK4FrQ3oG+8hglyJPM"
                             + "uTGSivsRICjsb/M0X5lWgetO8fleQHC+rS4GDm0odYULFKDy5wFXtHZCmOgYXfPa"
                             + "g2lwVop4ZCsIiapB3itcefDi3h8xAXgUlzOWGg35iw9tVRf+Ehl2Gx834IQBycQ8"
                             + "RROC75UpUM5csQr69ahi3ondLXcpn1PzuZHXs73u/w91M0Do+hq7uMpcSHRm/1qv"
                             + "pwGTXLtNic/Qi2tcZFBdThLAv6jq97TKESJ07Hjt6zFFgRnYcKB4zP+5wORhGW92"
                             + "vJybAjzvNudEYnNnlf5GYFBXRrkMNtDnOAxTQA6K9xfwwEu9U0/LZ6D8dnCzzGNG"
                             + "+C7mcOLoVyfQLiYGUg/NpryYwrkvxE+ve2KlL2GEAWvJtjNgChmcL+0stRVBCjie"
                             + "Il0oUghAP4RRy2q7yDzea/Nu6UmV4UJkMBcbxfj8I2ClGuS8vZrxv91kzXLnfo94"
                             + "0UzeMmMQpkQxyztuDt+ZGmDwT9q1sOnSlenloN46lUZe8CNfAQzxzmJBAJ9lmBPF"
                             + "NqjLByGNLuKQOOcOTuejYLUCAwEAAQ=="
                             + "-----END PUBLIC KEY-----";

	static String jwtHeader  = "{"
                             + "   \"alg\": \"RS256\","
                             + "   \"kid\": \"E5679ABB367BBDD07994C89AB314BF2B12CECBC1\","
                             + "   \"typ\": \"JWT\","
                             + "   \"x5t\": \"5WeauzZ7vdB5lMiasxS_KxLOy8E\""
                             + "}";

	static String jwtPayload = "{"
                             + "   \"nbf\": ${nbf},"
                             + "   \"exp\": ${exp},"
                             + "   \"iss\": \"https://mock-service.fae.pos-dev.de\","
                             + "   \"aud\": ["
                             + "     \"https://orangeidp/resources\","
                             + "     \"orange_hub\","
                             + "     \"oauth2-resource\""
                             + "   ],"
                             + "   \"client_id\": \"${client_id}\","
                             + "   \"sub\": \"842922e2-911b-4d92-8f8c-0cd7620a00e9\","
                             + "   \"auth_time\": ${auth_time},"
                             + "   \"idp\": \"orangeidp\","
                             + "   \"session_id\": \"4a7da23a-5174-4f9b-85db-781c00b0936d\","
                             + "   \"principal_id\": \"${principal_id}\","
                             + "   \"origin_user_id\": \"${origin_user_id}\","
                             + "   \"principal_name\": \"${principal_name}\","
                             + "   \"origin_user_name\": \"${origin_user_name}\","
                             + "   \"principal_customer_id\": \"${principal_customer_id}\","
                             + "   \"origin_customer_id\": \"${origin_customer_id}\","
                             + "   \"principal_partner_id\": \"${principal_partner_id}\","
                             + "   \"origin_partner_id\": \"${origin_partner_id}\","
                             + "   \"customer_group\": \"${customer_group}\","
                             + "   \"scope\": ["
                             + "     \"openid\","
                             + "     \"orange_hub\","
                             + "     \"oauth2-resource\""
                             + "    ],"
                             + "   \"amr\": ["
                             + "     \"external\""
                             + "    ],"
                             + "   \"authorities\": ["
                             + "     \"USER\","
                             + "     \"ADMIN_PROMOTION\""
                             + "    ],"
                             + "   \"origin_roles\": ["
                             + "     \"USER\","
                             + "     \"ADMIN_PROMOTION\""
                             + "    ]"
                             + "}";

	public static String encodeJWTHeader(String strPlainHeader)
    {
        try {
        	return Base64.getEncoder().encodeToString(strPlainHeader.replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "").getBytes());
        } catch(Exception ex) {
        	return "";
        }
    }
	public static String encodeJWTPayload(String strPlainPayload)
    {
        try {
        	return Base64.getEncoder().encodeToString(strPlainPayload.replaceAll("\\s+(?=([^\"]*\"[^\"]*\")*[^\"]*$)", "").getBytes());
        } catch(Exception ex) {
        	return "";
        }
    }
	public static String signJWT(String strEncodedHeader, String strEncodedPayload, String strPrivKey)
    {
        try {
            String realPrivKey = "";
    		
            if(strPrivKey.length() > 0)
            {
            	realPrivKey = strPrivKey.replaceAll("-----END PRIVATE KEY-----", "")
            	                        .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                                        .replaceAll("\n", "");
            } else {
            	realPrivKey = privateKey.replaceAll("-----END PRIVATE KEY-----", "")
                                        .replaceAll("-----BEGIN PRIVATE KEY-----", "")
                                        .replaceAll("\n", "");
            }
        	String strPlainHeader  = new String(Base64.getDecoder().decode(strEncodedHeader));

	        String strAlgorithm    = "SHA256withRSA";
	        if(strPlainHeader.contains("RS256"))
	        {
	        	strAlgorithm       = "SHA256withRSA";
	        } else if(strPlainHeader.contains("RS515")) {
	        	strAlgorithm       = "SHA512withRSA";
	        } else if(strPlainHeader.contains("RS384")) {
	        	strAlgorithm       = "SHA384withRSA";
	        }
	        
	        PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(realPrivKey));
	        KeyFactory kf                   = KeyFactory.getInstance("RSA");
	        Signature signature             = Signature.getInstance(strAlgorithm);

	        signature.initSign(kf.generatePrivate(privKeySpec));
	        signature.update(new String(strEncodedHeader + "." + strEncodedPayload).getBytes());

	        return Base64.getUrlEncoder().encodeToString(signature.sign());

        } catch(Exception ex) {
        	return "";
        }
    }
    public static String getJWT(String strPlainHeader, String strPlainPayload, String strPrivKey)
    {
        try {
        	String strEncodedHeader  = encodeJWTHeader(strPlainHeader);
        	String strEncodedPayload = encodeJWTPayload(strPlainPayload);
        	
        	return new String(strEncodedHeader + "." + strEncodedPayload + "." + signJWT(strEncodedHeader, strEncodedPayload, strPrivKey));
        } catch(Exception ex) {
        	return "";
        }
    }

	public static String encodeHoffmanJWT(String client_id, String principal_id, String origin_user_id, String principal_name, String origin_user_name, String principal_customer_id, String origin_customer_id, String principal_partner_id, String origin_partner_id, String customer_group)
    {
		String strClientID            = "Mock-Client-Code";
		String strPrincipalID         = "mho";
		String strOriginUserID        = "842922e2-911b-4d92-8f8c-0cd7620a00e9";
		String strPrincipalName       = "Mario Hoffmann";
		String strOriginUserName      = "Mario Hoffmann";
		String strPrincipalCustomerID = "Hoffmann Group";
		String strOriginCustomerID    = "5fc198bd-72b6-4ef2-99e5-faa6914a5cc0";
		String strPrincipalPartnerID  = "Deutschland-Sud";
		String strOriginPartnerID     = "f15deb56-7b11-4c01-9e5a-b4c04da7344d";     		
		String strCustomerGroup       = "b2b";
		String strPlainPayload        = jwtPayload;

        try {
        	Instant instant = Instant.now(Clock.systemUTC());
        	strPlainPayload = strPlainPayload.replace("${nbf}", String.valueOf(instant.getEpochSecond()));
        	strPlainPayload = strPlainPayload.replace("${auth_time}", String.valueOf(instant.getEpochSecond()));
        	instant = instant.plus(7, ChronoUnit.DAYS);
        	strPlainPayload = strPlainPayload.replace("${exp}", String.valueOf(instant.getEpochSecond()));

        	if(client_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${client_id}", client_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${client_id}", strClientID);
        	}
        	if(principal_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${principal_id}", principal_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${principal_id}", strPrincipalID);
        	}
        	if(origin_user_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${origin_user_id}", origin_user_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${origin_user_id}", strOriginUserID);
        	}
        	if(principal_name.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${principal_name}", principal_name);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${principal_name}", strPrincipalName);
        	}
        	if(origin_user_name.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${origin_user_name}", origin_user_name);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${origin_user_name}", strOriginUserName);
        	}
        	if(principal_customer_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${principal_customer_id}", principal_customer_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${principal_customer_id}", strPrincipalCustomerID);
        	}
        	if(origin_customer_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${origin_customer_id}", origin_customer_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${origin_customer_id}", strOriginCustomerID);
        	}
        	if(principal_partner_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${principal_partner_id}", principal_partner_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${principal_partner_id}", strPrincipalPartnerID);
        	}
        	if(origin_partner_id.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${origin_partner_id}", origin_partner_id);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${origin_partner_id}", strOriginPartnerID);
        	}
        	if(customer_group.length() > 0)
        	{
        		strPlainPayload = strPlainPayload.replace("${customer_group}", customer_group);
        	} else {
        		strPlainPayload = strPlainPayload.replace("${customer_group}", strCustomerGroup);
        	}
        	return getJWT(jwtHeader, strPlainPayload, privateKey);
        			
        } catch(Exception ex) {
        	return "";
        }
    }
    public static boolean verifyJWT(String strJWT, String strModulusB64u, String strExponentB64u, String strPublicKeyRSA)
    {
    	boolean retValue = false;

        try {
	        String[] jwtValues    = strJWT.split("\\.");
	        String strPlainHeader = new String(Base64.getDecoder().decode(jwtValues[0]));
	
	        String strAlgorithm   = "SHA256withRSA";
	        if(strPlainHeader.contains("RS256"))
	        {
	        	strAlgorithm      = "SHA256withRSA";
	        } else if(strPlainHeader.contains("RS515")) {
	        	strAlgorithm      = "SHA512withRSA";
	        } else if(strPlainHeader.contains("RS384")) {
	        	strAlgorithm      = "SHA384withRSA";
	        }
	        byte[] jwtToken       = new String(jwtValues[0] + "." + jwtValues[1]).getBytes();
	        byte[] jwtSignature   = Base64.getUrlDecoder().decode(jwtValues[2]);

	        Signature signature   = Signature.getInstance(strAlgorithm);
	        PublicKey publicKey   = getPublicKey(strModulusB64u, strExponentB64u, strPublicKeyRSA);
	        
	        signature.initVerify(publicKey);
	        signature.update(jwtToken);
        
	        retValue = signature.verify(jwtSignature);
	        if(!retValue) {
		        publicKey   = getPublicKey("", "", strPublicKeyRSA);

		        signature.initVerify(publicKey);
		        signature.update(jwtToken);
	        
		        retValue = signature.verify(jwtSignature);
	        }
	        return retValue;

        } catch(Exception ex) {
        	return false;
        }
    }

    private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
    public static String toHexFromBytes(byte[] bytes)
    {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ )
        {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    
    public static PublicKey getPublicKey(String strModulusB64u, String strExponentB64u, String strPublicKeyRSA)
    {
    	try {
			byte exponentB[]      = Base64.getUrlDecoder().decode(strExponentB64u);
			byte modulusB[]       = Base64.getUrlDecoder().decode(strModulusB64u);
			BigInteger exponent   = new BigInteger(toHexFromBytes(exponentB), 16);
			BigInteger modulus    = new BigInteger(toHexFromBytes(modulusB), 16);
			
			RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
			KeyFactory factory    = KeyFactory.getInstance("RSA");

            return factory.generatePublic(spec);

        } catch(Exception ex1) {
        	try {
                String realPubKey = "";
        		
                if(strPublicKeyRSA.length() > 0)
                {
                	realPubKey    = strPublicKeyRSA.replaceAll("-----END PUBLIC KEY-----", "")
                                                   .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                                                   .replaceAll("\n", "");
                } else {
                	realPubKey    = publicKey.replaceAll("-----END PUBLIC KEY-----", "")
                                             .replaceAll("-----BEGIN PUBLIC KEY-----", "")
                                             .replaceAll("\n", "");
                }
	            X509EncodedKeySpec spec = new X509EncodedKeySpec(Base64.getDecoder().decode(realPubKey));
		        KeyFactory keyFactory   = KeyFactory.getInstance("RSA");
	
		        return keyFactory.generatePublic(spec);

            } catch(Exception ex2) {
            	return null;
            }
        }
   	}
/*
    public static void main(String[] args)
    {
    	PublicKey RSA1 = getPublicKey("", "", publicKey);
    	
   		//PublicKey RSA2 = getPublicKey(jArray.get(i).getAsJsonObject().get("n").getAsString(),jArray.get(i).getAsJsonObject().get("e").getAsString(),"");
		PublicKey RSA2 = getPublicKey("17bEZ9xX7YYbeMbFKgyNf1BHo7WSnM3gSKUWK3J18FZlVWnr2oxUuoL3nx0IbdDQb1UPAQfKPC7ndxbKFDJgyyFTnUsJM4XOVJ_zkdLADN6VXxA5nm1on1s-auM1ASj4MSjyFfFyECR71d6K9HRXqRGs52an4RMzR9wLPdSLrnR8Ztuxp7rgELL2NWEIcElhr5XUvGIokpWZVET5P58lpsCrhBY1_CQLmN5n6bqeOTj9SaVtRplnMpWLpD8dffmBZ3BMI-LVWXQSqQ_OAOyYJg4eY5HRu6Bbw52sV5mBtFXlQOBZlmgGr0iqPpeqYExJiSFiyu7PK84AF1oFBl0bb9ftY1F6BpAR5nP4V8di-9vOxxR1wW7MJx7hERVpM0JlsCFsH4gecwc36834q3fhniVYAiVxG-J4Ni-9DoxVp_dveB2LlbySLf_sbv4VNRNEEWdg0rhuLjpHlyaf24DdSuzcO2GpWSc1GhD0LjzFMXT3wv0wcfnHyAP5QhfjfAr068ZqWoBWHq3hqFTvuwpT6CMmAPd8AZM6LvUX27P_jF3W1S7G96Scm7HIwMlZ-sLIAw-BzDlvNjif4rB7r88ncfhleQGxozgdrYmHdQ0VJmS3Pom-cyCmt5zIE9CrAX8gvS9D1rQzHvRnPtrBG9VWOg25cZN_G6wOkpdwNbEidmk","AQAB","");

    	String token = encodeHoffmanJWT("", "", "1120258", "", "", "", "", "");
    	Boolean verify = verifyJWT(token, "", "", publicKey);
    	System.out.println("JWT = " + token + "Verified = " + verify);
    }
*/
}
