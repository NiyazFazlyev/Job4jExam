package ru.job4j.exam;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {
    private Button previousFragment;
    private OnBackButtonClickListener callback;

    public interface OnBackButtonClickListener {
        void onBackButtonClicked(String message);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        previousFragment = view.findViewById(R.id.back);
        previousFragment.setOnClickListener(this::toPreviousFragment);
        return view;
    }

    public void toPreviousFragment(View view) {
//        TextView fromSecond = getActivity().findViewById(R.id.fromSecond);
//        fromSecond.setText("Message from first fragment");
        callback.onBackButtonClicked("Message from first fragment");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callback = (SecondFragment.OnBackButtonClickListener) context; // назначаем активити при присоединении фрагмента к активити
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callback = null; // обнуляем ссылку при отсоединении фрагмента от активити
    }
}
