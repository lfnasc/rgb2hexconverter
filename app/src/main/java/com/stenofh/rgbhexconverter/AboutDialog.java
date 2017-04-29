package com.stenofh.rgbhexconverter;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by leo on 29/04/17.
 */

public class AboutDialog extends Dialog {

    public AboutDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_about);

        // setting ButterKnife
        ButterKnife.bind(this);

    }

    @OnClick(R.id.buttonOkDialog)
    public void onClickOk() {
        dismiss();
    }
}
