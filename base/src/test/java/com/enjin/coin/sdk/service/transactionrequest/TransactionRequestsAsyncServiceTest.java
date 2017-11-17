package com.enjin.coin.sdk.service.transactionrequest;

import com.enjin.coin.sdk.config.Config;
import com.enjin.coin.sdk.service.transactionrequests.TransactionRequestsAsyncService;
import com.enjin.coin.sdk.service.transactionrequests.impl.TransactionRequestsAsyncServiceImpl;
import com.enjin.coin.sdk.util.JsonRpcUtils;
import com.enjin.coin.sdk.vo.transactionrequest.CancelTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.CancelTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.CreateTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.CreateTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.GetTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.GetTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableCancelTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableCreateTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableCreateTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableGetTransactionRequestRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableGetTransactionRequestResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableListTransactionRequestsRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.ImmutableListTransactionRequestsResponseVO;
import com.enjin.coin.sdk.vo.transactionrequest.ListTransactionRequestsRequestVO;
import com.enjin.coin.sdk.vo.transactionrequest.ListTransactionRequestsResponseVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Future;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(PowerMockRunner.class)
@PrepareForTest(TransactionRequestsAsyncServiceImpl.class)
public class TransactionRequestsAsyncServiceTest {

    TransactionRequestsAsyncService transactionRequestsAsyncService;
    Config enjinConfig;

    @Before
    public void setUp() {
        enjinConfig = new Config();
    }

