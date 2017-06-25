package com.theonepiano.smartpiano.view.home;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.theonepiano.smartpiano.R;
import com.theonepiano.smartpiano.model.home.bean.HomeCategoryGenreList;
import com.theonepiano.smartpiano.ui.home.adapter.CategoryFilterListAdapter;
import com.yyydjk.library.DropDownMenu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jim on 2017/6/25.
 */

public class CategoryFilterView extends LinearLayout {
    DropDownMenu mCategoryDropDownMenu;

    CategoryFilterListAdapter mCategoryListAdapter1;
    CategoryFilterListAdapter mCategoryListAdapter2;

    private String headers[] = {"难度", "风格", "更多"};
    private String difficulties[] = {"不限", "新手", "流行", "古典", "轻音乐"};
    private String styles[] = {"流行", "古典", "轻音乐"};
    private String more[] = {"最新曲集", "最热曲集", "经典教材", "考级教材", "直播课专区"};

    private List<View> popupViews = new ArrayList<>();

    private Context mContext;

    private View mContentView;

    public CategoryFilterView(Context context) {
        super(context);
    }

    public CategoryFilterView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CategoryFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public void setContentView(View view) {
        mContentView = view;
    }

    public void updateGenresItem(List<HomeCategoryGenreList> genreList) {
        mCategoryDropDownMenu = (DropDownMenu) findViewById(R.id.drop_down_menu);

        final ListView categoryListView1 = new ListView(mContext);

        mCategoryListAdapter1 = new CategoryFilterListAdapter(mContext, Arrays.asList(difficulties));
        categoryListView1.setDividerHeight(0);
        categoryListView1.setAdapter(mCategoryListAdapter1);

        popupViews.add(categoryListView1);

        categoryListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCategoryListAdapter1.setCheckedItemPosition(position);
                mCategoryDropDownMenu.setTabText(position == 0 ? headers[0] : difficulties[position]);
                mCategoryDropDownMenu.closeMenu();
            }
        });

        final ListView categoryListView2 = new ListView(mContext);

        mCategoryListAdapter2 = new CategoryFilterListAdapter(mContext, Arrays.asList(styles));
        categoryListView2.setDividerHeight(0);
        categoryListView2.setAdapter(mCategoryListAdapter2);

        popupViews.add(categoryListView2);

        categoryListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCategoryListAdapter2.setCheckedItemPosition(position);
                mCategoryDropDownMenu.setTabText(position == 0 ? headers[1] : styles[position]);
                mCategoryDropDownMenu.closeMenu();
            }
        });

        popupViews.add(customMoreView());

        // init dropdownview
        mCategoryDropDownMenu.setDropDownMenu(Arrays.asList(headers), popupViews, mContentView);
    }

    private void init(Context context) {
        mContext = context;
    }

    /**
     * Get more view
     *
     * @return
     */
    private View customMoreView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_home_category_filter_more, null);
        return view;
    }
}
