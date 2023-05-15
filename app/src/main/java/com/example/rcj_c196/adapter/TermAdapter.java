package com.example.rcj_c196.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rcj_c196.R;
import com.example.rcj_c196.activities.Terms.detailTerm;
import com.example.rcj_c196.entities.Terms;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder {
        private final TextView termItemView;
        private TermViewHolder(View itemview){
            super(itemview);
            termItemView=itemview.findViewById(R.id.textView3);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Terms current=mTerms.get(position);
                    Intent intent = new Intent(context, detailTerm.class);
                    intent.putExtra("ID", current.getTermID());
                    intent.putExtra("Name", current.getTermName());
                    intent.putExtra("Start Date", current.getTermStart());
                    intent.putExtra("End Date", current.getTermEnd());
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<Terms> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;
    public TermAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.term_list_item,parent,false);

        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
    if (mTerms!=null){
    Terms current=mTerms.get(position);
    String name=current.getTermName();
        holder.termItemView.setText(name);
    }else {
        holder.termItemView.setText("Nothing to show");
    }
    }

    @Override
    public int getItemCount() {
        return mTerms.size();
    }

    public void setTerms(List<Terms> terms){
        mTerms=terms;
        notifyDataSetChanged();
    }

}
