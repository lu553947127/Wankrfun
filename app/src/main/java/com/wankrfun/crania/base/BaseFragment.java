package com.wankrfun.crania.base;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.base
 * @ClassName: BaseFragment
 * @Description: 公共Fragment
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:03 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:03 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public abstract class BaseFragment extends Fragment implements IView{
    public Context mContext;
    public BaseActivity mActivity;
    public Bundle arguments;
    protected Unbinder unBinder;
    protected LoadDialog loadDialog;
    protected SparseArray<BaseDialog> dialogArray = new SparseArray<>();

    protected boolean isPrepared = false;//页面ui初始化完成
    protected boolean isInited = false;//数据是否已从服务器拉取，拉取成功后设为true

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
        this.mActivity = (BaseActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arguments = getArguments();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);
        unBinder = ButterKnife.bind(this, view);
        if (isUseEventBus()) {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataAndEvent(savedInstanceState, view);
        load();
    }

    private void load() {
        initDataFromService();
    }

    @Override
    public void showLoading() {
        if (loadDialog == null) {
            loadDialog = new LoadDialog(mActivity);
        }
        loadDialog.showDialog();
    }

    @Override
    public void hideLoading() {
        if (loadDialog != null) {
            loadDialog.hideDialog();
        }
    }

    /**
     * 保险起见的dialog关闭，防止内存泄漏
     */
    public void addDialog(BaseDialog dialog) {
        dialogArray.put(dialogArray.size(), dialog);
    }

    @Override
    public void onDestroyView() {
        unBinder.unbind();
        if (isUseEventBus() && EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (loadDialog != null) {
            loadDialog.dismiss();
            loadDialog = null;
        }
        if (dialogArray != null) {
            for (int i = 0; i < dialogArray.size(); i++) {
                if (dialogArray.get(i) != null) {
                    dialogArray.get(i).dismiss();
                }
            }
        }

        dialogArray = null;
        super.onDestroyView();
    }

    /**
     * 初始化布局
     *
     * @return 布局
     */
    protected abstract int initLayout();

    /**
     * EventBus开关
     */
    public abstract boolean isUseEventBus();

    /**
     * 初始化数据
     *
     * @param savedInstanceState 数据状态
     */
    protected abstract void initDataAndEvent(Bundle savedInstanceState, View view);


    /**
     * 从服务器获取数据
     */
    protected abstract void initDataFromService();
}
