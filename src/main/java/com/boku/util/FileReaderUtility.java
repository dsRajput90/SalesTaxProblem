package com.boku.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
/**
 * File reader utility which reads the content from the file and returns list of string back to the calling method
 * */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class FileReaderUtility {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(FileReaderUtility.class);
	
	public List<String> readFile(String filePath) throws IOException, URISyntaxException{
		List<String> response = Collections.emptyList();
	    LOGGER.info("Reading file content from filePath {}", filePath);		

		try (Stream<String> stream = Files.lines(Paths.get(this.getClass().getClassLoader()
			      .getResource("./" + filePath).toURI()))) {
			response = stream.collect(Collectors.toList());
		} catch (IOException e) {
			LOGGER.error("IOException occurred while retrieving data", e);
			throw e;
		}
		return response;

	}

}
