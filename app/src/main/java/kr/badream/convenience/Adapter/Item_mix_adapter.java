package kr.badream.convenience.Adapter;

/**
 * Created by Administrator on 2016-05-17.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import kr.badream.convenience.Helper.Helper_itemData;
import kr.badream.convenience.R;

/**
 * Created by user on 16. 3. 2.
 */
public class Item_mix_adapter extends RecyclerView.Adapter<Item_mix_adapter.ViewHolder> {

    private Context mContext;
    public ArrayList<Helper_itemData> list;
    private LinearLayoutManager linearLayoutManager;

    public ArrayList<Helper_itemData> getContactsList() {
        return list;
    }

    public Item_mix_adapter(Context context, ArrayList<Helper_itemData> _dataSet, LinearLayoutManager linearLayoutManager) {
        mContext = context;
        list = _dataSet;
        this.linearLayoutManager = linearLayoutManager;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_item_list, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Picasso.with(mContext).load(new File(list.get(position).path)).into(holder.album);

        holder.itemimage.setImageResource(list.get(position).getImage());
        holder.itemname.setText(list.get(position).getName());
        holder.itemprice.setText(""+list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    public void remove(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void add(Helper_itemData item, int position) {
        list.add(position, item);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         private ImageView itemimage;
         public TextView itemname;
         public TextView itemprice;

        public ViewHolder(View itemView) {
            super(itemView);
            //album = (ImageView) itemView.findViewById(R.id.album_art1);
            //text = (TextView) itemView.findViewById(R.id.year);

            itemimage = (ImageView) itemView.findViewById(R.id.item_image);
            itemname = (TextView) itemView.findViewById(R.id.item_name);
            itemprice = (TextView) itemView.findViewById(R.id.item_price);

            itemView.setClickable(true);
            itemView.setOnClickListener(this);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    // song is selected
                    //Log.i("", "index : " + getAdapterPosition());

                    return true;
                }
            });
        }

        @Override
        public void onClick(View v) {

            //Log.e("number", "index : " + list.get(0).roomname);
            //Log.e("number", "index : " + getAdapterPosition());
        }

    }
}