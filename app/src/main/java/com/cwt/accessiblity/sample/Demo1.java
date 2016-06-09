package com.cwt.accessiblity.sample;

import android.accessibilityservice.AccessibilityService;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import java.util.Locale;

/**
 * Created by CwT on 16/6/4.
 */
public class Demo1 extends AccessibilityService implements TextToSpeech.OnInitListener {

    private static final String LOG_TAG = "cc";
    private static final String SEPARATOR = ", ";
    private static final String DASHLINE = "--------------";

    private boolean mTextToSpeechInitialized;

    private TextToSpeech mTts;

    enum Status {
        ENABLE,
        CHECK_BOX,
        CLICK_BUTTON,
        DISABLE
    }

    private Status curStatus = Status.ENABLE;

    @Override
    public void onServiceConnected() {
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

        if (curStatus == Status.ENABLE) {
            if (source.isClickable() &&
                    event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED
                    && source.getClassName().equals("android.widget.ListView")) {
                if (source.findAccessibilityNodeInfosByText("cwt's accessibility service").size() > 0
                        && source.findAccessibilityNodeInfosByText("开启").size() > 0
                        && source.findAccessibilityNodeInfosByText("服务").size() > 0) {
                    if (source.getChildCount() < 3)
                        return;
                    AccessibilityNodeInfo thirdNode = source.getChild(2);
                    if (thirdNode.getClassName().equals("android.widget.LinearLayout")
                            && thirdNode.isClickable()) {
                        thirdNode.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                        curStatus = Status.CHECK_BOX;
                        Log.d(LOG_TAG, "++++++++++++++++change to check box");
                    }
                }
            }
        } else if (curStatus == Status.CHECK_BOX) {
            if (source.getClassName().equals("android.widget.Switch")) {
                source.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                curStatus = Status.CLICK_BUTTON;
                Log.d(LOG_TAG, "++++++++++++++++change to click button");
            }
        } else if (curStatus == Status.CLICK_BUTTON) {
            Log.d(LOG_TAG, source.toString());
            if (source.getClassName().equals("android.widget.Button")
                    && source.getText().toString().equals("确定")) {
                source.performAction(AccessibilityNodeInfo.ACTION_CLICK);
                curStatus = Status.DISABLE;
                Log.d(LOG_TAG, "++++++++++++++++change to disable");
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
