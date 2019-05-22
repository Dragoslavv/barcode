package com.twilio;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import com.database.Database;
import java.security.*;
import java.math.*;
import java.sql.ResultSet;

public class SendSMS {

    static final String ACCOUNT_SID = "AC41b28e65ca277ed4bdeb63ad53e2f3e5";
    static final String AUTH_TOKEN = "6490e3a894a5b1a423ecfed57ff1a1b4";
    static int BODY = (int)(Math.random() * 50 + 1);

    public static void main(String[] args) {

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Database cnn = Database.getDbCon();

        if(cnn != null) {

            Message message = Message.creator(
                    new PhoneNumber("+381695013001"),
                    new PhoneNumber("+16145240069"),
                    "Barcode id [ " + MD5("" + BODY) + " ] ").create();

            cnn.insert("INSERT INTO `barcode-scanner` (`code,sid,message`) VALUES ('"+ MD5("" + BODY) +"','"+ message.getSid() +"','"+ message.getBody() +"')");
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