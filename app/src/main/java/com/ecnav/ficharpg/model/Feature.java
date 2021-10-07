package com.ecnav.ficharpg.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Feature implements Parcelable
{
    private String name;
    private String description;

    public Feature()
    {
    }

    public Feature(Parcel source)
    {
        this.name = source.readString();
        this.description = source.readString();
    }

    public String getNome()
    {
        return name;
    }

    public void setNome(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeString(name);
        dest.writeString(description);
    }

    public static final Parcelable.Creator<Feature> CREATOR = new Creator<Feature>()
    {
        @Override
        public Feature createFromParcel(Parcel source)
        {
            return new Feature(source);
        }

        @Override
        public Feature[] newArray(int size)
        {
            return new Feature[0];
        }
    };
}
