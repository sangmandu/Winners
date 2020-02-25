package com.example.winners_app;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ImageSelectionActivity extends AppCompatActivity {

    ImageView iv_selected;
    Button btn_ImageSend, btn_ImageSelection;
    File tempSelectfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selection);
        tedPermission();

        btn_ImageSend = findViewById(R.id.btnImageSend);
        btn_ImageSend.setEnabled(false);
        btn_ImageSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileUploadUtils.send2Server(tempSelectfile);
            }
        });
        btn_ImageSelection = findViewById(R.id.btnImageSelection);
        btn_ImageSelection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, 1);
            }
        });

        iv_selected = findViewById(R.id.iv_picture);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode != 1 || resultCode != RESULT_OK){
            return;
        }

        Uri dataUri = data.getData();
        iv_selected.setImageURI(dataUri);

        try{
            InputStream in = getContentResolver().openInputStream(dataUri);
            Bitmap image = BitmapFactory.decodeStream(in);
            iv_selected.setImageBitmap(image);
            in.close();

            String date = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
            tempSelectfile = new File(Environment.getExternalStorageDirectory()+"/Download/", "temp_"+ date + ".jpeg");
            OutputStream out = new FileOutputStream(tempSelectfile);
            image.compress(Bitmap.CompressFormat.JPEG, 100, out);
            Log.d("file",tempSelectfile.getName());
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        btn_ImageSend.setEnabled(true);
    }

    private void tedPermission() {

        PermissionListener permissionListener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
                // 권한 요청 성공

            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                // 권한 요청 실패
            }
        };

        TedPermission.with(this)
                .setPermissionListener(permissionListener)
                .setRationaleMessage(getResources().getString(R.string.permission_2))
                .setDeniedMessage(getResources().getString(R.string.permission_1))
                .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .check();

    }

}
