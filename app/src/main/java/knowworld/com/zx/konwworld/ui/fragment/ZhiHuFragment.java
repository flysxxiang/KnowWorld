package knowworld.com.zx.konwworld.ui.fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.LoadingFooter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import knowworld.com.zx.konwworld.Constants;
import knowworld.com.zx.konwworld.R;
import knowworld.com.zx.konwworld.adapter.ZhiHuRecycViewAdapter;
import knowworld.com.zx.konwworld.api.RetrofitHelper;
import knowworld.com.zx.konwworld.base.BaseFragment;
import knowworld.com.zx.konwworld.bean.Getposts;
import knowworld.com.zx.konwworld.ui.activity.WeixinDetailsActivity;
import knowworld.com.zx.konwworld.utils.LogUtils;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author fly_xiang_mac
 * @description
 * @time 2016-09-18
 */
public class ZhiHuFragment extends BaseFragment {
    @Bind(R.id.recyclerview)
    LRecyclerView mRecyclerView;
    @Bind(R.id.zhihu_tv)
    TextView mTextView;
    private List<Getposts.AnswersBean> mDatas;

    private StringBuilder timeString = new StringBuilder();
    private int mMYear;
    private int mMMonthOfYear;
    private int mMDayOfMonth;

    @Override
    public View setView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recyelerview, null);

        return view;
    }

    @Override
    public void afterView () {
        Calendar calendar = Calendar.getInstance();
        mMYear = calendar.get(Calendar.YEAR);
        mMMonthOfYear = calendar.get(Calendar.MONTH);
        mMDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        getTime(mMYear, mMMonthOfYear, mMDayOfMonth);
        mTextView.setText("知乎头条时间选择:" + timeString);
        loadData(timeString.toString());

    }


    @OnClick(R.id.zhihu_tv)
    void selectTime () {
        DatePickerDialog datePickerDialog = new DatePickerDialog(mActivity, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet (DatePicker view, int year, int monthOfYear,
                                   int dayOfMonth) {

                getTime(year, monthOfYear, dayOfMonth);
                loadData(timeString.toString());
            }
        }, mMYear, mMMonthOfYear, mMDayOfMonth);

        // 禁止日期
        Date date = new Date();
        long time = date.getTime();
        datePickerDialog.getDatePicker().setMaxDate(time);
        datePickerDialog.show();
    }

    private void loadData (String time) {
        Observable<Getposts> observable = RetrofitHelper.getInstance().getZhiHuRetrofit().ZhihuRepos(time);
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new Subscriber<Getposts>() {
                            @Override
                            public void onCompleted () {
                            }

                            @Override
                            public void onError (Throwable e) {
                            }

                            @Override
                            public void onNext (Getposts retrofitEntity) {
                                mDatas = retrofitEntity.getAnswers();
                                LinearLayoutManager manager = new LinearLayoutManager(getActivity());
                                mRecyclerView.setLayoutManager(manager);

                                mRecyclerView.setRefreshProgressStyle(ProgressStyle.BallTrianglePath); //设置下拉刷新Progress的样式
                                mRecyclerView.setArrowImageView(R.drawable.ic_pulltorefresh_arrow);  //设置下拉刷新箭头
                                mRecyclerView.setPullRefreshEnabled(false);
                                ZhiHuRecycViewAdapter adapter = new ZhiHuRecycViewAdapter(getActivity(), mDatas);
                                LRecyclerViewAdapter mLRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
                                mRecyclerView.setAdapter(mLRecyclerViewAdapter);

                                mLRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                                    @Override
                                    public void onItemClick (View view, int i) {
                                        Intent intent = new Intent(getActivity(), WeixinDetailsActivity.class);
                                        intent.putExtra("url", Constants.AnswerUrl + mDatas.get(i).getQuestionid() + "/answer/" + mDatas.get(i).getAnswerid());
                                        startActivity(intent);
                                    }

                                    @Override
                                    public void onItemLongClick (View view, int i) {

                                    }
                                });
                            }
                        });

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {
            @Override
            public void onRefresh () {

            }

            @Override
            public void onScrollUp () {

            }

            @Override
            public void onScrollDown () {

            }

            @Override
            public void onBottom () {
                RecyclerViewStateUtils.setFooterViewState(mActivity, mRecyclerView, mDatas.size(), LoadingFooter.State.TheEnd, null);
            }

            @Override
            public void onScrolled (int i, int i1) {

            }

            @Override
            public void onScrollStateChanged (int i) {

            }
        });

    }

    private void getTime (int year, int monthOfYear, int dayOfMonth) {
        timeString.setLength(0);
        timeString.append(year);
        if ((monthOfYear + 1) < 10) {
            timeString.append("0").append(monthOfYear + 1);
        } else {
            timeString.append("").append(monthOfYear + 1);
        }

        if (dayOfMonth < 10) {
            timeString.append("0").append(dayOfMonth);
        } else {
            timeString.append("").append(dayOfMonth);
        }

        LogUtils.i("当前时间" + timeString.toString());
    }
}