package com.austindroids.austinfeedsme.di.modules;

import com.austindroids.austinfeedsme.common.EventsContract;
import com.austindroids.austinfeedsme.events.EventsActivity;

import dagger.Binds;
import dagger.Module;

/**
 * Created by darrankelinske on 10/6/17.
 */

@ActivityScoped
@Module
public abstract class EventsPresenterModule {

    @Binds
    public abstract EventsContract.View view(EventsActivity mainActivity);

}