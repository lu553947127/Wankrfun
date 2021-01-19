package com.wankrfun.crania.http.conver;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Converter;

/**
 * @ProjectName: Wankrfun
 * @Package: com.wankrfun.crania.http.conver
 * @ClassName: AppRequestBodyConverter
 * @Description: 自定义的Gson解析工厂
 * @Author: 鹿鸿祥
 * @CreateDate: 1/7/21 10:06 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 1/7/21 10:06 AM
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppRequestBodyConverter<T> implements Converter<T, RequestBody> {

    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8      = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    AppRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override public RequestBody convert(T value) throws IOException {
        Buffer buffer = new Buffer();
        Writer writer = new OutputStreamWriter(buffer.outputStream(), UTF_8);
        JsonWriter jsonWriter = gson.newJsonWriter(writer);
        adapter.write(jsonWriter, value);
        jsonWriter.close();
        return RequestBody.create(MEDIA_TYPE, buffer.readByteString());
    }
}
