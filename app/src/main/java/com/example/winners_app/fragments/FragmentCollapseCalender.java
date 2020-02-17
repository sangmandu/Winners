package com.example.winners_app.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.winners_app.R;
import com.example.winners_app.adapter.CalenderAdapter;
import com.example.winners_app.models.Event;
import com.shrikanthravi.collapsiblecalendarview.data.Day;
import com.shrikanthravi.collapsiblecalendarview.widget.CollapsibleCalendar;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentCollapseCalender extends Fragment {

    private RecyclerView recyclerView;
    private CalenderAdapter calenderAdapter;
    private ArrayList<Event> events = new ArrayList<>();
    private ArrayList<Event> eventsSave = new ArrayList<>();
    private CollapsibleCalendar collapsibleCalendar;
    public static Day selectedDay;
    private ImageView hide_btn;
    private int first = 1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_calender,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        collapsibleCalendar = view.findViewById(R.id.calendarView);

        collapsibleCalendar.setCalendarListener(new CollapsibleCalendar.CalendarListener() {

            @Override
            public void onDayChanged() {
            }

            @Override
            public void onClickListener() {
            }

            @Override
            public void onDaySelect() {
                initEvents();
            }

            @Override
            public void onItemClick(View view) {
            }

            @Override
            public void onDataUpdate() {
                // 처음 켰을 때 리싸이클러뷰 보이게 ;; 코틀린 모듈 ㅅㅂ..
                if (first == 1)
                    first = 0;
                else
                    emptyEvents();
            }

            @Override
            public void onMonthChange() {
            }

            @Override
            public void onWeekChange(int i) {

            }
        });

        addEvent(2020, 2, 11, "수강신청", "8046FF");
        addEvent(2020, 2, 25, "수강신청 연기", "D81B60");
        addEvent(2020, 3, 16, "개강", "FF9800");


        Calendar todayCal = Calendar.getInstance();
        Day day = new Day(todayCal.get(Calendar.YEAR), todayCal.get(Calendar.MONTH),todayCal.get(Calendar.DAY_OF_MONTH));
        collapsibleCalendar.select(day);

        initEvents();

    }

    private void initEvents(){
        events.clear();
        selectedDay = collapsibleCalendar.getSelectedDay();

        for (Event event: eventsSave){
            if ( (0 <= getDiff(event.getmDate(), selectedDay) && getDiff(event.getmDate(), selectedDay) <= 30) )
                events.add(event);
        }

        initRecyclerView();
    }

    private void emptyEvents(){
        events.clear();
        initRecyclerView();
    }

    private void initRecyclerView() {
        LinearLayoutManager staggeredGridLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        calenderAdapter = new CalenderAdapter(events);
        recyclerView.setAdapter(calenderAdapter);
    }

    public void addEvent(int year, int month, int day, String title, String color){
        eventsSave.add(new Event(year, month-1, day, Color.parseColor("#" + color), title));
        collapsibleCalendar.addEventTag(year, month-1, day, Color.parseColor("#" + color));
    }

    // Day 클래스 비교가 안됨
    public static int getDiff(Day d1, Day d2){
        return (int)( (d1.toUnixTime() - d2.toUnixTime()) / (1000 * 60 * 60 * 24) );
    }
}