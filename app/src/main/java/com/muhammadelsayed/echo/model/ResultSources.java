package com.muhammadelsayed.echo.model;

import java.util.Arrays;

public class ResultSources {
    private String status;
    private Source[] sources;

    public ResultSources() {
    }

    public ResultSources(String status, Source[] sources) {
        this.status = status;
        this.sources = sources;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Source[] getSources() {
        return sources;
    }

    public void setSources(Source[] sources) {
        this.sources = sources;
    }

    @Override
    public String toString() {
        return "ResultSources{" +
                "status='" + status + '\'' +
                ", sources=" + Arrays.toString(sources) +
                '}';
    }
}
