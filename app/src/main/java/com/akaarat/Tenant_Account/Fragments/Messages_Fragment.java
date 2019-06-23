package com.akaarat.Tenant_Account.Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.akaarat.R;
import com.akaarat.SharedPrefManager;
import com.akaarat.Tenant_Account.Adapter.Messages_Adapter;
import com.akaarat.Tenant_Account.Model.Message_Inbox_Presenter;
import com.akaarat.Tenant_Account.Model.Messages;
import com.akaarat.Tenant_Account.View.Messages_Inbox_View;

import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Messages_Fragment extends Fragment implements Messages_Inbox_View,SwipeRefreshLayout.OnRefreshListener{


    public Messages_Fragment() {
        // Required empty public constructor
    }

    View view;
    RecyclerView recyclerNotification;
    Messages_Adapter messages_adapter;
    SwipeRefreshLayout mSwipeRefreshLayout;
    FrameLayout Frame_FeedBack;
    Message_Inbox_Presenter message_Inbox_Presenter;
    String User_ID;
    ProgressDialog progressdialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_messages_, container, false);
        message_Inbox_Presenter=new Message_Inbox_Presenter(getActivity(),this);
        progressdialog=new ProgressDialog(getContext());
        init();
        Recyclview();
        SwipRefresh();


        return view;
    }

    @Override
    public void onRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
        message_Inbox_Presenter.Inbox_SuperVisor(User_ID);

    }

    @Override
    public void GetMessagesParent(List<Messages> inbox_details) {
        Collections.reverse(inbox_details);
        messages_adapter = new Messages_Adapter(inbox_details, getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerNotification.setLayoutManager(linearLayoutManager);
        recyclerNotification.setItemAnimator(new DefaultItemAnimator());
        recyclerNotification.setAdapter(messages_adapter);
        mSwipeRefreshLayout.setRefreshing(false);

    }

    @Override
    public void Error() {
        mSwipeRefreshLayout.setRefreshing(false);

    }

    public void init(){
        Frame_FeedBack=view.findViewById(R.id.Frame_inbox);
        User_ID = SharedPrefManager.getInstance(getContext()).getUserID();

    }
    private void Recyclview() {
        recyclerNotification=view.findViewById(R.id.recycler_inbox);
        recyclerNotification.setHasFixedSize(true);
    }

    public void SwipRefresh(){
        mSwipeRefreshLayout =  view.findViewById(R.id.swipe_inbox);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);

        mSwipeRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                        mSwipeRefreshLayout.setRefreshing(true);
                        message_Inbox_Presenter.Inbox_SuperVisor(User_ID);

            }
        });
    }
}
