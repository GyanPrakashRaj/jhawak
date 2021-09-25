package com.glide.baymax.myglide.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.glide.baymax.myglide.R;
import com.glide.baymax.myglide.model.Results;
import com.glide.baymax.myglide.view.RatioImageView;

import java.util.List;

/**
 * Created by baymax on 2016/4/25.
 */
public class GirlRecyclerAdapter extends RecyclerView.Adapter<GirlRecyclerAdapter.MyViewHolder> {

    private Context context;
    private List<Results> resultsList;

    public GirlRecyclerAdapter(Context context, List<Results> results) {
        super();
        this.context = context;
        this.resultsList = results;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(
                R.layout.item_picture_girl, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Results results = resultsList.get(position);

        Glide
                .with(context)
                .load(results.getUrl())
                .asBitmap()
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap bitmap, GlideAnimation glideAnimation) {
                        if (bitmap.getWidth() != 0 && bitmap.getHeight() != 0) {
                            holder.imageView.setOriginalSize(bitmap.getWidth(), bitmap.getHeight());
                        }
                        holder.imageView.setImageBitmap(bitmap);
                    }
                });
    }

    public void removeData(int position)
    {
        resultsList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return resultsList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        public RatioImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (RatioImageView) itemView.findViewById(R.id.ratio_iv);
        }
    }
}

