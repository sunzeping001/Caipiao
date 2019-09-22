package com.great.caipiao.widget.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.great.caipiao.R;

import java.util.List;

/**
 * Created by sunzeping on 2019/9/16.
 * Function :
 * Desc :
 */
public class JiouAdapter extends RecyclerView.Adapter<JiouAdapter.VH>{

	private List<String> mListDatas;

	public JiouAdapter(List<String> mListDatas) {
		this.mListDatas = mListDatas;
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
	public JiouAdapter.VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.jiou_item_layout, viewGroup, false);
		return new VH(v);
	}

	@Override
	public void onBindViewHolder(@NonNull final JiouAdapter.VH vh, int i) {
		vh.tv_item.setText(mListDatas.get(i));
		vh.tv_item.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(vh.tv_item.getContext(), vh.tv_item.getText().toString(), Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public int getItemCount() {
		return mListDatas.size();
	}
}
