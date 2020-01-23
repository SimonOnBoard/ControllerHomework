package com.itis.javalab.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.itis.javalab.models.Product;

import java.io.IOException;

public class ProductSerializer extends StdSerializer<Product> {

    public ProductSerializer() {
        this(null);
    }

    public ProductSerializer(Class<Product> t) {
        super(t);
    }
    @Override
    public void serialize(Product product, JsonGenerator jgen, SerializerProvider serializerProvider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("id", product.getId());
        jgen.writeStringField("name", product.getName());
        jgen.writeStringField("price", product.getPrice().toString());
        jgen.writeStringField("count", product.getCount().toString());
        jgen.writeStringField("ended", product.getEnded().toString());
        jgen.writeStringField("photoPath", product.getPhotoPath());
        jgen.writeEndObject();
    }
}
