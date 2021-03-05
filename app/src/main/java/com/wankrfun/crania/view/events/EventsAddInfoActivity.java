package com.wankrfun.crania.view.events;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.XPopup;
import com.parse.ParseFile;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.GridImageAdapter;
import com.wankrfun.crania.app.MyApplication;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.bean.EventsDetailBean;
import com.wankrfun.crania.dialog.CustomBottomAddress;
import com.wankrfun.crania.dialog.CustomBottomPeople;
import com.wankrfun.crania.dialog.CustomBottomQuestions;
import com.wankrfun.crania.dialog.CustomBottomTime;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.image.ImageConfig;
import com.wankrfun.crania.image.ImageLoader;
import com.wankrfun.crania.utils.EventsUtils;
import com.wankrfun.crania.utils.NumberUtils;
import com.wankrfun.crania.utils.ParseUtils;
import com.wankrfun.crania.utils.PermissionUtils;
import com.wankrfun.crania.utils.PictureEnlargeUtils;
import com.wankrfun.crania.utils.PictureUtils;
import com.wankrfun.crania.viewmodel.EventsViewModel;
import com.wankrfun.crania.widget.CircleImageView;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.events
 * @ClassName: EventsAddInfoActivity
 * @Description: 活动添加详细信息页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/1/21 2:45 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/1/21 2:45 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class EventsAddInfoActivity extends BaseActivity {
    @BindView(R.id.iv_icon)
    AppCompatImageView ivIcon;
    @BindView(R.id.tv_type)
    AppCompatTextView tvType;
    @BindView(R.id.et_events)
    AppCompatEditText etEvents;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_address)
    AppCompatTextView tvAddress;
    @BindView(R.id.tv_time)
    AppCompatTextView tvTime;
    @BindView(R.id.tv_people)
    AppCompatTextView tvPeople;
    @BindView(R.id.iv_avatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tv_questions)
    AppCompatTextView tvQuestions;
    private GridImageAdapter gridImageAdapter;
    private List<String> picture_list = new ArrayList<>();
    private EventsViewModel eventsViewModel;

    private ParseFile eventImage;
    private boolean isLocUnlimited, isTimeUnlimited;
    private double longitude, latitude;
    private String eventType, eventTypeIcon, event_address, activity_time, eventSex;
    private int max_num;
    private EventsDetailBean eventsDetailBean;
    private List<Object> eventEditImage = new ArrayList<>();

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_events_add_info;
    }

    @Override
    public boolean isUseEventBus() {
        return true;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        ImageLoader.load(activity, new ImageConfig.Builder()
                .url(ParseUtils.getUserPhoto())
                .imageView(ivAvatar)
                .placeholder(R.drawable.rc_default_portrait)
                .errorPic(R.drawable.rc_default_portrait)
                .build());

        recyclerView.setLayoutManager(new GridLayoutManager(this, 3, LinearLayoutManager.VERTICAL, false));
        gridImageAdapter = new GridImageAdapter(picture_list);
        recyclerView.setAdapter(gridImageAdapter);

        //自定义添加图片九宫格布局 组合监听事件
        gridImageAdapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onTakePhotoClick(View view, int position) {
                PermissionUtils.openCameraOfStoragePermission(activity, 6);
            }

            @Override
            public void onItemClick(View view, int position) {
                PictureEnlargeUtils.getPictureEnlargeList(activity, PictureUtils.getImageBitmapAdd(picture_list), position);
            }

            @Override
            public void onItemDelClick(View view, int position) {
                new XPopup.Builder(activity).asConfirm(getString(R.string.reminder), "确认要删除该图片？", () -> {
                    picture_list.remove(position);
                    gridImageAdapter.setNewData(picture_list);
                }).show();
            }
        });

        getEditDetail();

        eventsViewModel = getViewModel(EventsViewModel.class);

        //创建活动成功返回结果
        eventsViewModel.eventsCreateLiveData.observe(this, eventsCreateBean -> {
            hideLoading();
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            EventBus.getDefault().post(new EventsEvent());
            ActivityUtils.finishActivity(EventsAddActivity.class);
            finish();
        });

        //编辑活动成功返回结果
        eventsViewModel.eventsEditLiveData.observe(this, eventsCreateBean -> {
            hideLoading();
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            EventBus.getDefault().post(new EventsEvent());
            ActivityUtils.finishActivity(EventsAddActivity.class);
            finish();
        });
    }

    /**
     * 活动编辑
     */
    private void getEditDetail() {
        eventsDetailBean = (EventsDetailBean) getIntent().getSerializableExtra("info");
        if (eventsDetailBean != null){
            eventType = eventsDetailBean.getData().getEvent().getEventType();
            eventTypeIcon = eventsDetailBean.getData().getEvent().getEventTypeIcon();

            EventsUtils.getEventsIcon(tvType, tvType, ivIcon, ivIcon, eventsDetailBean.getData().getEvent().getEventType(), eventsDetailBean.getData().getEvent().getEventTypeIcon());
            etEvents.setText(eventsDetailBean.getData().getEvent().getEvent_contents());

            picture_list.addAll(eventsDetailBean.getData().getEvent().getImages());
            eventEditImage.addAll(eventsDetailBean.getData().getEvent().getImages());
            gridImageAdapter.setNewEditData(picture_list, "edit");

            longitude = eventsDetailBean.getData().getEvent().getEventLocation().getLongitude();
            latitude = eventsDetailBean.getData().getEvent().getEventLocation().getLatitude();
            if (TextUtils.isEmpty(eventsDetailBean.getData().getEvent().getEvent_address())){
                isLocUnlimited = true;
                event_address = "可以商讨";
                tvAddress.setText(event_address);
            }else {
                isLocUnlimited = eventsDetailBean.getData().getEvent().getEvent_address().equals("可以商讨");
                event_address = eventsDetailBean.getData().getEvent().getEvent_address();
                tvAddress.setText(eventsDetailBean.getData().getEvent().getEvent_address());
            }

            isTimeUnlimited = NumberUtils.dealDateFormat(eventsDetailBean.getData().getEvent().getActivity_time()).equals("2200-01-01 08:30:00");
            activity_time = NumberUtils.dealDateFormat(eventsDetailBean.getData().getEvent().getActivity_time()).equals("2200-01-01 08:30:00") ?
                    "可以商讨" : NumberUtils.dealDateFormat(eventsDetailBean.getData().getEvent().getActivity_time());
            tvTime.setText(activity_time);

            max_num = eventsDetailBean.getData().getEvent().getMax_num();
            String peopleNum = max_num == 0 ? "不限人数" : eventsDetailBean.getData().getEvent().getMax_num() + "人";

            switch (eventsDetailBean.getData().getEvent().getEventSex()){
                case "x":
                    eventSex = "x:";
                    tvPeople.setText(peopleNum + ",不限性别");
                    break;
                case "m":
                    eventSex = "m:";
                    tvPeople.setText(peopleNum + ",限男生");
                    break;
                case "f":
                    eventSex = "f:";
                    tvPeople.setText(peopleNum + ",限女生");
                    break;
            }

        }else {
            eventType = getIntent().getStringExtra("type");
            eventTypeIcon = getIntent().getStringExtra("typeChildren");
            ivIcon.setImageResource(getIntent().getIntExtra("image", 0));
            tvType.setText(getIntent().getStringExtra("title"));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PermissionUtils.ALBUM_CODE & resultCode == RESULT_OK) {
            if (MyApplication.isAndroidQ) {
                if (Matisse.obtainResult(data).size() + picture_list.size() > 6){
                    ToastUtils.showShort(getString(R.string.photo_size_not));
                    return;
                }
                for (int i = 0; i < Matisse.obtainResult(data).size(); i++) {
                    picture_list.add(ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(i)));
                    eventEditImage.add(ParseUtils.setImageFile(new File(ImageLoader.getUriRealFilePath(activity, Matisse.obtainResult(data).get(i)))));
                }
            }else {
                if (Matisse.obtainPathResult(data).size()+ picture_list.size() > 6){
                    ToastUtils.showShort(getString(R.string.photo_size_not));
                    return;
                }
                picture_list.addAll(Matisse.obtainPathResult(data));
                for (int i = 0; i < Matisse.obtainPathResult(data).size(); i++) {
                    eventEditImage.add(ParseUtils.setImageFile(new File(Matisse.obtainPathResult(data).get(i))));
                }
            }
            LogUtils.e(picture_list);
            gridImageAdapter.setNewData(picture_list);
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_recommend, R.id.tv_address, R.id.tv_time, R.id.tv_people, R.id.ll_questions, R.id.tv_release})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_recommend://智能推荐
                break;
            case R.id.tv_address://选择地点
                new XPopup.Builder(activity)
                        .dismissOnTouchOutside(false)
                        .enableDrag(false)
                        .asCustom(new CustomBottomAddress(activity))
                        .show();
                break;
            case R.id.tv_time://选择时间
                new XPopup.Builder(activity)
                        .dismissOnTouchOutside(false)
                        .enableDrag(false)
                        .asCustom(new CustomBottomTime(activity))
                        .show();
                break;
            case R.id.tv_people://选择参与人
                new XPopup.Builder(activity)
                        .dismissOnTouchOutside(false)
                        .enableDrag(false)
                        .asCustom(new CustomBottomPeople(activity))
                        .show();
                break;
            case R.id.ll_questions://选问题
                new XPopup.Builder(activity)
                        .autoOpenSoftInput(true)
                        .dismissOnTouchOutside(false)
                        .enableDrag(false)
                        .asCustom(new CustomBottomQuestions(activity))
                        .show();
                break;
            case R.id.tv_release://发布

                if (TextUtils.isEmpty(eventType)){
                    ToastUtils.showShort("活动类型不能为空");
                    return;
                }

                if (TextUtils.isEmpty(eventTypeIcon)){
                    ToastUtils.showShort("活动类型不能为空");
                    return;
                }

                if (TextUtils.isEmpty(etEvents.getText().toString())){
                    ToastUtils.showShort("活动内容不能为空");
                    return;
                }

                if (picture_list.size() != 0){
                    eventImage = ParseUtils.setImageFile(new File(picture_list.get(0)));
                }

                if (!isLocUnlimited && TextUtils.isEmpty(event_address)){
                    ToastUtils.showShort("地点不能为空");
                    return;
                }

                if (!isTimeUnlimited && TextUtils.isEmpty(activity_time)){
                    ToastUtils.showShort("时间不能为空");
                    return;
                }

                if (TextUtils.isEmpty(eventSex)){
                    ToastUtils.showShort("参与人不能为空");
                    return;
                }

                showLoading();

                if (eventsDetailBean != null){
                    eventsViewModel.getEventsEdit(
                            eventsDetailBean.getData().getEvent().getObjectId()
                            , eventsDetailBean.getData().getEvent().getEventCreator()
                            , eventType
                            , eventTypeIcon
                            , etEvents.getText().toString()
                            , etEvents.getText().toString()
                            , eventImage
                            , eventEditImage
                            , isLocUnlimited
                            , longitude
                            , latitude
                            , event_address
                            , isTimeUnlimited
                            , activity_time
                            , max_num
                            , eventSex);
                }else {
                    eventsViewModel.getEventsCreate(eventType
                            , eventTypeIcon
                            , etEvents.getText().toString()
                            , etEvents.getText().toString()
                            , eventImage
                            , ParseUtils.setImageFile(picture_list)
                            , isLocUnlimited
                            , longitude
                            , latitude
                            , event_address
                            , isTimeUnlimited
                            , activity_time
                            , max_num
                            , eventSex);
                }
                break;
        }
    }

    @Subscribe
    public void onEventEvents(EventsEvent event) {
        switch (event.getType()){
            case "address"://选择地点
                tvAddress.setText(event.getTitle());
                event_address = event.getTitle();
                if (event.getTitle().equals("可以商讨")){
                    isLocUnlimited = true;
                    longitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE));
                    latitude = Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE));
                }else {
                    isLocUnlimited = false;
                    longitude = event.getLongitude();
                    latitude = event.getLatitude();
                }
                break;
            case "time"://选择时间
                tvTime.setText(event.getTitle());
                if (event.getTitle().equals("可以商讨")){
                    isTimeUnlimited = true;
                    activity_time = "2200-01-01 08:30:00";
                }else {
                    isTimeUnlimited = false;
                    activity_time = event.getTitle();
                }
                break;
            case "people"://选择参与人
                tvPeople.setText(event.getTitle());
                if (event.getTitle().equals("不限人数")){
                    max_num = 0;
                }else {
                    max_num = event.getNum();
                }

                switch (event.getSex()){
                    case "不限性别":
                        eventSex = "x:";
                        break;
                    case "限男生":
                        eventSex = "m:";
                        break;
                    case "限女生":
                        eventSex = "f:";
                        break;
                }
                break;
            case "questions"://选择问题
                tvQuestions.setText(event.getTitle());
                break;
        }
    }
}
