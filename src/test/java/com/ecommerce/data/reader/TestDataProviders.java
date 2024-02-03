package com.ecommerce.data.reader;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static com.ecommerce.constants.DataFilesPathConstants.CHECKOUT_DATA_FILE_PATH;

public class TestDataProviders {

    DataReader reader;

    public TestDataProviders(){
        reader = new DataReader();
    }

    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() throws IOException {
        return reader.readAndProvideJsonData(CHECKOUT_DATA_FILE_PATH);
    }

}
