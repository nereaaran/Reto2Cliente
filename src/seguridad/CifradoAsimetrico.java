/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seguridad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Clase que contiene el metodo para cifrar asimetricamente la contraseña que se
 * envia por la red al servidor.
 *
 * Usa el algoritmo RSA, el modo ECB y el padding PKCS1Padding.
 *
 * @author Nerea Aranguren
 */
public class CifradoAsimetrico {

    /**
     * Atributo estático y constante que guarda los loggers de la clase.
     */
    private static final Logger LOGGER = Logger.getLogger("seguridad.CifradoAsimetrico");

    /**
     * Ruta absoluta del proyecto.
     */
    private static final String filePath = new File("").getAbsolutePath();

    /**
     * Atributo que lee la ruta de la clave del archivo de propiedades.
     */
    private static final ResourceBundle RB = ResourceBundle.getBundle("archivos.Paths");

    /**
     * Metodo que cifra la contraseña del usuario con una clave publica.
     *
     *
     * @param contraseña La contraseña del usuario.
     * @return Un string con la contraseña cifrada en hexadecimal.
     */
    public String cifrarConClavePublica(String contraseña) {
        byte[] encodedMessage = null;

        try {
            LOGGER.info("CifradoAsimetrico: Cifrando con clave publica");

            byte fileKey[] = fileReader(filePath + RB.getString("ASIMETRIC_KEY_PUBLIC"));

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(fileKey);
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            encodedMessage = cipher.doFinal(contraseña.getBytes());
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            LOGGER.severe(e.getMessage());
        }
        return byteToHexadecimal(encodedMessage);
    }

    /**
     * Metodo que convierte un array de bytes en un string hexadecimal.
     *
     * @param bytes Array de bytes que llega.
     * @return Un String con valor hexadecimal.
     */
    private String byteToHexadecimal(byte[] bytes) {
        LOGGER.info("CifradoAsimetrico: Convirtiendo bytes a hexadecimal");

        String HEX = "";
        for (int i = 0; i < bytes.length; i++) {
            String h = Integer.toHexString(bytes[i] & 0xFF);
            if (h.length() == 1) {
                HEX += "0";
            }
            HEX += h;
        }
        return HEX.toUpperCase();
    }

    /**
     * Metodo que devuelve el contenido de un fichero.
     *
     * @param path Path del fichero.
     * @return El texto del fichero.
     */
    private byte[] fileReader(String path) {
        byte ret[] = null;
        File file = new File(path);
        try {
            LOGGER.info("CifradoAsimetrico: Leyendo archivo");

            ret = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            LOGGER.severe(e.getMessage());
        }
        return ret;
    }
}