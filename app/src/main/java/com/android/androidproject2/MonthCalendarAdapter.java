package com.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.time.Month;
import java.util.Calendar;

public class MonthCalendarAdapter extends FragmentStateAdapter{
    private  static int NUM_ITEMS = 100;
    private int year, month;
    public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
        /*String[] date = new String[6*7]; //6*7사이즈의 String형 배열을 선언
        year = Calendar.getInstance().get(Calendar.YEAR);
        month = Calendar.getInstance().get(Calendar.MONTH); //0부터 시작
        createFragment(-99);*/
    }


    @NonNull
    @Override
    //위치에 따라서
    public Fragment createFragment(int position) {
        //year = Calendar.getInstance().get(Calendar.YEAR);
        //month = Calendar.getInstance().get(Calendar.MONTH); //month 는 0부터 시작


        year = position;    //이건 알아서 수정
        month = position+1; //이건 알아서 수정

        return MonthCalendarFragment.newInstance(year, month);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}