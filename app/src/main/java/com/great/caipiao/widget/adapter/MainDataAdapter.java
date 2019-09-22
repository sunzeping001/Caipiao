package com.great.caipiao.widget.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.great.caipiao.R;
import com.great.caipiao.db.entity.MainData;

import java.util.List;

/**
 * Created by sunzeping on 2019/9/16.
 * Function :
 * Desc :
 */
public class MainDataAdapter extends RecyclerView.Adapter<MainDataAdapter.VH>{

	private List<MainData> mListDatas;

	public MainDataAdapter(List<MainData> mListDatas) {
		this.mListDatas = mListDatas;
	}

	public void setData(List<MainData> datas){
		mListDatas = datas;
	}

	public static class VH extends RecyclerView.ViewHolder{
		TextView tv_item;
		public VH(@NonNull View itemView) {
			super(itemView);
			tv_item = itemView.findViewById(R.id.tv_item);
		}
	}


	@NonNull
	@Override
	public MainDataAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jiou_item_layout, viewGroup, false);
		return new VH(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final MainDataAdapter.VH vh, int i) {
		vh.tv_item.setText(mListDatas.get(i).getData());
		vh.tv_item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(vh.tv_item.getContext(), vh.tv_item.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		if(mListDatas != null && mListDatas.size() > 0){
			return mListDatas.size();
		} else {
			return 0;
		}
	}
}
