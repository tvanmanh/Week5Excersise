package com.example.tranvanmanh.week5excersise;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.lang.annotation.Target;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by tranvanmanh on 4/5/2018.
 */

public class ToDolistFragment extends Fragment implements dataTaskInterface {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    private View view;

    private ArrayList<TaskItem> arrayList;
    private TaskItemAdapter adapter;
    private ListView lvTask;

    private Realm realm;

    private DialgFragment dialgFragment;

    private int pos;

    private Button btnAddTask;

    private dataTaskInterface dataTaskInterface;

    static ToDolistFragment fragment;
    public static ToDolistFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
       fragment = new ToDolistFragment();
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
        dialgFragment = new DialgFragment();
        dialgFragment.setTargetFragment(this, 0);
        dataTaskInterface = (dataTaskInterface)getActivity();
        view = inflater.inflate(R.layout.todolist_fragment, container, false);
        btnAddTask = (Button) view.findViewById(R.id.btnAddTask);
        btnAddTask.setOnClickListener(onAddTask);
        lvTask = (ListView)view.findViewById(R.id.lvTask);
        arrayList = new ArrayList<>();
        realm = Realm.getDefaultInstance();
//        realm.executeTransaction(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                realm.delete(TaskItem.class);
//            }
//        });
        if(realm != null){
            RealmResults<TaskItem> TaskItems = realm.where(TaskItem.class).findAll();
            if(TaskItems.size()>0)
            {
                for(int i = 0; i<TaskItems.size(); i++){
                    arrayList.add(TaskItems.get(i));

                }
            }}
        adapter = new TaskItemAdapter(getActivity(), arrayList, R.layout.lvtaskitem);
        lvTask.setAdapter(adapter);
        LvsetEvent();

        return view;

    }

    private void LvsetEvent(){
        lvTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                pos = i;
                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                alertDialog.setTitle("Information");
                alertDialog.setMessage("click Done : move this task to Tab Done \n click Edit: edit this task?");
                alertDialog.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dataTaskInterface.data(arrayList.get(pos).getNameTask(), arrayList.get(pos).getDate(), arrayList.get(pos).getPriority(), getTag());
                    }
                });
                alertDialog.setNegativeButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialgFragment.show(getFragmentManager(), "edit");
                    }
                });

                alertDialog.show();

            }
        });

        lvTask.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                DeleteTask(i);
                return false;
            }
        });
    }
    private void DeleteTask(final int position){

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        alertDialog.setTitle("Delete Task");
        alertDialog.setMessage("Do you want to delelete this task?");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                final TaskItem taskItem = arrayList.get(position);
                arrayList.remove(position);
                adapter.notifyDataSetChanged();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {

                        taskItem.deleteFromRealm();
                    }
                });
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        alertDialog.show();
    }

    private View.OnClickListener onAddTask = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            dialgFragment.show(getFragmentManager(), "dialog");
        }
    };

//    public void OnAddTask(View view) {
//
//        dialgFragment = new DialgFragment();
//        dialgFragment.show(getActivity().getFragmentManager(), "dialog");
//    }
    @Override
    public void data(final String nameTask, final String Date, final String priority, final String tag) {


        if (tag != "edit") {
            arrayList.add(new TaskItem(nameTask, Date, priority));
            adapter.notifyDataSetChanged();
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    TaskItem taskItem = realm.createObject(TaskItem.class);
                    taskItem.setNameTask(nameTask);
                    taskItem.setDate(Date);
                    taskItem.setPriority(priority);


                }
            });
        }else {
            final TaskItem taskItem = arrayList.get(pos);
            arrayList.set(pos,taskItem);
            adapter.notifyDataSetChanged();

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    taskItem.setNameTask(nameTask);
                    taskItem.setDate(Date);
                    taskItem.setPriority(priority);
                }
            });


        }
        //adapter.notifyDataSetChanged();

    }
}
