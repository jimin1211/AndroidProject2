package com.android.androidproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static int num = 0;

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    public MonthViewFragment() {

    }


    // TODO: Rename and change types and number of parameters
    public static MonthViewFragment newInstance(int year, int month) {
        MonthViewFragment fragment = new MonthViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*mParam1 = getArguments().getInt(ARG_PARAM1);
        mParam2 = getArguments().getInt(ARG_PARAM2);*/

        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
        else {
            mParam1 = Calendar.getInstance().get(Calendar.YEAR);
            mParam2 = Calendar.getInstance().get(Calendar.MONTH); //month 는 0부터 시작
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_month_view, container, false);


        ViewPager2 vpPager = rootview.findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new MonthCalendarAdapter(this);
        vpPager.setAdapter(adapter);

        vpPager.setCurrentItem(50,false);



        //vpPager.setCurrentItem(50,false);

        /*if(num == 0){ //처음 생성되었을 때 한번만 초기화해주기 위해서
            vpPager.setCurrentItem(50);
            num++;
        }*/


        return rootview;
    }
}