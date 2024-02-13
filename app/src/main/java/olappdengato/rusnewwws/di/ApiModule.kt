package olappdengato.rusnewwws.di

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import olappdengato.rusnewwws.data.remote.ZaimApi
import olappdengato.rusnewwws.domain.utils.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.SecureRandom
import java.security.cert.X509Certificate
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSession
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


@Module
@InstallIn(SingletonComponent::class)

object ApiModule {
    @Provides
    @Singleton
    fun provideApi(): ZaimApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
            .create(ZaimApi::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return try {
            val trustAllCerts: Array<TrustManager> = arrayOf(MyManager())
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, SecureRandom())
            OkHttpClient.Builder()
                .sslSocketFactory(sslContext.socketFactory, MyManager())
                .hostnameVerifier { _: String?, _: SSLSession? -> true }
                .build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}

@SuppressLint("CustomX509TrustManager")
class MyManager : X509TrustManager {

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkServerTrusted(
        p0: Array<out X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    @SuppressLint("TrustAllX509TrustManager")
    override fun checkClientTrusted(
        p0: Array<out X509Certificate>?,
        p1: String?
    ) {
        //allow all
    }

    override fun getAcceptedIssuers(): Array<X509Certificate> {
        return arrayOf()
    }
}