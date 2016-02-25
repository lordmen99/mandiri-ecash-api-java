package com.github.yafithekid.mandiri_ecash_api.client;

import com.github.yafithekid.mandiri_ecash_api.MEAURLFactory;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.github.yafithekid.mandiri_ecash_api.responses.*;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class MEASyncRESTClientImpl implements MEASyncRESTClient {
    public final static String TOKEN_EXPIRED = "TOKEN_EXPIRED";
    OkHttpClient okHttpClient = new OkHttpClient();
    MEAURLFactory meaurlFactory = new MEAURLFactory();
    private Gson gson = new Gson();

    @Override
    public MEALoginResponse login(MEALoginRequest meaLoginRequest) throws MEAIOException,MEAHttpException {
        Response response;
        try {
            response = okHttpClient.newCall(
                    new Request.Builder()
                            .url(meaurlFactory.login(meaLoginRequest.getUid(),meaLoginRequest.getMsisdn(),meaLoginRequest.getCredentials()))
                            .build()).
                    execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            return gson.fromJson(response.body().charStream(), MEALoginResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest) throws MEAIOException, MEAHttpException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.logout(meaLogoutRequest.getToken(),meaLogoutRequest.getMsisdn()))
                    .build()
            ).execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            return gson.fromJson(response.body().charStream(), MEALogoutResponse.class);
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.transferMemberInquiry(
                            request.getMsisdn(),request.getTo(),request.getAmount(),request.getToken()
                    ))
                    .build()
            ).execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            MEATransferMemberInquiryResponse meaResponse = gson.fromJson(response.body().charStream(), MEATransferMemberInquiryResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.transferMemberPayment(
                            request.getFrom(),request.getTo(),request.getAmount(),
                            request.getDescription(),request.getCredentials(),request.getToken()))
                    .build()
            ).execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            MEATransferMemberPaymentResponse meaResponse = gson.fromJson(response.body().charStream(), MEATransferMemberPaymentResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.balanceInquiry(meaRequest.getToken(),meaRequest.getMsisdn()))
                    .build()
            ).execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            MEABalanceInquiryResponse meaResponse = gson.fromJson(response.body().charStream(), MEABalanceInquiryResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.accountHistory(request.getPagesize(),request.getToken(),
                            request.getMsisdn(),request.getOnpage()))
                    .build()
            ).execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            MEAAccountHistoryResponse meaResponse = gson.fromJson(response.body().charStream(), MEAAccountHistoryResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }
    }

    @Override
    public MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request)
            throws MEAIOException, MEAHttpException, MEATokenExpiredException {
        try {
            Response response = okHttpClient.newCall(
                new Request.Builder()
                    .url(meaurlFactory.onStorePurchase(request.getAmount(),request.getToken(),
                            request.getMsisdn(),request.getCredentials()))
                    .build()
            ).execute();
            if (!response.isSuccessful()){
                throw new MEAHttpException(response.code(),response.body().toString());
            }
            MEAOnStorePurchaseResponse meaResponse = gson.fromJson(response.body().charStream(),MEAOnStorePurchaseResponse.class);
            if (meaResponse.getStatus().equalsIgnoreCase(TOKEN_EXPIRED)) {
                throw new MEATokenExpiredException();
            }
            return meaResponse;
        } catch (IOException e) {
            throw new MEAIOException(e);
        }

    }

}
