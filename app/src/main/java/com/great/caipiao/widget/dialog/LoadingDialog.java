package com.great.caipiao.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.great.caipiao.R;

/**
 * Created by sunzeping on 2019/9/28.
 * Function :
 * Desc :
 */
public class LoadingDialog extends Dialog {

	private View mView;
	private Context mContext;

	public LoadingDialog(@NonNull Context context) {
		super(context);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mView = LayoutInflater.from(mContext).inflate(R.layout.loading_dialog_layout, null);
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT);
		getWindow().setGravity(Gravity.CENTER);
		setCancelable(false);
		setCanceledOnTouchOutside(false);
		setContentView(mView);
	}

	public void showDialog() {
		this.show();
	}

	public void dismissDialog() {
		if (this.isShowing()) {
			dismiss();
		}
	}


}
