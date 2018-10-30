package com.developerdru.vividity.data.remote;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseQueryLiveData extends LiveData<DataSnapshot> {

    private static final String LOG_TAG = "FirebaseQueryLiveData";

    private final Query query;
    private final InternalValueEventListener listener = new InternalValueEventListener();

    public FirebaseQueryLiveData(Query query) {
        this.query = query;
    }

    @Override
    protected void onActive() {
        // super.onActive();
        Log.d(LOG_TAG, "onActive");
        query.addValueEventListener(listener);
    }

    @Override
    protected void onInactive() {
        // super.onInactive();
        Log.d(LOG_TAG, "onActive");
        query.removeEventListener(listener);

    }

    private class InternalValueEventListener implements ValueEventListener {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            setValue(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Log.e(LOG_TAG, "Can't listen to query " + query, databaseError.toException());
        }
    }
}