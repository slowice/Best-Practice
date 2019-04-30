package xb.common_utils.commonUtils;

import com.google.common.collect.Interner;
import com.google.common.collect.Interners;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
public class MyTest {
    public static void main(String[] args) {
        System.out.println(Arrays.equals("hehe".getBytes(StandardCharsets.UTF_8),new String("hehe").getBytes(StandardCharsets.UTF_8)));
        //Assert.isTrue(,"equals");

        System.out.println();
        Interner interner = Interners.newWeakInterner();
    }
}
