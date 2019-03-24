package com.boku;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SpringBootConsoleApplicationTest {

	@InjectMocks private SpringBootConsoleApplication myLauncher;
	
	@Test
	public void testMain() throws Exception {
		SpringBootConsoleApplication.main(new String[] {});
	}
}

