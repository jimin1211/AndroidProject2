package com.android.androidproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WeekViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeekViewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;
    //private static int day;

    public WeekViewFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static WeekViewFragment newInstance(int param1, int param2) {
        WeekViewFragment fragment = new WeekViewFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getInt(ARG_PARAM2);
        }
        else{
            mParam1 = Calendar.getInstance().get(Calendar.YEAR);
            mParam2 = Calendar.getInstance().get(Calendar.MONTH); //month 는 0부터 시작
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_week_view, container, false);

        ViewPager2 vpPager = rootview.findViewById(R.id.vpPager);
        FragmentStateAdapter adapter = new MonthCalendarAdapter(this,"WeekViewFragment"); //호출한 fragment를 구분하기 위해 현재 ragment이름을 넘겨줌
        vpPager.setAdapter(adapter);

        vpPager.setCurrentItem(50,false); //50페이지로 설정, 애니메이션 전환 false

        return rootview;
    }
}