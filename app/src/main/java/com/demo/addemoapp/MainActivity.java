package com.demo.addemoapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.ihs.app.framework.activity.HSAppCompatActivity;

import net.appcloudbox.ads.base.AcbInterstitialAd;
import net.appcloudbox.ads.common.utils.AcbError;
import net.appcloudbox.ads.interstitialad.AcbInterstitialAdLoader;
import net.appcloudbox.ads.interstitialad.AcbInterstitialAdManager;

import java.util.List;

import timber.log.Timber;

public class MainActivity extends HSAppCompatActivity {
    public static String INTERSTITIAL_AD_PLACEMENT_DRAGON = "Dragon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Timber.e("Click...");
                showInterstitial2();
            }
        });

//        AcbInterstitialAdManager.getInstance().activePlacementInProcess(INTERSTITIAL_AD_PLACEMENT_DRAGON);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private void showInterstitial() {
        String currentPlacementName = INTERSTITIAL_AD_PLACEMENT_DRAGON;
//        int adCount = AcbInterstitialAdManager.getInstance().getAdsCountInPlacement(currentPlacementName);
//        if (adCount > 0) {
            List<AcbInterstitialAd> adList = AcbInterstitialAdManager.getInstance().fetch(currentPlacementName, 1);
            if (adList.size() > 0) {
                final AcbInterstitialAd acbInterstitialAd = adList.get(0);
                acbInterstitialAd.setInterstitialAdListener(new AcbInterstitialAd.IAcbInterstitialAdListener() {
                    @Override
                    public void onAdDisplayed() {
                        Timber.v("InterstitialAds onAdDisplayed");
                    }

                    @Override
                    public void onAdClicked() {
                        Timber.v("InterstitialAds onAdClicked");
                    }

                    @Override
                    public void onAdClosed() {
                        Timber.v("InterstitialAds onAdClosed");
                    }

                    @Override
                    public void onAdDisplayFailed(AcbError acbError) {
                        Timber.v("InterstitialAds onAdDisplayFailed");
                    }
                });
                acbInterstitialAd.show(this, currentPlacementName, true);
            } else {
                Timber.e("InterstitialAds size: %d", adList.size());
            }
//        } else {
//            Timber.e("InterstitialAds adCount: %d", adCount);
//        }
    }
    
    private void showInterstitial2() {
        AcbInterstitialAdLoader loader = AcbInterstitialAdManager.getInstance().createLoaderWithPlacement(INTERSTITIAL_AD_PLACEMENT_DRAGON);
        loader.load(1, new AcbInterstitialAdLoader.AcbInterstitialAdLoadListener() {
            @Override
            public void onAdReceived(AcbInterstitialAdLoader acbInterstitialAdLoader, List<AcbInterstitialAd> list) {
                if (list != null) {
                    Timber.e("onAdReceived, list size: %d", list.size());
                    if (list.size() > 0) {
                        AcbInterstitialAd acbInterstitialAd = list.get(0);
                        acbInterstitialAd.show(MainActivity.this, INTERSTITIAL_AD_PLACEMENT_DRAGON);
                        acbInterstitialAd.setInterstitialAdListener(new AcbInterstitialAd.IAcbInterstitialAdListener() {
                            @Override
                            public void onAdDisplayed() {
                                Timber.e("onAdDisplayed : ");
                            }

                            @Override
                            public void onAdClicked() {
                                Timber.e("onAdClicked : ");
                            }

                            @Override
                            public void onAdClosed() {
                                Timber.e("onAdClosed : ");
                            }

                            @Override
                            public void onAdDisplayFailed(AcbError acbError) {
                                Timber.e("onAdDisplayFailed : ");
                            }
                        });
                    }
                } else {
                    Timber.e("onAdReceived, list is null");
                }
            }

            @Override
            public void onAdFinished(AcbInterstitialAdLoader acbInterstitialAdLoader, AcbError acbError) {
                if (acbError != null) {
                    Timber.e("onAdFinished : %s", acbError.toString());
                } else {
                    Timber.e("onAdFinished finish");
                }
            }
        });
    }
}
