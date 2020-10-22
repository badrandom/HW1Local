package com.example.hw1local;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NumberFragment extends Fragment {
    private String number;

    public NumberFragment(String number) {
        super();

        this.number = number;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_number, container, false);
        TextView textView;
        textView = v.findViewById(R.id.num_text);
        textView.setText(number);
        if (Integer.parseInt(number) % 2 == 0){
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorEven));
        } else {
            textView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorOdd));
        }
        return  v;
    }
}
