/*
 * Created Xandr https://xandrwix.wixsite.com/resume
 */
package rutta.aleksandr.android.interviewapp.ui;

import rutta.aleksandr.android.interviewapp.R;

import androidx.annotation.DrawableRes;

public enum StatusIcon {

	online(R.mipmap.contacts_list_status_online),

	offline(R.mipmap.contacts_list_status_offline),

	away(R.mipmap.contacts_list_status_away),

	busy(R.mipmap.contacts_list_status_busy),

	callforwarding(R.mipmap.contacts_list_call_forward),

	pending(R.mipmap.contacts_list_status_pending);

	private final int iconId;

	StatusIcon(@DrawableRes int iconId) {
		this.iconId = iconId;
	}

	public int getIconId() {
		return iconId;
	}
}
