package com.example.covizi.ui.scan;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.covizi.LoginActivity;
import com.example.covizi.QrCodeScan;
import com.example.covizi.R;

public class ScanFragment extends Fragment {

    private ScanViewModel mViewModel;
    private Button btn_checkin;

    public static ScanFragment newInstance() {
        return new ScanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
         View v = inflater.inflate(R.layout.scan_fragment, container, false);
         btn_checkin = v.findViewById(R.id.button_checkin);

         btn_checkin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(getActivity(), QrCodeScan.class);
                 startActivity(intent);
             }
         });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ScanViewModel.class);
        // TODO: Use the ViewModel
    }

}