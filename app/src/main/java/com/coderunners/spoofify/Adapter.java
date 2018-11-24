package com.coderunners.spoofify;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.coderunners.spoofify.Model.NewsPost;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<NewsPost> newsPosts;


    private Context context;
     //private OnItemClickListener onItemClickListener;


    public Adapter(List<NewsPost> newsPosts, Context context) {
        this.newsPosts = newsPosts;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup, false);
        return new MyViewHolder(view); //, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;

        NewsPost model = newsPosts.get(position);
        System.out.println("/n/n/n/n/n/n"+position+"/n/n/n/n/n");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Utils.getRandomDrawbleColor());
        requestOptions.error(Utils.getRandomDrawbleColor());
        requestOptions.centerCrop();
        Glide.with(context)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holders.imageView);

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.author.setText(model.getAuthor());
        holder.publishedAt.setText(Utils.DateFormat(model.getPublishedAt()));
        holder.time.setText(Utils.DateToTimeFormat(model.getPublishedAt()));




    }

    @Override
    public int getItemCount() {
        return newsPosts.size();
    }

    //public void setOnItemClickListener(OnItemClickListener onItemClickListener){
     //   this.onItemClickListener = onItemClickListener;
   // }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, desc, publishedAt, author, time;
        ImageView imageView;
        ProgressBar progressBar;
       // OnItemClickListener onItemClickListener;

        public MyViewHolder(View itemView){//, OnItemClickListener onItemClickListener) {
            super(itemView);
           // itemView.setOnClickListener(this);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author =  itemView.findViewById(R.id.author);
            publishedAt = itemView.findViewById(R.id.publishedAt);
            imageView = itemView.findViewById(R.id.img);
            progressBar = itemView.findViewById(R.id.progress_load_photo);
            time = itemView.findViewById(R.id.time);
            //this.onItemClickListener = onItemClickListener;

        }

      //  @Override
      //  public void onClick(View v) {
       //     onItemClickListener.onItemClick(v, getAdapterPosition());

      //  }
    }
    //public interface OnItemClickListener{
    //    void onItemClick(View view, int position);
    //}
}
