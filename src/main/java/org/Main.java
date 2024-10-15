package org;
public class Main {
    public static void main(String[] args) throws Exception {
        Producer producer = new Producer();
        producer.sendMessage("John Doe");

//         Start the consumer in a new thread
//        new Thread(() -> {
//            try {
//                Consumer.main(null);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}