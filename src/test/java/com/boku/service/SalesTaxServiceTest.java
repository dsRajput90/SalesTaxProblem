package com.boku.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.boku.util.FileReaderUtility;
import com.boku.util.ResponseCodes;

@RunWith(MockitoJUnitRunner.class)
public class SalesTaxServiceTest {

	@InjectMocks private SalesTaxService myLauncher;
	
	@InjectMocks private ItemDetailsService itemDetailsService;
	
	@InjectMocks private FileReaderUtility fileReader;
	
	@InjectMocks 
    private int fileCount;
	
	@Test
	public void testCalculateSalesTaxEmptyFileCount() throws Exception {
		ReflectionTestUtils.setField(myLauncher, "fileCount", 0, Integer.class);
		String response = myLauncher.calculateSalesTax();
		Assert.assertNotNull(response);
		Assert.assertEquals(ResponseCodes.EMPTY.getValue(), response);
	}

	@Test
	public void testCalculateSalesTaxNoFiles() throws Exception {
		ReflectionTestUtils.setField(myLauncher, "fileCount", 1, Integer.class);
		ReflectionTestUtils.setField(myLauncher, "filePath", null, String.class);
		
		String response = myLauncher.calculateSalesTax();
		Assert.assertNotNull(response);
		Assert.assertEquals(ResponseCodes.EMPTY.getValue(), response);
	}
	
	@Test
	public void testCalculateSalesTaxNoFileType() throws Exception {
		ReflectionTestUtils.setField(myLauncher, "fileCount", 1, Integer.class);
		ReflectionTestUtils.setField(myLauncher, "filePath", null, String.class);
		ReflectionTestUtils.setField(myLauncher, "fileType", null, String.class);
		
		String response = myLauncher.calculateSalesTax();
		Assert.assertNotNull(response);
		Assert.assertEquals(ResponseCodes.EMPTY.getValue(), response);
	}

	@Test
	public void testCalculateSalesTaxMissingFiles() throws Exception {
		ReflectionTestUtils.setField(myLauncher, "fileCount", 1, Integer.class);
		ReflectionTestUtils.setField(myLauncher, "filePath", "files", String.class);
		ReflectionTestUtils.setField(myLauncher, "fileType", ".txt", String.class);
		ReflectionTestUtils.setField(myLauncher, "fileReader",fileReader, FileReaderUtility.class);
        
		String response = myLauncher.calculateSalesTax();
		Assert.assertNotNull(response);
		Assert.assertEquals(ResponseCodes.FILE_INPUT_ERROR.getValue(), response);
	}

	@Test
	public void testCalculateSalesTaxWithEmptyFiles() throws Exception {
		ReflectionTestUtils.setField(myLauncher, "fileCount", 1, Integer.class);
		ReflectionTestUtils.setField(myLauncher, "filePath", "empty", String.class);
		ReflectionTestUtils.setField(myLauncher, "fileType", ".txt", String.class);
		ReflectionTestUtils.setField(myLauncher, "fileReader",fileReader, FileReaderUtility.class);
		
		String response = myLauncher.calculateSalesTax();
		Assert.assertNotNull(response);
		Assert.assertEquals(ResponseCodes.EMPTY_FILE.getValue(), response);
	}
	
	@Test
	public void testCalculateSalesTaxWithInvalidFiles() throws Exception {
		ReflectionTestUtils.setField(myLauncher, "fileCount", 1, Integer.class);
		ReflectionTestUtils.setField(myLauncher, "filePath", "invalid", String.class);
		ReflectionTestUtils.setField(myLauncher, "fileType", ".txt", String.class);
		ReflectionTestUtils.setField(myLauncher, "fileReader",fileReader, FileReaderUtility.class);
		ReflectionTestUtils.setField(myLauncher, "itemDetailsService" , new ItemDetailsService(), ItemDetailsService.class);
		
		String response = myLauncher.calculateSalesTax();
		Assert.assertNotNull(response);
		Assert.assertEquals(ResponseCodes.EMPTY.getValue(), response);
	}
}
