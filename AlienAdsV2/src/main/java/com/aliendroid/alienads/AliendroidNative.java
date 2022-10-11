package com.aliendroid.alienads;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.config.AppLovinCustomEventBanner;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesAdmob;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesAlien;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesApplovinMax;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesFacebook;
import com.aliendroid.alienads.interfaces.natives.OnLoadMediumNativesStartApp;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesAdmob;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesAlien;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesApplovinMax;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesFacebook;
import com.aliendroid.alienads.interfaces.natives.OnLoadSmallNativesStartApp;
import com.applovin.adview.AppLovinAdView;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdRevenueListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.nativeAds.MaxNativeAdListener;
import com.applovin.mediation.nativeAds.MaxNativeAdLoader;
import com.applovin.mediation.nativeAds.MaxNativeAdView;
import com.applovin.mediation.nativeAds.MaxNativeAdViewBinder;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.nativead.MediaView;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;
import com.google.android.gms.ads.nativead.NativeAdView;
import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;

public class AliendroidNative {
    public static AppLovinAdView adViewDiscovery;
    public static IronSourceBannerLayout adViewIron;
    public static OnLoadSmallNativesAdmob onLoadSmallNativesAdmob;
    public static OnLoadSmallNativesApplovinMax onLoadSmallNativesApplovinMax;
    public static OnLoadSmallNativesFacebook onLoadSmallNativesFacebook;
    public static OnLoadSmallNativesStartApp onLoadSmallNativesStartApp;
    public static OnLoadSmallNativesAlien onLoadSmallNativesAlien;
    public static OnLoadMediumNativesAdmob onLoadMediumNativesAdmob;
    public static OnLoadMediumNativesApplovinMax onLoadMediumNativesApplovinMax;
    public static OnLoadMediumNativesFacebook onLoadMediumNativesFacebook;
    public static OnLoadMediumNativesStartApp onLoadMediumNativesStartApp;
    public static OnLoadMediumNativesAlien onLoadMediumNativesAlien;
    private static NativeAd nativeAd;
    private static MaxNativeAdLoader nativeAdLoader;
    private static MaxNativeAdView nativeAdView;
    private static MaxAd nativeAdMax;

