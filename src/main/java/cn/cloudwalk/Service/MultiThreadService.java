package cn.cloudwalk.Service;

import com.alicp.jetcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class MultiThreadService {

    private int ticketNum = 10;

    @Autowired(required = false)
    private Cache cache;

    /**
     * 模拟并发场景
     */
    public void playOrder() {
        cache.tryLockAndRun("lock", 60, TimeUnit.SECONDS, new Runnable() {
            @Override
            public void run() {
                if (ticketNum > 0) {

                    System.out.println("购票成功，当前剩余" + ticketNum + "张余票");
                    ticketNum--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("余票不足");
                }
            }
        });
    }


}
