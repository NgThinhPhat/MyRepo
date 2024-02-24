package com.example.rss_reader.Activities.DTO;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rss_reader.R;

import java.util.List;
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private List<Item> itemList;
    private Context context;

    public ItemAdapter(List<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = itemList.get(position);
        holder.bind(item);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private TextView pubDateTextView;
        private Button openLinkButton;

        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txtTitle);
            pubDateTextView = itemView.findViewById(R.id.txtPubdate);
            openLinkButton = itemView.findViewById(R.id.btnOpen);
        }

        void bind(final Item item) {
            titleTextView.setText(item.getTitle());
            pubDateTextView.setText(item.getPubDate());

            openLinkButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openLinkInBrowser(item.getLink());
                }
            });
        }
        private void openLinkInBrowser(String link) {
            try {
                Uri webpage = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // Add this line
                context.startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
