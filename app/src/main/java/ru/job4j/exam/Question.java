package ru.job4j.exam;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Question implements Parcelable {
    private int id;
    private String text;
    private List<Option> options;
    private int answer;

        public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel source) {
            int id = source.readInt();
            String text = source.readString();
            List<Option>  options = new ArrayList<>();//Нужно будет поправить, пока не знаю как
            int answer = source.readInt();
            return new Question(id, text, options, answer);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public Question(int id, String text, List<Option> options, int answer) {
        this.id = id;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public int getAnswer() {
        return answer;
    }

    @Override
    public String toString(){
        return String.format("%s. %s %s Correct answer: %s", id, text, "\n", answer);
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(text);
        parcel.writeInt(answer);
        //Лист с вариантами пока пропустил
    }
}