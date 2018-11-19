/**
 * https://blog.csdn.net/t1dmzks/article/details/78440717
 */
package com.gavin.zkclient;

import java.util.List;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;

public class ZkClientBase {

    /** zookeeper地址 ,可以使用逗号隔开*/
    private static final String CONNECT_ADDR = "192.168.1.129:2181";
    
    /** session超时时间 */
    private static final int SESSION_OUTTIME = 10000;//ms
    
    public static void main(String[] args) throws Exception {
        ZkClient zkc = new ZkClient(new ZkConnection(CONNECT_ADDR), SESSION_OUTTIME);
        //1. create and delete方法
        
        zkc.createEphemeral("/temp");
        zkc.createPersistent("/super/c1", true);
        Thread.sleep(10000);
        zkc.delete("/temp");
        zkc.deleteRecursive("/super");
        
        //2. 设置path和data 并且读取子节点和每个节点的内容
        zkc.createPersistent("/super", "1234");
        zkc.createPersistent("/super/c1", "c1内容");
        zkc.createPersistent("/super/c2", "c2内容");
        List<String> list = zkc.getChildren("/super");
        for(String p : list){
            System.out.println(p);
            String rp = "/super/" + p;
            String data = zkc.readData(rp);
            System.out.println("节点为：" + rp + "，内容为: " + data);
        }
    }
}
