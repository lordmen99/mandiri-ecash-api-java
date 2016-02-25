package com.github.yafithekid.mandiri_ecash_api.client;


import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAHttpException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEAIOException;
import com.github.yafithekid.mandiri_ecash_api.exceptions.MEATokenExpiredException;
import com.github.yafithekid.mandiri_ecash_api.requests.*;
import com.github.yafithekid.mandiri_ecash_api.responses.*;

public interface MEASyncRESTClient {
    MEALoginResponse login(MEALoginRequest meaLoginRequest)
            throws MEAIOException,MEAHttpException;

    MEALogoutResponse logout(MEALogoutRequest meaLogoutRequest)
            throws MEAIOException,MEAHttpException;

    MEATransferMemberInquiryResponse transferMemberInquiry(MEATransferMemberInquiryRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEATransferMemberPaymentResponse transferMemberPayment(MEATransferMemberPaymentRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEABalanceInquiryResponse balanceInquiry(MEABalanceInquiryRequest meaRequest)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEAAccountHistoryResponse accountHistory(MEAAccountHistoryRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;

    MEAOnStorePurchaseResponse onStorePurchaseRequest(MEAOnStorePurchaseRequest request)
            throws MEAIOException,MEATokenExpiredException,MEAHttpException;
}
