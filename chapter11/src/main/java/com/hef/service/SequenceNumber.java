package com.hef.service;

/**
 * @author lifei
 * @since 2020/7/21
 */
public class SequenceNumber {

    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>(){
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

    public int getNextNum(){
        seqNum.set(seqNum.get()+1);
        return seqNum.get();
    }

    public static void main(String[] args) {
        SequenceNumber sn = new SequenceNumber();
        new TestClient(sn).start();
        new TestClient(sn).start();
        new TestClient(sn).start();
    }

    private static class TestClient extends Thread{
        private SequenceNumber sn;
        public TestClient(SequenceNumber sn){
            this.sn = sn;
        }
        @Override
        public void run() {
            for (int i = 0; i < 3; i++) {
                System.out.println("thread[ " + Thread.currentThread().getName() + "] sn[" + sn.getNextNum() + "]");
            }
        }
    }
}
