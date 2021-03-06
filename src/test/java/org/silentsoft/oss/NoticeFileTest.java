package org.silentsoft.oss;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;
import org.silentsoft.oss.license.ApacheLicense2_0;

public class NoticeFileTest {
	
	private static final License APACHE_LICENSE_2_0 = new ApacheLicense2_0();
	
	@Test
	public void noticeFileTest() throws Exception {
		String markdown = generateNoticeMarkdown();
		
		System.out.println("--------START OF THE NOTICE FILE--------");
		System.out.println(markdown);
		System.out.println("---------END OF THE NOTICE FILE---------");
		
		Assert.assertEquals(markdown, readFile());
	}
	
	private String generateNoticeMarkdown() {
		return NoticeFileGenerator.newInstance("JSON Beautifier", "silentsoft.org")
			.addText("This product includes software developed by silentsoft.org.")
			.addText("This product includes software developed by The Apache Software Foundation (http://www.apache.org/).")
			.addLibrary("material icons", "https://material.io/icons/", APACHE_LICENSE_2_0)
			.generate();
	}
	
	private String readFile() throws Exception {
		return String.join("\r\n", Files.readAllLines(Paths.get(System.getProperty("user.dir"), "NOTICE.md"), StandardCharsets.UTF_8));
	}

}
