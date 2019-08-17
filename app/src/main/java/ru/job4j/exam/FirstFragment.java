package ru.job4j.exam;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
//import android.support.v4.app.Fragment;

public class FirstFragment extends Fragment {
    private Button nextFragment;
    private OnNextButtonClickListener callback;

    public interface OnNextButtonClickListener {
        void onNextButtonClicked(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        nextFragment = view.findViewById(R.id.next_fragment);
        nextFragment.setOnClickListener(this::toNextFragment);
        if (getArguments() != null) {
            TextView fromSecond = view.findViewById(R.id.fromSecond);
            String msg = getArguments().get("message").toString();
            fromSecond.setText(msg);
        }
        return view;
    }

    public void toNextFragment(View view) {
        callback.onNextButtonClicked("Next button clicked");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (OnNextButtonClickListener) context; // назначаем активити при присоединении фрагмента к активити
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // обнуляем ссылку при отсоединении фрагмента от активити
    }

}
