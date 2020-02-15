package com.example.winners_app.TabActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.winners_app.R;
import com.example.winners_app.models.Competition;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CompSetting extends AppCompatActivity {
    private ImageView btn_back;
    private ImageView iv_image;
    private Button btn_image;
    private Button btn_save;
    private Button btn_cancel;

    private Calendar cal;

    private Uri selectedImage;

    private TextView tv_date;
    private TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_setting);

        iv_image = findViewById(R.id.iv_image);
        btn_image = findViewById(R.id.btn_image);
        tv_date = findViewById(R.id.tv_date);
        tv_time = findViewById(R.id.tv_time);
        btn_save = findViewById(R.id.btn_save);

        initTime();

        btn_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pickPhoto = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView et_name = findViewById(R.id.et_name);
                TextView et_url = findViewById(R.id.et_url);
                TextView et_add = findViewById(R.id.et_add);
                TextView et_loc = findViewById(R.id.et_loc);
                TextView et_note = findViewById(R.id.et_note);
                Drawable d = ((ImageView) iv_image).getDrawable();

                Competition comp = new Competition(
                        et_name.getText().toString(),
                        et_url.getText().toString(),
                        et_loc.getText().toString(),
                        et_add.getText().toString(),
                        et_note.getText().toString(),
                        cal,
                        d);
                TabCompete.mComps.add(comp);

                Toast.makeText(CompSetting.this, "대회목록 추가됨", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        btn_back = findViewById(R.id.back_title);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_cancel = findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initTime(){

        //Calendar를 이용하여 년, 월, 일, 시간, 분을 PICKER에 넣어준다.
        cal = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        tv_date.setText(df.format(cal.getTime()));

        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
        tv_time.setText(tf.format(cal.getTime()));

        //DATE PICKER DIALOG
        findViewById(R.id.btn_date_picker_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog dialog = new DatePickerDialog(CompSetting.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                        cal.set(Calendar.YEAR, year);
                        cal.set(Calendar.MONTH, month);
                        cal.set(Calendar.DATE, date);

                        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                        tv_date.setText(df.format(cal.getTime()));
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));

                dialog.show();
            }
        });


        //TIME PICKER DIALOG
        findViewById(R.id.btn_time_picker_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog dialog = new TimePickerDialog(CompSetting.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        cal.set(Calendar.HOUR_OF_DAY, hour);
                        cal.set(Calendar.MINUTE, min);

                        SimpleDateFormat tf = new SimpleDateFormat("HH:mm");
                        tv_time.setText(tf.format(cal.getTime()));
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지

                dialog.show();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        if(resultCode == RESULT_OK){
            selectedImage = imageReturnedIntent.getData();
            iv_image.setImageURI(selectedImage);
        }
    }

    @Override
    public void finish() {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent); //By not passing the intent in the result, the calling activity will get null data.
        super.finish();
    }
}
