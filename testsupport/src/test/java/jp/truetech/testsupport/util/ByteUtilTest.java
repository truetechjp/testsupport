package jp.truetech.testsupport.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ByteUtilTest {

    @Test
    public void test0() throws Exception {
        byte[] bytes = { 0 };
        String expected = "00";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test1() throws Exception {
        byte[] bytes = { 1 };
        String expected = "01";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test00() throws Exception {
        byte[] bytes = { 0, 0 };
        String expected = "0000";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test15() throws Exception {
        byte[] bytes = { 15 };
        String expected = "0f";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test16() throws Exception {
        byte[] bytes = { 16 };
        String expected = "10";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test255() throws Exception {
        byte[] bytes = { (byte) 255 };
        String expected = "ff";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test127128() throws Exception {
        byte[] bytes = { 127, (byte) 128, (byte) 129 };
        String expected = "7f8081";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

    @Test
    public void test151617() throws Exception {
        byte[] bytes = { 15, 16, 17 };
        String expected = "0f1011";
        String actual = ByteUtil.toHexString(bytes);
        assertThat(actual, is(expected));
    }

}
