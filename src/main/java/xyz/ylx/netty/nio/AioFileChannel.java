package xyz.ylx.netty.nio;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Slf4j
public class AioFileChannel {

    public static void main(String[] args) throws IOException {
        try (
                AsynchronousFileChannel s = AsynchronousFileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);
        ) {
            ByteBuffer buffer = ByteBuffer.allocate(2);
            log.debug("begin...");
            s.read(buffer, 0, null, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    log.debug("read completed...{}", result);
                    buffer.flip();
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    log.debug("read failed...");
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        log.debug("do other things...");
        System.in.read();
    }
}
