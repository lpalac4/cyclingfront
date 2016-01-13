package com.palsoft.cyclingfront.broadcastreceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.Location;

import com.palsoft.cyclingfront.services.CFLocationService;

import java.util.ArrayList;

/**
 * Created by luispalacios on 12/2/15.
 */
public class CFLocationBroadcastReceiver extends BroadcastReceiver {

    ArrayList<ILocationConsumer> mConsumers;

    public CFLocationBroadcastReceiver(ILocationConsumer locationConsumer){
        mConsumers = new ArrayList<>();
        mConsumers.add(locationConsumer);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if(mConsumers != null && !mConsumers.isEmpty()) {
            for(ILocationConsumer consumer : mConsumers) {
                Object location = intent.getParcelableExtra(CFLocationService.LOCATION_STATUS_DATA);

                if(location instanceof Location) consumer.locationChange((Location) location);
            }
        }
    }

    public interface ILocationConsumer {
        public void locationChange(Location location);
    }
}
