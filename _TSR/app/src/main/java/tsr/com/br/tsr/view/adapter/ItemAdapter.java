package tsr.com.br.tsr.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import tsr.com.br.tsr.R;
import tsr.com.br.tsr.utils.Fonts;
import tsr.com.br.tsr.utils.ImageUtils;
import tsr.com.br.tsr.model.ws.ItemWS;

/**
 * @author vivianlo
 * Product list adapter
 */
public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.MyViewHolder> {

    private final NumberFormat formatter;
    private List<ItemWS> mList;
    private LayoutInflater mLayoutInflater;
    private Context context;
    private IItemListener listener;

    public ItemAdapter(Context context, List<ItemWS> mList, IItemListener listener) {

        Locale ptBR = new Locale("pt", "BR");
        this.formatter = NumberFormat.getCurrencyInstance(ptBR);

        this.mList = mList;
        this.context = context;
        this.listener = listener;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View v = mLayoutInflater.inflate(R.layout.item_list, viewGroup, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int position) {

        final ItemWS itemWS = mList.get(position);

        myViewHolder.txtTitle.setText(itemWS.getName());

        String value = formatter.format(itemWS.getPriceMinimum());
        myViewHolder.txtPrice.setText(value);

        myViewHolder.imgPLaceholder.setAlpha(1f);

        ImageUtils.loadImage(context, itemWS.getUrl(), myViewHolder.relImag, myViewHolder.img, myViewHolder.imgPLaceholder);

        myViewHolder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.addProductKart();
            }
        });

        myViewHolder.containerItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) listener.clickItem(itemWS);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mList == null) {
            return 0;
        }

        return mList.size();
    }

    public void update(List<ItemWS> mList) {
        this.mList = mList;
        this.notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_title)
        public TextView txtTitle;

        @BindView(R.id.txt_price)
        public TextView txtPrice;

        @BindView(R.id.img_item)
        public ImageView img;

        @BindView(R.id.img_item_placeholder)
        public ImageView imgPLaceholder;

        @BindView(R.id.btn_item)
        public Button btn;

        @BindView(R.id.rel_img)
        public RelativeLayout relImag;

        @BindView(R.id.container_item)
        public LinearLayout containerItem;


        MyViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            txtTitle.setTypeface(Fonts.getRobotoBold());
            txtPrice.setTypeface(Fonts.getRobotoRegular());
            btn.setTypeface(Fonts.getRobotoBold());
        }

    }

    public interface IItemListener {
        void clickItem(ItemWS itemWS);
        void addProductKart();
    }
}
