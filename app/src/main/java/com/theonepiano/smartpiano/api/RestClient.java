package com.theonepiano.smartpiano.api;

import com.theonepiano.smartpiano.api.home.HomeService;

/**
 * Created by jim on 2017/6/18.
 */

public class RestClient {

    private static HomeService sHomeService;

    private RestClient() {
    }

    public static RestClient getInstance() {
        return RestClientHolder.INSTANCE;
    }

    public HomeService getHomeService() {
        if (sHomeService == null) {
            synchronized (HomeService.class) {
                if (sHomeService == null) {

                }
            }
        }

        return sHomeService;
    }

    private static class RestClientHolder {
        private static final RestClient INSTANCE = new RestClient();
    }
}
