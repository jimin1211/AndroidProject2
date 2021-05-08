package com.android.androidproject2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.time.Month;
import java.util.Calendar;

public class MonthCalendarAdapter extends FragmentStateAdapter{
    private static int NUM_ITEMS = 100;
    private static int preposition = 50;
    private static int year, month, day;
    private static int num1 = 0, num2 = 0;
    private static String fragname;
    //private String tabTitles[] = new String[] {"일", "월", "화", "수", "목", "금", "토"};
    /*public MonthCalendarAdapter(@NonNull Fragment fragment) {
        super(fragment);
    }*/

    public MonthCalendarAdapter(@NonNull Fragment fragment, String fragname) {
        super(fragment);
        this.fragname = fragname;
    }


    @NonNull
    @Override
    //위치에 따라서
    public Fragment createFragment(int position) {

        if(fragname.equals("MonthViewFragment")){ //monthviewfragment가 호출했을 때
            if(num1 == 0){
                year = Calendar.getInstance().get(Calendar.YEAR);
                month = Calendar.getInstance().get(Calendar.MONTH);
                num1++;
                return MonthCalendarFragment.newInstance(year, month);
            }

            if(preposition > position){ //이전으로 넘겼을 때
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
                day = 1;
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
                day = 1;
            }
            preposition = position;

            return MonthCalendarFragment.newInstance(year, month);
        }

        else{ //WeekViewFragment가 호출했을 때
            if(num2 == 0){
                Calendar today = Calendar.getInstance();
                today.set(year, month-1, day);
                today.set(Calendar.DAY_OF_WEEK,1);
                day = today.get(Calendar.DATE);
                num2++;
                return MonthCalendarFragment.newInstance(year, month, day);
            }
            if(preposition > position){ //이전으로 넘겼을 때
                Calendar today = Calendar.getInstance();
                today.set(year, month-1, day);
                today.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                today.add(Calendar.DATE, -7);
                year = today.get(Calendar.YEAR);
                month = today.get(Calendar.MONTH)+1;
                day = today.get(Calendar.DATE);
            }

            else if(preposition < position){  //다음으로 넘겼을 때
                Calendar today = Calendar.getInstance();
                today.set(year, month-1, day);
                today.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
                today.add(Calendar.DATE, 7);
                year = today.get(Calendar.YEAR);
                month = today.get(Calendar.MONTH)+1;
                day = today.get(Calendar.DATE);
            }

            preposition = position;
            return MonthCalendarFragment.newInstance(year, month, day);
        }
    }

    @Override
    public int getItemCount() {
        return NUM_ITEMS;
    }

    /*public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }*/
}