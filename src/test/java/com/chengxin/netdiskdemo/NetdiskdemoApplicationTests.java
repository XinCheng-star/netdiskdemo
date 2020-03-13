package com.chengxin.netdiskdemo;

import com.chengxin.netdiskdemo.util.FileUtil;
import com.chengxin.netdiskdemo.util.IDUtil;
import com.chengxin.netdiskdemo.util.MD5Util;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

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

	@Test
	void is_get_filename_from_path_true(){
		String path = "/folderName/fileName.txt";
		System.out.println(FileUtil.getFileFolderFromPath(path).equals("folderName"));
		System.out.println(FileUtil.getFileNameFromPath(path).endsWith("fileName"));
	}

}
