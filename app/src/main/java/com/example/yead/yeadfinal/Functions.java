package com.example.yead.yeadfinal;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.v7.app.AlertDialog;
import android.widget.RadioGroup;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Functions extends BaseActivity {

    private int checkedID;
    private final int DIALOG = 12345;

    Handler mHandler = new Handler() {

        public void handleMessage(Message msg){
            switch (msg.what){
                case DIALOG:
                    Bundle bundle = msg.getData();
                    String s = bundle.getString("msg");
                    toastShort("Dialog Message:"+s);
                    break;
                default:
            }

            super.handleMessage(msg);
        }

    };

    @BindView(R.id.rdg)
    RadioGroup radioGroup;
    @OnClick(R.id.dialog_ok)
    public void onClick() {
        switch(checkedID) {
            case R.id.rb1:
                singleChoiceDialog();
                break;
            case R.id.rb2:
                multiChoiceDialog();
                break;
            default:
        }
    }

    int choice = 0;
    public void singleChoiceDialog() {
        final String[] items = { "item1", "item2", "item3", "item4" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Single Choice Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setSingleChoiceItems(items, choice, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                choice = which;
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //toastShort("You clicked: Number "+choice +" item");
            }
        });
        builder.show();
    }

    ArrayList<Integer> choices = new ArrayList<>();
    public void multiChoiceDialog() {
        final String[] items = { "item1", "item2", "item3", "item4" };
        final boolean initChoiceSets[]={false, false, false, false};
        choices.clear();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Multi Choice Dialog");
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setMultiChoiceItems(items, initChoiceSets, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                if (isChecked) {
                    choices.add(which);
                } else {
                    choices.remove(which);
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int size = choices.size();
                String str = "";
                for (int i = 0; i < size; i++) {
                    str += items[choices.get(i)] + " ";
                }
                toastShort("You chose: "+str);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                toastShort("You clicked Cancel");
            }
        });
        builder.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions);
        ButterKnife.bind(this);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                //toastShort("You checked the radio button"+checkedId);
                checkedID = checkedId;
            }
        });
    }

}