    @Test
    public void testContructor() {
        transactionRequestsAsyncService = new TransactionRequestsAsyncServiceImpl(enjinConfig);
        assertThat(transactionRequestsAsyncService).isNotNull()
                .satisfies(o -> assertThat(o.toString()).isNotEmpty());
    }

   
    @SuppressWarnings("unchecked")
    @Test
    public void testGetTransactionRequest_Success() throws Exception {
        GetTransactionRequestRequestVO getTransactionRequestRequestVO = ImmutableGetTransactionRequestRequestVO.builder()
                .setAuth("xxxxxxxx")
                .setTxrId("123456")
                .build();

        GetTransactionRequestResponseVO returnedGetTransactionRequestResponseVO = ImmutableGetTransactionRequestResponseVO.builder().build();

        JsonRpcUtils mockJsonRpcUtils = PowerMockito.mock(JsonRpcUtils.class);
        PowerMockito.whenNew(JsonRpcUtils.class).withNoArguments().thenReturn(mockJsonRpcUtils);
        Mockito.when(mockJsonRpcUtils.sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class))).thenReturn(returnedGetTransactionRequestResponseVO);

        transactionRequestsAsyncService = new TransactionRequestsAsyncServiceImpl(enjinConfig);
        Future<GetTransactionRequestResponseVO> getTransactionRequestResponseFutureVO = transactionRequestsAsyncService.getTransactionRequestAsync(getTransactionRequestRequestVO);
        assertThat(getTransactionRequestResponseFutureVO).isNotNull();
        GetTransactionRequestResponseVO getTransactionRequestResponseVO = getTransactionRequestResponseFutureVO.get();
        assertThat(getTransactionRequestResponseVO).isNotNull();

        PowerMockito.verifyNew(JsonRpcUtils.class, Mockito.times(1)).withNoArguments();
        Mockito.verify(mockJsonRpcUtils, Mockito.times(1)).sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class));
    }

    

    @SuppressWarnings("unchecked")
    @Test
    public void testListTransactionRequests_Success() throws Exception {
        Map<String, Object> listIdentityMap = new HashMap<>();
        listIdentityMap.put("identity_id", "12345");
        Map<String, Object> listRecipientMap = new HashMap<>();
        listRecipientMap.put("identity_id", "54321");

        ListTransactionRequestsRequestVO listTransactionRequestsRequestVO = ImmutableListTransactionRequestsRequestVO.builder()
                .setAuth("xxxxxxxx")
                .setIdentityMap(listIdentityMap)
                .setAppId("123")
                .setRecipientMap(listRecipientMap)
                .setType("buy")
                .setAfterTxrId("1234567")
                .setLimit("50")
                .setCurrency("23456")
                .build();

        ListTransactionRequestsResponseVO[] returnedListTransactionRequestsResponseVOArray = new ListTransactionRequestsResponseVO[]{
                ImmutableListTransactionRequestsResponseVO.builder().build()
        };

        JsonRpcUtils mockJsonRpcUtils = PowerMockito.mock(JsonRpcUtils.class);
        PowerMockito.whenNew(JsonRpcUtils.class).withNoArguments().thenReturn(mockJsonRpcUtils);
        Mockito.when(mockJsonRpcUtils.sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class))).thenReturn(returnedListTransactionRequestsResponseVOArray);

        transactionRequestsAsyncService = new TransactionRequestsAsyncServiceImpl(enjinConfig);
        Future<ListTransactionRequestsResponseVO[]> listTransactionRequestsResponseFutureVOArray = transactionRequestsAsyncService.listTransactionRequestsAsync(listTransactionRequestsRequestVO);
        assertThat(listTransactionRequestsResponseFutureVOArray).isNotNull();
        ListTransactionRequestsResponseVO[] listTransactionRequestsResponseVOArray = listTransactionRequestsResponseFutureVOArray.get();
        assertThat(listTransactionRequestsResponseVOArray).isNotNull().hasSize(1);

        PowerMockito.verifyNew(JsonRpcUtils.class, Mockito.times(1)).withNoArguments();
        Mockito.verify(mockJsonRpcUtils, Mockito.times(1)).sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCreateTransactionRequest_Success() throws Exception {
        Map<String, Object> listIdentityMap = new HashMap<>();
        listIdentityMap.put("player_name", "Joe");
        Map<String, Object> listRecipientMap = new HashMap<>();
        listRecipientMap.put("player_name", "Alice");
        Map<String, Object> createValueMap = new HashMap<>();
        createValueMap.put("ENJ", "3000000000000000000");

        CreateTransactionRequestRequestVO createTransactionRequestRequestVO = ImmutableCreateTransactionRequestRequestVO.builder()
                .setAuth("xxxxxxxx")
                .setIdentityMap(listIdentityMap)
                .setRecipientMap(listRecipientMap)
                .setType("send")
                .setIcon("https://enjincoin.io/images/bubble.png")
                .setTitle("Mineplex: /transfer alice 3 ENJ")
                .setValueMap(createValueMap)
                .build();

        CreateTransactionRequestResponseVO returnedCreateTransactionRequestResponseVO = ImmutableCreateTransactionRequestResponseVO.builder().build();

        JsonRpcUtils mockJsonRpcUtils = PowerMockito.mock(JsonRpcUtils.class);
        PowerMockito.whenNew(JsonRpcUtils.class).withNoArguments().thenReturn(mockJsonRpcUtils);
        Mockito.when(mockJsonRpcUtils.sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class))).thenReturn(returnedCreateTransactionRequestResponseVO);

        transactionRequestsAsyncService = new TransactionRequestsAsyncServiceImpl(enjinConfig);
        Future<CreateTransactionRequestResponseVO> createTransactionRequestResponseFutureVO = transactionRequestsAsyncService.createTransactionRequestAsync(createTransactionRequestRequestVO);
        assertThat(createTransactionRequestResponseFutureVO).isNotNull();
        CreateTransactionRequestResponseVO createTransactionRequestResponseVO = createTransactionRequestResponseFutureVO.get();
        assertThat(createTransactionRequestResponseVO).isNotNull();

        PowerMockito.verifyNew(JsonRpcUtils.class, Mockito.times(1)).withNoArguments();
        Mockito.verify(mockJsonRpcUtils, Mockito.times(1)).sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void testCancelTransactionRequest_Success() throws Exception {
        CancelTransactionRequestRequestVO cancelTransactionRequestRequestVO = ImmutableCancelTransactionRequestRequestVO.builder()
                .setAuth("xxxxxxxx")
                .setTxrId("123456")
                .build();

        Boolean response = true;

        JsonRpcUtils mockJsonRpcUtils = PowerMockito.mock(JsonRpcUtils.class);
        PowerMockito.whenNew(JsonRpcUtils.class).withNoArguments().thenReturn(mockJsonRpcUtils);
        Mockito.when(mockJsonRpcUtils.sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class))).thenReturn(response);

        transactionRequestsAsyncService = new TransactionRequestsAsyncServiceImpl(enjinConfig);
        Future<CancelTransactionRequestResponseVO> cancelTransactionRequestResponseFutureVO = transactionRequestsAsyncService.cancelTransactionRequestAsync(cancelTransactionRequestRequestVO);
        assertThat(cancelTransactionRequestResponseFutureVO).isNotNull();
        CancelTransactionRequestResponseVO cancelTransactionRequestResponseVO = cancelTransactionRequestResponseFutureVO.get();
        assertThat(cancelTransactionRequestResponseVO).isNotNull()
                .satisfies(o -> assertThat(o.getResult()).isPresent()
                        .hasValueSatisfying(v -> assertThat(v).isTrue()));

        PowerMockito.verifyNew(JsonRpcUtils.class, Mockito.times(1)).withNoArguments();
        Mockito.verify(mockJsonRpcUtils, Mockito.times(1)).sendJsonRpcRequest(Mockito.anyString(), Mockito.any(), Mockito.anyString(), Mockito.isA(Map.class));
    }

}