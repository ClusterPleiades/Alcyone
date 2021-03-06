package com.pleiades.pleione.alcyone;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import java.util.ArrayList;

public class ListAdapterSetting extends BaseAdapter {
    private final ArrayList<String> settingList;
    private final int listCount;

    public ListAdapterSetting(ArrayList<String> newSettingList) {
        settingList = newSettingList;
        listCount = settingList.size();
    }

    @Override
    public int getCount() {
        return listCount;
    }

    @Override
    public Object getItem(int position) {
        return settingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        SettingViewHolder holder;

        if (convertView == null) {
            final Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_setting, parent, false);

            // holder
            holder = new SettingViewHolder();
            holder.tv = convertView.findViewById(R.id.settingTitle);
            holder.settingSwitch = convertView.findViewById(R.id.settingSwitch);

            convertView.setTag(holder);
        } else {
            holder = (SettingViewHolder) convertView.getTag();
        }
        final SharedPreferences prefs = convertView.getContext().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        holder.tv.setText(settingList.get(position));

        holder.settingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!LayoutSetting.settingErrorLock) {
                    if (isChecked) {
                        if (pos == 0) {
                            editor.putBoolean("startPage", true);
                            ConversationScript script1 = new ConversationScript("?????? ?????? ???????????? ??????????????????.. ??????????????? ????????? ?????????????", "playful");
                            ConversationScript script2 = new ConversationScript(".....", null);
                            ConversationScript script3 = new ConversationScript("?????? ?????? ???????????? ??? ?????????????!", "surprised");
                            ConversationControl.addConversation(script1, script2, script3);
                        } else if (pos == 1) {
                            editor.putBoolean("fastTalkSpeed", true);
                            ConversationScript script1 = new ConversationScript("?????? ?????? ?????? ????????????? ????????????...", "sad");
                            ConversationControl.addConversation(script1);
                        } else if (pos == 2) {
                            editor.putBoolean("receiveNotification", true);
                            editor.apply();
                            LayoutSchedule.setScheduleNotifications();
                            ConversationScript script1 = new ConversationScript("??????????????? ????????? ???????????? ????????? ??????, ?????? ??????????????????.", "glad");
                            ConversationControl.addConversation(script1);
                        } else if (pos == 3) {
                            editor.putBoolean("lowEndMode", true);
                        }
                    } else {
                        if (pos == 0) {
                            editor.putBoolean("startPage", false);
                            ConversationScript script1 = new ConversationScript("?????? ?????? ?????? ???????????? ??? ?????????????", "glad");
                            ConversationControl.addConversation(script1);
                        } else if (pos == 1) {
                            editor.putBoolean("fastTalkSpeed", false);
                            ConversationScript script1 = new ConversationScript("?????? ?????? ?????? ????????????? ????????????...", "shy");
                            ConversationControl.addConversation(script1);
                        } else if (pos == 2) {
                            editor.putBoolean("receiveNotification", false);
                            editor.apply();
                            LayoutSchedule.releaseScheduleNotifications();
                            ConversationScript script1 = new ConversationScript("?????? ?????? ??????????????? ????????? ????????? ??? ???????????????????", "glad");
                            ConversationScript script2 = new ConversationScript("????????? ??????.. ????????? ??????????????????????", "wily");
                            ConversationControl.addConversation(script1 , script2);
                        } else if (pos == 3) {
                            editor.putBoolean("lowEndMode", false);
                        }
                    }
                    editor.apply();
                }
            }
        });

        if (position < 4) {
            holder.settingSwitch.setVisibility(View.VISIBLE);
            LayoutSetting.settingErrorLock = true;
            if (position == 0)
                holder.settingSwitch.setChecked(prefs.getBoolean("startPage", false));
            else if (position == 1)
                holder.settingSwitch.setChecked(prefs.getBoolean("fastTalkSpeed", false));
            else if (position == 2)
                holder.settingSwitch.setChecked(prefs.getBoolean("receiveNotification", false));
            else if (position == 3)
                holder.settingSwitch.setChecked(prefs.getBoolean("lowEndMode", false));
            LayoutSetting.settingErrorLock = false;
        } else
            holder.settingSwitch.setVisibility(View.INVISIBLE);

        return convertView;
    }
}

class SettingViewHolder {
    TextView tv;
    SwitchCompat settingSwitch;
}