package com.example.kotyrasowe;

import android.os.Parcel;
import android.os.Parcelable;

public class SecondFilteringCatsDiseasesModel implements Parcelable {

    private boolean diseases;
    private String name;
    private boolean isSelected;


    public static final Creator<SecondFilteringCatsDiseasesModel> CREATOR = new Creator<SecondFilteringCatsDiseasesModel>() {
        public SecondFilteringCatsDiseasesModel createFromParcel(Parcel in) {
            return new SecondFilteringCatsDiseasesModel(in);
        }

        public SecondFilteringCatsDiseasesModel[] newArray(int size) {
            return new SecondFilteringCatsDiseasesModel[size];
        }
    };


    public SecondFilteringCatsDiseasesModel(Parcel in) {
        this.name = in.readString();
        this.diseases = in.readByte() != 0;
        this.isSelected = in.readByte() != 0;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeByte((byte) (this.diseases ? 1 : 0));
        dest.writeByte((byte) (this.isSelected ? 1 : 0));
    }

    public SecondFilteringCatsDiseasesModel(String name, boolean feature) {
        this.name = name;
        this.diseases = feature;
        this.isSelected = false;
    }

    public boolean isDiseases() {
        return diseases;
    }

    public void getDiseases(boolean feature) {
        this.diseases = feature;
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

