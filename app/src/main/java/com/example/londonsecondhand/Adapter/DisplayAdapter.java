package com.example.londonsecondhand.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.LayoutInflater;

import com.example.londonsecondhand.Fragment.HomeFragment;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import com.example.londonsecondhand.DataType.DisplayData;

import com.example.londonsecondhand.R;

public class DisplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> objects;
    private Context context;

    public DisplayAdapter(Context context, List<Object> objects) {
        this.objects = objects;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        viewHolder = new ListProductHolder(inflate(parent, R.layout.list_product));
        return viewHolder;
    }

    private View inflate(ViewGroup parent, int resId) {
        return LayoutInflater.from(parent.getContext()).inflate(resId, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Object object = objects.get(position);
        setListProduct((ListProductHolder) holder, (DisplayData.ListItem) object);
    }

    @Override
    public int getItemCount() {
        if (objects == null){
            return 0;
        }
        else {
            return objects.size();
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private void setListProduct(ListProductHolder holder, DisplayData.ListItem listItem) {
        //图片
        Integer id = listItem.getImageId();
        holder.itemUrl.setImageResource(id);
        //品牌
        String title = listItem.getTitle();
        holder.itemTitle.setText(title);
        //内容
        String description = listItem.getDescription();
        holder.itemContent.setText(description);
        //价格
        double price = listItem.getPrice();
        holder.itemPrice.setText("¥" + price);
    }

    class ListProductHolder extends RecyclerView.ViewHolder {
        ImageView itemUrl = itemView.findViewById(R.id.item_url);
        TextView itemTitle = itemView.findViewById(R.id.item_title);
        TextView itemContent = itemView.findViewById(R.id.item_content);
        TextView itemPrice = itemView.findViewById(R.id.item_price);

        public ListProductHolder(View itemView) {
            super(itemView);
        }
    }
}
