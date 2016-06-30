package com.swu.healthmonitor;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Date;
import java.util.Properties;

import android.accessibilityservice.AccessibilityService;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class AccessService extends AccessibilityService {

    private class email extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            SharedPreferences s = getSharedPreferences("com.swu.AccessibilityDemo-master", 0);
            System.out.println("doing");
            String mess = params[0];

            String to = s.getString("address", null);


            // Sender's email ID needs to be mentioned
            final String from = "stephen.kj.wu@gmail.com";
            final String password = "Kunjian168";
            // Assuming you are sending email from localhost
            String host = "localhost";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", "smtp.gmail.com");
            properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            properties.setProperty("mail.smtp.socketFactory.fallback", "false");
            properties.setProperty("mail.smtp.port", "465");
            properties.setProperty("mail.smtp.socketFactory.port", "465");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.debug", "true");
            properties.put("mail.store.protocol", "pop3");
            properties.put("mail.transport.protocol", "smtp");

            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);

                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));

                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));

                // Set Subject: header field
                message.setSubject("This is the Subject Line!");
                message.setText(mess);
                message.setSentDate(new Date());


                Transport.send(message);
                System.out.println("Sent message successfully....");


            } catch (MessagingException mex) {
                System.out.println("error?");
                mex.printStackTrace();
            }

            return null;
        }
    }

    private static final String LOG_TAG = "cc";
    private static final String TEXT_TAG = "text";
    private static final String SEPARATOR = ", ";
    private static final String DASHLINE = "--------------";

    private boolean mTextToSpeechInitialized;
    private boolean mStart = true;
    private boolean mStop = false;

    private TextToSpeech mTts;
    private AccessibilityNodeInfo mListView = null;
    private int Total = 0;
    private int Proceed = 0;

    enum Status {
        ENABLE,
        CHECK_BOX,
        CLICK_BUTTON,
        DISABLE
    }

    private Status curStatus = Status.ENABLE;

    @Override
    public void onServiceConnected() {
        Log.d(LOG_TAG, "hi!!");
    }


    public boolean isDepressed(AccessibilityEvent event){
        return true;
    }

    public void sendEmail(){
        email s = new email();
        s.execute("");
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

//        Log.d(LOG_TAG, event.getPackageName().toString());
        Log.d(LOG_TAG, event.toString());

//        if (!mTextToSpeechInitialized) {
//            Log.d(LOG_TAG, "Text to Speech is not ready");
//            return;
//        }
        if(isDepressed(event)){
            sendEmail();
        }
    }

    @Override
    public void onInterrupt() {

    }
}

