package com.freshnesstracker.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.freshnesstracker.app.R.layout.fragment_modify_expiry;
/**
 * Created by Sakura on 5/31/14.
 */
public class ModifyExpiryFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(fragment_modify_expiry, container, false);
    }
}
