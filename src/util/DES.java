package util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.Cipher;

/**
 * DES加密介绍
 * DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。DES加密算法出自IBM的研究，
 * 后来被美国政府正式采用，之后开始广泛流传，但是近些年使用越来越少，因为DES使用56位密钥，以现代计算能力，
 * 24小时内即可被破解。虽然如此，在某些简单应用中，我们还是可以使用DES加密算法，本文简单讲解DES的JAVA实现
 * 。
 * 注意：DES加密和解密过程中，密钥长度都必须是8的倍数
 */
public class DES {
    public DES() {
    }

    private static final String PASSWORD = "98790kF6FhqU84M0K16TJ76N";

    //测试
    public static void main(String args[]) {
        //待加密内容
        String str = "123456";
        //密码，长度要是8的倍数

        byte[] result = DES.encrypt(str.getBytes());
        String rr = new BASE64Encoder().encode(result);
        System.out.println("加密后：" + rr);
        //直接将如上内容解密
        try {
            byte[] decryResult = DES.decrypt(new BASE64Decoder().decodeBuffer(rr));
            System.out.println("解密后：" + new String(decryResult));
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 加密
     *
     * @param datasource byte[]
     * @return byte[]
     */
    public static byte[] encrypt(byte[] datasource) {
        try {
            SecureRandom random = new SecureRandom();
            DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
            //创建一个密匙工厂，然后用它把DESKeySpec转换成
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = keyFactory.generateSecret(desKey);
            //Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            //用密匙初始化Cipher对象
            cipher.init(Cipher.ENCRYPT_MODE, securekey, random);
            //现在，获取数据并加密
            //正式执行加密操作
            return cipher.doFinal(datasource);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     *
     * @param src      byte[]
     * @return byte[]
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom random = new SecureRandom();
        // 创建一个DESKeySpec对象
        DESKeySpec desKey = new DESKeySpec(PASSWORD.getBytes());
        // 创建一个密匙工厂
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        // 将DESKeySpec对象转换成SecretKey对象
        SecretKey securekey = keyFactory.generateSecret(desKey);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, random);
        // 真正开始解密操作
        return cipher.doFinal(src);
    }
}