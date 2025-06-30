using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Security.Cryptography;
using System.Text;
using System.Web;

namespace Web.Encriptacion
{
    public class CryptoUtil
    {
        // Clave secreta (32 caracteres = 256 bits para AES-256)
        private static readonly byte[] keyBytes = Encoding.ASCII.GetBytes("12345678901234567890123456789012"); // 32 bytes
        private static readonly byte[] ivBytes = Encoding.ASCII.GetBytes("1234567890123456"); // 16 bytes


        public static string Encrypt(string Desencriptado)
        {
            byte[] Encriptado = Encoding.UTF8.GetBytes(Desencriptado);

            using (Aes aes = Aes.Create())
            {
                aes.Key = keyBytes;
                aes.IV = ivBytes;
                aes.Padding = PaddingMode.PKCS7;

                using (var encryptor = aes.CreateEncryptor())
                using (var ms = new MemoryStream())
                {
                    using (var cryptoStream = new CryptoStream(ms, encryptor, CryptoStreamMode.Write))
                    {
                        cryptoStream.Write(Encriptado, 0, Encriptado.Length);
                        cryptoStream.FlushFinalBlock();
                    }
                    return Convert.ToBase64String(ms.ToArray());
                }
            }
        }

        public static string Decrypt(string Encriptado)
        {
            byte[] Desencriptado = Convert.FromBase64String(Encriptado);

            using (Aes aes = Aes.Create())
            {
                aes.Key = keyBytes;
                aes.IV = ivBytes;
                aes.Padding = PaddingMode.PKCS7;

                using (var decryptor = aes.CreateDecryptor())
                using (var ms = new MemoryStream(Desencriptado))
                using (var cryptoStream = new CryptoStream(ms, decryptor, CryptoStreamMode.Read))
                using (var reader = new StreamReader(cryptoStream))
                {
                    return reader.ReadToEnd();
                }
            }
        }
    }
}