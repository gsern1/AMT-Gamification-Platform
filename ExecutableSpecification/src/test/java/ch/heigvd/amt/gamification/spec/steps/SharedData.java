package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.amt.gamification.ExtendedAPI;
import ch.heigvd.gamification.ApiResponse;
import ch.heigvd.gamification.api.dto.Application;
import ch.heigvd.gamification.api.dto.Badge;

import ch.heigvd.gamification.api.dto.Credentials;
import ch.heigvd.gamification.api.dto.Token;

/**
 * Created by guillaume on 18.12.16.
 */
public class SharedData {

    public static final String POINTSCALE_RULE_NAME1 = "abc";
    public static final String BADGES_RULE_NAME1 = "Te";

    private int statusCode;
    private int applicationCounter = 0;
    private Application application;
    private ExtendedAPI api;
    private Token token;
    private Credentials credentials;
    private ApiResponse response;

    private Badge badge;
    private long badgeNbr;
    private long badgeRuleNbr;
    private long pointScaleRuleNbr;

    private long pointScaleNbr;



    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getApplicationCounter() {
        return applicationCounter;
    }

    public void setApplicationCounter(int applicationCounter) {
        this.applicationCounter = applicationCounter;
    }

    public void incrementApplicationCounter() {
        applicationCounter++;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public ExtendedAPI getApi() {
        return api;
    }

    public void setApi(ExtendedAPI api) {
        this.api = api;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public ApiResponse getResponse() {
        return response;
    }

    public void setResponse(ApiResponse response) {
        this.response = response;
    }

    public long getBadgeNbr() {
        return badgeNbr;
    }

    public void setBadgeNbr(long badgeNbr) {
        this.badgeNbr = badgeNbr;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public long getPointScaleNbr() {
        return pointScaleNbr;
    }

    public void setPointScaleNbr(long pointScaleNbr) {
        this.pointScaleNbr = pointScaleNbr;
    }

    public long getBadgeRuleNbr() {
        return badgeRuleNbr;
    }

    public void setBadgeRuleNbr(long badgeRuleNbr) {
        this.badgeRuleNbr = badgeRuleNbr;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public long getPointScaleRuleNbr() {
        return pointScaleRuleNbr;
    }

    public void setPointScaleRuleNbr(long pointScaleRuleNbr) {
        this.pointScaleRuleNbr = pointScaleRuleNbr;
    }
}
