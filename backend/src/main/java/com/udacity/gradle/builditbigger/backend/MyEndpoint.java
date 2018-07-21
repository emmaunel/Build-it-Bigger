package com.udacity.gradle.builditbigger.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.wordpress.ayo218.javalibrary.JokeTelling;

/** An endpoint class we are exposing */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.builditbigger.gradle.udacity.com",
                ownerName = "backend.builditbigger.gradle.udacity.com",
                packagePath = ""
        )
)
public class MyEndpoint {

    /** A simple endpoint method that takes a name and says Hi back */
//    @ApiMethod(name = "sayHi")
//    public MyBean sayHi(MyBean bean) {
////        MyBean response = new MyBean();
////        response.setData("Hi, " + name);
//
//        return bean;
//    }

    @ApiMethod(name = "getJoke")
    public MyBean getJoke(){
        MyBean bean = new MyBean();
        bean.setData(JokeTelling.getJoke());
        return bean;
    }

}
