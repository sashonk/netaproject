package com.me.neta;
 
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AndroidApplication {
	
	  private static final String AD_UNIT_ID = "ca-app-pub-4198140113968724/8806620094";
	  private static final String AD_UNIT_ID_I =	"ca-app-pub-4198140113968724/9724950092";
	  private static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/developer?id=TheInvader360";
	  private static final String GIRLFRIENDs_HUAWEY = "C1E23BAF9DEC590B1ADF948CF0B72E47";
	  protected AdView adView;
	  protected InterstitialAd interstitial;
	  protected View gameView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	  super.onCreate(savedInstanceState);

    	    AndroidApplicationConfiguration cfg = new AndroidApplicationConfiguration();
    	    cfg.useGL20 = false;
    	    cfg.useAccelerometer = false;
    	    cfg.useCompass = false;

    	    // Do the stuff that initialize() would do for you
    	    requestWindowFeature(Window.FEATURE_NO_TITLE);
    	    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    	    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

    	    RelativeLayout layout = new RelativeLayout(this);
    	    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
    	    layout.setLayoutParams(params);

    	    AdView admobView = createAdView();
    	    layout.addView(admobView);
    	    View gameView = createGameView(cfg);
    	    layout.addView(gameView);

    	    setContentView(layout);
    	    adView.loadAd(buildRequest());
    	    
    	    
    	    interstitial = createInterstitial();
    	    interstitial.loadAd(buildRequest());
    	    
    	    final TelephonyManager tm =(TelephonyManager)getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);

    	    String deviceid = tm.getDeviceId();
    	    System.out.println("DEVICE_ID:"+deviceid);
    }
    
    private InterstitialAd createInterstitial(){
    	InterstitialAd ad = new InterstitialAd(this);
    	ad.setAdUnitId(AD_UNIT_ID_I);
    	return ad;
    }
    

    
    private AdView createAdView() {
        adView = new AdView(this);
        adView.setAdSize(AdSize.SMART_BANNER);
        adView.setAdUnitId(AD_UNIT_ID);
        adView.setId(12345); // this is an arbitrary id, allows for relative positioning in createGameView()
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        adView.setLayoutParams(params);
        adView.setBackgroundColor(Color.BLACK);
        return adView;
      }

      private View createGameView(AndroidApplicationConfiguration cfg) {
        gameView = initializeForView(new NetaGame(new AndroidNative(this)), cfg);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.BELOW, adView.getId());
        gameView.setLayoutParams(params);
        return gameView;
      }
      
      // Вызовите displayInterstitial(), когда будете готовы показать межстраничное объявление.
      public void displayInterstitial() {
        if (interstitial.isLoaded()) {
          interstitial.show();
        }
      }
      
      private AdRequest buildRequest(){
         return  new AdRequest.Builder().
        		     addTestDevice(AdRequest.DEVICE_ID_EMULATOR).
        		     addTestDevice(GIRLFRIENDs_HUAWEY).
          		build();
      }

      public void startAdvertising(AdView adView) {
        AdRequest adRequest = new AdRequest.Builder().
        		addTestDevice(AdRequest.DEVICE_ID_EMULATOR).
        		addTestDevice(GIRLFRIENDs_HUAWEY).
        		build();
        adView.loadAd(adRequest);
      }
      
      @Override
      public void onResume() {
        super.onResume();
        if (adView != null) adView.resume();
      }

      @Override
      public void onPause() {
        if (adView != null) adView.pause();
        super.onPause();
      }

      @Override
      public void onDestroy() {
        if (adView != null) adView.destroy();
        super.onDestroy();
      }


}