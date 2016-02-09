package com.github.yafithekid.mandiri_ecash_api.client;

import com.github.yafithekid.mandiri_ecash_api.MEAURLFactory;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.responses.*;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MEASyncRESTClientImpl implements MEASyncRESTClient {
    OkHttpClient okHttpClient = new OkHttpClient();
    MEAURLFactory meaurlFactory = new MEAURLFactory();
    private Gson gson = new Gson();

    public MEALoginResponse login(MEALoginRequest meaLoginRequest) throws MEAIOException {
        Response response;
        try {
            response = okHttpClient.newCall(
                    new Request.Builder()
                            .url(meaurlFactory.login(meaLoginRequest.getUid(),meaLoginRequest.getMsisdn(),meaLoginRequest.getCredentials()))
                            .build()).
                    execute();
            return gson.fromJson(response.body().charStream(), MEALoginResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    public MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest) throws MEAIOException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.logout(meaLogoutRequest.getToken(),meaLogoutRequest.getMsisdn()))
                    .build()
            ).execute();
            //TODO need to throw another exception??
            return gson.fromJson(response.body().charStream(),MEALogoutResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    public MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request) throws MEAIOException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.transferMemberInquiry(
                            request.getMsisdn(),request.getTo(),request.getAmount(),request.getToken()
                    ))
                    .build()
            ).execute();
            //TODO need to throw another exception??
            return gson.fromJson(response.body().charStream(),MEATransferMemberInquiryResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    public MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request) throws MEAIOException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.transferMemberPayment(
                            request.getFrom(),request.getTo(),request.getAmount(),
                            request.getDescription(),request.getCredentials(),request.getToken()))
                    .build()
            ).execute();
            //TODO need to throw another exception??
            return gson.fromJson(response.body().charStream(),MEATransferMemberPaymentResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    public MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest) throws MEAIOException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.balanceInquiry(meaRequest.getToken(),meaRequest.getMsisdn()))
                    .build()
            ).execute();
            return gson.fromJson(response.body().charStream(),MEABalanceInquiryResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    public MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request) throws MEAIOException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.accountHistory(request.getPagesize(),request.getToken(),
                            request.getMsisdn(),request.getOnpage()))
                    .build()
            ).execute();
            return gson.fromJson(response.body().charStream(),MEAAccountHistoryResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    public MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request) throws MEAIOException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.onStorePurchase(request.getAmount(),request.getToken(),
                            request.getMsisdn(),request.getCredentials()))
                    .build()
            ).execute();
            return gson.fromJson(response.body().charStream(),MEAOnStorePurchaseResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }

    }

}
