package com.ecommerce.data.reader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import static org.hager.utils.Constants.projectPath;

public class DataReader {

    private static final String DATA_FILE_PATH = "//src//test//java//data//files//";

    public List<HashMap<String, String>> convertJsonDataToMap(String fileName) throws IOException {
        String jsonContent = FileUtils.readFileToString(new File(projectPath + DATA_FILE_PATH + fileName), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(jsonContent, new TypeReference<>() {

        });
    }
}
