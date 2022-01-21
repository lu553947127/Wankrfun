package com.wankrfun.crania.dialog;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.lxj.xpopup.core.BottomPopupView;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.SearchAddressAdapter;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.event.EventsEvent;
import com.wankrfun.crania.widget.XEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.dialog
 * @ClassName: CustomBottomAddress
 * @Description: 自定义选择地址底部弹窗
 * @Author: 鹿鸿祥
 * @CreateDate: 2/2/21 3:24 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/2/21 3:24 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@SuppressLint("CutPasteId")
public class CustomBottomAddress extends BottomPopupView implements PoiSearch.OnPoiSearchListener {
    private Context context;
    private SearchAddressAdapter searchAddressAdapter;
    private XEditText etKeyword;
    private String address = "可以商讨";
    private double longitude, latitude;

    public CustomBottomAddress(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected int getImplLayoutId() {
        return R.layout.dialog_custom_address;
    }

    @Override
    protected void onCreate() {
        super.onCreate();
        RecyclerView recyclerView = findViewById(R.id.rv);
        etKeyword = findViewById(R.id.et_search);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        searchAddressAdapter = new SearchAddressAdapter(R.layout.adapter_search_address, null);
        searchAddressAdapter.setEmptyView(R.layout.layout_loading, recyclerView);
        recyclerView.setAdapter(searchAddressAdapter);

        searchAddressAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (StringUtils.isTrimEmpty(searchAddressAdapter.getData().get(position).getSnippet())){
                ToastUtils.showShort("当前地址异常，请勿选择");
                return;
            }
            searchAddressAdapter.setIsSelect(searchAddressAdapter.getData().get(position).getPoiId());
            address = searchAddressAdapter.getData().get(position).getSnippet();
            longitude = searchAddressAdapter.getData().get(position).getLatLonPoint().getLongitude();
            latitude = searchAddressAdapter.getData().get(position).getLatLonPoint().getLatitude();
        });

        getSearchAddress();
    }

    @Override
    protected void onShow() {
        super.onShow();

        findViewById(R.id.tv_cancel).setOnClickListener(view -> dismiss());

        findViewById(R.id.tv_yes).setOnClickListener(view -> {
            if (StringUtils.isTrimEmpty(address)){
                ToastUtils.showShort("具体地点，不能为空哦");
                return;
            }
            EventBus.getDefault().post(new EventsEvent("address", address, longitude, latitude));
            dismiss();
        });

        AppCompatTextView tv_specific = findViewById(R.id.tv_specific);
        AppCompatTextView tv_discuss = findViewById(R.id.tv_discuss);

        tv_specific.setOnClickListener(view -> {
            tv_specific.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_discuss.setBackgroundResource(R.drawable.shape_gray_5);
            tv_specific.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            tv_discuss.setTextColor(getResources().getColor(R.color.white));
            findViewById(R.id.ll_search).setVisibility(VISIBLE);
            address = "";
        });

        tv_discuss.setOnClickListener(view -> {
            tv_specific.setBackgroundResource(R.drawable.shape_gray_5);
            tv_discuss.setBackgroundResource(R.drawable.shape_yellow_5);
            tv_specific.setTextColor(getResources().getColor(R.color.white));
            tv_discuss.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            findViewById(R.id.ll_search).setVisibility(GONE);
            address = "可以商讨";
        });

        doSearchQuery(etKeyword.getTrimmedString());
    }

    /**
     * 搜索周边poi
     *
     */
    private void doSearchQuery(String keyWord) {
        //第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        PoiSearch.Query query = new PoiSearch.Query(keyWord, "", "");
        query.setPageSize(30);
        query.setPageNum(0);
        PoiSearch poisearch = null;
        try {
            poisearch = new PoiSearch(context, query);
        } catch (AMapException e) {
            e.printStackTrace();
        }
        poisearch.setOnPoiSearchListener(this);
        poisearch.setBound(new PoiSearch.SearchBound(new LatLonPoint(Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LATITUDE))
                ,Double.parseDouble(SPUtils.getInstance().getString(SpConfig.LONGITUDE))), 500, true));
        poisearch.searchPOIAsyn();
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery2(String keyWord) {
        PoiSearch.Query mQuery = new PoiSearch.Query(keyWord, "", "");//第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        mQuery.setPageSize(50);// 设置每页最多返回多少条poiitem
        mQuery.setPageNum(0);// 设置查第一页
        mQuery.setCityLimit(true);
        PoiSearch mPoiSearch = null;
        try {
            mPoiSearch = new PoiSearch(context, mQuery);
        } catch (AMapException e) {
            e.printStackTrace();
        }
        mPoiSearch.setOnPoiSearchListener(this);
        mPoiSearch.searchPOIAsyn();// 异步搜索
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int resultCode) {
        if (resultCode == AMapException.CODE_AMAP_SUCCESS) {
            if (poiResult != null && poiResult.getPois().size() > 0) {
                List<PoiItem> poiItemList = poiResult.getPois();
                searchAddressAdapter.setKeyword(etKeyword.getTrimmedString());
                searchAddressAdapter.setNewData(poiItemList);
                LogUtils.e(poiItemList);
            }else {
                ToastUtils.showShort("没有搜索到您输入的地址哦");
            }
        }else {
            ToastUtils.showShort("没有搜索到您输入的地址哦");
        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int i) {

    }

    /**
     * 输入框搜索事件
     */
    private void getSearchAddress(){
        etKeyword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (StringUtils.isTrimEmpty(etKeyword.getTrimmedString())) {
                    ToastUtils.showShort("搜索内容不能为空。");
                    return;
                }
                doSearchQuery2(etKeyword.getTrimmedString());
            }
        });

        etKeyword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH) {
                if (StringUtils.isTrimEmpty(etKeyword.getTrimmedString())) {
                    ToastUtils.showShort("搜索内容不能为空。");
                    return true;
                }

                // 先隐藏键盘
                KeyboardUtils.hideSoftInput(etKeyword);
                // 搜索，进行自己要的操作...
                doSearchQuery2(etKeyword.getTrimmedString());
                return true;
            }
            return false;
        });
    }
}
