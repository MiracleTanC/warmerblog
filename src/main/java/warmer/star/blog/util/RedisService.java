
package warmer.star.blog.util;

public interface RedisService {
	/**
     * set存数据
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, String value);

    /**
     * get获取数据
     * @param key
     * @return
     */
    String get(String key);
    
    /**
     * get获取数据
     * @param key
     * @return obj
     */
    <T> T getObj(String key, T t);

    /**
     * 设置有效天数
     * @param key
     * @param expire
     * @return
     */
    boolean expire(String key, long expire);
    /**
     * 键值是否存在
     * @param key
     * @return
     */
    boolean isExists(String key);

    /**
     * 移除数据
     * @param key
     * @return
     */
    boolean remove(String key);
}
