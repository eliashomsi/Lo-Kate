package org.tensorflow.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import org.tensorflow.demo.env.Logger;

import java.util.ArrayList;
import java.util.Locale;

public class NoodleAssistant implements Runnable, RecognitionListener {
    public String result;
    public TextToSpeech t1;
    public boolean phase2 = false;
    public Intent speechIntent;
    public SpeechRecognizer mSpeechRecognizer;
    public Logger logger;

    public NoodleAssistant(TextToSpeech t1, SpeechRecognizer mSpeechRecognizer, Intent speechIntent) {
        this.t1 = t1;
        this.speechIntent = speechIntent;
        this.mSpeechRecognizer = mSpeechRecognizer;
        this.logger = new Logger();
        mSpeechRecognizer.setRecognitionListener(this);
    }

    @Override
    public void run() {
        mSpeechRecognizer.startListening(speechIntent);
        logger.i("NOODLE IS RUNNING");
    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int i) {

    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> matches = bundle.getStringArrayList(mSpeechRecognizer.RESULTS_RECOGNITION);

        for (int i = 0; i < matches.size(); i++) {
            matches.set(i, matches.get(i).toUpperCase());
        }
        logger.w("fsadfsf");

        if (!phase2) {
            t1.speak("what stupid", TextToSpeech.QUEUE_FLUSH, null);
            phase2 = true;
        }
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }
}
