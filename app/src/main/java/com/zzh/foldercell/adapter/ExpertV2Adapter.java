package com.zzh.foldercell.adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ramotion.foldingcell.FoldingCell;
import com.zzh.foldercell.ExpertV2Activity;
import com.zzh.vae.R;
import com.zzh.vae.model.DoctorMsg;

import java.util.HashSet;
import java.util.List;

/**
 * Created by ZZH on 16/5/11.
 *
 * @Date: 16/5/11
 * @Email: zzh_hz@126.com
 * @QQ: 1299234582
 * @Author: zzh
 * @Description:
 */
public class ExpertV2Adapter extends ArrayAdapter<DoctorMsg> {

    private HashSet<Integer> unfoldedIndexes = new HashSet<>();
    private List<DoctorMsg> dataList;
    private Context mContext;

    public ExpertV2Adapter(Context context, List<DoctorMsg> objects) {
        super(context, 0, objects);
        this.dataList = objects;
        mContext = context;
    }

    public void addAll(List<DoctorMsg> msg){
        dataList.addAll(msg);
    }

    public void clear(){
        dataList.clear();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final DoctorMsg msg = getItem(position);
        final ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_v2_expert_fragment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();

            FoldingCell cell = holder.foldingCell;
            if (unfoldedIndexes.contains(position)) {
                cell.unfold(true);
            } else {
                cell.fold(true);
            }
        }

        //bind data
        holder.expert_dept.setText(msg.getSpecialistDepartment());
        holder.expert_dept_detail.setText(msg.getSpecialistDepartment());
        holder.expert_good.setText(msg.getSpecialistStrongth());//擅长
        holder.expert_name.setText(msg.getSpecialistName());
        holder.expert_name_detail.setText(msg.getSpecialistName());
        holder.expert_hospital.setText(msg.getSpecialistHospital());
        holder.expert_hospital_detail.setText(msg.getSpecialistHospital());
        holder.expert_info.setText(msg.getSpecialistIntroduce());
        if ("1".equals(msg.getSpecialistLevel())) {
            holder.expert_level.setText("副主任医师");
            holder.expert_level_detail.setText("副主任医师");
        } else if ("2".equals(msg.getSpecialistLevel())) {
            holder.expert_level.setText("主任医师");
            holder.expert_level_detail.setText("主任医师");
        } else if ("3".equals(msg.getSpecialistLevel())) {
            holder.expert_level.setText("知名专家");
            holder.expert_level_detail.setText("知名专家");
        } else if ("4".equals(msg.getSpecialistLevel())) {
            holder.expert_level_detail.setText("首席专家");
            holder.expert_level.setText("首席专家");
        } else if ("5".equals(msg.getSpecialistLevel())) {
            holder.expert_level.setText("住治医师");
            holder.expert_level_detail.setText("住治医师");
        } else if ("6".equals(msg.getSpecialistLevel())) {
            holder.expert_level.setText("住院医师");
            holder.expert_level_detail.setText("住院医师");
        } else {
            holder.expert_level.setText("");
            holder.expert_level_detail.setText("");
        }
        Glide.with(mContext).load(msg.getIconUrl()).centerCrop().override(100, 100).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.expert_head);
        Glide.with(mContext).load(msg.getIconUrl()).centerCrop().override(100, 100).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.expert_head_detail);
        holder.show_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "------: "+position, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, ExpertV2Activity.class);
                intent.putExtra("msg", getItem(position));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) mContext, Pair.create((View)holder.expert_head_detail, "expert_head_detail"));
                    Bundle bundle = options.toBundle();
                    mContext.startActivity(intent, bundle);
                } else {
                    intent.putExtra("msg", getItem(position));
                    mContext.startActivity(intent);
                }
            }
        });
        return convertView;
    }

    // simple methods for register cell state changes
    public void registerToggle(int position) {
        if (unfoldedIndexes.contains(position))
            registerFold(position);
        else
            registerUnfold(position);
    }

    public void registerFold(int position) {
        unfoldedIndexes.remove(position);
    }

    public void registerUnfold(int position) {
        unfoldedIndexes.add(position);
    }

    static class ViewHolder{

        ImageView expert_head;
        ImageView expert_head_detail;
        ImageView expert_hospital_bg;

        TextView expert_name;
        TextView expert_name_detail;
        TextView expert_level;
        TextView expert_level_detail;
        TextView expert_dept;
        TextView expert_dept_detail;
        TextView expert_hospital;
        TextView expert_hospital_detail;
        TextView expert_good; //擅长
        TextView expert_info; //简介
        TextView show_info;

        FoldingCell foldingCell;
        public ViewHolder(View view){
            expert_head = (ImageView) view.findViewById(R.id.expert_head);
            expert_head_detail = (ImageView) view.findViewById(R.id.expert_head_detail);
            expert_hospital_bg = (ImageView) view.findViewById(R.id.expert_hospital_bg);

            expert_name = (TextView) view.findViewById(R.id.expert_name);
            expert_name_detail = (TextView) view.findViewById(R.id.expert_name_detail);

            expert_level = (TextView) view.findViewById(R.id.expert_level);
            expert_level_detail = (TextView) view.findViewById(R.id.expert_level_detail);
            expert_dept = (TextView) view.findViewById(R.id.expert_dept);
            expert_dept_detail = (TextView) view.findViewById(R.id.expert_dept_detail);
            expert_hospital = (TextView) view.findViewById(R.id.expert_hospital);
            expert_hospital_detail = (TextView) view.findViewById(R.id.expert_hospital_detail);

            expert_good = (TextView) view.findViewById(R.id.expert_good);
            expert_info = (TextView) view.findViewById(R.id.expert_info);
            show_info = (TextView) view.findViewById(R.id.show_info);

            foldingCell = (FoldingCell) view.findViewById(R.id.foldingCell);
        }
    }
}
