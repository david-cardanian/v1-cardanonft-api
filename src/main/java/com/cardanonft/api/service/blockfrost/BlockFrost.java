package com.cardanonft.api.service.blockfrost;

import com.cardanonft.api.service.blockfrost.vo.Address;
import com.cardanonft.api.service.blockfrost.vo.AddressTransaction;
import com.cardanonft.api.service.blockfrost.vo.Asset;
import com.cardanonft.api.service.blockfrost.vo.TransactionUtxo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface BlockFrost {
    @GET("/api/v0/addresses/{address}/transactions")
    Call<List<AddressTransaction>> addressTransactions(
            @Header("project_id") String projectId,
            @Path("address") String address,
            @Query("count") int count,
            @Query("page") int page
    );
    @GET("/api/v0/txs/{txHash}/utxos")
    Call<TransactionUtxo> transactionUtxo(
            @Header("project_id") String projectId,
            @Path("txHash") String txHash
    );
    @GET("/api/v0/addresses/{address}")
    Call<Address> address(
            @Header("project_id") String projectId,
            @Path("address") String address
    );
    @GET("/api/v0/assets/{asset}")
    Call<Asset> specificAsset(
            @Header("project_id") String projectId,
            @Path("asset") String address
    );
}
