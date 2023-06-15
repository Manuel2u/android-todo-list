package com.example.todoapp;

// TodoItem.java
import android.os.Parcel;
import android.os.Parcelable;

public class TodoItem implements Parcelable {
    private String description;
    private boolean completed;

    public TodoItem(String description, boolean completed) {
        this.description = description;
        this.completed = completed;
    }

    protected TodoItem(Parcel in) {
        description = in.readString();
        completed = in.readByte() != 0;
    }

    public static final Creator<TodoItem> CREATOR = new Creator<TodoItem>() {
        @Override
        public TodoItem createFromParcel(Parcel in) {
            return new TodoItem(in);
        }

        @Override
        public TodoItem[] newArray(int size) {
            return new TodoItem[size];
        }
    };

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(description);
        parcel.writeByte((byte) (completed ? 1 : 0));
    }
}
