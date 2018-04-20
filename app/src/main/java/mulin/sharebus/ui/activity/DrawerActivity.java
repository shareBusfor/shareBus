package mulin.sharebus.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ramotion.foldingcell.FoldingCell;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import mulin.sharebus.R;
import mulin.sharebus.adapter.FoldingCellListAdapter;
import mulin.sharebus.entity.Item;

public class DrawerActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "DrawerActivity";

    private SlidingUpPanelLayout mLayout;
    private ImageView headerView;
    private TextView tvName,tvContact;
    private ListView infoList;
    private float scale,textViewScale;

    final ArrayList<Item> items = Item.getTestingList();
    FoldingCellListAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        initView();
        initList();
    }

    private void initList() {

        items.get(0).setRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        adapter = new FoldingCellListAdapter(this,items);
        // add default btn handler for each request btn on each item if custom handler not found
        adapter.setDefaultRequestBtnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "DEFAULT HANDLER FOR ALL BUTTONS", Toast.LENGTH_SHORT).show();
            }
        });

        // set elements to adapter
        infoList.setAdapter(adapter);

        // set on click event listener to list view
        infoList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                // toggle clicked cell state
                ((FoldingCell) view).toggle(false);
                // register in adapter that state for selected cell is toggled
                adapter.registerToggle(pos);
            }
        });
    }

    private void initView(){

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        headerView = findViewById(R.id.iv_header);
        tvName = findViewById(R.id.tv_name);
        tvContact = findViewById(R.id.tv_contact);

        infoList = findViewById(R.id.mainListView);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer =  findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        mLayout = findViewById(R.id.sliding_layout);
        mLayout.setParallaxOffset(0);
        mLayout.setAlpha(1);
//        mLayout.setCoveredFadeColor(R.color.orange);
//        mLayout.set
        mLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                Log.i(TAG, "onPanelSlide, offset " + slideOffset);
                if(slideOffset < 0.8){
                    scale = 1-slideOffset/2;
                    textViewScale = 1 - slideOffset*2;
                    if(scale < 0.8) scale = (float) 0.8;

                    headerView.setScaleX(scale);
                    headerView.setScaleY(scale);
                    if (textViewScale < 0.7) textViewScale = 0;
                    tvContact.setScaleX(textViewScale);
                    tvContact.setScaleY(textViewScale);
                    tvName.setScaleY(textViewScale);
                    tvName.setScaleY(textViewScale);
                }
                if (slideOffset >= 0.8){
                    scale = 0;
                    textViewScale = 0;
                    headerView.setScaleX(scale);
                    headerView.setScaleY(scale);
                    tvContact.setScaleX(textViewScale);
                    tvContact.setScaleY(textViewScale);
                    tvName.setScaleY(textViewScale);
                    tvName.setScaleY(textViewScale);
                }


            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                Log.i(TAG, "onPanelStateChanged " + newState);
            }
        });
        mLayout.setAnchorPoint(0.8f);
//        mLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
