package com.muhammadelsayed.echo.model;

public class Retrosponse {
   private ResposneModel response;

    public ResposneModel getResponse() {
        return response;
    }

    public void setResponse(ResposneModel response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "Retrosponse{" +
                "response=" + response +
                '}';
    }
}
