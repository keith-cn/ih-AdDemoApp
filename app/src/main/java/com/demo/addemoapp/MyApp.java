package com.demo.addemoapp;

import android.content.Context;
import android.support.multidex.MultiDex;

import com.ihs.app.framework.HSApplication;

import net.appcloudbox.AcbAds;
import net.appcloudbox.ads.interstitialad.AcbInterstitialAdManager;

import timber.log.Timber;

public class MyApp extends HSApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new MyDebugTree());

        AcbAds.getInstance().initializeFromGoldenEye(this, new AcbAds.GoldenEyeInitListener() {
            @Override
            public void onInitialized() {
                Timber.e("InterstitialAds onInitialized");
//                // Interstitial 广告初始化
//                initInterstitialAd();
//                // Express 广告初始化
//                initExpressAd();
//                // Reward 广告初始化
//                initRewardAd();

                AcbInterstitialAdManager.getInstance().activePlacementInProcess(MainActivity.INTERSTITIAL_AD_PLACEMENT_DRAGON);
            }
        });
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
