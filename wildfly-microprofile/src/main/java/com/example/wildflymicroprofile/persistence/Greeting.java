package com.example.wildflymicroprofile.persistence;

import org.mongodb.morphia.annotations.*;

@Entity(value = "greetings", noClassnameStored = true)
@Indexes(@Index(fields = @Field("id")))
public class Greeting {

    @Id
    private String serviceName;

    private String text;

    public Greeting() {
    }

    private Greeting(Builder builder) {
        setServiceName(builder.serviceName);
        setText(builder.text);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Greeting copy) {
        Builder builder = new Builder();
        builder.serviceName = copy.getServiceName();
        builder.text = copy.getText();
        return builder;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Greeting greeting = (Greeting) o;

        if (serviceName != null ? !serviceName.equals(greeting.serviceName) : greeting.serviceName != null)
            return false;
        return text != null ? text.equals(greeting.text) : greeting.text == null;
    }

    @Override
    public int hashCode() {
        int result = serviceName != null ? serviceName.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }


    public static final class Builder {
        private String serviceName;
        private String text;

        private Builder() {
        }

        public Builder serviceName(String val) {
            serviceName = val;
            return this;
        }

        public Builder text(String val) {
            text = val;
            return this;
        }

        public Greeting build() {
            return new Greeting(this);
        }
    }
}
