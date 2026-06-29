package com.slipkprojects.sockshttp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.PersistableBundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.textfield.TextInputEditText;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Html;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.slipkprojects.sockshttp.activities.AboutActivity;
import com.slipkprojects.sockshttp.activities.BaseActivity;
import com.slipkprojects.sockshttp.activities.ConfigExportFileActivity;
import com.slipkprojects.sockshttp.activities.ConfigGeralActivity;
import com.slipkprojects.sockshttp.activities.ConfigImportFileActivity;
import com.slipkprojects.sockshttp.adapter.LogsAdapter;
import com.slipkprojects.sockshttp.fragments.ClearConfigDialogFragment;
import com.slipkprojects.sockshttp.fragments.CustomSniFragment;
import com.slipkprojects.sockshttp.fragments.ProxyRemoteDialogFragment;
import com.slipkprojects.sockshttp.util.GoogleFeedbackUtils;
import com.slipkprojects.sockshttp.util.HostChecker;
import com.slipkprojects.sockshttp.util.IpHunter;
import com.slipkprojects.sockshttp.util.Utils;
import com.slipkprojects.ultrasshservice.LaunchVpn;
import com.slipkprojects.ultrasshservice.SocksHttpService;
import com.slipkprojects.ultrasshservice.config.ConfigParser;
import com.slipkprojects.ultrasshservice.config.Settings;
import com.slipkprojects.ultrasshservice.logger.ConnectionStatus;
import com.slipkprojects.ultrasshservice.logger.SkStatus;
import com.slipkprojects.ultrasshservice.tunnel.TunnelManagerHelper;
import com.slipkprojects.ultrasshservice.tunnel.TunnelUtils;
import com.slipkprojects.ultrasshservice.util.SkProtect;
import com.slipkprojects.ultrasshservice.util.ToastUtil;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import smartdevelop.ir.eram.showcaseviewlib.BuildConfig;
import smartdevelop.ir.eram.showcaseviewlib.GuideView;
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType;
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity;
import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

/**
 * Activity Principal
 * @author SlipkHunter
 */

