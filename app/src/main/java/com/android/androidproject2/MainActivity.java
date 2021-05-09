package com.android.androidproject2;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //monthview 프레그먼트가 main의 주 화면으로 표시하기 위해
        FragmentManager fragmentManager = getSupportFragmentManager();
        //MainActivity가 AppCompatActivity를 확장하였으므로, FragmentManager 인스턴스 얻을 때 getSupportFragmentManager() 사용
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //fragment 추가, 삭제 또는 교체 등의 작업 수행 중 오류 발생 시, 다시 원래 상태로 되돌릴 수 있도록, FragmentTransaction 클래스 이용
        //FragmentManager의 beginTransaction() 메소드 호출 통해 FragmentTransaction의 인스턴스 얻어옴
        fragmentTransaction.add(R.id.main_container, new MonthViewFragment());
        //FragmentTransaction의 add() 메소드 통해 동적으로 fragment 추가
        fragmentTransaction.commit();
        //fragmentTransaction의 변경사항 저장
    }


    @Override
    /**액티비티에서 액션 및 오버플로 메뉴 생성 위해 onCreateOptionMenu() 메소드 재정의**/
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //MenuInflater 변수 inflater에 getMenuInflater() 할당
        inflater.inflate(R.menu.main_menu, menu);
        //main_menu.xml 에 의하여 정의된 메뉴 리소스가 Menu 객체로 팽창됨
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    /**사용자가 옵션 메뉴에서 항목 선택시, 시스템이 액티비티의 onOptionItemSelectied() 메소드 호출**/
    public boolean onOptionsItemSelected(MenuItem item) {
        //선택된 메뉴 항목에 대한 MenuItem 객체 전달
        switch (item.getItemId()) { //항목 식별 위해 getItemId() 호출하여 메뉴 항목에 대한 고유 ID 얻어옴
            case R.id.month_view: //id가 month_view 인 경우
                /**자바코드에서 동적으로 fragment 추가**/
                FragmentManager fragmentManager = getSupportFragmentManager();
                //안드로이드 이전 버전들에서도 프레그먼트 사용 가능하도록, fragmentManager에 getSupprotFragmentManager() 할당
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                //fragment 추가, 삭제 또는 교체 등의 작업 수행 중 오류 발생 시, 다시 원래 상태로 되돌릴 수 있도록, FragmentTransaction 클래스 이용
                //FragmentManager의 beginTransaction() 메소드 호출 통해 FragmentTransaction의 인스턴스 얻어옴
                fragmentTransaction.replace(R.id.main_container, new MonthViewFragment());
                //FragmentTransaction의 replace() 메소드 통해 동적으로 fragment 교체
                fragmentTransaction.commit();
                //fragmnetTransaction의 변경사항 저장
                Toast.makeText(getApplicationContext(), "month_view", Toast.LENGTH_SHORT).show();
                //위의 내용이 제대로 실행됐을 경우, "month_view" 토스트 메시지 띄우기
                return true;

            case R.id.week_view: //id가 week_view 인 경우
                fragmentManager = getSupportFragmentManager();
                //안드로이드 이전 버전들에서도 프레그먼트 사용 가능하도록, fragmentManager에 getSupprotFragmentManager() 할당
                fragmentTransaction = fragmentManager.beginTransaction();
                //fragment 추가, 삭제 또는 교체 등의 작업 수행 중 오류 발생 시, 다시 원래 상태로 되돌릴 수 있도록 FragmentTransaction 클래스 이용
                //FragmentManager의 beginTransaction() 메소드 호출 통해 FragmentTransaction의 인스턴스 얻어옴
                fragmentTransaction.replace(R.id.main_container, new WeekViewFragment());
                //FragmentTransaction의 replace() 메소드 통해 동적으로 fragment 교체
                fragmentTransaction.commit();
                //fragmnetTransaction의 변경사항 저장
                Toast.makeText(getApplicationContext(), "week_view", Toast.LENGTH_SHORT).show();
                //위의 내용이 제대로 실행됐을 경우, "week_view" 토스트 메시지 띄우기
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}