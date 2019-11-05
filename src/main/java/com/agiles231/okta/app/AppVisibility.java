package com.agiles231.okta.app;

import java.util.Map;

public class AppVisibility {

    private final Boolean autoSubmitToolbar;
    private final Hide hide;
    private final Map<String, Boolean> appLinks;
    
    public AppVisibility(Boolean autoSubmitToolbar, Hide hide, Map<String, Boolean> appLinks) {
        super();
        this.autoSubmitToolbar = autoSubmitToolbar;
        this.hide = hide;
        this.appLinks = appLinks;
    }

    public Boolean getAutoSubmitToolbar() {
        return autoSubmitToolbar;
    }

    public Hide getHide() {
        return hide;
    }

    public Map<String, Boolean> getAppLinks() {
        return appLinks;
    }
    
    public class Hide {
        private final Boolean iOS;
        private final Boolean web;
        
        public Hide(Boolean iOS, Boolean web) {
            super();
            this.iOS = iOS;
            this.web = web;
        }

        public Boolean getiOS() {
            return iOS;
        }

        public Boolean getWeb() {
            return web;
        }
        
        
    }
}