public class SocksHttpMainActivity extends BaseActivity
	implements DrawerLayout.DrawerListener, RewardedVideoAdListener,
			View.OnClickListener, RadioGroup.OnCheckedChangeListener,
				CompoundButton.OnCheckedChangeListener, SkStatus.StateListener, RenzGenerator.OnDismissListener
{
	



	@Override
	public void onRewarded(RewardItem reward) {
		// Reward the user.

	}

	@Override
	public void onRewardedVideoAdLeftApplication() {

	}

	@Override
	public void onRewardedVideoAdClosed() {

	}

	@Override
	public void onRewardedVideoAdFailedToLoad(int errorCode) {

	}

	@Override
	public void onRewardedVideoAdLoaded() {

	}

	@Override
	public void onRewardedVideoAdOpened() {

	}

	@Override
	public void onRewardedVideoStarted() {
		toastutil.showSuccessToast("Ads is loaded");
	}
	
	private static final String TAG = SocksHttpMainActivity.class.getSimpleName();
	private static final String UPDATE_VIEWS = "MainUpdate";
	public static final String OPEN_LOGS = "com.slipkprojects.sockshttp:openLogs";
	private LinearLayout cmodsniLayout;

	private TextView cmodsniText;
	
	private LinearLayout testinglayout;

	private DrawerPanelMain mDrawerPanel;
	
	private Settings mConfig;
	private Toolbar toolbar_main;
	private Handler mHandler;
	
	private LinearLayout mainLayout;
	private LinearLayout loginLayout;
	private LinearLayout proxyInputLayout;
	private TextView proxyText;
	private RadioGroup metodoConexaoRadio;
	//private RadioGroup tunnelmode_layout;
	private TextView selected_tunnel_options;
	private LinearLayout payloadLayout;
	private TextInputEditText payloadEdit;
	private CheckBox customPayloadSwitch;
	private Button starterButton;
	
	private ImageButton inputPwShowPass;
	private TextInputEditText inputPwUser;
	private TextInputEditText inputPwPass;
	
	private CardView configMsgLayout;
	private TextView configMsgText;

	private AdView adsBannerView;
	
	private CardView Viewgone;
	private CardView ViewGone;
	private CardView tunneltype;
	private TextView tunnelselected;
	
	private TextView mTextViewCountDown;
	private Button mButtonSet;
	private CheckBox mButtonStartPause;
	private Button mButtonReset;
	private CountDownTimer mCountDownTimer;
	private boolean mTimerRunning;
	private long mStartTimeInMillis;
	private long mTimeLeftInMillis;
	private long mEndTime;
	private EditText mEditTextInput;
	
    private ToastUtil toastutil;

	private static final String AD_UNIT_ID = "ca-app-pub-2923503945727793~2480051070";
	private RewardedVideoAd mRewardedVideoAd;
	private InterstitialAd interstitialAd;
	private InterstitialAd mInterstitialAd;
	
	private static final String[] tabTitle = {"INICIO","REGISTRO","AYUDA"};
	private LogsAdapter mLogAdapter;
	private RecyclerView logList;
	private TabLayout tabs;
	private ViewPager vp;
	private FloatingActionButton deleteLogs;
	
	private TextView status;
	private RelativeLayout tunnel_dialog_layout;
	
	View view1;
    View view2;
    View view3;
    View view4;
	View view20;
	View view21;
	View view22;
    View view5;
    View view6;
	View view7;
	View view8;
    private GuideView mGuideView;
	private AlertDialog dialog;
	
	private TextView tunneltypeselected;
	private TextView onoff;

	
	String APP = new String(new byte[]{84,104,105,115,32,97,112,112,108,105,99,97,116,105,111,110,32,105,115,32,111,119,110,101,100,32,98,121,32,78,117,116,114,111,32,87,111,114,108,100});
	
	@Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

		mHandler = new Handler();
		mConfig = new Settings(this);
		toastutil = new ToastUtil(this);
		
		mDrawerPanel = new DrawerPanelMain(this);
		Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
		SharedPreferences prefs = getSharedPreferences(SocksHttpApp.PREFS_GERAL, Context.MODE_PRIVATE);

		boolean showFirstTime = prefs.getBoolean("connect_first_time", true);
		int lastVersion = prefs.getInt("last_version", 0);

		// se primeira vez
		if (showFirstTime)
        {
            SharedPreferences.Editor pEdit = prefs.edit();
            pEdit.putBoolean("connect_first_time", false);
            pEdit.apply();

			Settings.setDefaultConfig(this);

			showBoasVindas();
        }

		try {
			int idAtual = ConfigParser.getBuildId(this);

			if (lastVersion < idAtual) {
				SharedPreferences.Editor pEdit = prefs.edit();
				pEdit.putInt("last_version", idAtual);
				pEdit.apply();

				// se estiver atualizando
				if (!showFirstTime) {
					if (lastVersion <= 12) {
						Settings.setDefaultConfig(this);
						Settings.clearSettings(this);

						Toast.makeText(this, "As configurações foram limpas para evitar bugs",
							Toast.LENGTH_LONG).show();
					}
				}

			}
		} catch(IOException e) {}
		
		
		// set layout
		doLayout();

		// verifica se existe algum problema
		SkProtect.CharlieProtect();

		// recebe local dados
		IntentFilter filter = new IntentFilter();
		filter.addAction(UPDATE_VIEWS);
		filter.addAction(OPEN_LOGS);
		
		LocalBroadcastManager.getInstance(this)
			.registerReceiver(mActivityReceiver, filter);
			
		doUpdateLayout();
		doTabs();
		alllayout();
	}
	
	
	public void alllayout()
	{
		CardView iphunter_layout = (CardView)findViewById(R.id.iphunter_layout);
		iphunter_layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					Intent iphunterintent = new Intent(SocksHttpMainActivity.this, IpHunter.class);
					iphunterintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(iphunterintent);
					
				}});
				
		CardView hostchecker_layout = (CardView)findViewById(R.id.hostchecker_layout);
		hostchecker_layout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View p1) {
					Intent hostintent = new Intent(SocksHttpMainActivity.this, HostChecker.class);
					startActivity(hostintent);
				}});
	}
	
	private void tunnel_options(){
		LayoutInflater inflater = LayoutInflater.from(this);
		final View v = inflater.inflate(R.layout.dialog_tunnelmode, null);
		final AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(v);
		RadioGroup metodoConexaoRadio = (RadioGroup) v.findViewById(R.id.activity_mainMetodoConexaoRadio);
		metodoConexaoRadio.setOnCheckedChangeListener(this);
		final TextView selected_tunnel_options = (TextView)v.findViewById(R.id.selected_tunneloptions);
		final TextView tunneltype_selected_layout = (TextView)v.findViewById(R.id.tunnelmode_selected_layout);
		//final SwitchCompat payload_enable = (SwitchCompat)v.findViewById(R.id.activity_mainCustomPayloadSwitch);
		// customPayloadSwitch = (CheckBox) findViewById(R.id.activity_mainCustomPayloadSwitch);
		final RadioButton btn_direct = (RadioButton) v.findViewById(R.id.activity_mainSSHDirectRadioButton);
		final RadioButton btn_ssh = (RadioButton) v.findViewById(R.id.activity_mainSSHProxyRadioButton);
		final RadioButton btn_ssl = (RadioButton) v.findViewById(R.id.activity_mainSSLProxyRadioButton);
		//savez = (Button) v.findViewById(R.id.savez);
		//dialog.getWindow().setBackgroundDrawable(new ColorDrawable(R.color.colorPrimary));
		metodoConexaoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
			{
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {

					// save id into your SharedPreferences

					if(btn_direct.isChecked())
					{
						((AppCompatRadioButton) findViewById(R.id.activity_mainSSHDirectRadioButton)).setChecked(true);
						//tunnel_type_txt.setText("Direct SSH");
						tunneltype_selected_layout.setText("Direct SSH");
						//selected_tunnel_options.setText("Direct SSH");
						SkStatus.logInfo("<font color='green'>Direct SSH Mode</font>");

					}
					if(btn_ssh.isChecked())
					{
						((AppCompatRadioButton) findViewById(R.id.activity_mainSSHProxyRadioButton)).setChecked(true);
						tunneltype_selected_layout.setText("HTTP Proxy ➔ SSH");
						//selected_tunnel_options.setText("HTTP Proxy ➔ SSH");
						SkStatus.logInfo("<font color='green'>SSH + PROXY Mode</font>");
						payloadEdit.setHint(R.string.payload);
					}
					else if(btn_ssl.isChecked())
					{
						((AppCompatRadioButton) findViewById(R.id.activity_mainSSLProxyRadioButton)).setChecked(true);
						tunneltype_selected_layout.setText("SSL/TLS ➝ SSH");
						//selected_tunnel_options.setText("SSL/TLS ➝ SSH");
						SkStatus.logInfo("<font color='green'>SSH + SSL/TLS Mode</font>");
						payloadLayout.setVisibility(View.VISIBLE);
					}
				}
			});
		

		builder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int i) {

					dialog.dismiss();

				}
			});
		/*btn_saveR.setOnClickListener(new View.OnClickListener() {
		 @Override
		 public void onClick(View v) {


		 }
		 });*/

		builder.show();
	}
	
	public void tunnel_options_layout(View v)
	{
		tunnel_options();
	}
	
	public void doTabs() {
        vp = (ViewPager)findViewById(R.id.viewpager);
        tabs = (TabLayout)findViewById(R.id.tablayout);
        vp.setAdapter(new MyAdapter(Arrays.asList(tabTitle)));
        vp.setOffscreenPageLimit(3);
        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setTabGravity(TabLayout.GRAVITY_FILL);
        tabs.setupWithViewPager(vp);

    }
    public class MyAdapter extends PagerAdapter
    {

        @Override
        public int getCount()
        {
            // TODO: Implement this method
            return 3;
        }

        @Override
        public boolean isViewFromObject(View p1, Object p2)
        {
            // TODO: Implement this method
            return p1 == p2;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            int[] ids = new int[]{R.id.tab1, R.id.tab2, R.id.tab3};
            int id = 0;
            id = ids[position];
            // TODO: Implement this method
            return findViewById(id);
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            // TODO: Implement this method
            return titles.get(position);
        }

        private List<String> titles;
        public MyAdapter(List<String> str)
        {
            titles = str;
        }
	}
	public void guides()
	{
		new GuideView.Builder(SocksHttpMainActivity.this)
			.setTitle("DIRECT SSH")
			.setContentText("In This Tunnel Type You Can You Use It As Raw Or Enable Custom Payload")
			.setGravity(Gravity.center)
			.setTargetView(view1)
			.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
			.setGuideListener(new GuideListener() {
				@Override
				public void onDismiss(View view) {
					//TODO ...
					new GuideView.Builder(SocksHttpMainActivity.this)
						.setTitle("HTTP Proxy")
						.setContentText("In This Tunnel Type You Can You Use It As Raw Or Enable Custom Payload")
						.setGravity(Gravity.center)
						.setTargetView(view2)
						.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
						.setGuideListener(new GuideListener() {

//ari nimu e solod ang bag o
							@Override
							public void onDismiss(View view) {
								//TODO ...
								new GuideView.Builder(SocksHttpMainActivity.this)
									.setTitle("SSL/TLS")
									.setContentText("You can use this one as Custom SNI only no payload")
									.setGravity(Gravity.center)
									.setTargetView(view3)
									.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
									.setGuideListener(new GuideListener() {
										@Override
										public void onDismiss(View view) {
											//TODO ...
											new GuideView.Builder(SocksHttpMainActivity.this)
												.setTitle("SSL/Payload")
												.setContentText("This Tunnel Type is Defaulted with Custom payload...\n\nYou can not disable payload.")
												.setGravity(Gravity.center)
												.setTargetView(view4)
												.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
												.setGuideListener(new GuideListener() {

													@Override
													public void onDismiss(View view) {
														//TODO ...
														new GuideView.Builder(SocksHttpMainActivity.this)
															.setTitle("Custom Payload")
															.setContentText("Tick/Untick This Box To Enable/Disable Payload Layout")
															.setGravity(Gravity.center)
															.setTargetView(view20)
															.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
															.setGuideListener(new GuideListener() {
													
																@Override
																public void onDismiss(View view) {
																	//TODO ...
																	new GuideView.Builder(SocksHttpMainActivity.this)
																		.setTitle("Connect/Disconnect")
																		.setContentText("Click This Button To :\n\nConnect = Start connection between the server...\n\nDisconnect = Stop the connection")
																		.setGravity(Gravity.center)
																		.setTargetView(view21)
																		.setDismissType(DismissType.outside) //optional - default dismissible by TargetView
																		.setGuideListener(new GuideListener() {
																
//ari nimu e solod ang bag o
													@Override
													public void onDismiss(View view) {
														//TODO ...

													}

												})
												.build()
												.show();
										}
									})
									.build()
									.show();
							}

						})
						.build()
						.show();
				}
			})
			.build()
			.show();
		}
	})
	.build()
	.show();
	  }
})
.build()
.show();

		updatingForDynamicLocationViews();
	}
	private void updatingForDynamicLocationViews() {
        view4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
				@Override
				public void onFocusChange(View view, boolean b) {
					mGuideView.updateGuideViewLocation();
				}
			});}
	
	public class DrawerPanelMain
	implements NavigationView.OnNavigationItemSelectedListener
	{
		private AppCompatActivity mActivity;

		public DrawerPanelMain(AppCompatActivity activity) {
			mActivity = activity;
		}


		private DrawerLayout drawerLayout;
		private ActionBarDrawerToggle toggle;

		public void setDrawer(Toolbar toolbar) {
			NavigationView drawerNavigationView = (NavigationView) mActivity.findViewById(R.id.drawerNavigationView);
			drawerLayout = (DrawerLayout) mActivity.findViewById(R.id.drawerLayoutMain);

			// set drawer
			toggle = new ActionBarDrawerToggle(mActivity,
											   drawerLayout, toolbar, R.string.open, R.string.cancel);

			drawerLayout.setDrawerListener(toggle);

			toggle.syncState();

			// set app info
			PackageInfo pinfo = Utils.getAppInfo(mActivity);
			if (pinfo != null) {
				String version_nome = pinfo.versionName;
				int version_code = pinfo.versionCode;
				String header_text = String.format("v. %s (%d)", version_nome, version_code);

				View view = drawerNavigationView.getHeaderView(0);

				TextView app_info_text = view.findViewById(R.id.nav_headerAppVersion);
				app_info_text.setText(header_text);
			}

			// set navigation view
			drawerNavigationView.setNavigationItemSelectedListener(this);
		}

		public ActionBarDrawerToggle getToogle() {
			return toggle;
		}

		public DrawerLayout getDrawerLayout() {
			return drawerLayout;
		}

		@Override
		public boolean onNavigationItemSelected(@NonNull MenuItem item) {
			int id = item.getItemId();

			switch(id)
			{
				case R.id.miPhoneConfg:
					PackageInfo app_info = Utils.getAppInfo(mActivity);
					if (app_info != null) {
						String aparelho_marca = Build.BRAND.toUpperCase();

						if (aparelho_marca.equals("SAMSUNG") || aparelho_marca.equals("HUAWEY")) {
							toastutil.showDefaultToast(getString(R.string.error_no_supported));
						}
						else {
							try {
								Intent in = new Intent(Intent.ACTION_MAIN);
								in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
								in.setClassName("com.android.settings", "com.android.settings.RadioInfo");
								mActivity.startActivity(in);
							} catch(Exception e) {
								toastutil.showDefaultToast(getString(R.string.error_no_supported));
							}
						}
					}
					break;
				case R.id.miGen:
					if(customPayloadSwitch.isChecked())
					{
						showGenerator();
						
					} else {
						toastutil.showInfoToast("Please enable the Custom Payload Switch..");
							
						}
					break;
					
				case R.id.iphunter2:
					Intent iphunterintent = new Intent(mActivity, IpHunter.class);
					iphunterintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(iphunterintent);
					if (adsBannerView != null && TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
						adsBannerView.setAdListener(new AdListener() {
								@Override
								public void onAdLoaded() {
									if (adsBannerView != null && !isFinishing()) {
										adsBannerView.setVisibility(View.VISIBLE);
									}
								}
							});
						adsBannerView.postDelayed(new Runnable() {
								@Override
								public void run() {
									// carrega ads interestitial
									AdsManager.newInstance(getApplicationContext())
										.loadAdsInterstitial();
									// ads banner
									if (adsBannerView != null && !isFinishing()) {
										adsBannerView.loadAd(new AdRequest.Builder()
															 .build());
									}
								}
							}, 5000);
					}
					
					break;

				/*case R.id.hostchecker2:
					Intent hostcheckerintent = new Intent(mActivity, HostChecker.class);
					hostcheckerintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(hostcheckerintent);
					break;*/
				

				case R.id.miSettings:
					Intent intent = new Intent(mActivity, ConfigGeralActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(intent);
					if (adsBannerView != null && TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
						adsBannerView.setAdListener(new AdListener() {
								@Override
								public void onAdLoaded() {
									if (adsBannerView != null && !isFinishing()) {
										adsBannerView.setVisibility(View.VISIBLE);
									}
								}
							});
						adsBannerView.postDelayed(new Runnable() {
								@Override
								public void run() {
									// carrega ads interestitial
									AdsManager.newInstance(getApplicationContext())
										.loadAdsInterstitial();
									// ads banner
									if (adsBannerView != null && !isFinishing()) {
										adsBannerView.loadAd(new AdRequest.Builder()
															 .build());
									}
								}
							}, 5000);
					}
					break;

				case R.id.miSettingsSSH:
					Intent intent2 = new Intent(mActivity, ConfigGeralActivity.class);
					intent2.setAction(ConfigGeralActivity.OPEN_SETTINGS_SSH);
					intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(intent2);
					if (adsBannerView != null && TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
						adsBannerView.setAdListener(new AdListener() {
								@Override
								public void onAdLoaded() {
									if (adsBannerView != null && !isFinishing()) {
										adsBannerView.setVisibility(View.VISIBLE);
									}
								}
							});
						adsBannerView.postDelayed(new Runnable() {
								@Override
								public void run() {
									// carrega ads interestitial
									AdsManager.newInstance(getApplicationContext())
										.loadAdsInterstitial();
									// ads banner
									if (adsBannerView != null && !isFinishing()) {
										adsBannerView.loadAd(new AdRequest.Builder()
															 .build());
									}
								}
							}, 5000);
					}
					break;

					
				case R.id.miAvaliarPlaystore:
					Intent email1 = new Intent(Intent.ACTION_SEND);  
					String url = "https://t.me/AlfSilic";
					Intent intent3 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
					intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(Intent.createChooser(intent3, mActivity.getText(R.string.open_with)));
					toastutil.showInfoToast("Please make sure you join");

					break;

					
				case R.id.more:
					Intent aboutIntent = new Intent(mActivity, AboutActivity.class);
					startActivity(aboutIntent);
					break;
					
					
				case R.id.miAdmin:
					String url1 = "https://t.me/AlfSilic";
					Intent intent4 = new Intent(Intent.ACTION_VIEW, Uri.parse(url1));
					intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					mActivity.startActivity(Intent.createChooser(intent4, mActivity.getText(R.string.open_with)));
					break;

				case R.id.miSendFeedback:
					if (false && Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
						try {
							GoogleFeedbackUtils.bindFeedback(mActivity);
						} catch (Exception e) {
							toastutil.showDefaultToast("Not available on your device");
							SkStatus.logDebug("Error: " + e.getMessage());
						}
					}
					else {
						Intent email = new Intent(Intent.ACTION_SEND);  
						email.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

						email.putExtra(Intent.EXTRA_EMAIL, new String[]{"alfresilic@gmail.com"});  
						email.putExtra(Intent.EXTRA_SUBJECT, "JP Injector - " + mActivity.getString(R.string.feedback));  
						email.setType("message/rfc822"); 

						toastutil.showDefaultToast("Choose an Email client");
						mActivity.startActivity(Intent.createChooser(email, "Choose an Email client:"));

					}
					break;
			}

			return true;
		}

	}

	/**
	 * Layout
	 */
	private void showGenerator() {

		RenzGenerator gen = new RenzGenerator(this);
		gen.setDismissListener(this);
		dialog = new AlertDialog.Builder(this).create();
		dialog.setView(gen);
		dialog.show();

	}

	@Override
	public void onDismiss(String payload)
	{
		payloadEdit.setText(payload);
		toastutil.showDefaultToast("Payload successfuly generated.");
		dialog.dismiss();
		// TODO: Implement this method
	}
	
	//for example ito code ng connect
	
	 
	 
	private void doLayout() {
		setContentView(R.layout.activity_main_drawer);
		
		
		toolbar_main = (Toolbar) findViewById(R.id.toolbar_main);
		mDrawerPanel.setDrawer(toolbar_main);
		setSupportActionBar(toolbar_main);
		mEditTextInput=(EditText)findViewById(R.id.time);

		mTextViewCountDown = (TextView) findViewById(R.id.duration);

		mButtonSet = (Button) findViewById(R.id.set);
		mButtonSet.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					String input = mEditTextInput.getText().toString();
					if (input.length() == 0) {
						toastutil.showConfusingToast("Field can't be empty");
						return;
					}
					long millisInput = Long.parseLong(input) * 1000;
					if (millisInput == 0) {
						toastutil.showDefaultToast("Please enter a positive number");
						return;
					}
					setTime(millisInput);
					mEditTextInput.setText("55");
				}
			});

		MobileAds.initialize(this, "ca-app-pub-3940256099942544/5224354917");
		mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
		mRewardedVideoAd.setRewardedVideoAdListener(this);


		loadRewardedVideoAd();

		mInterstitialAd = new InterstitialAd(this);
		mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
		mInterstitialAd.loadAd(new AdRequest.Builder().build());
		if (mInterstitialAd.isLoaded()) {
			mInterstitialAd.show();
		}

		interstitialAd = new InterstitialAd(SocksHttpMainActivity.this);
		interstitialAd.setAdUnitId((AD_UNIT_ID));
		AdRequest adRequest1 = new AdRequest.Builder().build();
		interstitialAd.loadAd(adRequest1);
		interstitialAd.setAdListener(new AdListener() {
				public void onAdLoaded() {
					if (interstitialAd.isLoaded()) {
						interstitialAd.show();
					}
				}
			});
			

		mButtonStartPause = (CheckBox) findViewById(R.id.start);
		mButtonStartPause.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (mTimerRunning) {
						//pauseTimer();
						mEditTextInput.setText("55");
					    pauseTimer();
						onoff.setText("OFF");
						SkStatus.logInfo("<font color='red'>Auto Reconnect is Stopping....</font>");
					} else {
						String input = mEditTextInput.getText().toString();

						if (input.length() == 0) {
							toastutil.showDefaultToast("Field can't be empty");
							return;
						}
						long millisInput = Long.parseLong(input) * 1000;
						if (millisInput == 0) {
							toastutil.showDefaultToast("Please enter a positive number");
							return;
						}
						setTime(millisInput);
						mEditTextInput.setText("");
						startTimer();
						onoff.setText("ON");
						SkStatus.logInfo("<font color='green'>Auto Reconnect is Starting....</font>");
					}
				}
			});

		mButtonReset = (Button) findViewById(R.id.reset);
		mButtonReset.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					resetTimer();
				}
			});
		
		
		
		// set ADS
		adsBannerView = (AdView) findViewById(R.id.adBannerMainView);
		
		if (!BuildConfig.DEBUG) {
			//adsBannerView.setAdUnitId(SocksHttpApp.ADS_UNITID_BANNER_MAIN);
		}
		
		if (TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
			adsBannerView.setAdListener(new AdListener() {
				@Override
				public void onAdLoaded() {
					if (adsBannerView != null) {
						adsBannerView.setVisibility(View.VISIBLE);
					}
				}
			});
			adsBannerView.loadAd(new AdRequest.Builder()
				.build());
		}
		LinearLayoutManager layoutManager = new LinearLayoutManager(this);
		mLogAdapter = new LogsAdapter(layoutManager,this);
		deleteLogs = (FloatingActionButton)findViewById(R.id.clearLog);
		logList = (RecyclerView) findViewById(R.id.recyclerLog);
		logList.setAdapter(mLogAdapter);
		logList.setLayoutManager(layoutManager);
		mLogAdapter.scrollToLastPosition();
		deleteLogs.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View p1)
				{
					mLogAdapter.clearLog();
					//SkStatus.logInfo("<font color='red'>Log Cleared!</font>");
							
					toastutil.showDefaultToast(getString(R.string.logs_cleared));
				
							// TODO: Implement this method
				}


			});
		view1 = findViewById(R.id.activity_mainSSHDirectRadioButton);
        view2 = findViewById(R.id.activity_mainSSHProxyRadioButton);
        view3 = findViewById(R.id.activity_mainSSLProxyRadioButton);
		view4 = findViewById(R.id.activity_mainSSLPayloadRadioButton);
		view20 = findViewById(R.id.activity_mainCustomPayloadSwitch);
		view21 = findViewById(R.id.activity_starterButtonMain);
		
		
		status = (TextView)findViewById(R.id.status);
		tunneltypeselected = (TextView)findViewById(R.id.tunneltypeselected);
		onoff = (TextView)findViewById(R.id.onoff);
		
		tunnel_dialog_layout = (RelativeLayout)findViewById(R.id.tunnel_dialog_layout);
		selected_tunnel_options = (TextView)findViewById(R.id.selected_tunneloptions);
		//payloadlock = (LinearLayout)findViewById(R.id.payloadlock);
		ViewGone = (CardView) findViewById(R.id.ViewGone);
		Viewgone = (CardView) findViewById(R.id.Viewgone);
		mainLayout = (LinearLayout) findViewById(R.id.activity_mainLinearLayout);
		loginLayout = (LinearLayout) findViewById(R.id.activity_mainInputPasswordLayout);
		starterButton = (Button) findViewById(R.id.activity_starterButtonMain);

		inputPwUser = (TextInputEditText) findViewById(R.id.activity_mainInputPasswordUserEdit);
		inputPwPass = (TextInputEditText) findViewById(R.id.activity_mainInputPasswordPassEdit);

		inputPwShowPass = (ImageButton) findViewById(R.id.activity_mainInputShowPassImageButton);

		((TextView) findViewById(R.id.activity_mainAutorText))
			.setOnClickListener(this);

		proxyInputLayout = (LinearLayout) findViewById(R.id.activity_mainInputProxyLayout);
		proxyText = (TextView) findViewById(R.id.activity_mainProxyText);

		/*Spinner spinnerTunnelType = (Spinner) findViewById(R.id.activity_mainTunnelTypeSpinner);
		String[] items = new String[]{"SSH DIRECT", "SSH + PROXY", "SSH + SSL (beta)"};
		//create an adapter to describe how the items are displayed, adapters are used in several places in android.
		//There are multiple variations of this, but this is the basic variant.
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
		//set the spinners adapter to the previously created one.
		spinnerTunnelType.setAdapter(adapter);*/
		cmodsniLayout = (LinearLayout) findViewById(R.id.cmods_snilayout);
		cmodsniText = (TextView) findViewById(R.id.cmods_snitext);
		cmodsniText.setText(mConfig.getPrivString(Settings.CUSTOM_SNI));
		//tunnelmode_layout = (RadioGroup)findViewById(R.id.tunnelmode_layout);
		metodoConexaoRadio = (RadioGroup) findViewById(R.id.activity_mainMetodoConexaoRadio);
		customPayloadSwitch = (CheckBox) findViewById(R.id.activity_mainCustomPayloadSwitch);

		starterButton.setOnClickListener(this);
		proxyInputLayout.setOnClickListener(this);

		payloadLayout = (LinearLayout) findViewById(R.id.activity_mainInputPayloadLinearLayout);
		payloadEdit = (TextInputEditText) findViewById(R.id.activity_mainInputPayloadEditText);

		configMsgLayout = (CardView) findViewById(R.id.activity_mainMensagemConfigLinearLayout);
		configMsgText = (TextView) findViewById(R.id.activity_mainMensagemConfigTextView);
		cmodsniLayout.setOnClickListener(new OnClickListener(){
				SharedPreferences prefs = mConfig.getPrefsPrivate();
				@Override
				public void onClick(View p1) {
					if (!prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
						doSaveData();

						DialogFragment fragSni = new CustomSniFragment();
						fragSni.show(getSupportFragmentManager(), "customSni");
					}
				}
			});
		// fix bugs
		if (mConfig.getPrefsPrivate().getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			if (mConfig.getPrefsPrivate().getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				inputPwUser.setText(mConfig.getPrivString(Settings.USUARIO_KEY));
				inputPwPass.setText(mConfig.getPrivString(Settings.SENHA_KEY));
			}
		}
		else {
			payloadEdit.setText(mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY));
		}

		//tunnelmode_layout.setOnCheckedChangeListener(this);
		metodoConexaoRadio.setOnCheckedChangeListener(this);
		customPayloadSwitch.setOnCheckedChangeListener(this);
		inputPwShowPass.setOnClickListener(this);
	}

	private void loadRewardedVideoAd() {
		mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
								new AdRequest.Builder().build());
	}
	
	private void doUpdateLayout() {
		SharedPreferences prefs = mConfig.getPrefsPrivate();

		boolean isRunning = SkStatus.isTunnelActive();
		int tunnelType = prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);

		setStarterButton(starterButton, this);
		setPayloadSwitch(tunnelType, !prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true));

		String proxyStr = getText(R.string.no_value).toString();

		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			proxyStr = "*******";
			proxyInputLayout.setEnabled(false);
			customPayloadSwitch.setEnabled(false);
			payloadLayout.setVisibility(View.VISIBLE);
			payloadLayout.setEnabled(false);
			tunnel_dialog_layout.setEnabled(false);
		}
		else {
			String proxy = mConfig.getPrivString(Settings.PROXY_IP_KEY);

			if (proxy != null && !proxy.isEmpty())
				proxyStr = String.format("%s:%s", proxy, mConfig.getPrivString(Settings.PROXY_PORTA_KEY));
			proxyInputLayout.setEnabled(!isRunning);
		} 

		proxyText.setText(proxyStr);
		String sniStr = getText(R.string.no_value).toString();


		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			sniStr = "*******";
			cmodsniLayout.setEnabled(false);
			payloadLayout.setVisibility(View.VISIBLE);
			payloadLayout.setEnabled(false);
			customPayloadSwitch.setEnabled(false);
			tunnel_dialog_layout.setEnabled(false);
		}
		else {
			String sni = mConfig.getPrivString(Settings.CUSTOM_SNI);

			if (sni != null && !sni.isEmpty())
				sniStr = String.format(mConfig.getPrivString(Settings.CUSTOM_SNI));
			cmodsniLayout.setEnabled(!isRunning);
		} 

		cmodsniText.setText(sniStr);
		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			sniStr = "*******";
			proxyStr = "*******";
			cmodsniLayout.setEnabled(false);
			payloadLayout.setVisibility(View.VISIBLE);
			payloadLayout.setEnabled(false);
			customPayloadSwitch.setEnabled(false);
			tunnel_dialog_layout.setEnabled(false);
		}
		else {
			String sni = mConfig.getPrivString(Settings.CUSTOM_SNI);

			if (sni != null && !sni.isEmpty())
				sniStr = String.format(mConfig.getPrivString(Settings.CUSTOM_SNI));
			cmodsniLayout.setEnabled(!isRunning);
			String proxy = mConfig.getPrivString(Settings.PROXY_IP_KEY);

			if (proxy != null && !proxy.isEmpty())
				proxyStr = String.format("%s:%s", proxy, mConfig.getPrivString(Settings.PROXY_PORTA_KEY));
			proxyInputLayout.setEnabled(!isRunning);
		}
		

		switch (tunnelType) {
			case Settings.bTUNNEL_TYPE_SSH_DIRECT:
				((RadioButton) findViewById(R.id.activity_mainSSHDirectRadioButton))
					.setChecked(true);
					tunneltypeselected.setText(" Direct SSH");
				break;

			case Settings.bTUNNEL_TYPE_SSH_PROXY:
				((RadioButton) findViewById(R.id.activity_mainSSHProxyRadioButton))
					.setChecked(true);
				tunneltypeselected.setText(" HTTP Proxy");
				break;

			/*case Settings.bTUNNEL_TYPE_SSL_TLS:
				((RadioButton) findViewById(R.id.activity_mainSSLProxyRadioButton))
					.setChecked(true);
				tunneltypeselected.setText("TLS/SSL");
				break;*/
			case Settings.bTUNNEL_TYPE_SSL_TLS:
				((RadioButton) findViewById(R.id.activity_mainSSLProxyRadioButton))
					.setChecked(true);
				tunneltypeselected.setText(" SSL/TLS");
				break;
			case Settings.bTUNNEL_TYPE_PAY_SSL:
				((AppCompatRadioButton) findViewById(R.id.activity_mainSSLPayloadRadioButton))
					.setChecked(true);
				//toolbar_main.setTitle("SSL Tunnel");
				tunneltypeselected.setText(" SSL/Payload");
				break;
		}

		int msgVisibility = View.GONE;
		int loginVisibility = View.GONE;
		String msgText = "";
		boolean enabled_radio = !isRunning;

		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {

			if (prefs.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				loginVisibility = View.VISIBLE;

				inputPwUser.setText(mConfig.getPrivString(Settings.USUARIO_KEY));
				inputPwPass.setText(mConfig.getPrivString(Settings.SENHA_KEY));

				inputPwUser.setEnabled(!isRunning);
				inputPwPass.setEnabled(!isRunning);
				inputPwShowPass.setEnabled(!isRunning);

				//inputPwPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}

			String msg = mConfig.getPrivString(Settings.CONFIG_MENSAGEM_KEY);
			if (!msg.isEmpty()) {
				msgText = msg.replace("\n", "<br/>");
				msgVisibility = View.VISIBLE;
			}

			if (mConfig.getPrivString(Settings.PROXY_IP_KEY).isEmpty() ||
				mConfig.getPrivString(Settings.PROXY_PORTA_KEY).isEmpty()) {
				enabled_radio = false;
			}
		}

		loginLayout.setVisibility(loginVisibility);
		configMsgText.setText(msgText.isEmpty() ? "" : Html.fromHtml(msgText));
		configMsgLayout.setVisibility(msgVisibility);

		// desativa/ativa radio group
		for (int i = 0; i < metodoConexaoRadio.getChildCount(); i++) {
			metodoConexaoRadio.getChildAt(i).setEnabled(enabled_radio);
		}
		/*for (int i = 0; i < tunnelmode_layout.getChildCount(); i++) {
			tunnelmode_layout.getChildAt(i).setEnabled(enabled_radio);
		}*/
		
	}
	
	
	private synchronized void doSaveData() {
		SharedPreferences prefs = mConfig.getPrefsPrivate();
		SharedPreferences.Editor edit = prefs.edit();

		if (mainLayout != null && !isFinishing())
			mainLayout.requestFocus();
		
		if (!prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			if (payloadEdit != null && !prefs.getBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true)) {
				edit.putString(Settings.CUSTOM_PAYLOAD_KEY, payloadEdit.getText().toString());
			}
		}
		else {
			if (prefs.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				edit.putString(Settings.USUARIO_KEY, inputPwUser.getEditableText().toString());
				edit.putString(Settings.SENHA_KEY, inputPwPass.getEditableText().toString());
			}
		}

		edit.apply();
	}

	private void pauseTimer() {
		mCountDownTimer.cancel();
		mTimerRunning = false;
		updateWatchInterface();
	}
	private void resetTimer() {
		mTimeLeftInMillis = mStartTimeInMillis;
		updateCountDownText();
		updateWatchInterface();
	}

	private void updateWatchInterface() {
		if (mTimerRunning) {
			mEditTextInput.setVisibility(View.GONE);
			mButtonSet.setVisibility(View.GONE);
			mButtonReset.setVisibility(View.GONE);
			mButtonStartPause.setText("Auto Reconnect");
		} else {
			mEditTextInput.setVisibility(View.GONE);
			mButtonSet.setVisibility(View.GONE);
			mButtonStartPause.setText("Auto Reconnect");
			if (mTimeLeftInMillis < 1000) {
				mButtonStartPause.setVisibility(View.INVISIBLE);
			} else {
				mButtonStartPause.setVisibility(View.VISIBLE);
			}
			if (mTimeLeftInMillis < mStartTimeInMillis) {
				mButtonReset.setVisibility(View.GONE);
			} else {
				mButtonReset.setVisibility(View.GONE);
			}
		}
	}


	private void updateCountDownText() {
		int hours = (int) (mTimeLeftInMillis / 1000) / 3600;
		int minutes = (int) ((mTimeLeftInMillis / 1000) % 3600) / 60;
		int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
		String timeLeftFormatted;
		if (hours > 0) {
			timeLeftFormatted = String.format(Locale.getDefault(),
											  "%d:%02d:%02d", hours, minutes, seconds);
		} else {
			timeLeftFormatted = String.format(Locale.getDefault(),
											  "%02d:%02d", minutes, seconds);
		}
		mTextViewCountDown.setText(timeLeftFormatted);
	}

	private void setTime(long milliseconds) {
		mStartTimeInMillis = milliseconds;
		resetTimer();

	}

	private void startTimer() {
		mEndTime = System.currentTimeMillis() + mTimeLeftInMillis;
		mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {


			@Override
			public void onTick(long millisUntilFinished) {
				mTimeLeftInMillis = millisUntilFinished;
				updateCountDownText();
			}
			@Override
			public void onFinish() {
				mTimerRunning = false;
				updateWatchInterface();
				resetTimer();
				startTimer();
				Toast.makeText(SocksHttpMainActivity.this, "Reconnecting please wait...", Toast.LENGTH_SHORT).show();
				mLogAdapter.clearLog();
				Intent reconTunnel = new Intent(SocksHttpService.TUNNEL_SSH_RESTART_SERVICE);
				LocalBroadcastManager.getInstance(SocksHttpMainActivity.this)
					.sendBroadcast(reconTunnel);

				//just a test👇👇
				//starterButton.performClick();
				// i dont know what to do so i test that 0ne
				// build by renz mortel your bf
				//try this 2 👇👇👇

				///startOrStopTunnel(this);

				//context.startService(startVPN);
			}
		}.start();
		mTimerRunning = true;
		updateWatchInterface();
	}
	

	/**
	 * Tunnel SSH
	 */

	public void startOrStopTunnel(AppCompatActivity activity) {
		if (SkStatus.isTunnelActive()) {
			TunnelManagerHelper.stopSocksHttp(activity);
			//mLogAdapter.clearLog();
		}
		else {
			// oculta teclado se vísivel, tá com bug, tela verde
			//Utils.hideKeyboard(activity);
			
			Settings config = new Settings(activity);
			
			if (config.getPrefsPrivate()
					.getBoolean(Settings.CONFIG_INPUT_PASSWORD_KEY, false)) {
				if (inputPwUser.getText().toString().isEmpty() || 
						inputPwPass.getText().toString().isEmpty()) {
					
						toastutil.showErrorToast(getString(R.string.error_userpass_empty));
					return;
				}
			}
			
			Intent intent = new Intent(activity, LaunchVpn.class);
			intent.setAction(Intent.ACTION_MAIN);
			vp.setCurrentItem(1);
			if (config.getHideLog()) {
				intent.putExtra(LaunchVpn.EXTRA_HIDELOG, true);
			}
			
			activity.startActivity(intent);
		}
	}

	private void setPayloadSwitch(int tunnelType, boolean isCustomPayload) {
		SharedPreferences prefs = mConfig.getPrefsPrivate();

		boolean isRunning = SkStatus.isTunnelActive();

		customPayloadSwitch.setChecked(isCustomPayload);

		if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
			payloadEdit.setEnabled(false);

			if (mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY).isEmpty()) {
				customPayloadSwitch.setEnabled(false);
			}
			else {
				customPayloadSwitch.setEnabled(!isRunning);
				tunnel_dialog_layout.setEnabled(!isRunning);
			}

			if (!isCustomPayload && tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY)
				payloadEdit.setText(Settings.PAYLOAD_DEFAULT);
			else
				payloadEdit.setText("*******");
			//payloadlock.setVisibility(8);
			Viewgone.setVisibility(8);
		}
		else {
			Viewgone.setVisibility(0);
			//payloadlock.setVisibility(0);
			customPayloadSwitch.setEnabled(!isRunning);
			tunnel_dialog_layout.setEnabled(!isRunning);
			if (isCustomPayload) {
				payloadEdit.setText(mConfig.getPrivString(Settings.CUSTOM_PAYLOAD_KEY));
				payloadEdit.setEnabled(!isRunning);
			}
			else if (tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY) {
				payloadEdit.setText(Settings.PAYLOAD_DEFAULT);
				payloadEdit.setEnabled(false);
			}
		}

		if (isCustomPayload || tunnelType == Settings.bTUNNEL_TYPE_SSH_PROXY) {
			payloadLayout.setVisibility(View.VISIBLE);
			Viewgone.setVisibility(View.VISIBLE);
		}
		else {
			payloadLayout.setVisibility(View.GONE);
			Viewgone.setVisibility(View.GONE);
		}
	}

	public void setStarterButton(Button starterButton, AppCompatActivity activity) {
		String state = SkStatus.getLastState();
		boolean isRunning = SkStatus.isTunnelActive();

		if (starterButton != null) {
			int resId;
			
			SharedPreferences prefsPrivate = new Settings(activity).getPrefsPrivate();

			if (ConfigParser.isValidadeExpirou(prefsPrivate
					.getLong(Settings.CONFIG_VALIDADE_KEY, 0))) {
				resId = R.string.expired;
				starterButton.setEnabled(false);

				if (isRunning) {
					startOrStopTunnel(activity);
				}
			}
			else if (prefsPrivate.getBoolean(Settings.BLOQUEAR_ROOT_KEY, false) &&
					ConfigParser.isDeviceRooted(activity)) {
			   resId = R.string.blocked;
			   starterButton.setEnabled(false);
			   
			   
			   toastutil.showErrorToast(getString(R.string.error_root_detected));

			   if (isRunning) {
				   startOrStopTunnel(activity);
			   }
			}
			else if (SkStatus.SSH_INICIANDO.equals(state)) {
				resId = R.string.stop;
				starterButton.setEnabled(false);
			}
			else if (SkStatus.SSH_PARANDO.equals(state)) {
				resId = R.string.state_stopping;
				starterButton.setEnabled(false);
			}
			else {
				resId = isRunning ? R.string.stop : R.string.start;
				starterButton.setEnabled(true);
			}

			starterButton.setText(resId);
		}
	}
	

	
	@Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        if (mDrawerPanel.getToogle() != null)
			mDrawerPanel.getToogle().syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mDrawerPanel.getToogle() != null)
			mDrawerPanel.getToogle().onConfigurationChanged(newConfig);
    }
	
	private boolean isMostrarSenha = false;
	
	@Override
	public void onClick(View p1)
	{
		SharedPreferences prefs = mConfig.getPrefsPrivate();

		switch (p1.getId()) {
			case R.id.activity_starterButtonMain:
				doSaveData();
				startOrStopTunnel(this);
				break;

			case R.id.activity_mainInputProxyLayout:
				if (!prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
					doSaveData();

					DialogFragment fragProxy = new ProxyRemoteDialogFragment();
					fragProxy.show(getSupportFragmentManager(), "proxyDialog");
				}
				break;

			case R.id.activity_mainAutorText:
				String url = "http://t.me/SlipkProjects";
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(Intent.createChooser(intent, getText(R.string.open_with)));
				break;
				
			case R.id.activity_mainInputShowPassImageButton:
				isMostrarSenha = !isMostrarSenha;
				if (isMostrarSenha) {
					inputPwPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
					inputPwShowPass.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_visibility_black_24dp));
				}
				else {
					inputPwPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
					inputPwShowPass.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_visibility_off_black_24dp));
				}
			break;
		}
	}

	@Override
	public void onCheckedChanged(RadioGroup p1, int p2)
	{
		SharedPreferences.Editor edit = mConfig.getPrefsPrivate().edit();

		switch (p1.getCheckedRadioButtonId()) {
			case R.id.activity_mainSSHDirectRadioButton:
				selected_tunnel_options.setText("Direct SSH");
				ViewGone.setVisibility(View.GONE);
				cmodsniLayout.setVisibility(View.GONE);
				customPayloadSwitch.setChecked(false);
				customPayloadSwitch.setClickable(true);
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT);
				proxyInputLayout.setVisibility(View.GONE);
				break;

			case R.id.activity_mainSSHProxyRadioButton:
				selected_tunnel_options.setText("HTTP Proxy ➔ SSH");
				ViewGone.setVisibility(View.GONE);
				cmodsniLayout.setVisibility(View.GONE);
				customPayloadSwitch.setChecked(false);
				customPayloadSwitch.setClickable(true);
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY);
				proxyInputLayout.setVisibility(View.VISIBLE);
				break;
			case R.id.activity_mainSSLProxyRadioButton:
				selected_tunnel_options.setText("SSL/TLS ➔ SSH");
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSL_TLS);
				edit.putString(Settings.CUSTOM_PAYLOAD_KEY, cmodsniText.getText().toString());
				proxyInputLayout.setVisibility(View.GONE);
				cmodsniLayout.setVisibility(View.VISIBLE);
				ViewGone.setVisibility(View.VISIBLE);
				customPayloadSwitch.setClickable(false);
				customPayloadSwitch.setChecked(false);
				edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, true).apply();
				//edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_PROXY);
				//payloadLayout.setVisibility(View.VISIBLE);
				break;
			case R.id.activity_mainSSLPayloadRadioButton:
				edit.putInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_PAY_SSL);
				customPayloadSwitch.setClickable(false);
				customPayloadSwitch.setChecked(true);
				payloadLayout.setVisibility(View.VISIBLE);
				cmodsniLayout.setVisibility(View.VISIBLE);
				ViewGone.setVisibility(View.VISIBLE);
				proxyInputLayout.setVisibility(View.GONE);
				tunneltypeselected.setText("SSL + Payload ➔ SSH");
				break;
		}

		edit.apply();

		doSaveData();
		doUpdateLayout();
	}

	@Override
	public void onCheckedChanged(CompoundButton p1, boolean p2)
	{
		SharedPreferences prefs = mConfig.getPrefsPrivate();
		SharedPreferences.Editor edit = prefs.edit();

		switch (p1.getId()) {
			case R.id.activity_mainCustomPayloadSwitch:
				edit.putBoolean(Settings.PROXY_USAR_DEFAULT_PAYLOAD, !p2);
				setPayloadSwitch(prefs.getInt(Settings.TUNNELTYPE_KEY, Settings.bTUNNEL_TYPE_SSH_DIRECT), p2);
				break;
		}

		edit.apply();

		doSaveData();
	}
	
	protected void showBoasVindas() {
		new AlertDialog.Builder(this)
            . setTitle(R.string.attention)
            . setMessage(APP)
			. setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface di, int p) {
					// ok
				}
			})
			. setCancelable(false)
            . show();
	}
	
	@Override
	public void updateState(final String state, String msg, int localizedResId, final ConnectionStatus level, Intent intent)
	{
		mHandler.post(new Runnable() {
				@Override
				public void run() {
					doUpdateLayout();
					if (SkStatus.isTunnelActive()){

						if (level.equals(ConnectionStatus.LEVEL_CONNECTED)){
							status.setText(R.string.connected);
								toastutil.showSuccessToast(getString(R.string.state_connected));
								
							
						}

						if (level.equals(ConnectionStatus.LEVEL_NOTCONNECTED)){
							status.setText(R.string.servicestop);
						}	

						if (level.equals(ConnectionStatus.LEVEL_CONNECTING_SERVER_REPLIED)){
							status.setText(R.string.authenticating);
						}		

						if (level.equals(ConnectionStatus.LEVEL_CONNECTING_NO_SERVER_REPLY_YET)){
							status.setText(R.string.connecting);
						}			
						if (level.equals(ConnectionStatus.LEVEL_AUTH_FAILED)){
							status.setText(R.string.authfailed);
						}					
						if (level.equals(ConnectionStatus.UNKNOWN_LEVEL)){
							status.setText(R.string.disconnected);
							toastutil.showConfusingToast(getString(R.string.state_disconnected));
						}				
						//if (level.equals(ConnectionStatus.LEVEL_RECONNECTING)){
						//		status.setText(R.string.reconnecting);
					}				
					if (level.equals(ConnectionStatus.LEVEL_NONETWORK)){
						status.setText(R.string.nonetwork);
						toastutil.showDefaultToast(getString(R.string.nonetwork));
					}			
				}});
		
		switch (state) {
			case SkStatus.SSH_CONECTADO:
				// carrega ads banner
				if (adsBannerView != null && TunnelUtils.isNetworkOnline(SocksHttpMainActivity.this)) {
					adsBannerView.setAdListener(new AdListener() {
						@Override
						public void onAdLoaded() {
							if (adsBannerView != null && !isFinishing()) {
								adsBannerView.setVisibility(View.VISIBLE);
							}
						}
					});
					adsBannerView.postDelayed(new Runnable() {
						@Override
						public void run() {
							// carrega ads interestitial
							AdsManager.newInstance(getApplicationContext())
								.loadAdsInterstitial();
							// ads banner
							if (adsBannerView != null && !isFinishing()) {
								adsBannerView.loadAd(new AdRequest.Builder()
									.build());
							}
						}
					}, 5000);
				}
			break;
		}
	}


	/**
	 * Recebe locais Broadcast
	 */

	private BroadcastReceiver mActivityReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action == null)
                return;

            if (action.equals(UPDATE_VIEWS) && !isFinishing()) {
				doUpdateLayout();
			}
			else if (action.equals(OPEN_LOGS)) {
				
			}
        }
    };


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerPanel.getToogle() != null && mDrawerPanel.getToogle().onOptionsItemSelected(item)) {
            return true;
        }

		// Menu Itens
		switch (item.getItemId()) {

			case R.id.miLimparConfig:
				if (!SkStatus.isTunnelActive()) {
					DialogFragment dialog = new ClearConfigDialogFragment();
					dialog.show(getSupportFragmentManager(), "alertClearConf");
				} else {
					
					toastutil.showDefaultToast(getString(R.string.error_tunnel_service_execution));
				}
				break;
				
			case R.id.guide:
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				} else {
				guides();
				}
				break;
				
			/*case R.id.tunneloptions:
				tunnel_options();
				break;*/
				
			case R.id.miSettings:
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				} else {
					Intent intentSettings = new Intent(this, ConfigGeralActivity.class);
					//intentSettings.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(intentSettings);
				}
				break;

			case R.id.miSettingImportar:
				if (mRewardedVideoAd.isLoaded()) {
					mRewardedVideoAd.show();
				} else {
					if (mInterstitialAd.isLoaded()) {
						mInterstitialAd.show();

					} else {
						Intent intentImport = new Intent(this, ConfigImportFileActivity.class);
						startActivity(intentImport);
					}
				}

				break;

			case R.id.miSettingExportar:
				if (mInterstitialAd.isLoaded()) {
					mInterstitialAd.show();
				} else {
					SharedPreferences prefs = mConfig.getPrefsPrivate();

					if (prefs.getBoolean(Settings.CONFIG_PROTEGER_KEY, false)) {
						toastutil.showErrorToast(getString(R.string.error_settings_blocked));
						
					}
					else {
						Intent intentExport = new Intent(this, ConfigExportFileActivity.class);
						startActivity(intentExport);
				    }
				}
				break;

				// logs opções
			case R.id.miLimparLogs:
				break;
				
			case R.id.miExit:
				if (Build.VERSION.SDK_INT >= 16) {
					finishAffinity();
				}
				
				System.exit(0);
			break;
		}

		return super.onOptionsItemSelected(item);
	}

	

	@Override
    public void onResume() {
        super.onResume();

		
		
		//doSaveData();
		//doUpdateLayout();
		
		SkStatus.addStateListener(this);
		
		if (adsBannerView != null) {
			adsBannerView.resume();
		}
    }

	@Override
	protected void onPause()
	{
		super.onPause();
		
		doSaveData();
		
		SkStatus.removeStateListener(this);
		
		if (adsBannerView != null) {
			adsBannerView.pause();
		}
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		
		

		LocalBroadcastManager.getInstance(this)
			.unregisterReceiver(mActivityReceiver);
			
		if (adsBannerView != null) {
			adsBannerView.destroy();
		}
	}


	/**
	 * DrawerLayout Listener
	 */

	@Override
	public void onDrawerOpened(View view) {
		
	}

	@Override
	public void onDrawerClosed(View view) {
		
	}

	@Override
	public void onDrawerStateChanged(int stateId) {}
	@Override
	public void onDrawerSlide(View view, float p2) {}

	
	/**
	 * Utils
	 */

	public static void updateMainViews(Context context) {
		Intent updateView = new Intent(UPDATE_VIEWS);
		LocalBroadcastManager.getInstance(context)
			.sendBroadcast(updateView);
	}
	
	public void showExitDialog() {
		AlertDialog dialog = new AlertDialog.Builder(this).
			create();
		dialog.setTitle(getString(R.string.attention));
		dialog.setMessage(getString(R.string.alert_exit));

		dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.
				string.exit),
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					Utils.exitAll(SocksHttpMainActivity.this);
				}
			}
		);

		dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.
				string.minimize),
			new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// minimiza app
					Intent startMain = new Intent(Intent.ACTION_MAIN);
					startMain.addCategory(Intent.CATEGORY_HOME);
					startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(startMain);
				}
			}
		);

		dialog.show();
	}
}

