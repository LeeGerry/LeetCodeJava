package company.LinkedIn.phoneinterview;


import java.util.*;

/**
 * Created by Gary on 4/5/18.
 */
public class RetainBestCache<K, T extends Rankable>{
    private Map<K, T> cache;
    private TreeMap<Long, Set<K>> rankingSet;
    private DataSource<K, T> dataSource;
    private int size;
    // 构造中两个参数，一个ds, 一个缓存size
    public RetainBestCache(DataSource<K, T>ds, int entriesToRetain) {
        cache = new HashMap<>();
        rankingSet = new TreeMap<>();
        dataSource = ds;
        size = entriesToRetain;
    }

    /* Gets some data.
        If possible, retrieves it from cache to be fast.
        If the data is not cached, retrieves it from the data source.
        If the cache is full, attempt to cache the returned data,
            evicting the T with lowest rank among the ones that it has available
        If there is a tie, the cache may choose any T with lowest rank to evict.
     */
    public T get(K key){
        if (cache.containsKey(key)) //如果缓存中有，则直接返回
            return cache.get(key);
        return findDataFromDataSource(key); // 没有，则从DS中查找
    }

    /**从DS中查找*/
    private T findDataFromDataSource(K key){
        // 因为从cache中没有找到，所以一定要把从ds中找到的数据放入到cache中
        // 此时，如果缓存已经满了，那么就要让级别低的出去一个
        if (cache.size() >= size) evictElement();
        // 从DS中找到
        T t = dataSource.get(key);
        // 放到缓存中
        cache.put(key, t);
        // rank级别
        long rank = t.getRank();
        // 如果在rankingSet中没有当前级别key，则放入新的HashSet用来保存
        if (!rankingSet.containsKey(rank)) {
            rankingSet.put(rank, new HashSet<>());
        }
        // 在该级别set中加入该key
        rankingSet.get(rank).add(key);
        return t; // 返回ds中找到的数据
    }

    /** 从缓存中删除一个低rank的数据*/
    private void evictElement(){
        // 先从TreeSet 中 拿到最小的那个级别的entry
        Map.Entry<Long, Set<K>> entry = rankingSet.firstEntry();
        // 拿到这个级别的其中一个key值，这里取第一个
        K key = entry.getValue().iterator().next();
        // 从entry的set中删除该key值
        entry.getValue().remove(key);
        // 从cache中删除该key值对应的数据 （为了放新数据）
        cache.remove(key);
        // 如果entry中的set中已经空了，就从rankingSet中删除
        if (entry.getValue().size() == 0){
            rankingSet.remove(entry.getKey());
        }
    }
}

interface Rankable {
    // 返回该对象的rank
    long getRank();
}
interface DataSource<K, T extends Rankable> {
    T get(K key);
}
