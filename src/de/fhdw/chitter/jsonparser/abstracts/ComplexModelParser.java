package de.fhdw.chitter.jsonparser.abstracts;

import org.json.simple.JSONObject;

public abstract class ComplexModelParser<T> extends Parser<T> {

    public abstract JSONObject convertToJsonObject(T model);

    public abstract T convertFromJsonObject(JSONObject object);

}
