package com.enjin.coin.sdk.service.events.impl;

import com.enjin.coin.sdk.config.Config;
import com.enjin.coin.sdk.service.BaseService;
import com.enjin.coin.sdk.service.events.EventsService;
import com.enjin.coin.sdk.util.ArrayUtils;
import com.enjin.coin.sdk.util.Constants;
import com.enjin.coin.sdk.util.ObjectUtils;
import com.enjin.coin.sdk.util.StringUtils;
import com.enjin.coin.sdk.vo.event.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <p>Synchronous implementation of EventsService</p>
 */
public class EventsServiceImpl extends BaseService implements EventsService {

    private static final Logger LOGGER = Logger.getLogger(EventsServiceImpl.class.getName());

    /**
     * Class constructor
     *
     * @param enjinConfig - the enjinConfig to use
     */
    public EventsServiceImpl(Config enjinConfig) {
        super(enjinConfig);
    }

    @Override
    public GetEventResponseVO getEvent(GetEventRequestVO getEventRequestVO) {
        GetEventResponseVO getEventResponseVO = null;

        if (ObjectUtils.isNull(getEventRequestVO) || StringUtils.isEmpty(getEventRequestVO.getAuth())
                || StringUtils.isEmpty(getEventRequestVO.getEventId())) {
            LOGGER.warning("getEventRequestVO is null, auth or eventId passed in are null or empty");
            return getEventResponseVO;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("auth", getEventRequestVO.getAuth().get());
        params.put("event_id", getEventRequestVO.getEventId().get());

        // Construct new request
        String method = Constants.METHOD_EVENTS_GET;

        getEventResponseVO = (GetEventResponseVO) jsonRpcUtils.sendJsonRpcRequest(getEventsUrl(), GetEventResponseVO.class, method, params);

        return getEventResponseVO;
    }

    @Override
    public ListEventsResponseVO listEvents(ListEventsRequestVO listEventsRequestVO) {
        ListEventsResponseVO listEventsResponseVO = null;

        if (ObjectUtils.isNull(listEventsRequestVO) || StringUtils.isEmpty(listEventsRequestVO.getAuth())
                || StringUtils.isEmpty(listEventsRequestVO.getAppId())
                || StringUtils.isEmpty(listEventsRequestVO.getAfterEventId())
                || StringUtils.isEmpty(listEventsRequestVO.getLimit())) {
            LOGGER.warning("listEventsRequestVO is null, auth, appId, afterEventId or limit passed in are null or empty");
            return listEventsResponseVO;
        }

        Map<String, Object> params = new HashMap<>();
        params.put("auth", listEventsRequestVO.getAuth().get());
        params.put("app_id", listEventsRequestVO.getAppId().get());
        params.put("after_Event_id", listEventsRequestVO.getAfterEventId().get());
        params.put("limit", listEventsRequestVO.getLimit().get());

        // Construct new request
        String method = Constants.METHOD_EVENTS_LIST;

        GetEventResponseVO[] getEventResponseVOArray = (GetEventResponseVO[]) jsonRpcUtils.sendJsonRpcRequest(getEventsUrl(), GetEventResponseVO[].class, method, params);
        if (ArrayUtils.isEmpty(getEventResponseVOArray)) {
            LOGGER.warning("No Events returned");
            return listEventsResponseVO;
        }
        listEventsResponseVO = ImmutableListEventsResponseVO.builder()
                .setGetEventsResponseVOArray(getEventResponseVOArray)
                .build();

        return listEventsResponseVO;
    }

}