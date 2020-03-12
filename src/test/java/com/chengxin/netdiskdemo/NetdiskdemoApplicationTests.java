package com.chengxin.netdiskdemo;

import com.chengxin.netdiskdemo.util.IDUtil;
import com.chengxin.netdiskdemo.util.MD5Util;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

@SpringBootTest
class NetdiskdemoApplicationTests {

	@Test
	void contextLoads() throws IOException {
		System.out.println(IDUtil.getId().length()+"   "+IDUtil.getId());

		String encrpyPwd =  MD5Util.getMd5FromPwd("pWd");
		System.out.println( encrpyPwd.length()+ "  "+encrpyPwd);

		File file = new File("file_table.sql");
		FileInputStream fileInputStream = new FileInputStream(file);
		byte[] bytes = new byte[(int) (file.getUsableSpace()+1)];
		fileInputStream.read(bytes);
		String encryFile = MD5Util.getMd5FromFile(bytes);
		System.out.println("FILE :::  "+ encryFile);



	}

}
