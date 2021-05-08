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
    private static int preposition = 50;
    private static int year, month;
    private static int num = 0;
    private String tabTitles[] = new String[] {"일", "월", "화", "수", "목", "금", "토"};
    public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }


    @NonNull
    @Override
    //위치에 따라서
    public Fragment createFragment(int position) {
        /*if(preposition == 50){ //가장 처음이면 현재 날짜
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
        }*/

        if(num == 0){
            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
            num++;
            return MonthCalendarFragment.newInstance(year, month);
        }


        /*else */if(preposition > position){ //이전으로 넘겼을 때
            if(month == 0){ //현재 month값이 0(1월)일 때
                //year = year-1; //이전년으로
                year--;
                month = 11; //12월로
            }
            else {
                //year = year;
                //month = month - 1;
                month--;
            }
        }

        else if(preposition < position){  //다음으로 넘겼을 때
            if(month == 11){ //현재 month값이 11(12월)일 때
                //year = year+1; //다음년으로
                year++;
                month = 0; //1월로
            }
            else {
                //year = year;
                //month = month + 1;
                month++;
            }
        }

        preposition = position;

        return MonthCalendarFragment.newInstance(year, month);
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}