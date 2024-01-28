package com.handonn.finapp.accounts.config;

import com.handonn.finapp.common.model.BaseResponse;
import feign.Response;
import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;

import java.io.IOException;
import java.lang.reflect.Type;

public class CustomFeignDecoder implements Decoder {
    private final Decoder delegate;

    public CustomFeignDecoder(ObjectFactory<HttpMessageConverters> messageConverters) {
        this.delegate = new SpringDecoder(messageConverters);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        Object decodedObject = delegate.decode(response, type);

        if (decodedObject instanceof BaseResponse<?> baseResponse) {
            return baseResponse.getData();
        }

        return decodedObject;
    }
}
