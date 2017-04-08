package com.example.yead.yeadfinal.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.example.yead.yeadfinal.Functions;
import com.example.yead.yeadfinal.Opener;
import com.example.yead.yeadfinal.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Yead on 4/7/2017.
 */

public class CustomDialog extends Dialog {
    @OnClick(R.id.dialog_ok)
    public void okClick(){

        listener.onClickListener();
        Intent intent = new Intent(getContext(), Opener.class );
        getContext().startActivity(intent);
        //dismiss();
    }
    private ICustomDialogEventListener listener;

    public interface ICustomDialogEventListener{
        public void onClickListener();
    }

    public CustomDialog(@NonNull Context context, ICustomDialogEventListener iCustomDialogEventListener) {
        super(context, R.style.dialog);
        this.listener= iCustomDialogEventListener;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_dialog);
        ButterKnife.bind(this);

    }
}
