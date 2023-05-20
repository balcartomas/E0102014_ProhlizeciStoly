package com.veba.e0102014_prohlizecistoly.data.security

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec
import kotlin.random.Random

class CryptoManager {

    fun encrypt(data: ByteArray): ByteArray {
        val iv = generateRandomIv()
        val cipher = getEncryptCipher(iv)

        val encrypted = cipher.doFinal(data)
        val encryptedDataWithIv = ByteArray(IV_SIZE + encrypted.size)
        System.arraycopy(iv, 0, encryptedDataWithIv, 0, IV_SIZE)
        System.arraycopy(encrypted, 0, encryptedDataWithIv, IV_SIZE, encrypted.size)

        return encryptedDataWithIv
    }

    fun decrypt(data: ByteArray): ByteArray {
        val iv = ByteArray(IV_SIZE)
        System.arraycopy(data, 0, iv, 0, IV_SIZE)

        val cipher = getDecryptCipherForIv(iv)
        return cipher.doFinal(data, IV_SIZE, data.size - IV_SIZE)
    }

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    private fun getEncryptCipher(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.ENCRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun getDecryptCipherForIv(iv: ByteArray): Cipher {
        return Cipher.getInstance(TRANSFORMATION).apply {
            init(Cipher.DECRYPT_MODE, getKey(), IvParameterSpec(iv))
        }
    }

    private fun generateRandomIv(): ByteArray {
        val iv = ByteArray(IV_SIZE)
        Random.Default.nextBytes(iv)
        return iv
    }

    private fun getKey(): SecretKey {
        val existingKey = keyStore.getEntry(SECRET, null) as? KeyStore.SecretKeyEntry
        return existingKey?.secretKey ?: createKey()
    }

    private fun createKey(): SecretKey {
        return KeyGenerator.getInstance(ALGORITHM).apply {
            init(
                KeyGenParameterSpec.Builder(
                    SECRET,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(BLOCK_MODE)
                    .setEncryptionPaddings(PADDING)
                    .setUserAuthenticationRequired(false)
                    .setRandomizedEncryptionRequired(true)
                    .build()
            )
        }.generateKey()
    }

    companion object {
        private const val SECRET = "secret"

        private const val IV_SIZE = 16
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_CBC
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_PKCS7
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"

    }
}