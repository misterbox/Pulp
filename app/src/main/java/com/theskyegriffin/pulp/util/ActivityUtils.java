package com.theskyegriffin.pulp.util;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import static com.google.common.base.Preconditions.checkNotNull;

public class ActivityUtils {
    public static void addFragmentToActivity(@NonNull FragmentManager fragManager, @NonNull Fragment fragment, int frameId) {
        checkNotNull(fragManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void addFragmentToActivity(@NonNull FragmentManager fragManager, @NonNull Fragment fragment, String tag) {
        checkNotNull(fragManager);
        checkNotNull(fragment);
        FragmentTransaction transaction = fragManager.beginTransaction();
        transaction.add(fragment, tag);
        transaction.commit();
    }
}
