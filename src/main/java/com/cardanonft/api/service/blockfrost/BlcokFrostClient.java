package com.cardanonft.api.service.blockfrost;

import com.cardanonft.api.constants.CommonConstants;
import com.cardanonft.api.service.blockfrost.vo.Address;
import com.cardanonft.api.service.blockfrost.vo.AddressTransaction;
import com.cardanonft.api.service.blockfrost.vo.Asset;
import com.cardanonft.api.service.blockfrost.vo.TransactionUtxo;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class BlcokFrostClient {

    private static Logger logger = LoggerFactory.getLogger(BlcokFrostClient.class);

    private BlockFrost blockFrost;
    private String apiUrl = CommonConstants.API_URL_BLOCKFROST;
    private String apiUrl_test = CommonConstants.API_URL_BLOCKFROST_TEST;
    private String apiKey = CommonConstants.API_KEY_BLOCKFROST;
    private String apiKey_test = CommonConstants.API_KEY_BLOCKFROST_TEST;
    private String CONTENTS_TYPE = "application/x-www-form-urlencoded;charset=UTF-8";
    public BlcokFrostClient() {
        this.blockFrost = this.create();
    }
    private String profile = System.getProperty("spring.profiles.active");

    protected BlockFrost create() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(60, TimeUnit.SECONDS)
                .build();
        if("real".equals(profile))
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(BlockFrost.class);
        }else{
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl_test)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            return retrofit.create(BlockFrost.class);
        }
    }
    public List<AddressTransaction> addressTransactions(String address,int count, int page) {
        String apiKeys = "";
        if("real".equals(profile))
        {
            apiKeys = apiKey;
        }else{
            apiKeys = apiKey_test;
        }
        Call<List<AddressTransaction>> call = this.blockFrost.addressTransactions(apiKeys,address,count, page);

        try {
            Response<List<AddressTransaction>> response = call.execute();
            if(!response.isSuccessful()){
                logger.error("addressTransactions API 호출시 오류발생 : "+response.code());
            }
            return response.body();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public Address address(String address) {
        String apiKeys = "";
        if("real".equals(profile))
        {
            apiKeys = apiKey;
        }else{
            apiKeys = apiKey_test;
        }
        Call<Address> call = this.blockFrost.address(apiKeys,address);

        try {
            Response<Address> response = call.execute();
            if(!response.isSuccessful()){
                logger.error("Address API 호출시 오류발생 : "+response.code());
            }
            return response.body();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
    public TransactionUtxo transactionUtxos(String txHash) {
        String apiKeys = "";
        if("real".equals(profile))
        {
            apiKeys = apiKey;
        }else{
            apiKeys = apiKey_test;
        }
        Call<TransactionUtxo> call = this.blockFrost.transactionUtxo(apiKeys,txHash);

        try {
            Response<TransactionUtxo> response = call.execute();
            if(!response.isSuccessful()){
                logger.error("transactionUtxos API 호출시 오류발생 : "+response.code());
            }
            return response.body();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public Asset asset(String asset) {
        String apiKeys = "";
        if("real".equals(profile))
        {
            apiKeys = apiKey;
        }else{
            apiKeys = apiKey_test;
        }
        Call<Asset> call = this.blockFrost.specificAsset(apiKeys,asset);

        try {
            Response<Asset> response = call.execute();
            if(!response.isSuccessful()){
                logger.error("Address API 호출시 오류발생 : "+response.code());
            }
            return response.body();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return null;
    }
}
