package dev.jsinco.canvord.objects.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import dev.jsinco.canvord.objects.Meeting;

import java.io.IOException;

public class MeetingTypeAdapter extends TypeAdapter<Meeting> {
    @Override
    public void write(JsonWriter out, Meeting value) throws IOException {

    }

    @Override
    public Meeting read(JsonReader in) throws IOException {
        return null;
    }
}
