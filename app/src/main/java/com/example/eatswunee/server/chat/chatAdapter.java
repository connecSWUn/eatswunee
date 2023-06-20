package com.example.eatswunee.server.chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eatswunee.R;
import com.example.eatswunee.server.messages;

import java.util.List;

public class chatAdapter extends RecyclerView.Adapter<chatAdapter.ViewHolder> {

    private List<messages> messagesList;
    Context context;
    private String user_name;

    final int TYPE_MY = 0;
    final int TYPE_OTHER = 1;

    public chatAdapter(String user_name, List<messages> messagesList, Context context) {
        this.user_name = user_name;
        this.messagesList = messagesList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        if(messagesList.get(position).getMessage_sender().equals(user_name)) {
            return TYPE_OTHER;
        } else {
            return TYPE_MY;
        }
    }

    @NonNull
    @Override
    public chatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  null;
        if(viewType == TYPE_MY) view = LayoutInflater.from(context).inflate(R.layout.chat_my_message, parent, false);
        else view = LayoutInflater.from(context).inflate(R.layout.chat_other_message, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        messages item = messagesList.get(position);

        holder.message.setText(item.getMessage_content());
        holder.date.setText(item.getMessage_created_at());
    }

    @Override
    public int getItemCount() { return messagesList.size(); }

    public void addChat(messages messages) {
        messagesList.add(messages);
        notifyItemInserted(messagesList.size() - 1);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView message, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            message = itemView.findViewById(R.id.message);
            date = itemView.findViewById(R.id.message_date);
        }
    }

}
