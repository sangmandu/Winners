package com.example.winners_app;

import android.util.Log;
import android.view.textclassifier.TextLinks;

import java.io.File;
import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class FileUploadUtils {
    public static void send2Server(File file){
        android.os.Handler mHandler = new android.os.Handler();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("uploaded_file", file.getName(), RequestBody.create(MultipartBody.FORM, file))
                .addFormDataPart("result", "photo_image")
                .build();
        Request request = new Request.Builder()
                .url("http://wns8363.dothome.co.kr/UploadImage.php")
                .post(requestBody)
                .build();
        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                    Log.d("Test", e.toString());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.d("Test", response.body().string());
            }
        });
        mHandler.postDelayed(new Runnable() {
            public void run() {
                RequestBody requestBody2 = new FormBody.Builder()
                        .add("filename", file.getName())
                        .build();
                Request request2 = new Request.Builder()
                        .url("http://wns8363.dothome.co.kr/UploadImageDB.php")
                        .post(requestBody2)
                        .build();
                OkHttpClient client2 = new OkHttpClient();
                client2.newCall(request2).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.d("Test2", response.body().string());
                    }
                });
            }
        }, 200); // 0.5초후

    }
}
