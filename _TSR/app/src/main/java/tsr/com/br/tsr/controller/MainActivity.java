package tsr.com.br.tsr.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import tsr.com.br.tsr.R;
import tsr.com.br.tsr.utils.Fonts;
import tsr.com.br.tsr.view.fragment.DetailsFragment;
import tsr.com.br.tsr.view.fragment.ListItemFragment;
import tsr.com.br.tsr.model.ws.ItemWS;

/**
 * @author vivianlo
 */
public class MainActivity extends AppCompatActivity {

    private static String FRAGS = "FRAGS";
    private Fragment actualFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Fonts.initFonts(this);

        if (savedInstanceState == null) {
            showListItemFragment();
        } else {
            actualFrag = getSupportFragmentManager().getFragment(savedInstanceState, FRAGS);
            showFragment();
        }

    }

    public void showFragment () {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.content_frame, actualFrag);
        transaction.addToBackStack(null);

        transaction.commit();
    }

    /** Show screen main */
    public void showListItemFragment() {

        actualFrag = new ListItemFragment();

        showFragment();
    }

    /**
     * Show product details screen
     * @param itemWS - product selected
     */
    public void showFragDetail(ItemWS itemWS) {

        DetailsFragment newFragment = new DetailsFragment();
        newFragment.setData(itemWS);
        actualFrag = newFragment;

        showFragment();
    }


    @Override
    public void onBackPressed() {
        Fragment f = getSupportFragmentManager().findFragmentById(R.id.content_frame);

        if (f instanceof ListItemFragment) {
            finish();
        } else {
            showListItemFragment();
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, FRAGS, actualFrag);
    }
}
