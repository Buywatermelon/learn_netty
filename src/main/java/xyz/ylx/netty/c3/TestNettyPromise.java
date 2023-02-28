package xyz.ylx.netty.c3;

import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public class TestNettyPromise {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 1.准备EventLoop对象
        EventLoop eventLoop = new NioEventLoopGroup().next();
        // 2.可以主动创建promise
        Promise<Integer> promise = new DefaultPromise<>(eventLoop);

        eventLoop.submit(() -> {
            log.debug("开始计算...");
            try {
                // int i = 1 / 0;
                Thread.sleep(1000L);
                promise.setSuccess(80);
            } catch (Exception e) {
                e.printStackTrace();
                promise.setFailure(e);
            }
        });

        log.debug("等待结果...");
        log.debug("结果是 {}", promise.get());
    }
}
