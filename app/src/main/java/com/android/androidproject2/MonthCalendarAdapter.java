package com.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.time.Month;
import java.util.Calendar;

public class MonthCalendarAdapter extends FragmentStateAdapter{
    private  static int NUM_ITEMS = 100;
    private static int preosition = 50;
    private int year, month;
    public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    //위치에 따라서
    public Fragment createFragment(int position) {
        if(preosition == 50){
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
        }
        else if(preosition > position){
            if(month == 0){ //현재 month값이 0(1월)일 때
                year = year-1; //이전년으로
                month = 11; //12월로
            }
            else {
                year = year;
                month = month - 1;
            }
        }
        else{
            if(month == 11){ //현재 month값이 11(12월)일 때
                year = year+1; //다음년으로
                month = 0; //1월로
            }
            else {
                year = year;
                month = month + 1;
            }
        }
        preosition = position;


        //year = Calendar.getInstance().get(Calendar.YEAR);
        //month = Calendar.getInstance().get(Calendar.MONTH); //month 는 0부터 시작

        //year = position;    //이건 알아서 수정
        //month = position+1; //이건 알아서 수정

        return MonthCalendarFragment.newInstance(year, month);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }
}