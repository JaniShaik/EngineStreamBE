package com.ste.inventorymanagement.util;


import java.util.Base64;


public class PasswordUtils {


    public static String generateencryptPassword(String password) {
        Base64.Encoder encoder = Base64.getEncoder();       
        String str = encoder.encodeToString(password.getBytes());  
       return str;  
         
         
    }  
    
    public static String generatedecryotPassword(String encodedpassword) {
    	Base64.Decoder decoder = Base64.getDecoder();    
        String dStr = new String(decoder.decode(encodedpassword));  
        return dStr; 
    }
    
    public static boolean verifyUserPassword(String providedPassword,
            String securedPassword) {
    	boolean returnValue = false;
        Base64.Encoder encoder = Base64.getEncoder();       
        String str = encoder.encodeToString(providedPassword.getBytes());  
    	returnValue = str.equalsIgnoreCase(securedPassword);
    	return returnValue;
    }
    
    
}

