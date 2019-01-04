/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.utils;

import androidx.annotation.NonNull;

public class StrUtils {

    public static boolean contains(@NonNull String text, @NonNull String search) {
        return text.toLowerCase().contains(search.toLowerCase());
    }
}
