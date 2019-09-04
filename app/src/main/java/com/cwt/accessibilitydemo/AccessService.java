package com.cwt.accessibilitydemo;

import android.accessibilityservice.AccessibilityService;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Locale;

/**
 * Created by CwT on 16/6/4.
 */
public class AccessService extends AccessibilityService implements TextToSpeech.OnInitListener {

    private static final String LOG_TAG = "cc";
    private static final String SEPARATOR = ", ";
    private static final String DASHLINE = "--------------";

    private boolean mTextToSpeechInitialized;
    private boolean mStart = false;

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
        mTts = new TextToSpeech(getApplicationContext(), this);
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {

//        Log.d(LOG_TAG, event.getPackageName().toString());
        Log.d(LOG_TAG, event.toString());

//        if (!mTextToSpeechInitialized) {
//            Log.d(LOG_TAG, "Text to Speech is not ready");
//            return;
//        }

        AccessibilityNodeInfo source = event.getSource();
        if (source == null) {
            return;
        }

        if (!event.getPackageName().toString().equals("com.cyanogenmod.filemanager"))
            return;

        if (source.getClassName().toString().equals("android.widget.ListView")) {
            mListView = source;
            for (int i = 0; i < event.getRecordCount(); i++) {

            }
        }

        Log.d(LOG_TAG, DASHLINE);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            mTts.setLanguage(Locale.US);
            mTextToSpeechInitialized = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mTextToSpeechInitialized)
            mTts.shutdown();
    }
}
