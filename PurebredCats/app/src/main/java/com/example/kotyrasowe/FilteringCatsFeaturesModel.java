package com.example.kotyrasowe;

import android.os.Parcel;
import android.os.Parcelable;

public class FilteringCatsFeaturesModel implements Parcelable {

    private boolean feature;
    private String name;
    private boolean isSelected;


    public static final Creator<FilteringCatsFeaturesModel> CREATOR = new Creator<FilteringCatsFeaturesModel>() {
        public FilteringCatsFeaturesModel createFromParcel(Parcel in) {
            return new FilteringCatsFeaturesModel(in);
        }

        public FilteringCatsFeaturesModel[] newArray(int size) {
            return new FilteringCatsFeaturesModel[size];
        }
    };


    public FilteringCatsFeaturesModel(Parcel in) {
        this.name = in.readString();
        this.feature = in.readByte() != 0;
        this.isSelected = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeByte((byte) (this.feature ? 1 : 0));
        dest.writeByte((byte) (this.isSelected ? 1 : 0));
    }

    public FilteringCatsFeaturesModel(String name,  boolean feature) {
        this.name = name;
        this.feature = feature;
        this.isSelected = false;
    }

    public boolean isFeature() {
        return feature;
    }

    public void setFeature(boolean feature) {
        this.feature = feature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}