package com.github.mixinmatch.appointments.Controllers;

import com.github.mixinmatch.appointments.models.Item;
import com.microsoft.azure.functions.*;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.function.context.FunctionCatalog;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class Controller {

    @Autowired
    private FunctionCatalog functionCatalog;

    @FunctionName("Health")
    public HttpResponseMessage run(
            @HttpTrigger(name ="request", methods = {HttpMethod.GET}, route = "health", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
                                   ExecutionContext executionContext) {
        Supplier<String> fn = functionCatalog.lookup(Supplier.class, "Health");
        return request.createResponseBuilder(HttpStatus.OK)
                .body(fn.get())
                .header("Content-Type", "application/json")
                .build();
    }


    @FunctionName("GetItems")
    public HttpResponseMessage run2(
            @HttpTrigger(name="request", methods = {HttpMethod.GET}, route="items", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            ExecutionContext executionContext
        ) {
        Supplier<Collection<Item>> fn = functionCatalog.lookup(Supplier.class, "GetItems");

        return request.createResponseBuilder(HttpStatus.OK)
                .body(fn.get())
                .header("Content-Type", "application/json")
                .build();
    }

    @FunctionName("GetItem")
    public HttpResponseMessage run3(
            @HttpTrigger(name="request", methods = {HttpMethod.GET}, route="item/{uuid}", authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<String>> request,
            @BindingName("uuid") String uuid,
            ExecutionContext executionContext
    ) {
        Function<String, String> bean = functionCatalog.lookup(Function.class, "GetItem");
        return request.createResponseBuilder(HttpStatus.OK)
                .body(bean.apply(uuid))
                .header("Content-Type", "application/json")
                .build();
    }
}
