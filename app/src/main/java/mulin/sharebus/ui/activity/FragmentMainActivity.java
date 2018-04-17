package mulin.sharebus.ui.activity;

import android.Manifest;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mikepenz.crossfadedrawerlayout.view.CrossfadeDrawerLayout;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.MiniDrawer;
import com.mikepenz.materialdrawer.interfaces.ICrossfader;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.mikepenz.materialdrawer.model.interfaces.Nameable;
import com.mikepenz.materialdrawer.util.DrawerUIUtils;
import com.mikepenz.materialize.util.UIUtils;
import com.tbruyelle.rxpermissions2.RxPermissions;

import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import mulin.sharebus.R;
import mulin.sharebus.ui.fragment.FragmentMainHomeFragment;
import mulin.sharebus.ui.fragment.MainLoginFragment;
import mulin.sharebus.util.LogUtils;


/**
 * Created by mulin on 2018/4/11.
 */


//@RuntimePermissions
public class FragmentMainActivity extends SupportActivity {


    //save our header or result
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private CrossfadeDrawerLayout crossfadeDrawerLayout = null;

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {

        FragmentAnimator fragmentAnimator = super.onCreateFragmentAnimator();
        fragmentAnimator.setEnter(0);
        fragmentAnimator.setExit(0);
        return new DefaultHorizontalAnimator();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_fragment_hone);

        // Handle Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        initDrawer(savedInstanceState);

        if(savedInstanceState == null){
            loadRootFragment(R.id.frame_container, FragmentMainHomeFragment.newInstance());
            LogUtils.e("fragment","loadRootFragment");
        }


    }

    private void initDrawer(Bundle savedInstanceState){
        final IProfile profile = new ProfileDrawerItem().withName("Mike Penz").withEmail("mikepenz@gmail.com").withIcon(R.drawable.header_drawe).withIdentifier(100);

        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(true)
                .withHeaderBackground(R.drawable.header)
                .withTranslucentStatusBar(true)
//                .withHeaderBackground(R.color.white)
//                .withProfileImagesVisible(false)
                .addProfiles(
                        profile
                )
                .withSavedInstance(savedInstanceState)
//                .withOnAccountHeaderListener((view, profile, current) -> false)
                .build();

        //Create the drawer
        result = new DrawerBuilder()
                .withActivity(this)
                .withHasStableIds(true)
                .withDrawerLayout(R.layout.crossfade_drawer)
                .withDrawerWidthDp(120)
                .withGenerateMiniDrawer(true)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
//                        new PrimaryDrawerItem().withName("钱包").withIcon()
                        new PrimaryDrawerItem().withName(R.string.drawer_item_chi_wallet).withIcon(GoogleMaterial.Icon.gmd_account_balance_wallet).withIdentifier(1).withSelectedIconColor(Color.parseColor("#FF8F00")).withIdentifier(1).withSelectedTextColor(Color.parseColor("#FF8F00")).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_chi_notification).withIcon(GoogleMaterial.Icon.gmd_notifications).withIdentifier(2).withSelectedIconColor(Color.parseColor("#FF8F00")).withIdentifier(2).withSelectedTextColor(Color.parseColor("#FF8F00")).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_chi_verificayion).withIcon(GoogleMaterial.Icon.gmd_verified_user).withIdentifier(3).withSelectedIconColor(Color.parseColor("#FF8F00")).withIdentifier(3).withSelectedTextColor(Color.parseColor("#FF8F00")).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_chi_activities).withIcon(GoogleMaterial.Icon.gmd_local_activity).withIdentifier(4).withSelectedIconColor(Color.parseColor("#FF8F00")).withIdentifier(4).withSelectedTextColor(Color.parseColor("#FF8F00")).withSelectable(false),
                        new PrimaryDrawerItem().withName(R.string.drawer_item_chi_setting).withIcon(GoogleMaterial.Icon.gmd_settings).withSelectedIconColor(Color.parseColor("#FF8F00")).withIdentifier(5).withSelectedTextColor(Color.parseColor("#FF8F00")).withSelectable(false),
//                        new PrimaryDrawerItem().withName(R.stringample").withName(R.string.drawer_item_advanced_drawer).withIcon(GoogleMaterial.Icon.gmd_adb).withIdentifier(5),
                        new SectionDrawerItem().withName(R.string.drawer_item_section_header),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_open_source).withIcon(FontAwesome.Icon.faw_github),
                        new SecondaryDrawerItem().withName(R.string.drawer_item_contact).withIcon(GoogleMaterial.Icon.gmd_format_color_fill).withTag("Bullhorn")
                ) // add the items we want to use with our Drawer
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        if (drawerItem instanceof Nameable) {

                            Toast.makeText(FragmentMainActivity.this, ((Nameable) drawerItem).getName().getText(FragmentMainActivity.this), Toast.LENGTH_SHORT).show();
                        }
                        return false;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .withShowDrawerOnFirstLaunch(false)
                .build();


        crossfadeDrawerLayout = (CrossfadeDrawerLayout) result.getDrawerLayout();

        //define maxDrawerWidth
        crossfadeDrawerLayout.setMaxWidthPx(DrawerUIUtils.getOptimalDrawerWidth(this));
        //add second view (which is the miniDrawer)
        final MiniDrawer miniResult = result.getMiniDrawer();
        //build the view for the MiniDrawer
        View view = miniResult.build(this);
        //set the background of the MiniDrawer as this would be transparent
        view.setBackgroundColor(UIUtils.getThemeColorFromAttrOrRes(this, com.mikepenz.materialdrawer.R.attr.material_drawer_background, com.mikepenz.materialdrawer.R.color.material_drawer_background));
        //we do not have the MiniDrawer view during CrossfadeDrawerLayout creation so we will add it here
        crossfadeDrawerLayout.getSmallView().addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        //define the crossfader to be used with the miniDrawer. This is required to be able to automatically toggle open / close
        miniResult.withCrossFader(new ICrossfader() {
            @Override
            public void crossfade() {
                boolean isFaded = isCrossfaded();
                crossfadeDrawerLayout.crossfade(400);

                //only close the drawer if we were already faded and want to close it now
                if (isFaded) {
                    result.getDrawerLayout().closeDrawer(GravityCompat.START);
                }
            }

            @Override
            public boolean isCrossfaded() {
                return crossfadeDrawerLayout.isCrossfaded();
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    @Override
    public void onBackPressedSupport() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            super.onBackPressed();
        }


    }
}
