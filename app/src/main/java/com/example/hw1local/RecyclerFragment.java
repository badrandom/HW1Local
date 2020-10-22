package com.example.hw1local;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerFragment extends Fragment {
    private static final int START = 100;
    private static final String DIGIT_KEY = "data";
    private int count = START;
    private ArrayList<Data> listNumbers = new ArrayList<>();
    private MyAdapter adapter = new MyAdapter(listNumbers);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState != null) {
            count = savedInstanceState.getInt(DIGIT_KEY);
        }

        for (int i = 1; i <= count; i++)
            listNumbers.add(new Data(Integer.toString(i)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recycler, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button buttonAdd = view.findViewById(R.id.button);
        RecyclerView list = view.findViewById(R.id.list);
        list.setAdapter(adapter);
        int col;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            col =4;
        }
        else {
            col = 3;
        }
        list.setLayoutManager(new GridLayoutManager(view.getContext(), col));

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                listNumbers.add(new Data(Integer.toString(count)));
                adapter.notifyItemInserted(count - 1);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DIGIT_KEY, count);
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private ArrayList<Data> listDataAdapter;

        public MyAdapter(ArrayList listDataAdapter) {
            this.listDataAdapter = listDataAdapter;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.element_layout, parent, false);
            return new MyHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.txtView.setText(listDataAdapter.get(position).number);
            if (position % 2 == 0) {
                holder.txtView.setTextColor(getResources().getColor(R.color.colorBlue));
            } else {
                holder.txtView.setTextColor(ContextCompat.getColor(getContext(), R.color.colorRed));
            }
        }

        @Override
        public int getItemCount() {
            return listDataAdapter.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        TextView txtView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            txtView = itemView.findViewById(R.id.number);
            txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String txt = txtView.getText().toString();
                    Fragment fragment = new NumberFragment(txt);
                    getFragmentManager().
                            beginTransaction().
                            replace(R.id.recycler_fragment, fragment).
                            addToBackStack(null).
                            commitAllowingStateLoss();
                }
            });
        }
    }


    class Data {
        String number;

        public Data(String number) {
            this.number = number;
        }
    }
}
