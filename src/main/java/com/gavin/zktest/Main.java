package com.gavin.zktest;

public class Main {
    
    public static void main(String[] args) {
        try {
            ZKManager zkManager = new ZKManagerImpl();
            //zkManager.create("/HelloWorld", "12312312".getBytes());
            
            Object zNodeData = zkManager.getZNodeData("/HelloWorld", true);
            System.out.println(zNodeData.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
