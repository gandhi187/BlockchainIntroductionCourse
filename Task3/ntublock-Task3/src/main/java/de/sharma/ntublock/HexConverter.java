/**
 * 
 */
package de.sharma.ntublock;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import javax.xml.bind.DatatypeConverter;

/**
 * @author David Sharma (david.sharma@gmx.de)
 * 
 */
public class HexConverter {

	public String calculateHex() {
		File file = new File("src/main/java/de/sharma/ntublock/Task3_SecondTerm.java");
		String hex = (DatatypeConverter.printHexBinary(checksum(file)));
		return hex;
	}

	public byte[] checksum(File input) {
		try (InputStream in = new FileInputStream(input)) {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] block = new byte[4096];
			int length;
			while ((length = in.read(block)) > 0) {
				digest.update(block, 0, length);
			}
			return digest.digest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
