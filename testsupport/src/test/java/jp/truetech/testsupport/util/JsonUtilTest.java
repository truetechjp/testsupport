package jp.truetech.testsupport.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class JsonUtilTest {
    
    @Test
    public void test() {
        String json = 
          "{\n" + 
          "    \"firstname\": \"SORANO\",\n" + 
          "    \"lastname\": \"TAROU\",\n" + 
          "    \"birthday\": \"1963/09/30\",\n" + 
          "    \"transactions\": [\n" + 
          "        {\n" + 
          "            \"xxx\": 123\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"xxx\": 456\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"xxx\": 789\n" + 
          "        },\n" + 
          "        {\n" + 
          "            \"refund\": [\n" + 
          "                {\n" + 
          "                    \"xxx\": 456\n" + 
          "                },\n" + 
          "                {\n" + 
          "                    \"xxx\": 789\n" + 
          "                }\n" + 
          "            ]\n" + 
          "        }\n" + 
          "    ],\n" + 
          "    \"lastupdate\":\"2019/10/05\",\n" + 
          "    \"history\":[\n" + 
          "        {\"xxx\":123},\n" + 
          "        {\"xxx\":456},\n" + 
          "        {\"xxx\":789},\n" + 
          "        {\"refund\":[\n" + 
          "            {\"xxx\":456},\n" + 
          "            {\"xxx\":789}\n" + 
          "        ]}\n" + 
          "    ],   \n" + 
          "    \"create\":\"2019/10/05\"\n" + 
          "}";
        String expected =
          "{\n" + 
          "  \"firstname\":\"SORANO\",\n" + 
          "  \"lastname\":\"TAROU\",\n" + 
          "  \"birthday\":\"1963/09/30\",\n" + 
          "  \"transactions\":[\n" + 
          "    {\n" + 
          "      \"xxx\":123\n" + 
          "    },\n" + 
          "    {\n" + 
          "      \"xxx\":456\n" + 
          "    },\n" + 
          "    {\n" + 
          "      \"xxx\":789\n" + 
          "    },\n" + 
          "    {\n" + 
          "      \"refund\":[\n" + 
          "        {\n" + 
          "          \"xxx\":456\n" + 
          "        },\n" + 
          "        {\n" + 
          "          \"xxx\":789\n" + 
          "        }\n" + 
          "      ]\n" + 
          "    }\n" + 
          "  ],\n" + 
          "  \"lastupdate\":\"2019/10/05\",\n" + 
          "  \"history\":[\n" + 
          "    {\n" + 
          "      \"xxx\":123\n" + 
          "    },\n" + 
          "    {\n" + 
          "      \"xxx\":456\n" + 
          "    },\n" + 
          "    {\n" + 
          "      \"xxx\":789\n" + 
          "    },\n" + 
          "    {\n" + 
          "      \"refund\":[\n" + 
          "        {\n" + 
          "          \"xxx\":456\n" + 
          "        },\n" + 
          "        {\n" + 
          "          \"xxx\":789\n" + 
          "        }\n" + 
          "      ]\n" + 
          "    }\n" + 
          "  ],\n" + 
          "  \"create\":\"2019/10/05\"\n" + 
          "}";
        System.out.println(expected);
        String actual = JsonUtil.format(json);
        System.out.println(actual);
        assertThat(actual, is(expected));
    }
}
