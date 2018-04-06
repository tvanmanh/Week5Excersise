package com.example.tranvanmanh.week5excersise;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by tranvanmanh on 4/5/2018.
 */

public class DoneFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private View view;

    private ListView lvDone;
    private TaskItemAdapter adapter;
    private ArrayList<TaskItem> arrayList;

    private Realm realm;

    public static DoneFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DoneFragment fragment = new DoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.done_fragment, container, false);
        lvDone = (ListView) view.findViewById(R.id.lvDone);
        arrayList = new ArrayList<>();
 //       realm = Realm.getDefaultInstance();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.delete(TaskItem.class);
//            }
//        });
//        if(realm != null){
//            RealmResults<TaskItem> TaskItems = realm.where(TaskItem.class).findAll();
//            if(TaskItems.size()>0)
//            {
//                for(int i = 0; i<TaskItems.size(); i++){
//                    arrayList.add(TaskItems.get(i));
//
//                }
//            }}
        adapter = new TaskItemAdapter(getActivity(), arrayList, R.layout.lvtaskitem);
        lvDone.setAdapter(adapter);

        return view;

    }

     protected  void displayListView(final String nameTask, final String Date, final String priority, String tag) {

        arrayList.add(new TaskItem(nameTask, Date, priority));
        adapter.notifyDataSetChanged();
//         realm.executeTransaction(new Realm.Transaction() {
//             @Override
//             public void execute(Realm realm) {
//
//                 TaskItem taskItem = realm.createObject(TaskItem.class);
//                 taskItem.setNameTask(nameTask);
//                 taskItem.setDate(Date);
//                 taskItem.setPriority(priority);
//
//
//             }
//         });

    }
}
