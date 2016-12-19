package ch.heigvd.amt.gamification.spec.steps;

import ch.heigvd.gamification.api.dto.Application;

/**
 * Created by guillaume on 18.12.16.
 */
public class SharedData {
    private int statusCode;
    private int applicationCounter = 0;
    private Application application;

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
}
