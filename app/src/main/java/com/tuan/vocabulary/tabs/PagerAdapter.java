package com.tuan.vocabulary.tabs;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tuanl on 20-Feb-17.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;
    private String [] tabTitles = {"Review", "Vocabulary"};

    public PagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                FragmentReview fragReview = new FragmentReview();
                return fragReview;
            case 1:
                FragmentVocabulary fragVoca = new FragmentVocabulary();
                return fragVoca;
            default:
                FragmentReview fragment = new FragmentReview();
                return fragment;
        }
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}
