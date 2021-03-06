package com.pleiades.pleione.alcyone;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

import static android.content.Context.MODE_PRIVATE;

public class LayoutCostume extends Fragment {
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public static ListAdapterCostume adapterCostume;
    public static ArrayList<Costume> costumeList;
    public static int clickedPosition;
    public static int storyRequest;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.activity_costume, container, false);
        context = getContext();

        costumeList = PrefsController.getCostumeListPrefs(context, "costumeList");
        Collections.sort(costumeList);

        final SharedPreferences prefs = context.getSharedPreferences("prefs", MODE_PRIVATE); // doesn't need context at activity onCreate
        final SharedPreferences.Editor editor = prefs.edit();

        adapterCostume = new ListAdapterCostume(costumeList);
        ListView costumeListView = v.findViewById(R.id.costumeList);
        costumeListView.setAdapter(adapterCostume);

        costumeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (costumeList.get(position).unlocked) {
                    if (position != LayoutAlcyone.costumeSelectedPosition) {
                        // sleep
                        if (LayoutAlcyone.isAlcyoneSleep) {
                            // above snack bar
                            MainActivity.fabAboveSnackAnimation(false);

                            // snack bar initialize
                            Snackbar snackbar;
                            snackbar = Snackbar.make(view, "???..! ???????????? ?????? ?????????!", Snackbar.LENGTH_SHORT);
                            View snackBarView = snackbar.getView();
                            snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDrawIcon));
                            TextView snackBarTextView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                            snackBarTextView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                            snackbar.addCallback(new Snackbar.Callback() {
                                @Override
                                public void onDismissed(Snackbar snackbar, int event) {
                                    MainActivity.fabAboveSnackAnimation(true);
                                }
                            });
                            snackbar.show();
                        } else {
                            if (!ConversationControl.inConversation && !ConversationControl.inConversationInstant) {
                                ConversationScript script1, script2, script3, script4;
                                switch (position) {
                                    case 0:
                                        break;
                                    case 1:
                                        script1 = new ConversationScript("????????? ??????.. ?????? ????????? ?????? ???????????????...", "shy");
                                        ConversationControl.addConversation(script1);
                                        break;
                                    case 2:
                                        script1 = new ConversationScript("??????...", null);
                                        script2 = new ConversationScript(" ?????? ?????? ????????? ??? ???????????????.", "glad");
                                        ConversationControl.addConversation(script1, script2);
                                        break;
                                    case 3:
                                        script1 = new ConversationScript("??????????????? ??? ???????????????????", null);
                                        script2 = new ConversationScript("??????????????????. ?????? ???????????????!", "glad");
                                        ConversationControl.addConversation(script1, script2);
                                        break;
                                    case 4:
                                        script1 = new ConversationScript("????????? ????????? ?????? ???????????? ???????????????. ???????????? ?????? ????????????..", "sad");
                                        script2 = new ConversationScript("??? ????????? ???????????????.", "shy");
                                        script3 = new ConversationScript("?????? ????????? ????????? ??? ????????????.", "shy");
                                        ConversationControl.addConversation(script1, script2, script3);
                                        break;
                                    case 5:
                                        script1 = new ConversationScript("???????????????.. ??? ????????? ???????????? ?????? ?????? ??????????", "shy");
                                        ConversationControl.addConversation(script1);
                                        break;
                                    case 6:
                                        script1 = new ConversationScript("???????????? ???????????? ??????????????????.. ??? ?????? ?????? ??????????????????.", "shy");
                                        script2 = new ConversationScript("??????.. ?????? ??????????", "shy");
                                        script3 = new ConversationScript("..????????????. ??? ?????? ?????? ????????????.", "shy");
                                        ConversationControl.addConversation(script1, script2, script3);
                                        break;
                                    case 7:
                                        script1 = new ConversationScript("??? ?????? ?????? ????????? ????????????.. ????????? ??? ????????? ????????? ??????????????? ??????????", "sad");
                                        ConversationControl.addConversation(script1);
                                        break;
                                    case 8:
                                        script1 = new ConversationScript("??? ???????????? ???????????? ???????????????.", null);
                                        script2 = new ConversationScript("???????????????? ????????? ?????? ???????????? ??????????????????.", "sneer");
                                        script3 = new ConversationScript("???? ???????????? ???????????????????", "surprised");
                                        script4 = new ConversationScript("?????? ??? ??????.", "shy");
                                        ConversationControl.addConversation(script1, script2, script3, script4);
                                        break;
                                    case 9:
                                        script1 = new ConversationScript("??? ??? ??????..", "surprised");
                                        script2 = new ConversationScript("??????????????? ?????? ????????? ????????????..", "shy");
                                        script3 = new ConversationScript("???????????? ??????????????? ?????????!", "shout");
                                        ConversationControl.addConversation(script1, script2, script3);
                                        break;
                                    case 10:
                                        script1 = new ConversationScript("??? ????????? ????????????.. ???????????? ????????? ????????? ?????????.", null);
                                        script2 = new ConversationScript("?????? ????????? ???????????????..", "sad");
                                        script3 = new ConversationScript("???????????? ??? ?????? ???????????? ????????? ?????? ??? ???????????? ????????????.", "sad");
                                        ConversationControl.addConversation(script1, script2, script3);
                                        break;
                                    case 11:
                                        script1 = new ConversationScript("???! ????????? ??? ????????? ????????????. ????????????!", null);
                                        script2 = new ConversationScript("?????? ??????????????? ?????? ????????? ????????? ?????????????", null);
                                        script3 = new ConversationScript("?????????????????? ?????? ????????? ?????? ???????????? ??????????????????.", "sad");
                                        ConversationControl.addConversation(script1, script2, script3);
                                        break;
                                    default:
                                }
                            }
                            editor.putInt("costumeSelectedPosition", position);
                            editor.putString("costumeSelectedName", costumeList.get(position).costumeName);
                            editor.apply();
                            LayoutAlcyone.costumeSelectedPosition = position;
                            LayoutAlcyone.costumeSelectedName = costumeList.get(position).costumeName;
                            adapterCostume.notifyDataSetChanged();
                        }
                    }
                } else {
                    if (position < 9) {
                        // above snack bar
                        MainActivity.fabAboveSnackAnimation(false);

                        // snack bar initialize
                        Snackbar snackbar;

                        if (position > 6) {
                            snackbar = Snackbar.make(view, "?????? ?????? ????????? ??????????????? ??? ????????????!", Snackbar.LENGTH_SHORT);
                        } else {
                            snackbar = Snackbar.make(view, "??? ???????????? ????????? ????????? ?????? ???????????? ??? ?????????!", Snackbar.LENGTH_SHORT);
                        }
                        View snackBarView = snackbar.getView();
                        snackBarView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDrawIcon));
                        TextView snackBarTextView = snackBarView.findViewById(com.google.android.material.R.id.snackbar_text);
                        snackBarTextView.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
                        snackbar.addCallback(new Snackbar.Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                MainActivity.fabAboveSnackAnimation(true);
                            }
                        });
                        snackbar.show();
                    } else {
                        // buy costume
                        clickedPosition = position;

//                        switch (position) {
//                            case 9:
//                                MainActivity.billingProcessor.purchase(getActivity(), "bunny");
//                                break;
//                            case 10:
//                                MainActivity.billingProcessor.purchase(getActivity(), "bloomer");
//                                break;
//                            case 11:
//                                MainActivity.billingProcessor.purchase(getActivity(), "succubus");
//                                break;
//                        }
                    }
                }
            }
        });

        return v;
    }

    public static void unlockProcess() {
        if (!LayoutCostume.costumeList.get(LayoutCostume.clickedPosition).unlocked) {
            LayoutCostume.costumeList.get(LayoutCostume.clickedPosition).unlocked = true;

            switch (LayoutCostume.clickedPosition) {
                case 9:
                    LayoutCostume.storyRequest = 33;
                    break;
                case 10:
                    LayoutCostume.storyRequest = 34;
                    break;
                case 11:
                    LayoutCostume.storyRequest = 35;
                    break;
            }
            LayoutStory.storyList.get(LayoutCostume.storyRequest).unlocked = true;

            LayoutCostume.adapterCostume.notifyDataSetChanged();

            MainActivity.costumeProgress++;
            MainActivity.storyProgress++;
            MainActivity.setCostumeProgressBar();
            MainActivity.setStoryProgressBar();

            PrefsController.setCostumeListPrefs(context, "costumeList", LayoutCostume.costumeList);
            PrefsController.setStoryListPrefs(context, "storyList", LayoutStory.storyList);

            getNewCostumeDialog();
        }
    }

    public static void billingErrorToast() {
        Toast.makeText(context, "???.. ?????????. =3=", Toast.LENGTH_SHORT).show();
    }

    private static void getNewCostumeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AppTheme_AlertDialogOverlay);
        builder.setTitle("????????? ???????????? ??????????????????!");

        // cannot cancel
        builder.setCancelable(false);

        builder.setPositiveButton("????????? ??????",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        LayoutAlcyone.fromOtherLayout = 2; // 2 is to costume
                        LayoutAlcyone.storyRequest = storyRequest;
                        LayoutAlcyone.storyRequestContinue = -1;
                        Intent intent = new Intent(context, LayoutSplashStory.class);
                        intent.putExtra("request", 0);
                        Activity activity = (Activity) context;
                        activity.startActivityForResult(intent, 0); // request code 0
                        ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    }
                });
        builder.setNegativeButton("??????",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
        builder.show();
    }
}

class Costume implements Comparable<Costume> {
    String costumeName;
    boolean unlocked = false;
    int priority;

    @Override
    public int compareTo(Costume costume) {
        return Integer.compare(this.priority, costume.priority);
    }
}