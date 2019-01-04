/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.domain;

import android.os.Parcel;
import android.os.Parcelable;

import rutta.aleksandr.android.interviewapp.ui.StatusIcon;

public class Person implements Parcelable {

    private String firstName = "";

    private String lastName = "";

    private StatusIcon statusIcon = StatusIcon.pending;

    private String statusMessage = "";

    protected Person(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        statusMessage = in.readString();
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(statusMessage);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public StatusIcon getStatusIcon() {
        return statusIcon;
    }

    public String getStatusMessage() {
        return statusMessage;
    }
}