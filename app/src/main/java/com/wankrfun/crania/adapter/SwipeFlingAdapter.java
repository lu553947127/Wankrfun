package com.wankrfun.crania.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.wankrfun.crania.R;
import com.wankrfun.crania.bean.BaseBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.adapter
 * @ClassName: SwipeFlingAdapter
 * @Description: java类作用描述
 * @Author: 鹿鸿祥
 * @CreateDate: 1/19/21 10:25 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/19/21 10:25 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class SwipeFlingAdapter extends BaseAdapter{

    ArrayList<Talent> objs;

    public SwipeFlingAdapter() {
        objs = new ArrayList<>();
    }

    public void addAll(Collection<Talent> collection) {
        if (isEmpty()) {
            objs.addAll(collection);
            notifyDataSetChanged();
        } else {
            objs.addAll(collection);
        }
    }

    public void clear() {
        objs.clear();
        notifyDataSetChanged();
    }

    public boolean isEmpty() {
        return objs.isEmpty();
    }

    public void remove(int index) {
        if (index > -1 && index < objs.size()) {
            objs.remove(index);
            notifyDataSetChanged();
        }
    }


    @Override
    public int getCount() {
        return objs.size();
    }

    @Override
    public Talent getItem(int position) {
        if(objs==null ||objs.size()==0) return null;
        return objs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // TODO: getView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Talent talent = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_swipe_fling, parent, false);
            holder  = new ViewHolder();
            convertView.setTag(holder);
//            convertView.getLayoutParams().width = cardWidth;
//            holder.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
            //holder.portraitView.getLayoutParams().width = cardWidth;
//            holder.portraitView.getLayoutParams().height = cardHeight;
//            holder.nameView = (TextView) convertView.findViewById(R.id.name);
            //parentView.getLayoutParams().width = cardWidth;
            //holder.jobView = (TextView) convertView.findViewById(R.id.job);
            //holder.companyView = (TextView) convertView.findViewById(R.id.company);
//            holder.cityView = (TextView) convertView.findViewById(R.id.city);
//            holder.eduView = (TextView) convertView.findViewById(R.id.education);
//            holder.workView = (TextView) convertView.findViewById(R.id.work_year);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


//        holder.portraitView.setImageResource(talent.headerIcon);

//        holder.nameView.setText(String.format("%s", talent.nickname));
        //holder.jobView.setText(talent.jobName);

//        final CharSequence no = "暂无";

//        holder.cityView.setHint(no);
//        holder.cityView.setText(talent.cityName);
//        holder.cityView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.home01_icon_location,0,0);
//
//        holder.eduView.setHint(no);
//        holder.eduView.setText(talent.educationName);
//        holder.eduView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.home01_icon_edu,0,0);
//
//        holder.workView.setHint(no);
//        holder.workView.setText(talent.workYearName);
//        holder.workView.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.home01_icon_work_year,0,0);


        return convertView;
    }

    private static class ViewHolder {
        ImageView portraitView;
        TextView nameView;
        TextView cityView;
        TextView eduView;
        TextView workView;
        CheckedTextView collectView;

    }

    public static class Talent {
        public int headerIcon;
        public String nickname;
        public String cityName;
        public String educationName;
        public String workYearName;
    }
}
