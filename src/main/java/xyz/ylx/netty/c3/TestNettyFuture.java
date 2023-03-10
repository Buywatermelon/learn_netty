package xyz.ylx.netty.c3;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TestNettyFuture {
    public static void main(String[] args) throws Exception {
        NioEventLoopGroup group = new NioEventLoopGroup();
        EventLoop eventLoop = group.next();
        Future<Integer> future = eventLoop.submit(() -> {
            log.debug("执行计算");
            Thread.sleep(1000L);
            return 70;
        });
        // 同步接收结果
        log.debug("等待结果");
        log.debug("结果是 {}", future.get());

        // 异步接受结果
        future.addListener(future1 -> log.debug("接收结果：{}", future1.getNow()));
    }
}
