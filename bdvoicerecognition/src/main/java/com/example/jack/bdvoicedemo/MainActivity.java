package com.example.jack.bdvoicedemo;

import android.content.ComponentName;
import android.content.Intent;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.speech.VoiceRecognitionService;
import com.baidu.voicerecognition.android.ui.BaiduASRDigitalDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecognitionListener{

    private SpeechRecognizer speechRecognizer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, new ComponentName(this, VoiceRecognitionService.class));
        // 注册监听器
        speechRecognizer.setRecognitionListener(this);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startASR();
                start();
            }
        });
    }

    void startASR() {
        Intent intent = new Intent();
        bindParams(intent);
        speechRecognizer.startListening(intent);
    }

    void bindParams(Intent intent) {
        // 设置识别参数
    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onError(int error) {

    }

    @Override
    public void onResults(Bundle results) {
        System.out.println(results);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    //-------------------------------------------------------------------
    private static final int REQUEST_UI = 1;
    private void start() {
        Intent recognizerIntent = new Intent();
        recognizerIntent.setAction("com.baidu.action.RECOGNIZE_SPEECH");
        // recognizerIntent.put("...", "...") TODO 为recognizerIntent设置参数，支持的参数见本文档的“识别参数”一节
        recognizerIntent.putExtra("sample", 16000); // 离线仅支持16000采样率
        recognizerIntent.putExtra("language", "cmn-Hans-CN"); // 离线仅支持中文普通话
        recognizerIntent.putExtra("prop", 20000); // 输入
        // 为了支持离线识别能力，请参考“离线语音识别参数设置”一节
        startActivityForResult(recognizerIntent, REQUEST_UI);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(SpeechRecognizer.RESULTS_RECOGNITION);
            // data.get... TODO 识别结果包含的信息见本文档的“结果解析”一节
            for(String s : results){
                System.out.println("Result:"+s);
            }
        }
    }
}
