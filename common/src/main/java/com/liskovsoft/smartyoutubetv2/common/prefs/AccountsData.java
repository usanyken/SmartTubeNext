package com.liskovsoft.smartyoutubetv2.common.prefs;

import android.annotation.SuppressLint;
import android.content.Context;
import com.liskovsoft.sharedutils.helpers.Helpers;

public class AccountsData {
    @SuppressLint("StaticFieldLeak")
    private static AccountsData sInstance;
    private final Context mContext;
    private final AppPrefs mAppPrefs;
    private boolean mIsSelectAccountOnBootEnabled;

    public AccountsData(Context context) {
        mContext = context;
        mAppPrefs = AppPrefs.instance(mContext);
        restoreState();
    }

    public static AccountsData instance(Context context) {
        if (sInstance == null) {
            sInstance = new AccountsData(context.getApplicationContext());
        }

        return sInstance;
    }

    public void selectAccountOnBoot(boolean select) {
        mIsSelectAccountOnBootEnabled = select;
        persistState();
    }

    public boolean isSelectAccountOnBootEnabled() {
        return mIsSelectAccountOnBootEnabled;
    }

    private void persistState() {
        mAppPrefs.setAccountsData(Helpers.mergeObject(mIsSelectAccountOnBootEnabled));
    }

    private void restoreState() {
        String data = mAppPrefs.getAccountsData();

        String[] split = Helpers.splitObject(data);

        mIsSelectAccountOnBootEnabled = Helpers.parseBoolean(split, 0, true);
    }
}
