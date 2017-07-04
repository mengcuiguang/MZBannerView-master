package com.meng.mzbannerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int[] RES = new int[]{R.mipmap.image5, R.mipmap.image2, R.mipmap.image3, R.mipmap.image4, R.mipmap.image6, R.mipmap.image7, R.mipmap.image8};

    private MZBannerView mMZBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMZBanner.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMZBanner.pause();
    }

    private void initView() {

        mMZBanner = (MZBannerView) findViewById(R.id.banner);
        mMZBanner.setBannerPageClickListener(new MZBannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                Toast.makeText(MainActivity.this, "click page:" + position, Toast.LENGTH_LONG).show();
            }
        });
        List<DataBean> list = new ArrayList<>();
        DataBean dataEntry = null;
        for (int i = 0; i < RES.length; i++) {
            dataEntry = new DataBean();
            dataEntry.resId = RES[i];
            dataEntry.desc = "The time you enjoy wasting , is not wasted.";
            list.add(dataEntry);
        }

        mMZBanner.setIndicatorRes(R.drawable.dot_unselect_image,R.drawable.dot_select_image);
        mMZBanner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
    }

    public static class BannerViewHolder implements MZViewHolder<DataBean> {
        private ImageView mImageView;
        private TextView mDesc;

        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            mDesc = (TextView) view.findViewById(R.id.page_desc);
            return view;
        }

        @Override
        public void onBind(Context context, int position, DataBean data) {
            // 数据绑定
            mImageView.setImageResource(data.resId);
            mDesc.setText(data.desc);
        }
    }
}
