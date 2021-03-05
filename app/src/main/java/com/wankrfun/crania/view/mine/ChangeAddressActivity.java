package com.wankrfun.crania.view.mine;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.wankrfun.crania.R;
import com.wankrfun.crania.adapter.SearchAddressAdapter;
import com.wankrfun.crania.base.BaseActivity;
import com.wankrfun.crania.base.SpConfig;
import com.wankrfun.crania.viewmodel.MineViewModel;
import com.wankrfun.crania.widget.XEditText;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.view.mine
 * @ClassName: ChangeAddressActivity
 * @Description: 修改用户位置页面
 * @Author: 鹿鸿祥
 * @CreateDate: 2/24/21 2:12 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2/24/21 2:12 PM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class ChangeAddressActivity extends BaseActivity implements PoiSearch.OnPoiSearchListener {
    @BindView(R.id.fake_status_bar)
    View fakeStatusBar;
    @BindView(R.id.tv_bar_title)
    AppCompatTextView tvBarTitle;
    @BindView(R.id.tv_bar_right)
    AppCompatTextView tvBarRight;
    @BindView(R.id.et_search)
    XEditText etSearch;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_my_address)
    AppCompatTextView tvMyAddress;
    private MineViewModel mineViewModel;
    private SearchAddressAdapter searchAddressAdapter;

    @Override
    protected int initLayoutRes() {
        return R.layout.activity_change_address;
    }

    @Override
    public boolean isUseEventBus() {
        return false;
    }

    @Override
    protected void initDataAndEvent(Bundle savedInstanceState) {
        BarUtils.setStatusBarColor(fakeStatusBar, getResources().getColor(R.color.color_111111));
        KeyboardUtils.showSoftInput(etSearch);
        tvBarTitle.setText("修改位置");
        tvBarRight.setText("确定");
        etSearch.setText(getIntent().getStringExtra("address"));

        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        searchAddressAdapter = new SearchAddressAdapter(R.layout.adapter_search_address, null, "edit");
        recyclerView.setAdapter(searchAddressAdapter);

        searchAddressAdapter.setOnItemClickListener((adapter, view, position) -> {
            searchAddressAdapter.setIsSelect(searchAddressAdapter.getData().get(position).getPoiId());
            etSearch.setText(searchAddressAdapter.getData().get(position).getCityName() + "," + searchAddressAdapter.getData().get(position).getProvinceName());
        });

        mineViewModel = getViewModel(MineViewModel.class);

        //修改位置成功返回结果
        mineViewModel.userUploadAddressLiveData.observe(this, eventsCreateBean -> {
            ToastUtils.showShort(eventsCreateBean.getData().getMsg());
            finish();
        });
    }

    /**
     * 开始进行poi搜索
     */
    protected void doSearchQuery(String keyWord) {
        PoiSearch.Query mQuery = new PoiSearch.Query(keyWord, "", "");//第一个参数表示搜索字符串，第二个参数表示poi搜索类型，第三个参数表示poi搜索区域（空字符串代表全国）
        mQuery.setPageSize(50);// 设置每页最多返回多少条poiitem
        mQuery.setPageNum(0);// 设置查第一页
        mQuery.setCityLimit(true);
        PoiSearch mPoiSearch = new PoiSearch(activity, mQuery);
        mPoiSearch.setOnPoiSearchListener(this);
        mPoiSearch.searchPOIAsyn();// 异步搜索
    }

    @Override
    public void onPoiSearched(PoiResult poiResult, int resultCode) {
        if (resultCode == AMapException.CODE_AMAP_SUCCESS) {
            if (poiResult != null && poiResult.getPois().size() > 0) {
                List<PoiItem> poiItemList = poiResult.getPois();
                searchAddressAdapter.setKeyword(etSearch.getTrimmedString());
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

    @OnTextChanged(value = R.id.et_search, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    void getSearchAddress(Editable editable) {
        if (searchAddressAdapter == null){
            return;
        }
        if (TextUtils.isEmpty(editable.toString().trim())){
            searchAddressAdapter.setNewData(null);
            tvMyAddress.setVisibility(View.VISIBLE);
        }else {
            if (editable.toString().trim().equals(SPUtils.getInstance().getString(SpConfig.CITY) + "," +SPUtils.getInstance().getString(SpConfig.PROVINCE))){
                searchAddressAdapter.setNewData(null);
                tvMyAddress.setVisibility(View.VISIBLE);
            }else {
                doSearchQuery(editable.toString().trim());
                tvMyAddress.setVisibility(View.GONE);
            }
        }
    }

    @OnClick({R.id.iv_bar_back, R.id.tv_bar_right, R.id.tv_my_address})
    void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_bar_back:
                finish();
                break;
            case R.id.tv_bar_right:
                if (TextUtils.isEmpty(etSearch.getTrimmedString())){
                    ToastUtils.showShort("位置不能为空");
                    return;
                }
                mineViewModel.getUploadAddress(etSearch.getTrimmedString());
                break;
            case R.id.tv_my_address://我的定位
                etSearch.setText(SPUtils.getInstance().getString(SpConfig.CITY) + "," +SPUtils.getInstance().getString(SpConfig.PROVINCE));
                tvMyAddress.setVisibility(View.GONE);
                break;
        }
    }
}
