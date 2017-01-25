package ch.heigvd.amt.gamification;

import ch.heigvd.gamification.*;
import ch.heigvd.gamification.api.dto.Badge;
import ch.heigvd.gamification.api.dto.PointScale;
import com.google.gson.reflect.TypeToken;
import com.squareup.okhttp.Call;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Ornidon on 19.12.2016.
 */
public class ExtendedAPI {
    private ApiClient apiClient;

    public ExtendedAPI() {
        this(Configuration.getDefaultApiClient());
    }

    public ExtendedAPI(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiResponse<Object> callWithParams(String method, String path, Object body, String token, String returnType) throws ApiException {

        String localVarPath = path.replaceAll("\\{format\\}", "json");

        List<Pair> localVarQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if (token != null)
            localVarHeaderParams.put("token", token);

        String[] localVarAuthNames = new String[]{};

        Call call = apiClient.buildCall(localVarPath, method, localVarQueryParams, body, localVarHeaderParams, localVarFormParams, localVarAuthNames, null);

        Type localVarReturnType = new TypeToken<List<PointScale>>() {
        }.getType();

        if (returnType.equals("PointScales")) {
            localVarReturnType = new TypeToken<List<PointScale>>() {
            }.getType();
        } else if (returnType.equals("Badge")) {
            localVarReturnType = new TypeToken<Badge>() {
            }.getType();
        }


        return apiClient.execute(call, localVarReturnType);
    }
}
