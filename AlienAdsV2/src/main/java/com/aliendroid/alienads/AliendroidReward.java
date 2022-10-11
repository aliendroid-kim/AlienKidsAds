package com.aliendroid.alienads;

import android.app.Activity;

import androidx.annotation.NonNull;

import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsAdmob;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsAlienView;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsApplovinDiscovery;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsApplovinMax;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsGoogle;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsIronSource;
import com.aliendroid.alienads.interfaces.rewards.load.OnLoadRewardsStartApp;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAdmob;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAlienMedition;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsAlienView;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsApplovinDiscovery;
import com.aliendroid.alienads.interfaces.rewards.show.OnShowRewardsGoogle;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.MaxReward;
import com.applovin.mediation.MaxRewardedAdListener;
import com.applovin.mediation.ads.MaxRewardedAd;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinSdk;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.admanager.AdManagerAdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.model.Placement;
import com.ironsource.mediationsdk.sdk.RewardedVideoListener;

import java.util.Map;

public class AliendroidReward {
    public static MaxRewardedAd rewardedAd;
    public static boolean unlockreward = false;
    public static AppLovinIncentivizedInterstitial incentivizedInterstitial;
    public static OnLoadRewardsAdmob onLoadRewardsAdmob;
    public static OnLoadRewardsStartApp onLoadRewardsStartApp;
    public static OnLoadRewardsGoogle onLoadRewardsGoogle;
    public static OnLoadRewardsApplovinDiscovery onLoadRewardsApplovinDiscovery;
    public static OnLoadRewardsApplovinMax onLoadRewardsApplovinMax;
    public static OnLoadRewardsIronSource onLoadRewardsIronSource;
    public static OnLoadRewardsAlienView onLoadRewardsAlienView;
    public static OnShowRewardsAdmob onShowRewardsAdmob;
    public static OnShowRewardsGoogle onShowRewardsGoogle;
    public static OnShowRewardsApplovinDiscovery onShowRewardsApplovinDiscovery;
    public static OnShowRewardsAlienMedition onShowRewardsAlienMedition;
    public static OnShowRewardsAlienView onShowRewardsAlienView;
    public static boolean SHOW_ALIEN_VIEW = false;
    private static RewardedAd mRewardedAd;

