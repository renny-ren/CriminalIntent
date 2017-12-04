package com.renjunhong.scujcc.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.List;
import java.util.UUID;

/**
 * Created by renjunhong on 2017/11/06.
 */

public class CrimePaperActivity extends AppCompatActivity
        implements CrimeFragment.Callbacks {

    private static final String EXTRA_CRIME_ID =
            "com.bignerdranch.android.criminalintent.crime_id";

    private ViewPager mViewPaper;
    private List<Crime> mCrimes;

    public static Intent newInent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePaperActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_paper);

        UUID crimeId = (UUID) getIntent().getSerializableExtra(EXTRA_CRIME_ID);

        mViewPaper = (ViewPager) findViewById(R.id.activity_crime_paper_view_paper);
        mCrimes = CrimeLab.get(this).getmCrimes();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPaper.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Crime crime = mCrimes.get(position);
                return CrimeFragment.newInstance(crime.getId());
            }

            @Override
            public int getCount() {
                return mCrimes.size();
            }
        });

        for (int i = 0; i < mCrimes.size(); i++) {
            if (mCrimes.get(i).getId().equals(crimeId)) {
                mViewPaper.setCurrentItem(i);
                break;
            }
        }
    }

    @Override
    public void onCrimeUpdated(Crime crime) {

    }
}
