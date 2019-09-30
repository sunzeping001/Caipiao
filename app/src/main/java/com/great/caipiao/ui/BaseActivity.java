package com.great.caipiao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.great.caipiao.widget.dialog.LoadingDialog;

/**
 * Created by sunzeping on 2019/9/28.
 * Function :
 * Desc :
 */
public class BaseActivity extends Activity{

	private static LoadingDialog loadingDialog;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadingDialog = new LoadingDialog(this);
	}

	@Override
	protected void onDestroy() {
		if(loadingDialog != null && loadingDialog.isShowing()){
			loadingDialog.dismiss();
			loadingDialog = null;
		}
		super.onDestroy();
	}

	public void showLoading(){
		loadingDialog.showDialog();
	}

	public void dismissLoading(){
		loadingDialog.dismissDialog();
	}
}
