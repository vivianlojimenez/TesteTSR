package tsr.com.br.tsr.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tsr.com.br.tsr.R;
import tsr.com.br.tsr.controller.AppBO;
import tsr.com.br.tsr.controller.MainActivity;
import tsr.com.br.tsr.controller.ws.GetListController;
import tsr.com.br.tsr.utils.Fonts;
import tsr.com.br.tsr.view.adapter.ItemAdapter;
import tsr.com.br.tsr.model.ws.ItemWS;
import tsr.com.br.tsr.model.ws.ResponseWS;

/**
 * @author vivianlo
 * Fragment of product list screen
 */
public class ListItemFragment extends Fragment implements ItemAdapter.IItemListener{

    @BindView(R.id.recycler_itens)
    public RecyclerView recyclerView;

    @BindView(R.id.layout)
    public NestedScrollView layout;

    @BindView(R.id.txt_name)
    public TextView txtName;

    @BindView(R.id.progress)
    public ProgressBar progress;

    @BindView(R.id.txt_title_error)
    public TextView txtError;

    private List<ItemWS> list;
    private MenuItem menuItemKart;
    private ItemAdapter itemAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.frag_list_item, null);

        setToolbar(rootView);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    private void setToolbar (View view) {

        if (getActivity() instanceof AppCompatActivity) {
            Toolbar toolbar = view.findViewById(R.id.toolbar);

            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.setSupportActionBar(toolbar);

            ActionBar actionBar = activity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowTitleEnabled(false);
                actionBar.setHomeAsUpIndicator(R.mipmap.icon_menu);
            }

            TextView mTitle = toolbar.findViewById(R.id.toolbar_title);
            mTitle.setVisibility(View.GONE);

            setHasOptionsMenu(true);
        }

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
        menuItemKart = menu.findItem(R.id.item_kart);
        changeBadgeKart();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        service();

        initView();
    }

    /**
     * Method responsible for request the product list
     */
    private void service () {

        progress.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        txtError.setVisibility(View.GONE);

        new GetListController(new GetListController.IGetListListener() {
            @Override
            public void onSuccess(ResponseWS response) {
                if (response == null || response.getData() == null) onError(-1);
                else {
                    list = response.getData().getProducts();
                    updateView();
                }
            }

            //in case of error related to the request or the content of the return, a message will be displayed
            // informing the code and that an error has occurred
            @Override
            public void onError(int code) {
                txtError.setText(String.format(getResources().getString(R.string.msg_error), code));
                progress.setVisibility(View.GONE);
                layout.setVisibility(View.GONE);
                txtError.setVisibility(View.VISIBLE);
            }
        }).start();

    }

    private void initView(){

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        itemAdapter = new ItemAdapter(getActivity(), list, this);
        recyclerView.setAdapter(itemAdapter);

        txtName.setText(String.format(getResources().getString(R.string.title_main), "André"));
        txtName.setTypeface(Fonts.getRobotoBold());

        txtError.setTypeface(Fonts.getRobotoBold());

        changeBadgeKart();

    }

    private void updateView () {

        if (itemAdapter == null) return;

        itemAdapter.update(list);
        progress.setVisibility(View.GONE);
        txtError.setVisibility(View.GONE);
        layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void clickItem(ItemWS itemWS) {
        if (getActivity() != null && getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).showFragDetail(itemWS);
        }
    }

    /**
     * Method responsible for add a product to the kart and calling the method to change the badge
     */
    @Override
    public void addProductKart() {

        AppBO.getInstance().addProductsKart();
        Toast.makeText(getActivity(), R.string.msg_toast_add_kart, Toast.LENGTH_SHORT).show();
        changeBadgeKart();

    }

    /**
     * Method to change the badge
     */
    private void changeBadgeKart () {

        if (menuItemKart != null) {

            menuItemKart.setVisible(true);

            View menuView = menuItemKart.getActionView();

            TextView viewBadge = menuView.findViewById(R.id.menu_badge);

            if (viewBadge != null) {
                viewBadge.setVisibility(View.VISIBLE);
                viewBadge.setText(String.valueOf(AppBO.getInstance().getQtdProductsKart()));
            }

            RelativeLayout relativeLayout = (RelativeLayout) menuItemKart.getActionView();
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AppBO.getInstance().clearProductKart();
                    changeBadgeKart();
                }
            });

        }
    }

}