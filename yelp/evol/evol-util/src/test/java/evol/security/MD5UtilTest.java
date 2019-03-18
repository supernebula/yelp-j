package evol.security;

import org.junit.Assert;
import org.junit.Test;

public class MD5UtilTest {

    @Test
    public void MD5Test() {
        String sign = MD5Util.MD5("1234567890ABCDEFG_0123456789");
        System.out.println("sign:");
        System.out.println(sign);
        //Assert.assertEquals(sign, "f6a66a0908db4f9650bc873fc4aebe62");
    }

    @Test
    public void MD5BySaltTest() {
        String sign = MD5Util.MD5("1234567890ABCDEFG_0123456789", "SALT0123");
        System.out.println("sign:");
        System.out.println(sign);
        //Assert.assertEquals(sign, "56a008a5d79885498730b8035343652a");
    }
}