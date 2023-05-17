package io.explore.bootstrap;

import io.aeron.Aeron;
import io.aeron.Publication;
import io.aeron.Subscription;
import io.aeron.driver.MediaDriver;
import io.aeron.logbuffer.FragmentHandler;
import lombok.extern.log4j.Log4j2;
import org.agrona.concurrent.IdleStrategy;
import org.agrona.concurrent.SleepingIdleStrategy;
import org.agrona.concurrent.UnsafeBuffer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.ByteBuffer;

@Log4j2
@Configuration
public class AeronConfig {

    @Bean
    public CommandLineRunner aeronPubSubConfig(){
        return args -> {
            final String channel = "aeron:ipc";
            final String message = "my message";
            final IdleStrategy idle = new SleepingIdleStrategy();
            final UnsafeBuffer unsafeBuffer = new UnsafeBuffer(ByteBuffer.allocate(256));
            log.info("Starting Aeron infrastructure....");
            try (MediaDriver driver = MediaDriver.launch();
                 Aeron aeron = Aeron.connect();
                 Subscription sub = aeron.addSubscription(channel, 10);
                 Publication pub = aeron.addPublication(channel, 10))
            {
                while (!pub.isConnected())
                {
                    idle.idle();
                }
                unsafeBuffer.putStringAscii(0, message);
                log.info("sending: {}" , () -> message);
                while (pub.offer(unsafeBuffer) < 0)
                {
                    idle.idle();
                }
                FragmentHandler handler = (buffer, offset, length, header) ->
                        log.info("received: {}", () -> buffer.getStringAscii(offset));
                while (sub.poll(handler, 1) <= 0)
                {
                    idle.idle();
                }
            }
        };
    }
}