    public static void SmallNativeAdmob(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup, String Hpk1,
                                        String Hpk2, String Hpk3, String Hpk4, String Hpk5) {
        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadSmallNativesAdmob != null) {
                    onLoadSmallNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":
                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        break;
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_small_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }

        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();
        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();
        builder.withNativeAdOptions(adOptions);
        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadSmallNativesAdmob != null) {
                                            onLoadSmallNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {
                                            case "APPLOVIN-M":
                                                MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                                                        .setTitleTextViewId(R.id.title_text_view)
                                                        .setBodyTextViewId(R.id.body_text_view)
                                                        .setAdvertiserTextViewId(R.id.advertiser_textView)
                                                        .setIconImageViewId(R.id.icon_image_view)
                                                        .setMediaContentViewGroupId(R.id.media_view_container)
                                                        .setOptionsContentViewGroupId(R.id.ad_options_view)
                                                        .setCallToActionButtonId(R.id.cta_button)
                                                        .build();
                                                nativeAdView = new MaxNativeAdView(binder, activity);

                                                nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                                                nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                                                    @Override
                                                    public void onAdRevenuePaid(MaxAd ad) {

                                                    }
                                                });
                                                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                                        if (nativeAd != null) {
                                                            nativeAd.destroy();
                                                        }
                                                        if (nativeAdMax != null) {
                                                            nativeAdLoader.destroy(nativeAdMax);
                                                        }

                                                        nativeAdMax = ad;
                                                        layNative.removeAllViews();
                                                        layNative.addView(nativeAdView);
                                                    }

                                                    @Override
                                                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                                                    }

                                                    @Override
                                                    public void onNativeAdClicked(final MaxAd ad) {

                                                    }
                                                });

                                                nativeAdLoader.loadAd(nativeAdView);
                                                break;
                                            case "MOPUB":
                                            case "UNITY":

                                                break;
                                            case "IRON":
                                                adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                                                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                        FrameLayout.LayoutParams.WRAP_CONTENT);
                                                layNative.addView(adViewIron, 0, layoutParams);
                                                IronSource.loadBanner(adViewIron, idNativeBackup);
                                                break;
                                            case "STARTAPP":
                                                break;
                                            case "APPLOVIN-D":
                                                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                                                Bundle bannerExtras = new Bundle();
                                                bannerExtras.putString("zone_id", idNativeBackup);
                                                builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                                                boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                                                AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                                                adViewDiscovery = new AppLovinAdView(adSize, activity);
                                                layNative.addView(adViewDiscovery);
                                                adViewDiscovery.loadNextAd();
                                                break;
                                            case "FACEBOOK":
                                                break;
                                            case "ALIEN-M":
                                                break;
                                            case "ALIEN-V":
                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);

    }

    public static void SmallNativeMax(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_small_native)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_textView)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build();
        nativeAdView = new MaxNativeAdView(binder, activity);

        nativeAdLoader = new MaxNativeAdLoader(nativeId, activity);
        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
            @Override
            public void onAdRevenuePaid(MaxAd ad) {

            }
        });
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (onLoadSmallNativesApplovinMax != null) {
                    onLoadSmallNativesApplovinMax.onNativeAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        break;
                }

                if (nativeAdMax != null) {
                    nativeAdLoader.destroy(nativeAdMax);
                }
                nativeAdMax = ad;
                layNative.removeAllViews();
                layNative.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                if (onLoadSmallNativesApplovinMax != null) {
                    onLoadSmallNativesApplovinMax.onNativeAdLoadFailed("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        AdLoader.Builder builder2 = new AdLoader.Builder(activity, idNativeBackup);
                        builder2.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {

                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }

                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_small_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }

                        });
                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder2.withNativeAdOptions(adOptions);

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder2
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.BANNER);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        boolean isTablet2 = AppLovinSdkUtils.isTablet(activity);
                        AppLovinAdSize adSize = isTablet2 ? AppLovinAdSize.LEADER : AppLovinAdSize.BANNER;
                        adViewDiscovery = new AppLovinAdView(adSize, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "FACEBOOK":
                        break;
                    case "ALIEN-M":
                        break;
                    case "ALIEN-V":
                        break;
                }
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                if (onLoadSmallNativesApplovinMax != null) {
                    onLoadSmallNativesApplovinMax.onNativeAdClicked();
                }
            }
        });

        nativeAdLoader.loadAd(nativeAdView);
    }


    public static void SmallNativeFan(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

    }


    public static void SmallNativeStartApp(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
    }

    public static void MediumNativeStartApp(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

    }

    public static void MediumNativeAdmob(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup, String Hpk1,
                                         String Hpk2, String Hpk3, String Hpk4, String Hpk5) {

        AdLoader.Builder builder = new AdLoader.Builder(activity, nativeId);
        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
            @Override
            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                if (onLoadMediumNativesAdmob != null) {
                    onLoadMediumNativesAdmob.onNativeAdLoaded();
                }
                if (nativeAd != null) {
                    nativeAd.destroy();
                }
                switch (selectAdsBackup) {
                    case "APPLOVIN-M":
                        if (nativeAdMax != null) {
                            nativeAdLoader.destroy(nativeAdMax);
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":
                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        break;
                }
                nativeAd = nativeAds;
                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                        .inflate(R.layout.admob_big_native, null);
                populateNativeAdView(nativeAds, adView);
                layNative.removeAllViews();
                layNative.addView(adView);
            }


        });

        VideoOptions videoOptions = new VideoOptions.Builder()
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);


        AdRequest request = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5)
                .build();
        AdLoader adLoader =
                builder
                        .withAdListener(
                                new AdListener() {
                                    @Override
                                    public void onAdFailedToLoad(LoadAdError loadAdError) {
                                        if (onLoadMediumNativesAdmob != null) {
                                            onLoadMediumNativesAdmob.onAdFailedToLoad("");
                                        }
                                        switch (selectAdsBackup) {

                                            case "APPLOVIN-M": {
                                                MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                                                        .setTitleTextViewId(R.id.title_text_view)
                                                        .setBodyTextViewId(R.id.body_text_view)
                                                        .setAdvertiserTextViewId(R.id.advertiser_textView)
                                                        .setIconImageViewId(R.id.icon_image_view)
                                                        .setMediaContentViewGroupId(R.id.media_view_container)
                                                        .setOptionsContentViewGroupId(R.id.ad_options_view)
                                                        .setCallToActionButtonId(R.id.cta_button)
                                                        .build();
                                                nativeAdView = new MaxNativeAdView(binder, activity);

                                                nativeAdLoader = new MaxNativeAdLoader(idNativeBackup, activity);
                                                nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
                                                    @Override
                                                    public void onAdRevenuePaid(MaxAd ad) {

                                                    }
                                                });
                                                nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
                                                    @Override
                                                    public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {

                                                        if (nativeAd != null) {
                                                            nativeAd.destroy();
                                                        }
                                                        // Cleanup any pre-existing native ad to prevent memory leaks.
                                                        if (nativeAdMax != null) {
                                                            nativeAdLoader.destroy(nativeAdMax);
                                                        }

                                                        // Save ad for cleanup.
                                                        nativeAdMax = ad;

                                                        // Add ad view to view.
                                                        layNative.removeAllViews();
                                                        layNative.addView(nativeAdView);
                                                    }

                                                    @Override
                                                    public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {

                                                    }

                                                    @Override
                                                    public void onNativeAdClicked(final MaxAd ad) {

                                                    }
                                                });

                                                nativeAdLoader.loadAd(nativeAdView);
                                                break;
                                            }
                                            case "MOPUB":
                                            case "UNITY":

                                                break;
                                            case "IRON":
                                                adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                                                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                                        FrameLayout.LayoutParams.WRAP_CONTENT);
                                                layNative.addView(adViewIron, 0, layoutParams);
                                                IronSource.loadBanner(adViewIron, idNativeBackup);
                                                break;
                                            case "STARTAPP":
                                                break;
                                            case "APPLOVIN-D":
                                                AdRequest.Builder builder = new AdRequest.Builder().addKeyword(Hpk1).addKeyword(Hpk2)
                                                        .addKeyword(Hpk3).addKeyword(Hpk4).addKeyword(Hpk5);
                                                Bundle bannerExtras = new Bundle();
                                                bannerExtras.putString("zone_id", idNativeBackup);
                                                builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                                                adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                                                layNative.addView(adViewDiscovery);
                                                adViewDiscovery.loadNextAd();
                                                break;

                                            case "FACEBOOK":
                                                break;
                                            case "ALIEN-M":
                                                break;
                                        }
                                    }
                                })
                        .build();
        adLoader.loadAd(request);
    }

    public static void MediumNativeMax(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

        MaxNativeAdViewBinder binder = new MaxNativeAdViewBinder.Builder(R.layout.max_big_native)
                .setTitleTextViewId(R.id.title_text_view)
                .setBodyTextViewId(R.id.body_text_view)
                .setAdvertiserTextViewId(R.id.advertiser_textView)
                .setIconImageViewId(R.id.icon_image_view)
                .setMediaContentViewGroupId(R.id.media_view_container)
                .setOptionsContentViewGroupId(R.id.ad_options_view)
                .setCallToActionButtonId(R.id.cta_button)
                .build();
        nativeAdView = new MaxNativeAdView(binder, activity);

        nativeAdLoader = new MaxNativeAdLoader(nativeId, activity);
        nativeAdLoader.setRevenueListener(new MaxAdRevenueListener() {
            @Override
            public void onAdRevenuePaid(MaxAd ad) {

            }
        });
        nativeAdLoader.setNativeAdListener(new MaxNativeAdListener() {
            @Override
            public void onNativeAdLoaded(final MaxNativeAdView nativeAdView, final MaxAd ad) {
                if (onLoadMediumNativesApplovinMax != null) {
                    onLoadMediumNativesApplovinMax.onNativeAdLoaded();
                }
                switch (selectAdsBackup) {
                    case "ADMOB":
                        if (nativeAd != null) {
                            nativeAd.destroy();
                        }
                        break;
                    case "MOPUB":

                        break;
                    case "IRON":
                        if (adViewIron != null) {
                            adViewIron.isDestroyed();
                        }
                        break;
                    case "STARTAPP":

                        break;
                    case "APPLOVIN-D":
                        if (adViewDiscovery != null) {
                            adViewDiscovery.destroy();
                        }
                        break;
                    case "FACEBOOK":
                        break;
                }
                if (nativeAdMax != null) {
                    nativeAdLoader.destroy(nativeAdMax);
                }
                nativeAdMax = ad;
                layNative.removeAllViews();
                layNative.addView(nativeAdView);
            }

            @Override
            public void onNativeAdLoadFailed(final String adUnitId, final MaxError error) {
                if (onLoadMediumNativesApplovinMax != null) {
                    onLoadMediumNativesApplovinMax.onNativeAdLoadFailed("");
                }
                switch (selectAdsBackup) {
                    case "ADMOB": {
                        AdLoader.Builder builder = new AdLoader.Builder(activity, idNativeBackup);
                        builder.forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(@NonNull NativeAd nativeAds) {
                                if (nativeAd != null) {
                                    nativeAd.destroy();
                                }
                                nativeAd = nativeAds;
                                NativeAdView adView = (NativeAdView) activity.getLayoutInflater()
                                        .inflate(R.layout.admob_big_native, null);
                                populateNativeAdView(nativeAds, adView);
                                layNative.removeAllViews();
                                layNative.addView(adView);
                            }


                        });

                        VideoOptions videoOptions = new VideoOptions.Builder()
                                .build();

                        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                                .setVideoOptions(videoOptions)
                                .build();

                        builder.withNativeAdOptions(adOptions);

                        AdRequest request = new AdRequest.Builder()
                                .build();
                        AdLoader adLoader =
                                builder
                                        .withAdListener(
                                                new AdListener() {
                                                    @Override
                                                    public void onAdFailedToLoad(LoadAdError loadAdError) {

                                                    }
                                                })
                                        .build();
                        adLoader.loadAd(request);
                        break;
                    }
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "IRON":
                        adViewIron = IronSource.createBanner(activity, ISBannerSize.RECTANGLE);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                                FrameLayout.LayoutParams.WRAP_CONTENT);
                        layNative.addView(adViewIron, 0, layoutParams);
                        IronSource.loadBanner(adViewIron, idNativeBackup);
                        break;
                    case "STARTAPP":
                        break;
                    case "APPLOVIN-D":
                        AdRequest.Builder builder = new AdRequest.Builder();
                        Bundle bannerExtras = new Bundle();
                        bannerExtras.putString("zone_id", idNativeBackup);
                        builder.addCustomEventExtrasBundle(AppLovinCustomEventBanner.class, bannerExtras);

                        adViewDiscovery = new AppLovinAdView(AppLovinAdSize.MREC, activity);
                        layNative.addView(adViewDiscovery);
                        adViewDiscovery.loadNextAd();
                        break;
                    case "ALIEN-M":
                        break;
                    case "FACEBOOK":
                        break;
                }
            }

            @Override
            public void onNativeAdClicked(final MaxAd ad) {
                if (onLoadMediumNativesApplovinMax != null) {
                    onLoadMediumNativesApplovinMax.onNativeAdClicked();
                }
            }
        });
        nativeAdLoader.loadAd(nativeAdView);

    }

    public static void MediumNativeFan(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {

    }


    public static void MediumNativeAlien(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
    }

    public static void SmallNativeAlien(Activity activity, RelativeLayout layNative, String selectAdsBackup, String nativeId, String idNativeBackup) {
    }

    private static void populateNativeAdView(NativeAd nativeAd, NativeAdView adView) {
        adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));
        adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
        adView.setBodyView(adView.findViewById(R.id.ad_body));
        adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
        adView.setIconView(adView.findViewById(R.id.ad_app_icon));
        adView.setPriceView(adView.findViewById(R.id.ad_price));
        adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
        adView.setStoreView(adView.findViewById(R.id.ad_store));
        adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.GONE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.GONE);
        }
        adView.setNativeAd(nativeAd);
    }

}
