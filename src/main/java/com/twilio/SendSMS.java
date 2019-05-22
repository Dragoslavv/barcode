package com.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.database.Database;
import java.security.*;
import java.math.*;

public class SendSMS {

    public static final String ACCOUNT_SID = "AC41b28e65ca277ed4bdeb63ad53e2f3e5";
    public static final String AUTH_TOKEN = "6490e3a894a5b1a423ecfed57ff1a1b4";
    public static int BODY = (int)(Math.random() * 50 + 1);

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        if(Database.getDbCon() != null) {

            Message message = Message.creator(
                    new PhoneNumber("+381631111955"),
                    new PhoneNumber("+16145240069"),
                    "Barcode id [ " + MD5("" + BODY) + " ] ").create();

            System.out.println(message.getSid());
            System.out.println(MD5("" + BODY));
        }
    }

    public static String MD5(String md5) {
        try {

            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();

        } catch (java.security.NoSuchAlgorithmException e) {

            e.getMessage();

        }
        return null;
    }
}