package jp.truetech.testsupport.util;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgumentsTest {

    @Test
    public void コンストラクタでの初期化_引数なし() {
        Arguments arguments = new Arguments();
        assertThat(arguments, is(notNullValue()));
        String[] args = {};
        arguments = new Arguments(args);
        assertThat(arguments, is(notNullValue()));
    }

    @Test
    public void コンストラクタでの初期化() {
        String[] args = { "arg0=value0", "arg1=value1" };
        Arguments arguments = new Arguments(args);
        assertThat(arguments, is(notNullValue()));
        assertThat(arguments.contains("arg0"), is(true));
        assertThat(arguments.contains("arg1"), is(true));
        assertThat(arguments.contains("arg2"), is(false));
        assertThat(arguments.get("arg0"), is("value0"));
        assertThat(arguments.get("arg1"), is("value1"));
        assertThat(arguments.get("arg3"), is(nullValue()));
    }

    @Test
    public void 追加() {
        Arguments arguments = new Arguments();
        arguments.put("arg0", "value0");
        assertThat(arguments.get("arg0"), is("value0"));
    }

    @Test
    public void 値にデリミタを含む() {
        String[] args = { "arg0=valueA=A" };
        Arguments arguments = new Arguments(args);
        assertThat(arguments, is(notNullValue()));
        assertThat(arguments.contains("arg0"), is(true));
        assertThat(arguments.get("arg0"), is("valueA=A"));
    }
}
