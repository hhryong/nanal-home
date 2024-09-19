/**
 * 기존 MGOV에서 사용 하는 암호화
 */

package com.nanal.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class AES
{
	private final String KEY_ALGORITHM = "AES";
	private final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

	private final byte[] initialVector =
	{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,
			0x00};

	private final String PADDIND = "\0";
	private final int KEY_BYTE = 16;

	private final Cipher cipher;
	private final Key keySpec;
	private final IvParameterSpec ivParameterSpec;

	public AES(String key) throws Exception
	{
		if (key.getBytes().length < KEY_BYTE)
			key = new String(CommonUtil.paddingStringtoByte(KEY_BYTE, PADDIND,
					CommonUtil.cutString(KEY_BYTE, key)));

		keySpec = generateKey(KEY_ALGORITHM, key.getBytes());

		ivParameterSpec = new IvParameterSpec(initialVector);

		cipher = Cipher.getInstance(CIPHER_ALGORITHM);

	} // END AES ������

	public AES() throws Exception
	{
		SecretKeySpec keySpec2 = new SecretKeySpec(initialVector, KEY_ALGORITHM);
		this.keySpec = keySpec2;

		ivParameterSpec = new IvParameterSpec(initialVector);

		cipher = Cipher.getInstance(CIPHER_ALGORITHM);

	} // END AES ������

	public byte[] encrypt(byte[] value) throws Exception
	{
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

		return cipher.doFinal(value);
	}

	public byte[] encrypt(String value) throws Exception
	{
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivParameterSpec);

		return cipher.doFinal(value.getBytes());
	}

	public byte[] decrypt(byte[] value) throws Exception
	{
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

		return cipher.doFinal(value);
	}

	public byte[] decrypt(String value) throws Exception
	{
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivParameterSpec);

		return cipher.doFinal(value.getBytes());
	}

	public Key generateKey(String algorithm, byte[] keyData)
			throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException
	{
		String upper = algorithm.toUpperCase();

		if ("DES".equals(upper))
		{
			KeySpec keySpec = new DESKeySpec(keyData);

			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);

			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

			return secretKey;

		}
		else if ("DESede".equals(upper) || "TripleDES".equals(upper))
		{

			KeySpec keySpec = new DESedeKeySpec(keyData);

			SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(algorithm);

			SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);

			return secretKey;

		}
		else
		{

			SecretKeySpec keySpec = new SecretKeySpec(keyData, algorithm);

			return keySpec;
		}
	} // END generateKey
}
