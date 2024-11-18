package com.example.miniproyecto_3_battlership.model.serializable;

import java.io.Serializable;

public interface ISerializableFileHandler{
    void serialize(String fileName, Object element);
    Object deserialize(String fileName);
}
