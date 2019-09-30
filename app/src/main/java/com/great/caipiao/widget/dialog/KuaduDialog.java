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
import android.widget.Button;
import android.widget.CheckBox;

import com.great.caipiao.R;
import com.great.caipiao.util.Constans;

/**
 * Created by sunzeping on 2019/9/28.
 * Function :
 * Desc :
 */
public class KuaduDialog extends Dialog {

	private View mView;
	private Context mContext;
	private CheckBox ct_0;
	private CheckBox ct_1;
	private CheckBox ct_2;
	private CheckBox ct_3;
	private CheckBox ct_4;
	private CheckBox ct_5;
	private CheckBox ct_6;
	private CheckBox ct_7;
	private CheckBox ct_8;
	private CheckBox ct_9;

	private Button btn_confirm;

	public KuaduDialog(@NonNull Context context) {
		super(context);
		this.mContext = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		mView = LayoutInflater.from(mContext).inflate(R.layout.kuadu_dialog_layout, null);
		getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
				WindowManager.LayoutParams.MATCH_PARENT);
		getWindow().setGravity(Gravity.CENTER);
		setCancelable(true);
		setCanceledOnTouchOutside(false);
		setContentView(mView);
		initView();
		initBtn();
	}

	private void initBtn() {
		btn_confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				StringBuffer buffer = new StringBuffer();
				if(ct_0.isChecked()){
					buffer.append("0,");
				}
				if(ct_1.isChecked()){
					buffer.append("1,");
				}
				if(ct_2.isChecked()){
					buffer.append("2,");
				}
				if(ct_3.isChecked()){
					buffer.append("3,");
				}
				if(ct_4.isChecked()){
					buffer.append("4,");
				}
				if(ct_5.isChecked()){
					buffer.append("5,");
				}
				if(ct_6.isChecked()){
					buffer.append("6,");
				}
				if(ct_7.isChecked()){
					buffer.append("7,");
				}
				if(ct_8.isChecked()){
					buffer.append("8,");
				}
				if(ct_9.isChecked()){
					buffer.append("9");
				}
				Constans.CONFIG_DATA.setKuadu(buffer.toString());

			}
		});
	}

	private void initView() {
		btn_confirm = mView.findViewById(R.id.btn_confirm);
		ct_0 = mView.findViewById(R.id.ct_0);
		ct_1 = mView.findViewById(R.id.ct_1);
		ct_2 = mView.findViewById(R.id.ct_2);
		ct_3 = mView.findViewById(R.id.ct_3);
		ct_4 = mView.findViewById(R.id.ct_4);
		ct_5 = mView.findViewById(R.id.ct_5);
		ct_6 = mView.findViewById(R.id.ct_6);
		ct_7 = mView.findViewById(R.id.ct_7);
		ct_8 = mView.findViewById(R.id.ct_8);
		ct_9 = mView.findViewById(R.id.ct_9);
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
