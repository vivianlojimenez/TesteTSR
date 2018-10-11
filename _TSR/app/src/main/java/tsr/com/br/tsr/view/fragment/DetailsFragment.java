package tsr.com.br.tsr.view.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import tsr.com.br.tsr.R;
import tsr.com.br.tsr.utils.Fonts;
import tsr.com.br.tsr.utils.ImageUtils;
import tsr.com.br.tsr.model.ws.ItemWS;

/**
 * @author vivianlo
 * Fragment of product details screen
 */
public class DetailsFragment extends Fragment {

    private static String ITEM_WS = "ITEM_WS";

    @BindView(R.id.img_item)
    public ImageView img;

    @BindView(R.id.img_item_placeholder)
    public ImageView imgPLaceholder;

    @BindView(R.id.rel_img)
    public RelativeLayout relImag;

    @BindView(R.id.txt_title)
    public TextView txtTitle;

    @BindView(R.id.txt_detail)
    public TextView txtDetail;

    @BindView(R.id.btn_back)
    public Button btnBack;

    private ItemWS itemWS;

    public void setData (ItemWS itemWS) {
        this.itemWS = itemWS;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_detail_item, null);

        setToolbar(rootView);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    private void setToolbar (View view) {

        if (getActivity() instanceof AppCompatActivity) {
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.getMenu().clear();

            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(toolbar);

            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
            }

            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            mTitle.setVisibility(View.VISIBLE);
            mTitle.setTypeface(Fonts.getRobotoBold());
            mTitle.setText(R.string.product);

            setHasOptionsMenu(true);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (getActivity() != null) getActivity().onBackPressed();
        return true;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState != null &&
                (savedInstanceState.getSerializable(ITEM_WS) != null)) {
            itemWS = (ItemWS) savedInstanceState.getSerializable(ITEM_WS);
        }

        initView();
    }

    private void initView() {

        if (getActivity()  != null) ImageUtils.loadImage(getActivity(), itemWS.getUrl(), relImag, img, imgPLaceholder);

        txtTitle.setText(itemWS.getName());
        txtDetail.setText(itemWS.getDescription());

        txtTitle.setTypeface(Fonts.getRobotoBold());
        txtDetail.setTypeface(Fonts.getRobotoRegular());
        btnBack.setTypeface(Fonts.getRobotoBold());

    }

    @OnClick(R.id.btn_back)
    public void clickBtnBack () {
        if (getActivity() != null) getActivity().onBackPressed();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(ITEM_WS, itemWS);
    }
}
