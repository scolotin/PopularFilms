package com.scolotin.popularfilms.repository.connection

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.MainThreadDisposable
import io.reactivex.disposables.Disposable

class ConnectionStateObservable(private val context: Context) : Observable<ConnectionState>() {

    override fun subscribeActual(observer: Observer<in ConnectionState>) {
        val listener = NetworkStateListener(context, observer)
        observer.onSubscribe(listener)
    }

    class NetworkStateListener(
        private val context: Context,
        private val observer: Observer<in ConnectionState>
    ) : ConnectivityManager.NetworkCallback(), Disposable {

        private val disposable = object : MainThreadDisposable() {
            override fun onDispose() {
                connectivityManager.unregisterNetworkCallback(this@NetworkStateListener)
            }
        }

        private val connectivityManager: ConnectivityManager by lazy {
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        }

        init {
            connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), this)
        }

        override fun onAvailable(network: Network) {
            observer.onNext(ConnectionState.CONNECTED)
        }

        override fun onUnavailable() {
            observer.onNext(ConnectionState.DISCONNECTED)
        }

        override fun onLost(network: Network) {
            observer.onNext(ConnectionState.DISCONNECTED)
        }

        override fun dispose() = disposable.dispose()

        override fun isDisposed(): Boolean = disposable.isDisposed

    }

}
