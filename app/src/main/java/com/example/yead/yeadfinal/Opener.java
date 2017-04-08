package com.example.yead.yeadfinal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yead.yeadfinal.Adapter.ListViewAdapter;
import com.example.yead.yeadfinal.Adapter.ViewPagerAdapter;
import com.example.yead.yeadfinal.Dialog.CustomDialog;
import com.example.yead.yeadfinal.Util.Timer;

import java.util.ArrayList;

import butterknife.OnClick;

public class Opener extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView;
    private ArrayList<String> listResult;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opener);
//        initialView();
        listResult = new ArrayList<String>();
        creatFakeResult();
        initialView();
    }

    private void creatFakeResult(){

        listResult.add("Calculator");
        listResult.add("Stop Watch");
        listResult.add("Radio Button");
        listResult.add("Dialog Box");
        listResult.add("Gesture");


    }

    private void initialView() {
        listView = (ListView) findViewById(R.id.list_view);
        ListViewAdapter listViewAdapter = new ListViewAdapter(this, listResult);

        View view = getLayoutInflater().inflate(R.layout.swipe,null);
        LinearLayout listViewHeader = (LinearLayout)view.findViewById(R.id.swipe);

        viewPager = (ViewPager) view.findViewById(R.id.view_pager);
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());
        fragmentList.add(new Fragment3());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this.getSupportFragmentManager());
        viewPagerAdapter.setContent(fragmentList);
        viewPager.setAdapter(viewPagerAdapter);
        listView.addHeaderView(listViewHeader);

//        TextView tv = new TextView(this);
//        //tv.setText("We have no content");
//        tv.setTextSize(28);
//        tv.setGravity(Gravity.LEFT);
//        //listView.addFooterView(tv);

        listView.setAdapter(listViewAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "listView was clicked at position:" +position,Toast.LENGTH_LONG).show();
        Log.d("testListViewActivity",String.valueOf(position));
        switch(position){
            case 1:
                toActivity(Calculator.class);
                break;
           case 2:
               toActivity(Timer.class);
               break;
            case 3:
                toActivity(Functions.class);
                break;
            case 4:
                customDialog();
                break;
            case 5:
                toActivity(Gesture.class);
                break;


        }

    }

    private void customDialog() {
        final CustomDialog dialog = new CustomDialog(this, new CustomDialog.ICustomDialogEventListener() {
            @Override
            public void onClickListener() {
                Intent intent = new Intent();
                intent.putExtra("message", "Dialog");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        dialog.show();
    }
}
