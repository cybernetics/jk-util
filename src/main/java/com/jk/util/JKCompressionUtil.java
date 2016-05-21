package com.jk.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.jk.exceptions.handler.JKExceptionUtil;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class JKCompressionUtil {

	public static void compress(String fileName, String compressedFileName, String password) {
		try {
			ZipParameters zipParameters = new ZipParameters();
			zipParameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			zipParameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
			if (password != null) {
				zipParameters.setEncryptFiles(true);
				zipParameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
				zipParameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
				zipParameters.setPassword(password);
			}
			String destinationZipFilePath = compressedFileName;
			ZipFile zipFile = new ZipFile(destinationZipFilePath);
			zipFile.addFile(new File(fileName), zipParameters);
		} catch (ZipException e) {
			JKExceptionUtil.handle(e);
		}
	}

	public void decompress(String sourceZipFilePath, String extractedZipFilePath, String password) {
		try {
			ZipFile zipFile = new ZipFile(sourceZipFilePath);
			if (zipFile.isEncrypted()) {
				zipFile.setPassword(password);
			}

			zipFile.extractAll(extractedZipFilePath);
		} catch (Exception e) {
			JKExceptionUtil.handle(e);
		}
	}

}
