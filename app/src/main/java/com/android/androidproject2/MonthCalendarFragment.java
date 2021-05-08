package com.android.androidproject2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MonthCalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MonthCalendarFragment extends Fragment {
//여기서 달력 구현 (JAVA 파일로 구현

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static Calendar sDay = Calendar.getInstance(); //시작일;
    static int START_DAY_OF_WEEK; //시작일의 요일(1일의 요일)을 알아냄
    static int END_DAY;
    static int day;

    // TODO: Rename and change types of parameters
    private int mParam1;
    private int mParam2;

    public MonthCalendarFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static MonthCalendarFragment newInstance(int year, int month) {
        MonthCalendarFragment fragment = new MonthCalendarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, year);
        args.putInt(ARG_PARAM2, month);
        fragment.setArguments(args);
        return fragment;
    }

    public static String[] getDay(int year, int month){
        /**그냥 이렇게만 하면 오늘 날짜가 포함된 주만 나옴**/
        /**swipe시, 달이 계속 바뀜**/
        /**Adapter 부분을 수정해야할듯?**/

        day = Calendar.getInstance().get(Calendar.DATE);
        /**이거의 문제점은 무조건 현재 시간을 기준으로만 나옴......**/

        //day = sDay.get(Calendar.DATE);
        /**원래 이건데...... 이렇게 하면 무조건 1일로 됨...**/

        Calendar today = Calendar.getInstance();
        today.set(year, month, day);
        int week = today.get(Calendar.WEEK_OF_MONTH);
        int x = 0;
        String[] date = new String[7];

        for(int i=1; i<=today.getActualMaximum(Calendar.DATE) /*&& x<7*/; i++){
            today.set(year, month, i);
            if((today.get(Calendar.WEEK_OF_MONTH) == week)){
                //해당 주를 get해서 week랑 똑같으면
                //그 주 일요일부터 그냥 for문으로 집어넣기!!!

                if(x < today.get(Calendar.DAY_OF_WEEK)-1){
                    for(; x < today.get(Calendar.DAY_OF_WEEK)-1; ){
                        date[x++] = x+"";
                    }
                    date[x++] = i+"";
                    //date[x++] = today.get(Calendar.DAY_OF_WEEK)+"";
                }
                else{
                    date[x++] = i+"";
                    //date[x++] = today.get(Calendar.DAY_OF_WEEK)+"";
                }
            }

        }
        if(x < 7){
            for (; x < 7; x++) {
                date[x] = "";
            }
        }

        return date;

    }

    public static String[] getGrid() {
        String[] grid = new String[24*7];

        for(int i=0; i<=grid.length; i++) {
            grid[i] = "";
        }
        return grid;
    }


    public static String[] getItem(int year, int month){

        //Calendar sDay = Calendar.getInstance(); //시작일;
        //int START_DAY_OF_WEEK; //시작일의 요일(1일의 요일)을 알아냄
        //int END_DAY;
        String[] date = new String[6*7]; //6*7사이즈의 String형 배열을 선언

        //View rootview = inflater.inflate(R.layout.fragment_month_view, container, false);

        sDay.set(year,month,1);
        START_DAY_OF_WEEK = sDay.get(Calendar.DAY_OF_WEEK); //시작일의 요일(1일의 요일)을 알아냄
        END_DAY = sDay.getActualMaximum(Calendar.DATE); //현재 월의 마지막 날짜를 알아냄
        //배열에 요일 입력
        for(int i=0, n=1; i<date.length; i++){
            if(i < START_DAY_OF_WEEK-1)  //date배열에 첫번째 요일 전까지 공백으로 채움
                date[i] = "";            //DAY_OF_WEEK는 일요일:1 ~ 토요일:7로 결과가 나옴 -> START_DAT_OF_WEEK에서 -1을 해준 값 전까지가 공백

            else if((i >= START_DAY_OF_WEEK-1) && (n <= END_DAY)) {//START_DAY_OF_WEEK에서 -1해준 값부터 배열 시작
                date[i] = n+"";                                    //n값은 배열에 날짜를 입력하기위한 변수이고, 1일부터 END_DAY 즉, 마지막 날짜까지 배열에 입력
                n++;
            }

            else                    //이 전까지의 배열은 (START_DAY_OF_WEEK-1)+(END_DAY-1)까지 채워져있으니까
                date[i] = "";       //START_DAY_OF_WEEK+END_DAY-1부터 배열의 마지막까지 공백으로 채워줌.
        }
        return date;
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

        //((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mParam1+"년"+mParam2+"월");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_month_calendar, container, false);

//        switch(R.menu.main_menu) {
//            case R.id.month_view:
//
//                String[] date = getItem(mParam1,mParam2);
//                /**String[] date = getDay(mParam1,mParam2); 하면
//                 * 오늘이 속해있는 주의 날들이 한줄에 나옴**/
//                /**그런데 문제는 그 다음주로 날짜들이 넘어가지 않는다는 것**/
//
//                GridView gridView = (GridView) rootview.findViewById(R.id.gridview);
//                ArrayAdapter<String> GridViewAdapter = new ArrayAdapter<String>(
//                        getActivity(),
//                        android.R.layout.simple_list_item_1,
//                        date);
//                gridView.setAdapter(GridViewAdapter);
//
//            case R.id.week_view:
                String[] day = getDay(mParam1,mParam2);
                GridView wGridview = (GridView) rootview.findViewById(R.id.gridview);
                ArrayAdapter<String> wGridViewAdapter = new ArrayAdapter<String>(
                        getActivity(),
                        android.R.layout.simple_list_item_1,
                        day);
                wGridview.setAdapter(wGridViewAdapter);

//            default:
//                date = getItem(mParam1,mParam2);
//
//                gridView = (GridView) rootview.findViewById(R.id.gridview);
//                GridViewAdapter = new ArrayAdapter<String>(
//                        getActivity(),
//                        android.R.layout.simple_list_item_1,
//                        date);
//                gridView.setAdapter(GridViewAdapter);
//        }

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(mParam1+"년"+(mParam2+1)+"월");
        TextView textView = (TextView)rootview.findViewById(R.id.textview);
        textView.setText(mParam1+"년"+(mParam2+1)+"월");

        return rootview;
    }
}