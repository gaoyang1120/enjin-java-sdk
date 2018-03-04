package io.enjincoin.sdk.client.service.identities;

import java.io.IOException;
import java.util.Map;

import io.enjincoin.sdk.client.service.identities.vo.CreateIdentityRequestBody;
import io.enjincoin.sdk.client.service.identities.vo.CreateIdentityResponseBody;
import io.enjincoin.sdk.client.service.identities.vo.GetIdentityResponseBody;
import io.enjincoin.sdk.client.service.identities.vo.LinkIdentityRequestBody;
import io.enjincoin.sdk.client.service.identities.vo.LinkIdentityResponseBody;
import io.enjincoin.sdk.client.service.identities.vo.UpdateIdentityRequestBody;
import io.enjincoin.sdk.client.service.identities.vo.UpdateIdentityResponseBody;
import retrofit2.Response;

public interface SynchronousIdentitiesService {

    Response<GetIdentityResponseBody[]> getIdentitiesSync() throws IOException;

    Response<GetIdentityResponseBody[]> getIdentitiesSync(Map<String, Object> filter) throws IOException;

    Response<GetIdentityResponseBody> getIdentitySync(int id) throws IOException;

    Response<CreateIdentityResponseBody> createIdentitySync() throws IOException;

    Response<CreateIdentityResponseBody> createIdentitySync(CreateIdentityRequestBody request) throws IOException;

    Response<UpdateIdentityResponseBody> updateIdentitySync(Integer identityId, UpdateIdentityRequestBody request) throws IOException;

    Response<Boolean> deleteIdentitySync(final Integer identityId) throws IOException;

    Response<LinkIdentityResponseBody> linkIdentitySync(String linkingCode, LinkIdentityRequestBody linkIdentityRequestVO) throws IOException;

}