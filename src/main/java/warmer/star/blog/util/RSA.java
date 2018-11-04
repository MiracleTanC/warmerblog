package warmer.star.blog.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class RSA {  
	 private static final String DEFAULT_PUBLIC_KEY= 
	"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDB45NNFhRGWzMFPn9I7k7IexS5"+
	"XviJR3E9Je7L/350x5d9AtwdlFH3ndXRwQwprLaptNb7fQoCebZxnhdyVl8Jr2J3"+
	"FZGSIa75GJnK4IwNaG10iyCjYDviMYymvCtZcGWSqSGdC/Bcn2UCOiHSMwgHJSrg"+
	"Bm1Zzu+l8nSOqAurgQIDAQAB";
	 	 
    /**  
     * 公钥  
     */    
    private static RSAPublicKey publicKey;    
        
    static{
    	//rsaEncrypt.genKeyPair();    
        //加载公钥    
        try {    
        	//解决BC问题
            loadPublicKey(RSA.DEFAULT_PUBLIC_KEY);    
            System.out.println("加载公钥成功");    
        } catch (Exception e) {    
            System.err.println(e.getMessage());    
            System.err.println("加载公钥失败");    
        }    
    }
    /**  
     * 字节数据转字符串专用集合  
     */    
    private static final char[] HEX_CHAR= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};    
        
    /**  
     * 获取公钥  
     * @return 当前的公钥对象  
     */    
    private static RSAPublicKey getPublicKey() {    
        return publicKey;    
    }    
    
    /**  
     * 随机生成密钥对  
     */    
	private static void genKeyPair(){    
        KeyPairGenerator keyPairGen= null;    
        try {    
            keyPairGen= KeyPairGenerator.getInstance("RSA");    
        } catch (NoSuchAlgorithmException e) {    
            e.printStackTrace();    
        }    
        keyPairGen.initialize(1024, new SecureRandom());    
        KeyPair keyPair= keyPairGen.generateKeyPair();    
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();    
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        
        byte[] privateBytes = privateKey.getEncoded();
        Base64 encoder = new Base64();
        String privateStr = encoder.encode(privateBytes);
        byte[] publicBytes= publicKey.getEncoded();
        String publicStr = encoder.encode(publicBytes);
        System.out.println("privateKey:"+privateStr);
        System.out.println("publicKey:"+publicStr);
    }    
    
    /**  
     * 从文件中输入流中加载公钥  
     * @param in 公钥输入流  
     * @throws Exception 加载公钥时产生的异常  
     */    
    @SuppressWarnings("unused")
	private  static void  loadPublicKey(InputStream in) throws Exception{    
        try {    
            BufferedReader br= new BufferedReader(new InputStreamReader(in));    
            String readLine= null;    
            StringBuilder sb= new StringBuilder();    
            while((readLine= br.readLine())!=null){    
                if(readLine.charAt(0)=='-'){    
                    continue;    
                }else{    
                    sb.append(readLine);    
                    sb.append('\r');    
                }    
            }    
            loadPublicKey(sb.toString());    
        } catch (IOException e) {    
            throw new Exception("公钥数据流读取错误");    
        } catch (NullPointerException e) {    
            throw new Exception("公钥输入流为空");    
        }    
    }    
    
    
    /**  
     * 从字符串中加载公钥  
     * @param publicKeyStr 公钥数据字符串  
     * @throws Exception 加载公钥时产生的异常  
     */    
    private static void loadPublicKey(String publicKeyStr) throws Exception{    
        try {    
        	Base64 base64Decoder= new Base64();    
            byte[] buffer= base64Decoder.decode(publicKeyStr);  
            KeyFactory keyFactory= KeyFactory.getInstance("RSA");    
            X509EncodedKeySpec keySpec= new X509EncodedKeySpec(buffer);    
            publicKey= (RSAPublicKey) keyFactory.generatePublic(keySpec);    
        } catch (NoSuchAlgorithmException e) {    
            throw new Exception("无此算法");    
        } catch (InvalidKeySpecException e) {    
            throw new Exception("公钥非法");    
        } catch (NullPointerException e) {    
            throw new Exception("公钥数据为空");    
        }    
    }    
        
    /**  
     * 公钥加密过程  
     * @param publicKey 公钥  
     * @param plainTextData 明文数据  
     * @return  
     * @throws Exception 加密过程中的异常信息  
     */    
    private static byte[] encrypt(RSAPublicKey publicKey, byte[] plainTextData) throws Exception{    
        if(publicKey== null){    
            throw new Exception("加密公钥为空, 请设置");    
        }    
        Cipher cipher= null;    
        try {    
            cipher= Cipher.getInstance("RSA");//, new BouncyCastleProvider());    
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);    
            byte[] output= cipher.doFinal(plainTextData);    
            return output;    
        } catch (NoSuchAlgorithmException e) {    
            throw new Exception("无此加密算法");    
        } catch (NoSuchPaddingException e) {    
            e.printStackTrace();    
            return null;    
        }catch (InvalidKeyException e) {    
            throw new Exception("加密公钥非法,请检查");    
        } catch (IllegalBlockSizeException e) {    
            throw new Exception("明文长度非法");    
        } catch (BadPaddingException e) {    
            throw new Exception("明文数据已损坏");    
        }    
    }    
    /**  
     * 字节数据转十六进制字符串  
     * @param data 输入数据  
     * @return 十六进制内容  
     */    
    public static String byteArrayToString(byte[] data){    
        StringBuilder stringBuilder= new StringBuilder();    
        for (int i=0; i<data.length; i++){    
            //取出字节的高四位 作为索引得到相应的十六进制标识符 注意无符号右移    
            stringBuilder.append(HEX_CHAR[(data[i] & 0xf0)>>> 4]);    
            //取出字节的低四位 作为索引得到相应的十六进制标识符    
            stringBuilder.append(HEX_CHAR[(data[i] & 0x0f)]);    
            if (i<data.length-1){    
                stringBuilder.append(' ');    
            }    
        }    
        return stringBuilder.toString();    
    }    
    /**
     * encrypt String by Ras(add)  by publicKey
     * @return success:return encrypt Base64 String  ;  fail:"fail"
     * @throws Exception 
     */
    public static String encryptByPublicKey(String plaintext) throws Exception{
         try {
        	 Base64 encoder = new Base64();
        	 //encrypt process
        	 byte[] cipher = encrypt(getPublicKey(), plaintext.getBytes("utf-8"));    
        	 //byte[] to String by Base64 
			 return encoder.encode(cipher);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}  
    }
        /*public static void main(String[] args){    
    	//测试字符串    
        String encryptStr= "fd2cf5e6589a7ceccbc1cc57f6b299a4&&a43777b86615e6e3903b60c9c21f275f&&1512009697";    
        try {    
            System.out.println("明文："+encryptStr);
            String cipherStr = encryptByPublicKey(encryptStr);
            System.out.println("公钥加密密文："+cipherStr);
        } catch (Exception e) {    
            System.err.println(e.getMessage());    
        }   
    }  */
}  
