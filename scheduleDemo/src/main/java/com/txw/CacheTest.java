package com.txw;

import com.txw.domain.Dog;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Created by txw on 2018/3/14.
 */
public class CacheTest {

    public static void main(String[] args) {
        // 1. 创建缓存管理器
        CacheManager cacheManager = CacheManager.create("./src/main/resources/cache/ehcache.xml");

        // 2. 获取缓存对象
        Cache cache = cacheManager.getCache("HelloWorldCache");

        // 3. 创建元素
        Element element = new Element("key1", "value1");
        cache.put(element);
        Element value = cache.get("key1");
        System.out.println(value);
        System.out.println(value.getObjectValue());

        cache.remove("key1");
        Dog dog = new Dog(1L, "tai di");
        Element element2 = new Element("tai di", dog);
        cache.put(element2);
        Element value2 = cache.get("tai di");
        Dog dog2 = (Dog) value2.getObjectValue();
        System.out.println(dog2);
        System.out.println(cache.getSize());

        // 7. 刷新缓存
        cache.flush();

        // 8. 关闭缓存管理器
        cacheManager.shutdown();
    }
}
