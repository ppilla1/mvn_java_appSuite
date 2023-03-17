package io.explore;


import io.explore.dp.Fibonacci;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class AppTest
{
    @Test
    public void shouldAnswerWithTrue() {
        // Fib(1) = 1, Fib(2) = 1, Fib(3) = 2, Fib(4) = 3, Fib(5) = 5
        assertEquals(1, Fibonacci.fib(1, new HashMap<>()));
        assertEquals(1, Fibonacci.fib(2, new HashMap<>()));
        assertEquals(2, Fibonacci.fib(3, new HashMap<>()));
        assertEquals(3, Fibonacci.fib(4, new HashMap<>()));
        assertEquals(5, Fibonacci.fib(5, new HashMap<>()));
    }

    @Test
    public void fetchServerId() throws SocketException {
        Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();

        while(networks.hasMoreElements()) {
            NetworkInterface network = networks.nextElement();
            byte[] hardwareAddress = network.getHardwareAddress();

            if (null != hardwareAddress) {
                for(byte b : hardwareAddress) {
                    log.info(">> {}, {}", b, b & 255);
                }
                log.info("> {}, {}", null != hardwareAddress ? hardwareAddress.length:0, hardwareAddress);
            }
        }
    }

    private Integer add_double_to_integer_list(List<? extends Number> numbers) {

        Arrays.<Number>asList(1, 2, 3.4);
        List<Number> nums = new ArrayList<>();
        nums.add(2);
        nums.add(3.12);

        numbers.get(0);
        return (Integer) numbers.get(0);
    }

    @Disabled
    @Test
    public void test_covariance_in_java(){
        Number num1_2= new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 1.2f;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        };
        Number num_2_1= new Number() {
            @Override
            public int intValue() {
                return 0;
            }

            @Override
            public long longValue() {
                return 0;
            }

            @Override
            public float floatValue() {
                return 2.1f;
            }

            @Override
            public double doubleValue() {
                return 0;
            }
        };
        List<Number> floats = Arrays.asList(num1_2, num_2_1);
        add_double_to_integer_list(floats);
    }
}
