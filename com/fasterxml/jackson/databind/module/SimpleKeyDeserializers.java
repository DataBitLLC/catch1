package com.fasterxml.jackson.databind.module;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.KeyDeserializer;
import com.fasterxml.jackson.databind.deser.KeyDeserializers;
import com.fasterxml.jackson.databind.type.ClassKey;
import java.io.Serializable;
import java.util.HashMap;

public class SimpleKeyDeserializers implements KeyDeserializers, Serializable {
    private static final long serialVersionUID = 1;
    protected HashMap<ClassKey, KeyDeserializer> _classMappings;

    public SimpleKeyDeserializers() {
        this._classMappings = null;
    }

    public SimpleKeyDeserializers addDeserializer(Class<?> forClass, KeyDeserializer deser) {
        if (this._classMappings == null) {
            this._classMappings = new HashMap();
        }
        this._classMappings.put(new ClassKey(forClass), deser);
        return this;
    }

    public KeyDeserializer findKeyDeserializer(JavaType type, DeserializationConfig config, BeanDescription beanDesc) {
        if (this._classMappings == null) {
            return null;
        }
        return (KeyDeserializer) this._classMappings.get(new ClassKey(type.getRawClass()));
    }
}
