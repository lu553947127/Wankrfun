package com.wankrfun.crania.dialog;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.EventsAddChildrenAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.utils.RefreshUtils;
import com.xujiaji.happybubble.BubbleDialog;
import com.xujiaji.happybubble.BubbleLayout;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: BubbleTipDialog
 * @Description: 自定义气泡尖角自定义布局
 * @Author: 鹿鸿祥
 * @CreateDate: 1/29/21 3:35 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/29/21 3:35 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint({"InflateParams","SetTextI18n"})
public class BubbleTipDialog extends BubbleDialog {

    private OnClickCustomButtonListener mListener;

    public interface OnClickCustomButtonListener {
        void onClick(int image, String pos);
    }

    public void setClickListener(OnClickCustomButtonListener l) {
        this.mListener = l;
    }

    private EventsAddChildrenAdapter eventsAddChildrenAdapter;
    public BubbleTipDialog(BaseActivity activity, String title){
        super(activity);
        BubbleLayout bubbleLayout = new BubbleLayout(activity);
        bubbleLayout.setBubbleColor(Color.BLACK);
        bubbleLayout.setShadowColor(Color.BLACK);
        bubbleLayout.setBubbleRadius(10);
        setBubbleLayout(bubbleLayout);
        setTransParentBackground();
        setPosition(Position.TOP);
        View rootView = LayoutInflater.from(activity).inflate(R.layout.dialog_popup_events, null);
        ViewHolder mViewHolder = new ViewHolder(rootView);
        addContentView(rootView);

        mViewHolder.appCompatTextView.setText("更多" + title + "图标");
        mViewHolder.recyclerView.setLayoutManager(new GridLayoutManager(activity, 5, LinearLayoutManager.VERTICAL, false));
        eventsAddChildrenAdapter = new EventsAddChildrenAdapter(R.layout.adapter_events_add_children, null);
        switch (title){
            case "饭局":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsFoodList());
                break;
            case "运动":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsMotionList());
                break;
            case "游戏":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsGameList());
                break;
            case "下午茶":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsTeaList());
                break;
            case "k歌":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsKaraokeList());
                break;
            case "桌游":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsBoardPlaList());
                break;
            case "电影":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsFilmList());
                break;
            case "夜生活":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsNightLifeList());
                break;
            case "其他":
                eventsAddChildrenAdapter.setNewData(RefreshUtils.getEventsNotList());
                break;
        }

        mViewHolder.recyclerView.setAdapter(eventsAddChildrenAdapter);

        eventsAddChildrenAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mListener != null) {
                mListener.onClick(eventsAddChildrenAdapter.getData().get(position).getImages(), String.valueOf(position));
            }
        });
    }

    private static class ViewHolder {
        AppCompatTextView appCompatTextView;
        RecyclerView recyclerView;
        public ViewHolder(View rootView) {
            appCompatTextView = rootView.findViewById(R.id.tv_title);
            recyclerView = rootView.findViewById(R.id.rv);
        }
    }
}
