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
		TextView tv_jiou;
		TextView tv_zhihe;
		TextView tv_heshu;
		TextView tv_kuadu;
		TextView tv_daxiao;
		TextView tv_012;
		TextView tv_hezhi;

		public VH(@NonNull View itemView) {
			super(itemView);
			tv_item = itemView.findViewById(R.id.tv_item);
			tv_jiou = itemView.findViewById(R.id.tv_jiou);
			tv_zhihe = itemView.findViewById(R.id.tv_zhihe);
			tv_heshu = itemView.findViewById(R.id.tv_heshu);
			tv_kuadu = itemView.findViewById(R.id.tv_kuadu);
			tv_daxiao = itemView.findViewById(R.id.tv_daxiao);
			tv_012 = itemView.findViewById(R.id.tv_012);
			tv_hezhi = itemView.findViewById(R.id.tv_hezhi);
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
		vh.tv_jiou.setText(mListDatas.get(i).getJiou());
		vh.tv_zhihe.setText(mListDatas.get(i).getZhihe());
		vh.tv_heshu.setText(mListDatas.get(i).getTotal() + "");
		vh.tv_kuadu.setText(mListDatas.get(i).getKuadu() + "");
		vh.tv_daxiao.setText(mListDatas.get(i).getDaxiao());
		vh.tv_012.setText(mListDatas.get(i).get_012());
		vh.tv_hezhi.setText(mListDatas.get(i).getHezhi() + "");
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