    public static void LoadRewardAdmob(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {

            AdRequest adRequest = new AdRequest.Builder()
                    .build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsAdmob != null) {
                                onLoadRewardsAdmob.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsAdmob != null) {
                                onLoadRewardsAdmob.onAdLoaded("");
                            }
                            mRewardedAd = rewardedAd;

                        }
                    });
            switch (selectBackupAds) {
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;
                case "MOPUB":
                case "UNITY":
                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {

                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {

                        }
                    });
                    break;
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
                        }
                    });
                    break;
                case "STARTAPP":
                    break;

                case "ALIEN-M":

                    break;
                case "ALIEN-V":

                    break;


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardAlienMediation(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void LoadRewardAlienView(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
    }

    public static void LoadRewardUnity(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void LoadRewardGoogleAds(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            AdManagerAdRequest adRequest = new AdManagerAdRequest.Builder().build();
            RewardedAd.load(activity, idReward,
                    adRequest, new RewardedAdLoadCallback() {
                        @Override
                        public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                            if (onLoadRewardsGoogle != null) {
                                onLoadRewardsGoogle.onAdFailedToLoad();
                            }
                            mRewardedAd = null;
                        }

                        @Override
                        public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                            if (onLoadRewardsGoogle != null) {
                                onLoadRewardsGoogle.onAdFailedToLoad();
                            }
                            mRewardedAd = rewardedAd;

                        }
                    });
            switch (selectBackupAds) {
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);
                    break;
                case "MOPUB":
                case "UNITY":

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {
                            // A rewarded video was successfully received.
                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {
                            // A rewarded video failed to load.
                        }
                    });
                    break;
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
                        }
                    });
                    break;
                case "STARTAPP":
                    break;
                case "ALIEN-M":

                    break;
                case "ALIEN-V":

                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void LoadRewardApplovinMax(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            rewardedAd = MaxRewardedAd.getInstance(idReward, activity);
            rewardedAd.loadAd();
            MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                @Override
                public void onRewardedVideoStarted(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onRewardedVideoStarted();
                    }
                }

                @Override
                public void onRewardedVideoCompleted(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onRewardedVideoCompleted();
                    }
                    unlockreward = true;
                }

                @Override
                public void onUserRewarded(MaxAd ad, MaxReward reward) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onUserRewarded();
                    }
                }

                @Override
                public void onAdLoaded(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdLoaded();
                    }
                }

                @Override
                public void onAdDisplayed(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdDisplayed();
                    }
                }

                @Override
                public void onAdHidden(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdHidden();
                    }
                }

                @Override
                public void onAdClicked(MaxAd ad) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdClicked();
                    }
                }

                @Override
                public void onAdLoadFailed(String adUnitId, MaxError error) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdLoadFailed();
                    }
                }

                @Override
                public void onAdDisplayFailed(MaxAd ad, MaxError error) {
                    if (onLoadRewardsApplovinMax != null) {
                        onLoadRewardsApplovinMax.onAdDisplayFailed();
                    }
                }
            };
            rewardedAd.setListener(maxRewardedAdListener);
            switch (selectBackupAds) {
                case "ADMOB":
                case "GOOGLE-ADS":

                    AdRequest adRequest = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "MOPUB":
                case "UNITY":

                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {
                            // A rewarded video was successfully received.
                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {
                            // A rewarded video failed to load.
                        }
                    });
                    break;
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
                        }
                    });
                    break;
                case "STARTAPP":
                    break;
                case "ALIEN-M":

                    break;
                case "ALIEN-V":

                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardApplovinDis(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {
        try {
            incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idReward, AppLovinSdk.getInstance(activity));
            incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                @Override
                public void adReceived(AppLovinAd appLovinAd) {
                    if (onLoadRewardsApplovinDiscovery != null) {
                        onLoadRewardsApplovinDiscovery.adReceived();
                    }
                }

                @Override
                public void failedToReceiveAd(int errorCode) {
                    if (onLoadRewardsApplovinDiscovery != null) {
                        onLoadRewardsApplovinDiscovery.failedToReceiveAd("");
                    }
                }
            });
            switch (selectBackupAds) {
                case "ADMOB":
                case "GOOGLE-ADS":
                    AdRequest adRequest = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "MOPUB":
                case "UNITY":

                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);

                    break;
                case "IRON":
                    IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                        @Override
                        public void onRewardedVideoAdOpened() {
                        }

                        @Override
                        public void onRewardedVideoAdClosed() {
                        }

                        @Override
                        public void onRewardedVideoAvailabilityChanged(boolean available) {
                        }

                        @Override
                        public void onRewardedVideoAdRewarded(Placement placement) {
                            unlockreward = true;
                        }

                        @Override
                        public void onRewardedVideoAdShowFailed(IronSourceError error) {
                        }

                        @Override
                        public void onRewardedVideoAdClicked(Placement placement) {
                        }

                        @Override
                        public void onRewardedVideoAdStarted() {
                        }

                        @Override
                        public void onRewardedVideoAdEnded() {
                        }
                    });
                    break;
                case "STARTAPP":
                    break;
                case "ALIEN-M":
                    break;
                case "ALIEN-V":
                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardMopub(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }

    public static void LoadRewardIron(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            IronSource.setRewardedVideoListener(new RewardedVideoListener() {
                @Override
                public void onRewardedVideoAdOpened() {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdOpened();
                    }
                }

                @Override
                public void onRewardedVideoAdClosed() {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdClosed();
                    }
                }

                @Override
                public void onRewardedVideoAvailabilityChanged(boolean available) {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAvailabilityChanged();
                    }
                }

                @Override
                public void onRewardedVideoAdRewarded(Placement placement) {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdRewarded();
                    }
                    unlockreward = true;
                }

                @Override
                public void onRewardedVideoAdShowFailed(IronSourceError error) {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdShowFailed();
                    }
                    switch (selecBackuptAds) {
                        case "GOOGLE-ADS":
                        case "ADMOB":
                            if (mRewardedAd != null) {
                                Activity activityContext = activity;
                                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                    @Override
                                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                        unlockreward = true;
                                    }
                                });
                            }
                            break;
                        case "MOPUB":
                        case "UNITY":

                            break;
                        case "APPLOVIN-M":
                            if (rewardedAd.isReady()) {
                                rewardedAd.showAd();
                            }
                            break;
                        case "APPLOVIN-D":
                            if (incentivizedInterstitial != null) {
                                incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                    @Override
                                    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                        unlockreward = true;
                                    }

                                    @Override
                                    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                    }

                                    @Override
                                    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                    }

                                    @Override
                                    public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                    }


                                }, null, new AppLovinAdDisplayListener() {
                                    @Override
                                    public void adDisplayed(AppLovinAd appLovinAd) {

                                    }

                                    @Override
                                    public void adHidden(AppLovinAd appLovinAd) {
                                        incentivizedInterstitial.preload(null);
                                    }
                                });
                            }
                            break;
                        case "STARTAPP":

                            break;
                        case "ALIEN-V":
                            break;
                        case "ALIEN-M":
                            break;

                    }
                }

                @Override
                public void onRewardedVideoAdClicked(Placement placement) {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdClicked();
                    }
                }

                @Override
                public void onRewardedVideoAdStarted() {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdStarted();
                    }
                }

                @Override
                public void onRewardedVideoAdEnded() {
                    if (onLoadRewardsIronSource != null) {
                        onLoadRewardsIronSource.onRewardedVideoAdEnded();
                    }
                }
            });
            switch (selecBackuptAds) {
                case "ADMOB":
                case "GOOGLE-ADS":
                    AdRequest adRequest = new AdRequest.Builder()
                            .build();
                    RewardedAd.load(activity, idBackupReward,
                            adRequest, new RewardedAdLoadCallback() {
                                @Override
                                public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                                    mRewardedAd = null;
                                }

                                @Override
                                public void onAdLoaded(@NonNull RewardedAd rewardedAd) {
                                    mRewardedAd = rewardedAd;

                                }
                            });
                    break;
                case "APPLOVIN-D":
                    incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(idBackupReward, AppLovinSdk.getInstance(activity));
                    incentivizedInterstitial.preload(new AppLovinAdLoadListener() {
                        @Override
                        public void adReceived(AppLovinAd appLovinAd) {
                            // A rewarded video was successfully received.
                        }

                        @Override
                        public void failedToReceiveAd(int errorCode) {
                            // A rewarded video failed to load.
                        }
                    });
                    break;
                case "APPLOVIN-M":
                    rewardedAd = MaxRewardedAd.getInstance(idBackupReward, activity);
                    rewardedAd.loadAd();
                    MaxRewardedAdListener maxRewardedAdListener = new MaxRewardedAdListener() {
                        @Override
                        public void onRewardedVideoStarted(MaxAd ad) {

                        }

                        @Override
                        public void onRewardedVideoCompleted(MaxAd ad) {
                            unlockreward = true;
                        }

                        @Override
                        public void onUserRewarded(MaxAd ad, MaxReward reward) {

                        }

                        @Override
                        public void onAdLoaded(MaxAd ad) {

                        }

                        @Override
                        public void onAdDisplayed(MaxAd ad) {

                        }

                        @Override
                        public void onAdHidden(MaxAd ad) {

                        }

                        @Override
                        public void onAdClicked(MaxAd ad) {

                        }

                        @Override
                        public void onAdLoadFailed(String adUnitId, MaxError error) {

                        }

                        @Override
                        public void onAdDisplayFailed(MaxAd ad, MaxError error) {

                        }
                    };
                    rewardedAd.setListener(maxRewardedAdListener);

                    break;
                case "MOPUB":
                case "UNITY":

                    break;
                case "STARTAPP":
                    break;
                case "ALIEN-M":

                    break;
                case "ALIEN-V":

                    break;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void LoadRewardStartApp(Activity activity, String selectBackupAds, String idReward, String idBackupReward) {

    }


    public static void ShowRewardAdmob(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsAdmob != null) {
                            onShowRewardsAdmob.onUserEarnedReward();
                        }
                        unlockreward = true;
                        LoadRewardAdmob(activity, selecBackuptAds, idReward, idBackupReward);
                    }
                });
            } else {
                switch (selecBackuptAds) {
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        break;
                    case "ALIEN-V":
                        break;
                    case "ALIEN-M":

                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardAdmob(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardGoogleAds(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (mRewardedAd != null) {
                Activity activityContext = activity;
                mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                    @Override
                    public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                        if (onShowRewardsGoogle != null) {
                            onShowRewardsGoogle.onUserEarnedReward();
                        }
                        unlockreward = true;
                        LoadRewardGoogleAds(activity, selecBackuptAds, idReward, idBackupReward);
                    }
                });
            } else {
                switch (selecBackuptAds) {
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        break;
                    case "ALIEN-V":
                        break;
                    case "ALIEN-M":

                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardGoogleAds(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardApplovinMax(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (rewardedAd.isReady()) {
                rewardedAd.showAd();
                LoadRewardApplovinMax(activity, selecBackuptAds, idReward, idBackupReward);
            } else {
                switch (selecBackuptAds) {
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "APPLOVIN-D":
                        if (incentivizedInterstitial != null) {
                            incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                                @Override
                                public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                                    unlockreward = true;
                                }

                                @Override
                                public void userOverQuota(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {

                                }

                                @Override
                                public void validationRequestFailed(AppLovinAd ad, int errorCode) {

                                }


                            }, null, new AppLovinAdDisplayListener() {
                                @Override
                                public void adDisplayed(AppLovinAd appLovinAd) {

                                }

                                @Override
                                public void adHidden(AppLovinAd appLovinAd) {
                                    incentivizedInterstitial.preload(null);
                                }
                            });
                        }
                        break;
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        break;
                    case "ALIEN-V":
                        break;
                    case "ALIEN-M":

                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardApplovinMax(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardApplovinDis(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            if (incentivizedInterstitial != null) {
                incentivizedInterstitial.show(activity, new AppLovinAdRewardListener() {
                    @Override
                    public void userRewardVerified(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.userRewardVerified();
                        }
                        unlockreward = true;
                    }

                    @Override
                    public void userOverQuota(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.userOverQuota();
                        }
                    }

                    @Override
                    public void userRewardRejected(AppLovinAd ad, Map<String, String> response) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.userRewardRejected();
                        }
                    }

                    @Override
                    public void validationRequestFailed(AppLovinAd ad, int errorCode) {
                        if (onShowRewardsApplovinDiscovery != null) {
                            onShowRewardsApplovinDiscovery.validationRequestFailed();
                        }
                    }


                }, null, new AppLovinAdDisplayListener() {
                    @Override
                    public void adDisplayed(AppLovinAd appLovinAd) {

                    }

                    @Override
                    public void adHidden(AppLovinAd appLovinAd) {
                        incentivizedInterstitial.preload(null);
                    }
                });
                LoadRewardApplovinDis(activity, selecBackuptAds, idReward, idBackupReward);
            } else {
                switch (selecBackuptAds) {
                    case "GOOGLE-ADS":
                    case "ADMOB":
                        if (mRewardedAd != null) {
                            Activity activityContext = activity;
                            mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                                @Override
                                public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                    unlockreward = true;
                                }
                            });
                        }
                        break;
                    case "MOPUB":
                    case "UNITY":

                        break;
                    case "APPLOVIN-M":
                        if (rewardedAd.isReady()) {
                            rewardedAd.showAd();
                        }
                        break;
                    case "IRON":
                        IronSource.showRewardedVideo(idBackupReward);
                        break;
                    case "STARTAPP":
                        break;
                    case "ALIEN-V":
                        break;
                    case "ALIEN-M":

                        break;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LoadRewardApplovinDis(activity, selecBackuptAds, idReward, idBackupReward);
    }

    public static void ShowRewardMopub(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {

    }

    public static void ShowRewardIron(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
        try {
            IronSource.showRewardedVideo(idBackupReward);
            LoadRewardIron(activity, selecBackuptAds, idReward, idBackupReward);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void ShowRewardUnity(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
    }

    public static void ShowRewardStartApp(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
    }

    public static void ShowRewardAlienView(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
    }

    public static void ShowRewardAlienMediation(Activity activity, String selecBackuptAds, String idReward, String idBackupReward) {
    }
}
