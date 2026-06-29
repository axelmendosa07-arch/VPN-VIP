package com.slipkprojects.sockshttp.activities;

import androidx.appcompat.widget.Toolbar;
import android.view.View.OnClickListener;
import android.os.Bundle;
import com.slipkprojects.sockshttp.R;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import android.widget.TextView;
import android.text.Html;
import android.content.Intent;
import android.net.Uri;
import android.content.pm.PackageInfo;
import com.slipkprojects.sockshttp.util.Utils;
import com.google.android.gms.ads.AdView;
import com.slipkprojects.sockshttp.SocksHttpApp;
import com.slipkprojects.ultrasshservice.tunnel.TunnelUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;

public class AboutActivity extends BaseActivity implements OnClickListener {

	private Toolbar tb;
	private View changelog, license, dev;
	private AlertDialog.Builder ab;
    private TextView app_info_text;

    private AdView adsBannerView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO: Implement this method
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		tb = (Toolbar) findViewById(R.id.toolbar_main);
		setSupportActionBar(tb);
		changelog = findViewById(R.id.changelog);
		license = findViewById(R.id.license);
		dev = findViewById(R.id.developer);
		changelog.setOnClickListener(this);
		license.setOnClickListener(this);
		dev.setOnClickListener(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PackageInfo pinfo = Utils.getAppInfo(this);
        if (pinfo != null) {
            String version_nome = pinfo.versionName;
            int version_code = pinfo.versionCode;
            String header_text = String.format("%s (%d)", version_nome, version_code);
            app_info_text = (TextView) findViewById(R.id.appVersion);
			app_info_text.setText(header_text);
		}
        adsBannerView = (AdView) findViewById(R.id.adBannerMainView2);
        if (!SocksHttpApp.DEBUG) {
            adsBannerView.setAdUnitId(SocksHttpApp.ADS_UNITID_BANNER_SOBRE);
		}
        if (TunnelUtils.isNetworkOnline(this)) {

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
	}
	@Override
	public void onClick(View view) {
		// TODO: Implement this method
		int id = view.getId();
		if (id == R.id.changelog) {
			changelog();
		} else if (id == R.id.license) {
			license();
		} else if (id == R.id.developer) {
			startActivity(new Intent("android.intent.action.VIEW", 
									 Uri.parse("https://t.me/AlfSilic")));
		}
	}

	private void changelog() {
		// TODO: Implement this method
		ab = new AlertDialog.Builder(this);
		ab.setTitle("Atention!");
		ab.setMessage(Html.fromHtml(" • New version released <br> • Let us know when you find a bug"));
		ab.setPositiveButton(android.R.string.ok, null);
		ab.create().show();
	}

	private void license() {
		// TODO: Implement this method
        startActivity(new Intent(this, LicenseActivity.class));
	}
}
