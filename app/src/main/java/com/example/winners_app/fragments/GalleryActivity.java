package com.example.winners_app.fragments;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.winners_app.R;
import com.example.winners_app.Request.GetImageRequest;
import com.example.winners_app.adapter.MyPagerAdapter;
import com.example.winners_app.models.Cat;
import com.example.winners_app.resources.Cats;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    private Button btn_save, btn_comment, btn_delete;
    private ViewPager mMyViewPager;
    private String filter;
    ArrayList<Cat> cats = new ArrayList<>();
    ArrayList<Cat> cats2 = new ArrayList<>();
    ArrayList<GalleryItemFragment> fragments = new ArrayList<>();
    String imagetitle, imageurl;
    Bitmap BitmapImage;
    String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
    back task;
    String image_url, image_title;
    String Save_Path;
    String Save_folder = "/mydown";

    ProgressBar loadingBar;
    //DownloadThread dthread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        mMyViewPager = findViewById(R.id.view_pager);
        filter = GalleryFragment.query;
        btn_save = findViewById(R.id.btn_save);
        btn_comment = findViewById(R.id.btn_cmt);
        btn_delete = findViewById(R.id.btn_del);
        task = new back();
        init();


        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cat cat = cats2.get(mMyViewPager.getCurrentItem());
                image_url = cat.getImage().toString();
                image_title = cat.getTitle().toString();
                SaveBitmapToFileCache(BitmapImage, extStorageDirectory, image_title);
            }
        });
    }
    /*
    class DownloadThread extends Thread{
        String ServerUrl;
        String LocalPath;

        DownloadThread(String serverPath, String localPath){
            ServerUrl = serverPath;
            LocalPath = localPath;
        }

        @Override
        public void run(){
            URL imgurl;
            int Read;
            try{
                imgurl = new URL(ServerUrl);
                HttpURLConnection conn = (HttpURLConnection)
            }
        }

    }
    */

    private class back extends AsyncTask<String, Integer, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection) myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                BitmapImage = BitmapFactory.decodeStream(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return BitmapImage;
        }

        protected void onPostExecute(Bitmap img, String title) {
            SaveBitmapToFileCache(img, extStorageDirectory, title);
        }
    }


    public Bitmap GetImageFromURL(String strImageURL) {
        Bitmap imageBitmap = null;
        try {
            URL url = new URL(strImageURL);
            URLConnection conn = url.openConnection();
            conn.connect();

            int nSize = conn.getContentLength();
            BufferedInputStream bis = new BufferedInputStream(conn.getInputStream(), nSize);
            imageBitmap = BitmapFactory.decodeStream(bis);

            bis.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return imageBitmap;
    }


    public static void SaveBitmapToFileCache(Bitmap bitmap, String strFilePath, String filename) {
        File file = new File(strFilePath);

        if (!file.exists()) {
            file.mkdirs();
        }

        File fileCacheItem = new File(strFilePath + filename);
        OutputStream out = null;

        try {
            fileCacheItem.createNewFile();
            out = new FileOutputStream(fileCacheItem);

            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() {
        Handler mHandler = new Handler();
        ArrayList<Fragment> fragments = new ArrayList<>();
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                String TAG_NAME = "image";
                try {
                    cats.clear();
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray(TAG_NAME);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        String no = item.getString("NO");
                        String image = item.getString("IMAGE");
                        Cat cat = new Cat(no, image);
                        cats.add(i, cat);
                        if (cat.getTitle().toLowerCase().contains(filter)) {
                            cats2.add(cat);
                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        // 서버로 Volley를 이용해서 요청을 함.
        RequestQueue queue = Volley.newRequestQueue(GalleryActivity.this);
        GetImageRequest request = new GetImageRequest(responseListener);
        queue.add(request);

        mHandler.postDelayed(new Runnable() {
            public void run() {
                for (Cat cat : cats) {
                    if (cat.getTitle().toLowerCase().contains(filter)) {
                        GalleryItemFragment fragment = GalleryItemFragment.getInstance(cat);
                        fragments.add(fragment);
                        MyPagerAdapter pagerAdapter = new MyPagerAdapter(getSupportFragmentManager(), fragments);
                        mMyViewPager.setAdapter(pagerAdapter);
                        // 선택한 이미지에서 뷰어 시작
                        if (getIntent().hasExtra("current_pos")) {
                            int current_pos = getIntent().getIntExtra("current_pos", 0);
                            mMyViewPager.setCurrentItem(current_pos);
                        }
                    }
                }
            }
        }, 500); // 0.5초후

    }


}
