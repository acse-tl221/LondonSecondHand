package com.example.londonsecondhand.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.londonsecondhand.DataType.DisplayData;
import com.example.londonsecondhand.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.londonsecondhand.Utils.databaseUtils;
import com.example.londonsecondhand.Utils.ImageUtils;
import com.example.londonsecondhand.Adapter.DisplayAdapter;

public class HomeFragment extends Fragment {
    private List<Object> allObjects;
    private RecyclerView recyclerView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 1:
                    DisplayAdapter adapter = new DisplayAdapter(getContext(), allObjects);
                    adapter.setHasStableIds(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                    break;
                default:
                    break;
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.home_recycler);

        if (allObjects == null) {
            allObjects = new ArrayList<>();
        }

        //读取商品列表
        new Thread(new Runnable() {
            @Override
            public void run() {
                Connection con = databaseUtils.getconnection();
                PreparedStatement state = null;
                String sql = "select * from PopularItem";
                ResultSet res;
                try {
                    state = con.prepareStatement(sql);
                    res = state.executeQuery();
                    while (res.next()){
                        Integer imageid = ImageUtils.getimageid(res.getString(1));
                        String title = res.getString(2);
                        String context = res.getString(3);
                        Double price = res.getDouble(4);
                        allObjects.add(new DisplayData.ListItem(imageid,title,context,price));
                        Message msg = new Message();
                        msg.what = 1;
                        msg.obj = allObjects;
                        handler.sendMessage(msg);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        return view;
    }

}