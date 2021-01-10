package xb.data_construct.collection;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 *  List Set Map有哪些实现类？是否线程安全？他们是怎么做到增删查的？
 */
@Slf4j
public class CollectionSome {
    /**List
     * 有序，可重复
     * ArrayList，Vector，LinkedList
     * ArrayList:底层数据结构是数组，查询快，增删慢。
     *           底层是数组，增删元素需要重新生成数组，查找时间复杂度O(1)
     * LinkedList:底层数据结构是链表，查询慢，增删快
     *            增删的时间复杂度是O(1),查找的时间复杂度是O(N)
     */
    List list = new ArrayList();


    /**Set
     * 无序不重复
     * HashSet，LinkedHashSet，TreeSet
     * HashSet:底层数据结构是哈希表.通过插入元素的hashcode不同来确保不重复
     * LinkedHashSet:底层数据结构是链表和哈希表。链表保证元素有序，哈希表保证元素唯一
     * TreeSet：底层数据结构是红黑树。(唯一，有序)
     */
    //set去重：
    public static void duplicateRemove(){
        Set<String> names = new HashSet<>();
        names.add("Tom");
        names.add("Mary");
        if (names.add("Peter")) {
            log.info("Peter is added to the set");
        }
        if (!names.add("Tom")) {
            log.info("Tom is already added to the set");
        }
    }
    //set找交集
    public static void intersection(){
        Set<Integer> s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 7, 9));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(2, 4, 6, 8));
        log.info("s1 before intersection: " + s1);
        s1.retainAll(s2);
        log.info("s1 after intersection: " + s1);
    }
    //set找差集
    public static void diffrentSet(){
        Set<Integer> s1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5, 7, 9));
        Set<Integer> s2 = new HashSet<>(Arrays.asList(2, 4, 6, 8));
        log.info("s1 before difference: " + s1);
        s1.removeAll(s2);
        log.info("s1 after difference: " + s1);
    }

    /**Map
     * Hashtable，LinkedHashMap，HashMap，TreeMap
     * TreeMap:有序
     * HashMap:无序
     */
    public static void linkedHashMapTest(){
        Map<String,String> map = new LinkedHashMap<>();
    }

    public static int testList(){
        List<String> list = new ArrayList();
        list.add("1");

        return 0;
    }

    public static void main(String[] args) {
        testList();
    }

}
