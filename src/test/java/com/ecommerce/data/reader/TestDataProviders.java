package com.ecommerce.data.reader;

import org.testng.annotations.DataProvider;

import java.io.IOException;

import static com.ecommerce.constants.DataFilesPathConstants.CHECKOUT_DATA_FILE_PATH;

public class TestDataProviders {



    @DataProvider(name = "checkoutData")
    public Object[][] checkoutData() throws IOException {
        DataReader reader = new DataReader();
        return reader.readAndProvideJsonData(CHECKOUT_DATA_FILE_PATH);
    }

}
