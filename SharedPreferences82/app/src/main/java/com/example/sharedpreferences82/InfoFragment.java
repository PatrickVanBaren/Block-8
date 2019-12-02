package com.example.sharedpreferences82;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceManager;

public class InfoFragment extends Fragment {
    private static final String OPTION_KEY = "OPTION_KEY";
    private static final String SECRET_OPTION = "SECRET_OPTION";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final TextView textView = view.findViewById(R.id.setting_text);
        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getActivity());
        final boolean settingValue = sharedPreferences.getBoolean(OPTION_KEY, false);
        textView.setText(String.format("Option is: %s", String.valueOf(settingValue)));
        final boolean secretOption = sharedPreferences.getBoolean(SECRET_OPTION, false);
        if (secretOption) {
            textView.setText(String.format("Option is: %s\nSecret option is: %s",
                    String.valueOf(settingValue), String.valueOf(secretOption)));
        }
    }
}
