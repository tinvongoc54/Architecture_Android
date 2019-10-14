package com.example.myapplication.data.source.remote.middleware

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class UnauthorizedBroadcast(private val mContext: Context, val lifecycle: Lifecycle) : LifecycleObserver {
    private val localBM by lazy { LocalBroadcastManager.getInstance(mContext) }

    private val mBroadcastManager by lazy {
        object : BroadcastReceiver() {
            override fun onReceive(p0: Context?, p1: Intent?) {
                //TODO when unauthorized
            }
        }
    }

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun register() {
        localBM.registerReceiver(mBroadcastManager, IntentFilter(INTENT_UNAUTHORIZED))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun unregister() {
        localBM.unregisterReceiver(mBroadcastManager)
    }

    companion object {
        const val INTENT_UNAUTHORIZED = "action_unauthorized"
    }
}