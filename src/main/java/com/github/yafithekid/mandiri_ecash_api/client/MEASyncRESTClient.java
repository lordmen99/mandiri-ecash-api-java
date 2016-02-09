package com.github.yafithekid.mandiri_ecash_api.client;


import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.github.yafithekid.mandiri_ecash_api.responses.*;

public interface MEASyncRESTClient {
    public MEALoginResponse login(MEALoginRequest meaLoginRequest) throws MEAIOException, MEAIOException;

    public MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest) throws MEAIOException;

    public MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request) throws MEAIOException;

    public MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request) throws MEAIOException;

    public MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest) throws MEAIOException;

    public MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request) throws MEAIOException;

    public MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request) throws MEAIOException;
}
