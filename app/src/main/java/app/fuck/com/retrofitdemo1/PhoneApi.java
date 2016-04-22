package app.fuck.com.retrofitdemo1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Owner on 2016-4-22.
 */
public class PhoneApi {

    /**
     * HOST地址
     */
    public static final String BASE_URL = "http://apis.baidu.com";
    /**
     * 开发者key
     */
    public static final String API_KEY = "1f3dc397630fb771fa0d08f952f4305e";

    /**
     * 获取PhoneApi实例
     * @return
     */
    public static PhoneApi getApi(){
        return ApiHolder.phoneApi;
    }

    static class ApiHolder{
        private static PhoneApi phoneApi = new PhoneApi();
    }

    private PhoneService service;

    private PhoneApi(){
        //1.创建Retrofit对象
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)//主机地址
                .addConverterFactory(GsonConverterFactory.create())//解析方法
                .build();
        //2.创建访问API的请求
         service = retrofit.create(PhoneService.class);
    }

    /**
     * 获取PhoneService实例
     * @return
     */
    public PhoneService getService(){
        return service;
    }

}
